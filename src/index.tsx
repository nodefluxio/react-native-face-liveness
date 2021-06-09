import { NativeModules } from 'react-native';

type FaceLivenessType = {
  multiply(a: number, b: number): Promise<number>;
};

const { FaceLiveness } = NativeModules;

export default FaceLiveness as FaceLivenessType;
