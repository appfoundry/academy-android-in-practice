package be.appfoundry.aipdemo.extension

import android.app.Activity
import be.appfoundry.aipdemo.App

val Activity.app: App
    get() = application as App