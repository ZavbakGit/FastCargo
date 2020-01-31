package `fun`.gladkikh.app.fastcargo.common.interactor

import `fun`.gladkikh.app.fastcargo.common.type.Either
import `fun`.gladkikh.app.fastcargo.common.type.Failure
import kotlinx.coroutines.*

/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This abstraction represents an execution unit for different use cases (this means than any use
 * case in the application should implement this contract).
 *
 * By convention each [UseCase] implementation will execute its job in a background thread
 * (kotlin coroutine) and will post the result in the UI thread.
 */

abstract class UseCase<out Type, in Params> {

    abstract suspend fun run(params: Params): Either<Failure, Type>

    operator fun invoke(
        params: Params, coroutineScope: CoroutineScope,
        onResult: (Either<Failure, Type>) -> Unit = {}
    ): Job {
        return coroutineScope.launch {
            val result = withContext(Dispatchers.IO) {
                run(params)
            }
            onResult(result)
        }
    }
}
