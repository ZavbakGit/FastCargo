package `fun`.gladkikh.app.fastcargo.preferences

import `fun`.gladkikh.app.fastcargo.common.type.Either
import `fun`.gladkikh.app.fastcargo.common.type.Failure
import `fun`.gladkikh.app.fastcargo.common.type.None
import `fun`.gladkikh.app.fastcargo.domain.entity.AccountEntity
import `fun`.gladkikh.app.fastcargo.domain.entity.SettingsEntity
import android.content.SharedPreferences
import com.google.gson.Gson
import java.util.*
import javax.inject.Inject


class SharedPrefsManager @Inject constructor(
    private val prefs: SharedPreferences,
    private val gson: Gson
) {
    companion object {
        const val HOST = "preference_host"
        const val LOGIN_1C = "preference_login"
        const val PASSWORD_1C = "preference_pass"
        const val LIST_TSD = "list_tsd"

        const val ACCOUNT = "account"

    }

    fun getAccountEntity(): Either<Failure, AccountEntity> {
        try {
            val accountStr =
                prefs.getString(ACCOUNT, null)
                    ?: return Either.Left(Failure("Нет сохраненного акаунта!"))

            val account = gson.fromJson(accountStr, AccountEntity::class.java)
            return Either.Right(account)
        } catch (e: Exception) {
            return Either.Left(Failure("Ошибка чтения акаунта!"))
        }

    }

    fun saveAccountEntity(account: AccountEntity): Either<Failure, None> {
        return try {
            prefs.edit().apply {
                putSafely(ACCOUNT, gson.toJson(account))
            }.apply()
            Either.Right(None())
        } catch (e: Exception) {
            Either.Left(Failure("Ошибка записи акаунта!"))
        }
    }

    fun saveSettings(settingsEntity: SettingsEntity): Either<Failure, None> {
        return try {
            prefs.edit().apply {
                putSafely(HOST, settingsEntity.host)
                putSafely(LOGIN_1C, settingsEntity.login1C)
                putSafely(PASSWORD_1C, settingsEntity.password1C)
                //putSafely(LIST_TSD, settingsEntity.pdt)
                ///TODO Потом сохраним
            }.apply()
            Either.Right(None())
        } catch (e: Exception) {
            Either.Left(Failure("Ошибка записи акаунта!"))
        }
    }

    fun getSettings(): Either<Failure, SettingsEntity> {
        try {
            val host = prefs.getString(HOST, null)
            val login1C = prefs.getString(LOGIN_1C, null)
            val password1C = prefs.getString(PASSWORD_1C, null)
            val pdt = prefs.getString(LIST_TSD, 1.toString())?.toIntOrNull()

            return Either.Right(
                SettingsEntity(
                    host = host,
                    login1C = login1C,
                    date = Date(),
                    password1C = password1C,
                    pdt = pdt
                )
            )
        } catch (e: Exception) {
            return Either.Left(Failure("Ошибка чтения настроек!"))
        }
    }

}

fun SharedPreferences.Editor.putSafely(key: String, value: Int?) {
    if (value != null && value != 0) {
        putInt(key, value)
    }
}

fun SharedPreferences.Editor.putSafely(key: String, value: Long?) {
    if (value != null && value != 0L) {
        putLong(key, value)
    }
}

fun SharedPreferences.Editor.putSafely(key: String, value: String?) {
    if (value != null && value.isNotEmpty()) {
        putString(key, value)
    }
}