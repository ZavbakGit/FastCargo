package `fun`.gladkikh.app.fastcargo.domain.usecase

import `fun`.gladkikh.app.fastcargo.common.interactor.UseCase
import `fun`.gladkikh.app.fastcargo.common.type.*
import `fun`.gladkikh.app.fastcargo.domain.repository.SettingsRepository
import javax.inject.Inject

class CheckSettingsUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository
) : UseCase<None, None>() {
    override suspend fun run(params: None): Either<Failure, None> {
        return settingsRepository.getSettings().flatMap {
            if (it.host.isNullOrBlank() || it.login1C.isNullOrBlank()
                || it.password1C.isNullOrBlank()){
                return@flatMap Either.Left(SettingsFailure())
            }else{
                return@flatMap Either.Right(None())
            }
        }
    }
}
