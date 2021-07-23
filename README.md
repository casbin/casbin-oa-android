# casbin-oa-android

Casbin-oa-android is the Android version of casbin-oa (https://github.com/casbin/casbin-oa.git). Casbin-oa is an official manuscript processing, evaluation and display system for Casbin technical writers.

## Architecture

Casbin-oa-android contains 2 parts:

| Name     | Description                       | Language               | Source code                                                  |
| -------- | --------------------------------- | ---------------------- | ------------------------------------------------------------ |
| Frontend | Developed by React Native         | Javascript + Java      | [casdoor/casbin-oa-android ](https://github.com/casdoor/casbin-oa-android) (this repo) |
| Backend  | RESTful API backend for Casbin-oa | Golang + Beego + MySQL | https://github.com/casbin/casbin-oa/                         |

## Set up a development environment

## Node, JDK

**Node:** [Node.js (nodejs.org)](https://nodejs.org/en/)	

> version of Node must ≥ 12
>
> Do not use cnpm !!!

**JDK:** [Java SE Development Kit 8 - Downloads (oracle.com)](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)

> version of JDK must be 1.8. Currently, version 1.9 and higher are not supported. Note that version 1.8 is officially called version 8.

### Yarn

```shell
npm install -g yarn
```

> After installing yarn, you can use `yarn add` instead of `npm install`

### Android development environment

> This process is quite cumbersome. Please follow instructions very carefully, because it took me a long time to make my first react native app start smoothly

#### 0. For Chinese developers

Developers in China **must have stable proxy software**, otherwise they will continue to encounter link timeouts or disconnections during the download, installation, and configuration process, and development work will not be possible. 
Some proxy software may only provide the proxy function of the browser, or only proxy for specific websites, etc. Please research the configuration or replace other software by yourself. 
In short, if there is a URL in the error report, then 99% of them are unable to connect to the network normally.

#### 1. Install Android Studio

> Make sure you picked `Android SDK`, `Android SDK Platform` and `Android Virtual Device`

#### 2. Install Android SDK

> Make sure you installed sdk of Android 10 (Q) instead of the latest version of the Android SDK. You can choose to install the sdk of Android 10 (Q) in SDK Manager. You could find SDK Manager in **Welcome screen of Android Studio** or **Preferences -> Appearance & Behavior → System Settings → Android SDK**
>
> Make sure you have these components: `Android SDK Platform 29` and `Intel x86 Atom_64 System Image`
>
> Make sure your version of Android SDK Build-Tools is 29.0.2
>
> Make sure your version of NDK (Side by side) in SDK Tools is 20.1.5948944

#### 3. Configure the ANDROID_HOME environment variable

| Variable Name | Variable Value                                  |
| ------------- | ----------------------------------------------- |
| ANDROID_HOME  | The directory where your Android SDK is located |

> The SDK is installed in the following directory by default: `C:\Users\YourUsername\AppData\Local\Android\Sdk`
>
> Maybe you should restart your PC to ensure that the environment variables take effect

#### 4. Add some tool directories to the environment variable Path

```
%ANDROID_HOME%\platform-tools
%ANDROID_HOME%\emulator
%ANDROID_HOME%\tools
%ANDROID_HOME%\tools\bin
```

## Start the APP

```shell
yarn #Installation dependencies
yarn android #Start the app
```

> You could run the app in Emulator or Real Android Phone