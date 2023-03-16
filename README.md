# Confetti Landing Page Compose/Web Canvas

## Introduction

This is a trial project on creating web pages using compose/web canvas. The project renders composables on a browser's
canvas element.

### [Confetti](https://github.com/joreilly/Confetti)

Confetti is a Kotlin Multiplatform Mobile GraphQL based conference sample built using Jetpack Compose on Android and
SwiftUI for IOS Clients. The project mimics the [landing page](https://confetti-app.dev/) of the project using
composables instead of HTML elements.

## Build

Clone the repository and run the command

To run on a local machine ensure you update the value of the variable `resourcePath` in Main.kt to an empty string

```kotlin
const val resourcePath = ""
```

Then run the command below to host the project on your machine.

```
./gradlew jsBrowserDevelopmentRun
```

## Known Issues

* Fonts - Failing to apply `Opens Sans` to text in the page

## Contributing

Contributions to the project are welcome and highly appreciated. To contribute, simply clone the repository and make
your changes.

Ensure you build before pushing changes.

```
./gradlew build
```

## Resources

* [Kotlin/JS](https://kotlinlang.org/docs/js-overview.html)
