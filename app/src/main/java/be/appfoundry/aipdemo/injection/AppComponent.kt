package be.appfoundry.aipdemo.injection

import be.appfoundry.aipdemo.activity.dagger.DaggerActivity
import be.appfoundry.aipdemo.activity.retrofit.RetrofitActivity
import be.appfoundry.aipdemo.activity.rxjava.ReactiveActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = arrayOf(
        AppModule::class,
        ServiceModule::class
    )
)
interface AppComponent {
  fun inject(target: DaggerActivity)
  fun inject(target: RetrofitActivity)
  fun inject(target: ReactiveActivity)
}