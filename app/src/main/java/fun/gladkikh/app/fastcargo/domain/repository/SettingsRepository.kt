package `fun`.gladkikh.app.fastcargo.domain.repository

import `fun`.gladkikh.app.fastcargo.common.type.Either
import `fun`.gladkikh.app.fastcargo.common.type.Failure
import `fun`.gladkikh.app.fastcargo.common.type.None
import `fun`.gladkikh.app.fastcargo.domain.entity.SettingsEntity

interface SettingsRepository {
    fun getSettings():Either<Failure,SettingsEntity>
    fun saveSettings(settingsEntity: SettingsEntity):Either<Failure, None>
}