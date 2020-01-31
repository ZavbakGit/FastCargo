package `fun`.gladkikh.app.fastcargo.domain.repository

import `fun`.gladkikh.app.fastcargo.common.type.Either
import `fun`.gladkikh.app.fastcargo.common.type.Failure
import `fun`.gladkikh.app.fastcargo.common.type.None
import `fun`.gladkikh.app.fastcargo.domain.entity.AccountEntity
import `fun`.gladkikh.app.fastcargo.domain.entity.SettingsEntity

interface AccountRepository {
    fun getAccountEntity(): Either<Failure, AccountEntity>
    fun saveAccountEntity(account: AccountEntity): Either<Failure, None>
    fun login(password: String): Either<Failure, String>
}