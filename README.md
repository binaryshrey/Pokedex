![Banner](https://raw.githubusercontent.com/binaryshrey/Pokedex/main/assets/pokedex-banner.png)


<h1 align="center">Pokédex</h1>

<p align="center">
  <a href="https://pokedex-zenstudio.netlify.app/"><img alt="Web" src="https://img.shields.io/badge/Web-Pokedex-%231D9BF0?logo=gatsby&logoColor=ffffff"/></a> 
  <a href="https://android-arsenal.com/api?level=23"><img alt="API" src="https://img.shields.io/badge/API-23%2B-brightgreen.svg?style=flat"/></a>
  <a href="https://ktlint.github.io/"><img alt="ktlint" src="https://img.shields.io/badge/Code_Style-Excellent-%231D9BF0"/></a> 
<br>
</p>

<p align="center">  
Introducing the ultimate pokemon companion app with Poké-Scan feature that indentifies & provides in-depth information about any pokemon.
Explore 800+ pokemon(s) across all 8 generations and elevate your Pokemon Trainer Level with Poké-Quiz and Poké-Scan features
<br/><br/><br/>
Download the app today.
<br/><br/>
<a href="https://github.com/binaryshrey/Pokedex/releases/download/v1.0.0/pokedex.apk"><img alt="Pokedex App" src="https://img.shields.io/badge/Pokedex%20%E2%98%84%EF%B8%8F-APK-orange.svg?style=for-the-badge&logo=android" width="300"/></a> 
</p>

<br/>

## Features

<br/>

![App Features](https://raw.githubusercontent.com/binaryshrey/Pokedex/main/assets/pokedex-features.png)

<br/>


## Built With 🛠
- [Kotlin](https://kotlinlang.org/) - Official programming language for Android development.
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
  - [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) : A coroutine is an instance of suspendable computation.
  - [DataBinding](https://developer.android.com/topic/libraries/data-binding) : The Data Binding Library is a support library that allows you to bind UI components in your layouts to data sources in your app using a declarative format rather than programmatically.
  - [DataStore](https://developer.android.com/topic/libraries/architecture/datastore) : Jetpack DataStore is a data storage solution that allows you to store key-value pairs or typed objects with protocol buffers.
  - [Firebase](https://firebase.google.com/) : Firebase is a set of backend cloud computing services and application development platforms provided by Google.
  - [Glide](https://github.com/bumptech/glide) : An image loading and caching library for Android focused on smooth scrolling.
  - [Jetpack Navigation](https://developer.android.com/guide/navigation) : Navigation refers to the interactions that allow users to navigate across, into, and back out from the different pieces of content within your app.
  - [Jetpack Preference Setting](https://developer.android.com/develop/ui/views/components/settings) : Settings allow users to change the functionality and behavior of an application.
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) : LiveData is an observable data holder class.
  - [Lottie](https://lottiefiles.com/) : Lottie is a library for Android, iOS, Web, and Windows that parses Adobe After Effects animations exported as JSON with Bodymovin and renders them natively on mobile and on the web.
  - [ML-Kit](https://developers.google.com/ml-kit) : ML Kit brings Google's machine learning expertise to mobile developers in a powerful and easy-to-use package.
  - [Moshi](https://github.com/square/moshi) : A modern JSON library for Kotlin and Java.
  - [RecyelerView](https://developer.android.com/reference/androidx/recyclerview/widget/RecyclerView) : A flexible view for providing a limited window into a large data set.
  - [Retrofit](https://square.github.io/retrofit/) : A type-safe HTTP client for Android and Java.
  - [Room](https://developer.android.com/jetpack/androidx/releases/room) : The Room persistence library provides an abstraction layer over SQLite to allow fluent database access while harnessing the full power of SQLite.
  - [Safe Args](https://developer.android.com/guide/navigation/use-graph/safe-args) : The recommended way to navigate between destinations.
  - [Splash Screen API](https://developer.android.com/develop/ui/views/launch/splash-screen) : Splash Screen is usually the first screen visible to the user when the application is launched.
  - [Swipe Refresh Layout](https://developer.android.com/develop/ui/views/touch-and-input/swipe/add-swipe-interface) : The swipe-to-refresh user interface pattern is implemented entirely within the SwipeRefreshLayout widget, which detects the vertical swipe, displays a distinctive progress bar, and triggers callback methods in the app.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) : Stores UI-related data that isn't destroyed on UI changes. 
  
- [Material Components for Android](https://github.com/material-components/material-components-android) : Modular and customizable Material Design UI components for Android.
- [Figma](https://figma.com/) : Figma is a vector graphics editor and prototyping tool which is primarily web-based.

<br />

## Architecture

**Pokedex** is based on the MVVM architecture and the Repository pattern, which follows the [Google's official architecture guidance](https://developer.android.com/topic/architecture).
![architecture](https://raw.githubusercontent.com/binaryshrey/Avatars-AI/main/assets/mvvm.png)

**Pokedex** was built with [Guide to app architecture](https://developer.android.com/topic/architecture), so it would be a great sample to show how the architecture works in real-world projects.

The overall architecture of **Pokedex** is composed of three layers - the UI layer, the Domain layer and the Data layer. Each layer has dedicated components and they have each different responsibilities, as defined below.

### Architecture Overview

![architecture](https://raw.githubusercontent.com/binaryshrey/Avatars-AI/main/static/figure1.png)

- Each layer follows [unidirectional event/data flow](https://developer.android.com/topic/architecture/ui-layer#udf); the UI layer emits user events to the data layer, and the data layer exposes data as a stream to other layers.
- The data layer is designed to work independently from other layers and must be pure, which means it doesn't have any dependencies on the other layers.

With this loosely coupled architecture, you can increase the reusability of components and scalability of your app.

### UI Layer

![architecture](https://raw.githubusercontent.com/binaryshrey/Avatars-AI/main/static/figure2.png)

The UI layer consists of UI elements to configure screens that could interact with users and [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) that holds app states and restores data when configuration changes.
- UI elements observe the data flow via [DataBinding](https://developer.android.com/topic/libraries/data-binding), which is the most essential part of the MVVM architecture. 

### Data Layer

![architecture](https://raw.githubusercontent.com/binaryshrey/Avatars-AI/main/static/figure3.png)

The data Layer consists of repositories, which include business logic, such as querying data from the local database and requesting remote data from the network. It is implemented as an offline-first source of business logic and follows the [single source of truth](https://en.wikipedia.org/wiki/Single_source_of_truth) principle.<br>

<br/>

## Find this repository useful? :heart:
Support it by joining [stargazers](https://github.com/binaryshrey/Pokedex/stargazers) for this repository. :star: <br>
Also, [submit an issue](https://github.com/binaryshrey/Pokedex/issues) on GitHub for my any bugs or to request new features!

<br/>

## App Development setup

```
git clone https://github.com/binaryshrey/Pokedex.git
cd Pokedex
```

- Create a new Firebase project & add an android app with package name - dev.shreyansh.pokemon.pokedex
- Enable Google as 'Login-Provider' under Firebase Authentication 
- Download the google-services.json file and place it under the app folder

<br/>

## Web Development setup

```
git clone https://github.com/binaryshrey/Pokedex.git
cd Pokedex
npm i
gatsby develop
```

Development server runs at `http://localhost:8000`.

## Web Prod setup

```
gatsby build
gatsby serve
```
Production server runs at `http://localhost:9000`.
Build is generated at `public` (which is deployable).

<br/>

## Web Lighthouse scores

![Lighthouse metrics](https://raw.githubusercontent.com/binaryshrey/Pokedex/main/assets/lighthouse.png)

<br/>

## License 🔖
```
MIT License

Copyright (c) 2023 Shreyansh Saurabh

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.



