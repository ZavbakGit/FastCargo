package `fun`.gladkikh.app.fastcargo.remote

import `fun`.gladkikh.app.fastcargo.common.type.Either
import `fun`.gladkikh.app.fastcargo.common.type.Failure



import `fun`.gladkikh.app.fastcargo.remote.core.AutorithationUtil
import `fun`.gladkikh.app.fastcargo.remote.core.NetworkHandler
import `fun`.gladkikh.app.fastcargo.remote.core.Request
import `fun`.gladkikh.app.fastcargo.remote.entity.RequestEntity
import `fun`.gladkikh.app.fastcargo.remote.service.ServiceFactory

import android.content.Context


class RequestRemoteImpl1 constructor(
    context: Context,
    isDebug: Boolean,
    baseUrl: String
) : RequestRemote {

    private val request = Request(NetworkHandler(context))
    private val service = ServiceFactory(baseUrl).makeService(isDebug)

    override fun request(
        user: String,
        password: String,
        data: String
    ): Either<Failure, String> {
        val auth = AutorithationUtil.getStringAutorization(user, password)
        return request.make(service.getDataFromServer(auth, RequestEntity(data))) {
            it.data
        }
    }
}