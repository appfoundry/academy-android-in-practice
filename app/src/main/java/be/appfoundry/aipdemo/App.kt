package be.appfoundry.aipdemo

import android.app.Application
import be.appfoundry.aipdemo.injection.AppComponent
import be.appfoundry.aipdemo.injection.DaggerAppComponent

class App : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().build()
    }
}
