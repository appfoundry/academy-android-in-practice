# Demo project to go along with Android in Practice host talk

This demo project uses the [magicthegathering.io](magicthegathering.io) API because no api key is required and has a variety of data from text to images.\
_Note: loading images could throw an SSLHandshakeException and an unsafe OkHttpClient has been used in [App.kt](./app/src/main/java/be/appfoundry/aipdemo/core/App.kt)._

Noteworthy features:
- Full [Compose](https://developer.android.com/jetpack/compose) for UI.
- [Retrofit](https://square.github.io/retrofit) for network calls.
- [Room](https://developer.android.com/training/data-storage/room) for storage.
- [Dagger Hilt](https://developer.android.com/training/dependency-injection/hilt-android) for dependency injection.
- [Compose Destinations](https://composedestinations.rafaelcosta.xyz/) for navigation.
- [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) for concurrency.
