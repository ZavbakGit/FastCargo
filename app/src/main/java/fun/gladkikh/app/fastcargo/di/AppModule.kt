package `fun`.gladkikh.app.fastcargo.di

import `fun`.gladkikh.app.fastcargo.data.AccountRepositoryImpl
import `fun`.gladkikh.app.fastcargo.data.Preferences
import `fun`.gladkikh.app.fastcargo.data.SettingsRepositoryImpl
import `fun`.gladkikh.app.fastcargo.domain.repository.AccountRepository
import `fun`.gladkikh.app.fastcargo.domain.repository.SettingsRepository
import `fun`.gladkikh.app.fastcargo.preferences.PreferencesImpl
import `fun`.gladkikh.app.fastcargo.preferences.SharedPrefsManager
import `fun`.gladkikh.app.fastcargo.remote1.RequestRemote

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
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