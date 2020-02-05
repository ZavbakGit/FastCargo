package `fun`.gladkikh.app.fastcargo.domain.usecase

import `fun`.gladkikh.app.fastcargo.common.interactor.UseCase
import `fun`.gladkikh.app.fastcargo.common.type.*
import `fun`.gladkikh.app.fastcargo.domain.entity.AccountEntity
import `fun`.gladkikh.app.fastcargo.domain.repository.AccountRepository
import javax.inject.Inject

class LoginAccountUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) : UseCase<None, String>() {
    override suspend fun run(params: String): Either<Failure, None> {
        val account = accountRepository.getAccountEntity()

        if (account.isLeft) {
            return login(params)
        } else {

            return account.flatMap { accountEntity ->
                if (accountEntity.password == params) {
                    return@flatMap Either.Right(None())
                } else {
                    return@flatMap login(params)
                }
            }
        }
    }


    private fun login(password: String): Either<Failure, None> {
        val accountEntity = AccountEntity(
            user = null,
            guid = null,
            settings = null,
            password = null
        )

        return accountRepository.saveAccountEntity(accountEntity)
            .flatMap {
                accountRepository.login(password)
            }.flatMap {
                accountRepository.saveAccountEntity(it)
            }
    }


}
