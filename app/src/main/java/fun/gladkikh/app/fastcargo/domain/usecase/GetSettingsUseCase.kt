package `fun`.gladkikh.app.fastcargo.domain.usecase

import `fun`.gladkikh.app.fastcargo.common.type.Either
import `fun`.gladkikh.app.fastcargo.common.type.Failure
import `fun`.gladkikh.app.fastcargo.common.type.None
import `fun`.gladkikh.app.fastcargo.domain.entity.SettingsEntity
import `fun`.gladkikh.app.fastcargo.domain.repository.SettingsRepository
import `fun`.gladkikh.app.fastcargo.common.interactor.UseCase
import javax.inject.Inject

class GetSettingsUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository
) : UseCase<SettingsEntity, None>() {

    override suspend fun run(params: None): Either<Failure, SettingsEntity> {
        return settingsRepository.getSettings()
    }
}
