package be.appfoundry.pxldemo.injection;

import javax.inject.Singleton;

import be.appfoundry.pxldemo.model.User;
import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides @Singleton static User provideUser() {
        User user = new User();
        user.setName("Siebe");
        return user;
    }

}
