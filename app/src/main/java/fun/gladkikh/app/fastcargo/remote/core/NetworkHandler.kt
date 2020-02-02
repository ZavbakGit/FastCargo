package `fun`.gladkikh.app.fastcargo.remote.core

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo


/**
 * Injectable class which returns information about the network connection state.
 */

class NetworkHandler constructor(private val context: Context) {
    val isConnected get() = context.networkInfo?.isConnected
}

val Context.networkInfo: NetworkInfo? get() =
    (this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo
