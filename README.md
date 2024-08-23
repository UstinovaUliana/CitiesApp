# CitiesApp

App allows users to search cities by its name. 
For this app I've used https://api-ninjas.com/api/city API.

## Technology stack
The app is *multiplatform* and supports Android and Desktop targets. The source code is written in *Kotlin*. I've used *Jetpack Compose* for layout, *Ktor* for network interaction, *Kotlin Coroutines* for reactive programming, *Kodein* for dependency injection, *MVI* and *MVIKotlin* for architeecture.

## Screenshots

### Desktop
<img src="/screenshots/StartView.png" width="400" height="300"> 
<img src="/screenshots/LoadingView.png" width="400" height="300"> 
<img src="/screenshots/CitiesColumnView.png" width="400" height="300"> 
<img src="/screenshots/NothingFoundView.png" width="400" height="300"> 
<img src="/screenshots/ErrorView.png" width="400" height="300"> 

### Android

### Dark Theme
<img src="/screenshots/PhoneScreen.png" width="270" height="600"> 
<img src="/screenshots/DarkThemeView.jpg" width="270" height="600"> 


## Setup for Developers
1. Make sure you have downloaded the latest version of [Android Studio](https://developer.android.com/sdk/index.html). It works on Linux, Windows and Mac. Download the correct version for your OS.
2. Go to [the project repo](https://github.com/UstinovaUliana/CitiesApp) and fork it by clicking "Fork"
3. If you are working on Windows, download [Git Bash for Windows](https://git-for-windows.github.io/) to get a full Unix bash with Git functionality
4. Clone the repo to your desktop `git clone https://github.com/YOUR_USER_NAME/CitiesApp.git`
5. Open the project with Android Studio
6. Build a 'composeApp' application which is inside the base directory.
