package `fun`.gladkikh.app.fastcargo.data

import `fun`.gladkikh.app.fastcargo.App
import `fun`.gladkikh.app.fastcargo.common.type.*
import `fun`.gladkikh.app.fastcargo.domain.entity.AccountEntity
import `fun`.gladkikh.app.fastcargo.domain.repository.AccountRepository

import com.google.gson.Gson

class AccountRepositoryImpl(
    private val preferences: Preferences,
    private val gson: Gson
) : AccountRepository {


    override fun getAccountEntity(): Either<Failure, AccountEntity> {
        return preferences.getAccountEntity()
    }

    override fun saveAccountEntity(account: AccountEntity): Either<Failure, None> {
        return preferences.saveAccountEntity(account)
    }

    override fun testRemoteRequest(): Either<Failure, String> {
        val data = TestRemoteRequestData("test")
        return preferences.getSettings().flatMap {
            try {
                App.requestRemote!!.request(it.login1C!!, it.password1C!!, gson.toJson(data))
            } catch (e: Exception) {
                return@flatMap Either.Left(Failure(e.message))
            }
        }
    }

    override fun login(password: String): Either<Failure, AccountEntity> {
        val data = LoginData("login", password)
        return preferences.getSettings()
            .flatMap { settings ->
                try {
                    App.requestRemote!!.request(settings.login1C!!, settings.password1C!!, gson.toJson(data))
                        .map {
                            gson.fromJson(it,AccountEntity::class.java)
                        }
                } catch (e: Exception) {
                    return@flatMap Either.Left(Failure(e.toString()))
                }
            }
    }

    private data class LoginData(val command: String, val password: String)
    private data class TestRemoteRequestData(val command: String)

}