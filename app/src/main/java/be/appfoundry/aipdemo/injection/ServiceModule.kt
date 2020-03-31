package be.appfoundry.aipdemo.injection

import be.appfoundry.aipdemo.service.SwapiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import javax.inject.Singleton

@Module
class ServiceModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
            .baseUrl("https://swapi.co")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(JacksonConverterFactory.create())
            .build()


    @Provides
    @Singleton
    fun provideSwapiService(retrofit: Retrofit): SwapiService = retrofit.create(SwapiService::class.java)
}
