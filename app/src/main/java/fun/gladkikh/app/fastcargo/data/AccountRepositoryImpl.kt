package `fun`.gladkikh.app.fastcargo.data

import `fun`.gladkikh.app.fastcargo.common.type.Either
import `fun`.gladkikh.app.fastcargo.common.type.Failure
import `fun`.gladkikh.app.fastcargo.common.type.None
import `fun`.gladkikh.app.fastcargo.common.type.flatMap
import `fun`.gladkikh.app.fastcargo.domain.entity.AccountEntity
import `fun`.gladkikh.app.fastcargo.domain.repository.AccountRepository
import `fun`.gladkikh.app.fastcargo.remote.RequestRemote
import com.google.gson.Gson

class AccountRepositoryImpl(private val preferences: Preferences,
                            private val requestRemote: RequestRemote,
                            private val gson:Gson
) : AccountRepository {
    override fun getAccountEntity(): Either<Failure, AccountEntity> {
       return preferences.getAccountEntity()
    }

    override fun saveAccountEntity(account: AccountEntity): Either<Failure, None> {
        return preferences.saveAccountEntity(account)
    }

    override fun login(password:String): Either<Failure, String>{
        val data = LoginData("login",password)
        return preferences.getSettings().flatMap {
            try {
                requestRemote.request(it.login1C!!,it.password1C!!,it.host!!,gson.toJson(data))
            } catch (e: Exception) {
                return@flatMap Either.Left(Failure(e.message))
            }
        }
    }

    private data class LoginData(val command:String,val password:String)

}