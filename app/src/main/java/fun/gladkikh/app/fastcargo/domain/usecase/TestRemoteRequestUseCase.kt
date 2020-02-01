package `fun`.gladkikh.app.fastcargo.domain.usecase

import `fun`.gladkikh.app.fastcargo.common.type.Either
import `fun`.gladkikh.app.fastcargo.common.type.Failure
import `fun`.gladkikh.app.fastcargo.domain.repository.AccountRepository
import `fun`.gladkikh.app.fastcargo.common.interactor.UseCase
import `fun`.gladkikh.app.fastcargo.common.type.None
import javax.inject.Inject

class TestRemoteRequestUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) : UseCase<String, None>() {
    override suspend fun run(params: None): Either<Failure, String> {
        return accountRepository.testRemoteRequest()
    }
}
