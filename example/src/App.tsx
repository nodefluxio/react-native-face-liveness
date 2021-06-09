import * as React from 'react';

import { StyleSheet, View, Text, Button, PermissionsAndroid } from 'react-native';
import FaceLiveness from 'react-native-face-liveness';

export default function App() {
  const [result, setResult] = React.useState<number | undefined>();

  const requestCameraPermission = async () => {
    try {
      const granted = await PermissionsAndroid.request(
        PermissionsAndroid.PERMISSIONS.CAMERA,
        {
          title: "Cool Photo App Camera Permission",
          message:
            "Cool Photo App needs access to your camera " +
            "so you can take awesome pictures.",
          buttonNeutral: "Ask Me Later",
          buttonNegative: "Cancel",
          buttonPositive: "OK"
        }
      );
      if (granted === PermissionsAndroid.RESULTS.GRANTED) {
        console.log("You can use the camera");
      } else {
        console.log("Camera permission denied");
      }
    } catch (err) {
      console.warn(err);
    }
  };

  const onPressLiveness = () => {
    FaceLiveness.faceLiveness(
      'ACCESS_KEY',
      'SECRET_KEY',
      (isLive, image, score) => {
        console.log(isLive, image, score);
      },
      (errorMessage) => {
        console.log(errorMessage);
      }
    );
  }

  return (
    <View style={styles.container}>
      <Text>Result: {result}</Text>
      <Button title="request permissions" onPress={requestCameraPermission} />
      <Button title="Face Liveness" onPress={onPressLiveness} />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
});
