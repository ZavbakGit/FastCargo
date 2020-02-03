package `fun`.gladkikh.app.fastcargo.domain.usecase

import `fun`.gladkikh.app.fastcargo.common.interactor.UseCase
import `fun`.gladkikh.app.fastcargo.common.type.Either
import `fun`.gladkikh.app.fastcargo.common.type.Failure
import `fun`.gladkikh.app.fastcargo.common.type.None
import `fun`.gladkikh.app.fastcargo.common.type.flatMap
import `fun`.gladkikh.app.fastcargo.domain.entity.AccountEntity
import `fun`.gladkikh.app.fastcargo.domain.repository.AccountRepository
import javax.inject.Inject

class LoginAccountUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) : UseCase<None, String>() {
    override suspend fun run(params: String): Either<Failure, None> {
        return accountRepository.getAccountEntity().flatMap { account ->
            return@flatMap if (account.password == params) {
                Either.Right(None())
            } else {

                val accountEntity = AccountEntity(
                    user = null,
                    guid = null,
                    settings = null,
                    password = null
                )

                accountRepository.saveAccountEntity(accountEntity)
                    .flatMap {
                        accountRepository.login(params)
                    }.flatMap {
                        accountRepository.saveAccountEntity(it)
                    }
            }
        }
    }
}
