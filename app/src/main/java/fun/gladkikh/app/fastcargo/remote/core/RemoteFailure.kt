package `fun`.gladkikh.app.fastcargo.remote.core

import `fun`.gladkikh.app.fastcargo.common.type.Failure

class ServerError(exception: Throwable): Failure("Сетевая ошибка!")
class NetworkConnectionError:Failure("Нет сети")
class ServerErrorByDescription(val remote: ErrorDescriptionEntity):Failure("Ошибка на сервере!")