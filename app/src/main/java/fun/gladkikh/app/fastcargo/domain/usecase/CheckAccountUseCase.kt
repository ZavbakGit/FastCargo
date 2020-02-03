package `fun`.gladkikh.app.fastcargo.domain.usecase

import `fun`.gladkikh.app.fastcargo.common.interactor.UseCase
import `fun`.gladkikh.app.fastcargo.common.type.*
import `fun`.gladkikh.app.fastcargo.domain.repository.AccountRepository
import javax.inject.Inject

class CheckAccountUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) : UseCase<None, None>() {
    override suspend fun run(params: None): Either<Failure, None> {
        return accountRepository.getAccountEntity().flatMap {
            if (it.password.isNullOrBlank()) {
                return@flatMap Either.Left(AccountFailure())
            } else {
                return@flatMap Either.Right(None())
            }
        }
    }
}
