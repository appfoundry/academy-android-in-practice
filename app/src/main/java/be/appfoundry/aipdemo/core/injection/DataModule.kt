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

@Module
@InstallIn(SingletonComponent::class)
internal object DataModule {

    @Provides
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.magicthegathering.io")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    @Provides
    fun provideCardService(retrofit: Retrofit): CardService =
        retrofit.create(CardService::class.java)

    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).build()

    @Provides
    fun provideCardDao(appDatabase: AppDatabase) =
        appDatabase.cardDao()
}