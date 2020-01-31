package `fun`.gladkikh.app.fastcargo.remote


import `fun`.gladkikh.app.fastcargo.common.type.Either
import `fun`.gladkikh.app.fastcargo.common.type.Failure



interface RequestRemote {
    fun request(
        user: String,
        password: String,
        baseUrl: String,
        data: String
    ): Either<Failure, String>
}