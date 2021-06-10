# react-native-face-liveness

Nodeflux react native face liveness plugin

## Platform

- Android SDK 21 - 30

## Installation

```sh
npm install @nodeflux/react-native-face-liveness
```

## Usage

```js
import FaceLiveness from "@nodeflux/react-native-face-liveness";

// ...

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

// ...
```

Replace `ACCESS_KEY` and `SECRET_KEY` with your keys. You can get your key at here [here](https://cloud.nodeflux.io)

For more information visit [here](https://www.nodeflux.io/) or you can contact us at [here](https://www.nodeflux.io/Contact-Us)

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT
