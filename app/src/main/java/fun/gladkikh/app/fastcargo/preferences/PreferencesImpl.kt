package `fun`.gladkikh.app.fastcargo.preferences

import `fun`.gladkikh.app.fastcargo.common.type.Either
import `fun`.gladkikh.app.fastcargo.common.type.Failure
import `fun`.gladkikh.app.fastcargo.common.type.None
import `fun`.gladkikh.app.fastcargo.data.Preferences
import `fun`.gladkikh.app.fastcargo.domain.entity.AccountEntity
import `fun`.gladkikh.app.fastcargo.domain.entity.SettingsEntity
import javax.inject.Inject

class PreferencesImpl @Inject constructor (private val prefsManager: SharedPrefsManager): Preferences{
    override fun getSettings(): Either<Failure, SettingsEntity> {
        return prefsManager.getSettings()
    }

    override fun saveSettings(settingsEntity: SettingsEntity): Either<Failure, None> {
        return prefsManager.saveSettings(settingsEntity)
    }

    override fun getAccountEntity(): Either<Failure, AccountEntity> {
        return prefsManager.getAccountEntity()
    }

    override fun saveAccountEntity(account: AccountEntity): Either<Failure, None> {
        return prefsManager.saveAccountEntity(account)
    }
}