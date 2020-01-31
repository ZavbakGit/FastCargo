package `fun`.gladkikh.app.fastcargo.common.ui.ext


import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer


fun <T : Any, L : LiveData<T>> LifecycleOwner.onEvent(liveData: L, body: (T?) -> Unit) =
    liveData.observe(this, Observer(body))
