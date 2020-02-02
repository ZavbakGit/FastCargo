package `fun`.gladkikh.app.fastcargo.common.type

import `fun`.gladkikh.app.fastcargo.remote.core.ErrorDescriptionEntity

/**
 * Base Class for handling errors/failures/exceptions.
 */

open class Failure(var message:String? = null)
open class NetworkConnectionFailure:Failure("Ошибка соеденения!")
open class ErrorDescriptionFailure(val errorDescriptionEntity: ErrorDescriptionEntity)
    :Failure(errorDescriptionEntity.toString())

open class SettingsFailure:Failure("Ошибка заполнения настроек!")