package `fun`.gladkikh.app.fastcargo

import `fun`.gladkikh.app.fastcargo.remote.RequestRemoteImpl
import `fun`.gladkikh.app.fastcargo.remote.core.Request
import `fun`.gladkikh.app.fastcargo.remote.util.NetworkHandler
import `fun`.gladkikh.app.fastcargo.remote1.RequestRemoteImpl1
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.google.gson.GsonBuilder
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun request() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

        val requreImpl = RequestRemoteImpl1(
            appContext, true, "http://172.31.255.150//UT/hs/api/",
            Date()
        )


        val either = requreImpl.request("Админ", "123", "Hi")

        either.either({
            println(it)
        }, {
            println(
                it
            )
        })
        assertEquals("fun.gladkikh.app.fastcargo", appContext.packageName)
    }


//    @Test
//    fun useAppContext() {
//        // Context of the app under test.
//        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
//
//        val networkHandler = NetworkHandler(appContext)
//
//        val request = Request(networkHandler, GsonBuilder().create())
//
//        val requestRemoteImpl = RequestRemoteImpl(request)
//
//
//        val either = requestRemoteImpl.request(
//            "Админ",
//            "111",
//            "http://172.31.255.150//UT/hs/api/",
//            "Hi"
//        )
//
//        either.either({
//            println(it)
//        }, {
//            println(
//                it
//            )
//        })
//        assertEquals("fun.gladkikh.app.fastcargo", appContext.packageName)
//    }
}
