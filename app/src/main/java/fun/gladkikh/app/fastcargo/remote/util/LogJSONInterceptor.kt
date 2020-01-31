package `fun`.gladkikh.app.fastcargo.remote.util

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener

class LogJSONInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val response = chain.proceed(request)
        val rawJson = response.body()?.string()

        try {
            val obj = JSONTokener(rawJson).nextValue()
            val jsonLog = if (obj is JSONObject)
                obj.toString(4)
            else
                (obj as JSONArray).toString(4)
            Log.d("jsonLog", jsonLog)
        } catch (e: JSONException) {
            e.printStackTrace()
        }


        // Re-create the response before returning it because body can be read only once
        return response.newBuilder()
            .body(ResponseBody.create(response.body()?.contentType(), rawJson ?: "")).build()
    }
}