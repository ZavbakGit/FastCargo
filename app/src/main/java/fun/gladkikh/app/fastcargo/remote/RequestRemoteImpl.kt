package `fun`.gladkikh.app.fastcargo.remote

import `fun`.gladkikh.app.fastcargo.common.type.Either
import `fun`.gladkikh.app.fastcargo.common.type.Failure
import `fun`.gladkikh.app.fastcargo.remote.core.Request
import `fun`.gladkikh.app.fastcargo.remote.core.RequestEntity
import `fun`.gladkikh.app.fastcargo.remote.service.ApiFactory
import `fun`.gladkikh.app.fastcargo.remote.util.AuthorizationUtil
import `fun`.gladkikh.app.fastpallet8.Constants


class RequestRemoteImpl constructor(
    private val request: Request
) : RequestRemote {
    override fun request(
        user: String,
        password: String,
        baseUrl: String,
        data: String
    ): Either<Failure, String> {

        try {
            //val serviceFactory = ServiceFactory(baseUrl)

            //val apiService = serviceFactory.makeService(Constants.IS_TEST_BUILD)
            val apiService = ApiFactory().getApi(baseUrl)

            val auth = AuthorizationUtil.getStringAutorization(user, password)
            val requestEntity = RequestEntity(
                Constants.UID ?: "",
                data
            )
            return request.make(apiService.getDataFromServer(auth, requestEntity)) {
                it.data
            }
        } catch (e: Exception) {
            return Either.Left(Failure(e.message))
        }
    }
}