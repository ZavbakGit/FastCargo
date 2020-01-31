package `fun`.gladkikh.app.fastcargo.domain.usecase

import `fun`.gladkikh.app.fastcargo.common.type.Either
import `fun`.gladkikh.app.fastcargo.common.type.Failure
import `fun`.gladkikh.app.fastcargo.domain.repository.AccountRepository
import `fun`.gladkikh.app.fastcargo.common.interactor.UseCase
import javax.inject.Inject

class LoginAccountUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) : UseCase<String, String>() {
    override suspend fun run(params: String): Either<Failure, String> {
        return accountRepository.login(params)
    }
}
