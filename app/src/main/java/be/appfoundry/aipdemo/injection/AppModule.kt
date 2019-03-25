package be.appfoundry.aipdemo.injection

import be.appfoundry.aipdemo.model.User
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

  @Provides
  @Singleton
  fun provideUser(): User = User("Siebe")
}