package `fun`.gladkikh.app.fastcargo.common.ui

import `fun`.gladkikh.app.fastcargo.ui.common.Navigator
import `fun`.gladkikh.app.fastcargo.ui.common.OpenFormCommand
import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import javax.inject.Inject


abstract class BaseActivity : AppCompatActivity() {

    abstract val contentId: Int


    lateinit var navigator:Navigator

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigator = Navigator(this)
        setupContent()
    }

    open fun setupContent() {
        setContentView(contentId)
    }


    fun openForm(command: OpenFormCommand){
        navigator.openForm(command)
    }

    fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    inline fun <reified T : ViewModel> viewModel(body: T.() -> Unit): T {
        val vm = ViewModelProviders.of(this, viewModelFactory)[T::class.java]
        vm.body()
        return vm
    }


}

inline fun Activity?.base(block: BaseActivity.() -> Unit) {
    (this as? BaseActivity)?.let(block)
}



