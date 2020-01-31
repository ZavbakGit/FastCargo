package `fun`.gladkikh.app.fastcargo.data

import `fun`.gladkikh.app.fastcargo.common.type.Either
import `fun`.gladkikh.app.fastcargo.common.type.Failure
import `fun`.gladkikh.app.fastcargo.common.type.None
import `fun`.gladkikh.app.fastcargo.domain.entity.SettingsEntity
import `fun`.gladkikh.app.fastcargo.domain.repository.SettingsRepository

class SettingsRepositoryImpl(private val preferences: Preferences) : SettingsRepository {
    override fun getSettings(): Either<Failure, SettingsEntity> {
       return preferences.getSettings()
    }

    override fun saveSettings(settingsEntity: SettingsEntity): Either<Failure, None> {
        return preferences.saveSettings(settingsEntity)
    }
}