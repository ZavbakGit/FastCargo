package `fun`.gladkikh.app.fastcargo.remote.core


import `fun`.gladkikh.app.fastcargo.common.type.Either
import `fun`.gladkikh.app.fastcargo.common.type.Failure
import `fun`.gladkikh.app.fastcargo.remote.util.NetworkHandler
import com.google.gson.Gson
import retrofit2.Response
import javax.inject.Inject


class Request @Inject constructor(private val networkHandler: NetworkHandler, val gson: Gson) {

    fun <T : ResponseEntity, R> make(call: retrofit2.Call<T>, transform: (T) -> R): Either<Failure, R> {
        return when (networkHandler.isConnected) {
            true -> execute(call, transform)
            false, null -> Either.Left(NetworkConnectionError())
        }
    }

    private fun <T : ResponseEntity, R> execute(
        call: retrofit2.Call<T>,
        transform: (T) -> R
    ): Either<Failure, R> {
        return try {
            val response = call.execute()
            when (response.isSucceed()) {
                true -> Either.Right(transform((response.body()!!)))
                false -> Either.Left(response.parseError())
            }
        } catch (exception: Throwable) {
            Either.Left(ServerError(exception))
        }
    }

    fun <T : ResponseEntity> Response<T>.isSucceed(): Boolean {
        return isSuccessful && body() != null && (body() as ResponseEntity).success == 1
    }

    fun <T : ResponseEntity> Response<T>.parseError(): Failure {
        val message = (body() as ResponseEntity).error

        val error = gson.fromJson(message, ErrorDescriptionEntity::class.java)
        return ServerErrorByDescription(
            ErrorDescriptionEntity(
                code = error.code,
                description = error.description,
                extra = error.extra
            )
        )

    }
}





