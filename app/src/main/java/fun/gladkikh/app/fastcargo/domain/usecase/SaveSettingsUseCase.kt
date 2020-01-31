package `fun`.gladkikh.app.fastcargo.domain.usecase

import `fun`.gladkikh.app.fastcargo.common.type.Either
import `fun`.gladkikh.app.fastcargo.common.type.Failure
import `fun`.gladkikh.app.fastcargo.common.type.None
import `fun`.gladkikh.app.fastcargo.domain.entity.SettingsEntity
import `fun`.gladkikh.app.fastcargo.domain.repository.SettingsRepository
import `fun`.gladkikh.app.fastcargo.common.interactor.UseCase
import javax.inject.Inject

class SaveSettingsUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository
) : UseCase<None, SettingsEntity>() {

    override suspend fun run(params: SettingsEntity): Either<Failure, None> {
        return settingsRepository.saveSettings(params)
    }
}
