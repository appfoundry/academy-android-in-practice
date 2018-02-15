package be.appfoundry.aipdemo.injection;

import javax.inject.Singleton;

import be.appfoundry.aipdemo.model.User;
import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    @Singleton
    static User provideUser() {
        User user = new User("Siebe", 27);
        return user;
    }

}
