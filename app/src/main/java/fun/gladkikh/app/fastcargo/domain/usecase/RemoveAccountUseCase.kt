package `fun`.gladkikh.app.fastcargo.domain.usecase

import `fun`.gladkikh.app.fastcargo.common.interactor.UseCase
import `fun`.gladkikh.app.fastcargo.common.type.*
import `fun`.gladkikh.app.fastcargo.domain.entity.AccountEntity
import `fun`.gladkikh.app.fastcargo.domain.repository.AccountRepository
import javax.inject.Inject

class RemoveAccountUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) : UseCase<None, None>() {
    override suspend fun run(params: None): Either<Failure, None> {

        val accountEntity = AccountEntity(
            user = null,
            guid = null,
            settings = null,
            password = null
        )

        return accountRepository.saveAccountEntity(accountEntity)
    }
}
