package `fun`.gladkikh.app.fastcargo.di

import `fun`.gladkikh.app.fastcargo.data.AccountRepositoryImpl
import `fun`.gladkikh.app.fastcargo.data.Preferences
import `fun`.gladkikh.app.fastcargo.data.SettingsRepositoryImpl
import `fun`.gladkikh.app.fastcargo.domain.repository.AccountRepository
import `fun`.gladkikh.app.fastcargo.domain.repository.SettingsRepository

import android.content.Context
import com.google.gson.Gson
import dagger.Module
import dagger.Provides

import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideAppContext(): Context = context

    @Provides
    @Singleton
    fun provideSettingsRepository(preferences: Preferences): SettingsRepository {
        return SettingsRepositoryImpl(preferences)
    }

    @Provides
    @Singleton
    fun provideAccountRepository(preferences: Preferences,
                                 gson: Gson): AccountRepository {
        return AccountRepositoryImpl(preferences,gson)
    }


}