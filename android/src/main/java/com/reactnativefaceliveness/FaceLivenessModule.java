package com.reactnativefaceliveness;

import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Base64;
import android.util.Log;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;

import java.io.ByteArrayOutputStream;

import nodeflux.sdk.liveness.Liveness;

@ReactModule(name = FaceLivenessModule.NAME)
public class FaceLivenessModule extends ReactContextBaseJavaModule {
  public static final String NAME = "FaceLiveness";

  public FaceLivenessModule(ReactApplicationContext reactContext) {
    super(reactContext);
  }

  @Override
  @NonNull
  public String getName() {
    return NAME;
  }

  @ReactMethod
  void faceLiveness(String accessKey, String secretKey, Callback onSuccessCallback, Callback onErrorCallback) {
    ReactApplicationContext context = getReactApplicationContext();
    Intent intent = new Intent(context, Liveness.class);
    intent.putExtra("ACCESS_KEY", accessKey);
    intent.putExtra("SECRET_KEY", secretKey);
    intent.putExtra("THRESHOLD", 0.7);
    Liveness.setUpListener(new Liveness.LivenessCallback() {
      @Override
      public void onSuccess(boolean isLive, Bitmap bitmap, double score) {
        onSuccessCallback.invoke(isLive, BitMapToString(bitmap), score);
      }

      @Override
      public void onError(String message) {
        onErrorCallback.invoke(message);
      }
    });
    getCurrentActivity().startActivity(intent);
  }

  private String BitMapToString(Bitmap bitmap) {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
    byte[] b = baos.toByteArray();
    String temp = null;
    try {
      System.gc();
      temp = Base64.encodeToString(b, Base64.DEFAULT);
    } catch (Exception e) {
      e.printStackTrace();

    } catch (OutOfMemoryError e) {
      baos = new ByteArrayOutputStream();
      bitmap.compress(Bitmap.CompressFormat.JPEG, 30, baos);
      b = baos.toByteArray();
      temp = Base64.encodeToString(b, Base64.DEFAULT);
      Log.e("EWN", "Out of memory error catched");
    }
    return temp;
  }
}
