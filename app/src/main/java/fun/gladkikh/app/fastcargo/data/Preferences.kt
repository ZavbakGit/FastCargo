package `fun`.gladkikh.app.fastcargo.data

import `fun`.gladkikh.app.fastcargo.common.type.Either
import `fun`.gladkikh.app.fastcargo.common.type.Failure
import `fun`.gladkikh.app.fastcargo.common.type.None
import `fun`.gladkikh.app.fastcargo.domain.entity.AccountEntity
import `fun`.gladkikh.app.fastcargo.domain.entity.SettingsEntity

interface Preferences {
    fun getSettings(): Either<Failure, SettingsEntity>
    fun saveSettings(settingsEntity: SettingsEntity): Either<Failure, None>
    fun getAccountEntity(): Either<Failure, AccountEntity>
    fun saveAccountEntity(account: AccountEntity): Either<Failure, None>
}