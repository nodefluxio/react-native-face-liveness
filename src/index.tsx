import { NativeModules } from 'react-native';

type FaceLivenessType = {
  faceLiveness(
    accessKey: string,
    secretKey: string,
    onSuccess: CallableFunction,
    onError: CallableFunction
  ): any;
};

const { FaceLiveness } = NativeModules;

export default FaceLiveness as FaceLivenessType;
