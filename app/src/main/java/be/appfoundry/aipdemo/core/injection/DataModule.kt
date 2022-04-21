package be.appfoundry.aipdemo.core.injection

import android.content.Context
import androidx.room.Room
import be.appfoundry.aipdemo.data.database.AppDatabase
import be.appfoundry.aipdemo.data.network.CardService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DataModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.magicthegathering.io")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideCardService(retrofit: Retrofit): CardService =
        retrofit.create(CardService::class.java)

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).build()

    @Provides
    @Singleton
    fun provideCardDao(appDatabase: AppDatabase) =
        appDatabase.cardDao()
}
