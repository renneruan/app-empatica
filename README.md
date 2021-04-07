# Informações

- Projeto feito em base ao modelo de Android fornecido pela Empatica para desenvolvedores.
- Fiz alguns testes e ainda não organizei os arquivos, mas teste UDP, TCP e duas versões de banco de dados. O maior problema era a rede da UnB, o que foi contornado usando o 3G do celular para rotear.
- As classe sendo usadas são: MainActivity, CallAPIGet e CallAPI.
- Foi feito um servidor de banco de dados simple com o WAMP (http://www.wampserver.com/). Os códigos específicos criados para a comunicação estão presente no diretório wamp64.


# Links

ANDROID EXAMPLE: https://github.com/empatica/empalink-sample-project-android
IOS EXAMPLE: https://github.com/empatica/e4link-sample-project-ios
IOS API REFERENCE: https://developer.empatica.com/ios-sdk-api-reference-100.html

ANÁLISE DOS DADOS CAPTADOS:
- https://github.com/ddessy/RealTimeArousalDetectionUsingGSR
- http://www.ledalab.de/
- https://www.mathworks.com/matlabcentral/fileexchange/9223-simpleeda-emg
- https://github.com/johnksander/BreatheEasyEDA

# EmpaLink Sample Project

## Introduction

This sample project gives you the boilerplate code you need to connect to an Empatica E4 device and start streaming data.

The sample application implemented in the project has very simple functionalities:

- It initializes the EmpaLink library with your API key.
- If the previous step is successful, it starts scanning for Empatica devices, till it finds one that can be used with the API key you inserted in the code.
- When such a device has been found, the app connects to the devices and streams data for 10 seconds, then it disconnects.

## Setup

- Clone / download this repository.
- Open the sample project in Android Studio.
- Make sure you have a valid API key. You can request one for your Empatica Connect account from our [Developer Area][1].
- Edit `MainActivity.java` and assign your API key to the `EMPATICA_API_KEY` constant .
- Download the Android SDK from our [Developer Area][1].
- Unzip the archive you've downloaded and copy the `.aar` file you'll find inside into the `libs` folder contained in the sample project.
- Build and run the project.
- If a device is in range and its light is blinking green, but the app doesn't connect, please check that the discovered device can be used with your API key. If the `allowed` parameter is always false, the device is not linked to your API key. Please check your [Developer Area][1].

If you need any additional information about the Empatica API for Android, please check the [official documentation][2].

[1]: https://www.empatica.com/connect/developer.php
[2]: http://developer.empatica.com
