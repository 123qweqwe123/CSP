This project was bootstrapped with [Create React Native App](https://github.com/react-community/create-react-native-app).

Below you'll find information about performing common tasks. The most recent version of this guide is available [here](https://github.com/react-community/create-react-native-app/blob/master/react-native-scripts/template/README.md).

## Table of Contents

* [Updating to New Releases](#updating-to-new-releases)
* [Available Scripts](#available-scripts)
  * [npm start](#npm-start)
  * [npm test](#npm-test)
  * [npm run ios](#npm-run-ios)
  * [npm run android](#npm-run-android)
  * [npm run eject](#npm-run-eject)
* [Writing and Running Tests](#writing-and-running-tests)
* [Environment Variables](#environment-variables)
  * [Configuring Packager IP Address](#configuring-packager-ip-address)
* [Adding Flow](#adding-flow)
* [Customizing App Display Name and Icon](#customizing-app-display-name-and-icon)
* [Sharing and Deployment](#sharing-and-deployment)
  * [Publishing to Expo's React Native Community](#publishing-to-expos-react-native-community)
  * [Building an Expo "standalone" app](#building-an-expo-standalone-app)
  * [Ejecting from Create React Native App](#ejecting-from-create-react-native-app)
    * [Build Dependencies (Xcode & Android Studio)](#build-dependencies-xcode-android-studio)
    * [Should I Use ExpoKit?](#should-i-use-expokit)
* [Troubleshooting](#troubleshooting)
  * [Networking](#networking)
  * [iOS Simulator won't open](#ios-simulator-wont-open)
  * [QR Code does not scan](#qr-code-does-not-scan)

## Updating to New Releases

You should only need to update the global installation of `create-react-native-app` very rarely, ideally never.

Updating the `react-native-scripts` dependency of your app should be as simple as bumping the version number in `package.json` and reinstalling your project's dependencies.

Upgrading to a new version of React Native requires updating the `react-native`, `react`, and `expo` package versions, and setting the correct `sdkVersion` in `app.json`. See the [versioning guide](https://github.com/react-community/create-react-native-app/blob/master/VERSIONS.md) for up-to-date information about package version compatibility.

## Available Scripts

If Yarn was installed when the project was initialized, then dependencies will have been installed via Yarn, and you should probably use it to run these commands as well. Unlike dependency installation, command running syntax is identical for Yarn and NPM at the time of this writing.

### `npm start`

Runs your app in development mode.

Open it in the [Expo app](https://expo.io) on your phone to view it. It will reload if you save edits to your files, and you will see build errors and logs in the terminal.

Sometimes you may need to reset or clear the React Native packager's cache. To do so, you can pass the `--reset-cache` flag to the start script:

```
npm start -- --reset-cache
# or
yarn start -- --reset-cache
```

#### `npm test`

Runs the [jest](https://github.com/facebook/jest) test runner on your tests.

#### `npm run ios`

Like `npm start`, but also attempts to open your app in the iOS Simulator if you're on a Mac and have it installed.

#### `npm run android`

Like `npm start`, but also attempts to open your app on a connected Android device or emulator. Requires an installation of Android build tools (see [React Native docs](https://facebook.github.io/react-native/docs/getting-started.html) for detailed setup). We also recommend installing Genymotion as your Android emulator. Once you've finished setting up the native build environment, there are two options for making the right copy of `adb` available to Create React Native App:

##### Using Android Studio's `adb`

1. Make sure that you can run adb from your terminal.
2. Open Genymotion and navigate to `Settings -> ADB`. Select “Use custom Android SDK tools” and update with your [Android SDK directory](https://stackoverflow.com/questions/25176594/android-sdk-location).

##### Using Genymotion's `adb`

1. Find Genymotion’s copy of adb. On macOS for example, this is normally `/Applications/Genymotion.app/Contents/MacOS/tools/`.
2. Add the Genymotion tools directory to your path (instructions for [Mac](http://osxdaily.com/2014/08/14/add-new-path-to-path-command-line/), [Linux](http://www.computerhope.com/issues/ch001647.htm), and [Windows](https://www.howtogeek.com/118594/how-to-edit-your-system-path-for-easy-command-line-access/)).
3. Make sure that you can run adb from your terminal.

#### `npm run eject`

This will start the process of "ejecting" from Create React Native App's build scripts. You'll be asked a couple of questions about how you'd like to build your project.

**Warning:** Running eject is a permanent action (aside from whatever version control system you use). An ejected app will require you to have an [Xcode and/or Android Studio environment](https://facebook.github.io/react-native/docs/getting-started.html) set up.

## Customizing App Display Name and Icon

You can edit `app.json` to include [configuration keys](https://docs.expo.io/versions/latest/guides/configuration.html) under the `expo` key.

To change your app's display name, set the `expo.name` key in `app.json` to an appropriate string.

To set an app icon, set the `expo.icon` key in `app.json` to be either a local path or a URL. It's recommended that you use a 512x512 png file with transparency.

## Writing and Running Tests

This project is set up to use [jest](https://facebook.github.io/jest/) for tests. You can configure whatever testing strategy you like, but jest works out of the box. Create test files in directories called `__tests__` or with the `.test` extension to have the files loaded by jest. See the [the template project](https://github.com/react-community/create-react-native-app/blob/master/react-native-scripts/template/App.test.js) for an example test. The [jest documentation](https://facebook.github.io/jest/docs/en/getting-started.html) is also a wonderful resource, as is the [React Native testing tutorial](https://facebook.github.io/jest/docs/en/tutorial-react-native.html).

## Environment Variables

You can configure some of Create React Native App's behavior using environment variables.

### Configuring Packager IP Address

When starting your project, you'll see something like this for your project URL:

```
exp://192.168.0.2:19000
```

The "manifest" at that URL tells the Expo app how to retrieve and load your app's JavaScript bundle, so even if you load it in the app via a URL like `exp://localhost:19000`, the Expo client app will still try to retrieve your app at the IP address that the start script provides.

In some cases, this is less than ideal. This might be the case if you need to run your project inside of a virtual machine and you have to access the packager via a different IP address than the one which prints by default. In order to override the IP address or hostname that is detected by Create React Native App, you can specify your own hostname via the `REACT_NATIVE_PACKAGER_HOSTNAME` environment variable:

Mac and Linux:

```
REACT_NATIVE_PACKAGER_HOSTNAME='my-custom-ip-address-or-hostname' npm start
```

Windows:
```
set REACT_NATIVE_PACKAGER_HOSTNAME='my-custom-ip-address-or-hostname'
npm start
```

The above example would cause the development server to listen on `exp://my-custom-ip-address-or-hostname:19000`.

## Adding Flow

Flow is a static type checker that helps you write code with fewer bugs. Check out this [introduction to using static types in JavaScript](https://medium.com/@preethikasireddy/why-use-static-types-in-javascript-part-1-8382da1e0adb) if you are new to this concept.

React Native works with [Flow](http://flowtype.org/) out of the box, as long as your Flow version matches the one used in the version of React Native.

To add a local dependency to the correct Flow version to a Create React Native App project, follow these steps:

1. Find the Flow `[version]` at the bottom of the included [.flowconfig](.flowconfig)
2. Run `npm install --save-dev flow-bin@x.y.z` (or `yarn add --dev flow-bin@x.y.z`), where `x.y.z` is the .flowconfig version number.
3. Add `"flow": "flow"` to the `scripts` section of your `package.json`.
4. Add `// @flow` to any files you want to type check (for example, to `App.js`).

Now you can run `npm run flow` (or `yarn flow`) to check the files for type errors.
You can optionally use a [plugin for your IDE or editor](https://flow.org/en/docs/editors/) for a better integrated experience.

To learn more about Flow, check out [its documentation](https://flow.org/).

## Sharing and Deployment

Create React Native App does a lot of work to make app setup and development simple and straightforward, but it's very difficult to do the same for deploying to Apple's App Store or Google's Play Store without relying on a hosted service.

### Publishing to Expo's React Native Community

Expo provides free hosting for the JS-only apps created by CRNA, allowing you to share your app through the Expo client app. This requires registration for an Expo account.

Install the `exp` command-line tool, and run the publish command:

```
$ npm i -g exp
$ exp publish
```

### Building an Expo "standalone" app

You can also use a service like [Expo's standalone builds](https://docs.expo.io/versions/latest/guides/building-standalone-apps.html) if you want to get an IPA/APK for distribution without having to build the native code yourself.

### Ejecting from Create React Native App

If you want to build and deploy your app yourself, you'll need to eject from CRNA and use Xcode and Android Studio.

This is usually as simple as running `npm run eject` in your project, which will walk you through the process. Make sure to install `react-native-cli` and follow the [native code getting started guide for React Native](https://facebook.github.io/react-native/docs/getting-started.html).

#### Should I Use ExpoKit?

If you have made use of Expo APIs while working on your project, then those API calls will stop working if you eject to a regular React Native project. If you want to continue using those APIs, you can eject to "React Native + ExpoKit" which will still allow you to build your own native code and continue using the Expo APIs. See the [ejecting guide](https://github.com/react-community/create-react-native-app/blob/master/EJECTING.md) for more details about this option.

## Troubleshooting

### Networking

If you're unable to load your app on your phone due to a network timeout or a refused connection, a good first step is to verify that your phone and computer are on the same network and that they can reach each other. Create React Native App needs access to ports 19000 and 19001 so ensure that your network and firewall settings allow access from your device to your computer on both of these ports.

Try opening a web browser on your phone and opening the URL that the packager script prints, replacing `exp://` with `http://`. So, for example, if underneath the QR code in your terminal you see:

```
exp://192.168.0.1:19000
```

Try opening Safari or Chrome on your phone and loading

```
http://192.168.0.1:19000
```

and

```
http://192.168.0.1:19001
```

If this works, but you're still unable to load your app by scanning the QR code, please open an issue on the [Create React Native App repository](https://github.com/react-community/create-react-native-app) with details about these steps and any other error messages you may have received.

If you're not able to load the `http` URL in your phone's web browser, try using the tethering/mobile hotspot feature on your phone (beware of data usage, though), connecting your computer to that WiFi network, and restarting the packager.

### iOS Simulator won't open

If you're on a Mac, there are a few errors that users sometimes see when attempting to `npm run ios`:

* "non-zero exit code: 107"
* "You may need to install Xcode" but it is already installed
* and others

There are a few steps you may want to take to troubleshoot these kinds of errors:

1. Make sure Xcode is installed and open it to accept the license agreement if it prompts you. You can install it from the Mac App Store.
2. Open Xcode's Preferences, the Locations tab, and make sure that the `Command Line Tools` menu option is set to something. Sometimes when the CLI tools are first installed by Homebrew this option is left blank, which can prevent Apple utilities from finding the simulator. Make sure to re-run `npm/yarn run ios` after doing so.
3. If that doesn't work, open the Simulator, and under the app menu select `Reset Contents and Settings...`. After that has finished, quit the Simulator, and re-run `npm/yarn run ios`.

### QR Code does not scan

If you're not able to scan the QR code, make sure your phone's camera is focusing correctly, and also make sure that the contrast on the two colors in your terminal is high enough. For example, WebStorm's default themes may [not have enough contrast](https://github.com/react-community/create-react-native-app/issues/49) for terminal QR codes to be scannable with the system barcode scanners that the Expo app uses.

If this causes problems for you, you may want to try changing your terminal's color theme to have more contrast, or running Create React Native App from a different terminal. You can also manually enter the URL printed by the packager script in the Expo app's search bar to load it manually.



脚手架从 [react-native-dva-starter](https://github.com/nihgwu/react-native-dva-starter) 参考而来

### React Navigation

路由使用的是 [React Navigation](https://reactnavigation.org/docs/intro/)
[Event Subscriptions](https://github.com/react-navigation/react-navigation/pull/3345)

### [flex 布局学习](http://www.ruanyifeng.com/blog/2015/07/flex-grammar.html)

[A Comlete Guide to Flexbox](https://css-tricks.com/snippets/css/a-guide-to-flexbox/)
flex 布局（flexible box）
![](http://www.ruanyifeng.com/blogimg/asset/2015/bg2015071004.png)
容器成员 flex container
**容器属性：**
flex-direction 主轴的方向
flex-wrap 排列在一个轴线上的项目如何换行
justify-content 项目在主轴上的对其方式
**项目属性**
order  排列顺序
flex-grow  项目的放大比例，默认为0
flex-shrink 项目的缩小比例，默认值1
flex-basis   项目占据的主轴空间（宽度、高度），默认值 auto
flex:flex-grow/flex-shrink

### ES6 知识

[修饰器](http://es6.ruanyifeng.com/#docs/decorator)


### debug 

#+D 打开开发者菜单
#+R 重载 JavaScript
使用 `console.error/warn` 来手动的控制错误/警告在屏幕上面的显示

使用 chrome 远程 debug js
使用 React Developer Tools
`npm install -g react-devtools`


### 参考

[React Native 第三方组件汇总](https://www.jianshu.com/p/53ff78168acc)
NativeBase UI 库

[IOS 开发是否要采用 React Native](http://www.cocoachina.com/ios/20171120/21218.html)


## 开发遇到的问题

1、jsconfig.json 文件，消除某些类文件中装饰器的错误

2、**addListener is not a function?**

这个问题在我重建项目，编译之后一直报错，出现 _addListener is not a function_
我对比之前的项目发现 package.json 里面定义的 react-navigation 的版本是 1.0.0-beta.21，但是执行 `npm ls react-navigation` 却发现版本是 `1.0.0-beta.29`，执行 `npm i` 竟然不是按照 package.json 里面定义的版本来下载而是默认安装的最高版本？我将版本降到 1.0.0-beta.21 问题得到了解决，然后还提了个 [issue](https://github.com/react-navigation/react-navigation/issues/2824#issuecomment-362977602)
再接着开发的过程中，我想点击按钮路由到扫描二维码的界面，参考 Account.js 里面的 gotoLogin 部分代码，我想如果在 `static navigationOptions = ...` 静态属性里面如果能获取到当前对象的引用就好了，找到一个类似的 [issue](https://github.com/react-navigation/react-navigation/issues/149#issuecomment-276872081)，但是按照这个配置，一直报错，大概意思是 headerRight 只能返回一个 component 而非 object，我想问题大概是我的 react-navigation 的版本的问题？我将版本升级到最新的 react-navigation，然后开始翻源码来解决 addListener 的问题，在 源码 createNavigationContainer.js-182 行可以看到如果 this._isStateful() 才会往 navigation 添加 addListener 方法，而只有组件没有定义 navigation 属性，该值才为 true。所以我最终找到 本项目 router.js 的 119 行，给 navigation 手动添加 addListener() 方法，最终解决了这个问题。
但是解决这个问题并没有解决上面静态属性的问题，我接着翻 api，找到了[如下一段](https://reactnavigation.org/docs/headers.html#using-params-in-the-title)，发现我的 navigation 定义的地方有问题，具体修改将 Home.js-12 行。

总结：1、不要害怕翻源码  2、首先记得去看 api，再去google 搜，实在不行再去提 issue
  

3、**No bundle url presend**

编译之后，报错提示如上
https://github.com/facebook/react-native/issues/12754#issuecomment-286200110
不要关闭当前的 Simulator 和 terminal，再次执行 `react-native run-ios`

4、**添加字体文件**

https://github.com/oblador/react-native-vector-icons
将字体文件目录拖到 xcode 对应的目录中，然后在 Info.plist 里面添加 Fonts procided by application 添加对应的字体文件（在 vscode 里面显示的目录和 xcode 显示的目录结构是不一样的，注意在 xcode 里面进行操作）

5、**执行 npm run eject**

将 create-react-native-app 项目恢复成原始的 react-native 项目，比如导入字体文件、打包等都需要这个操作，而原始的项目是可以通过 expo 来进行开发，比较方便