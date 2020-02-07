package `fun`.gladkikh.app.fastcargo.ui.print

import `fun`.gladkikh.app.fastcargo.R
import `fun`.gladkikh.app.fastcargo.common.ui.BaseActivity
import `fun`.gladkikh.app.fastcargo.ui.navigation.NavigateHandler
import android.os.Bundle
import androidx.navigation.Navigation

class PrintActivity :BaseActivity(){
    override val contentId = R.layout.print_activity

    lateinit var navigateHandler: NavigateHandler
        private set


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigateHandler =
            NavigateHandler(Navigation.findNavController(this, R.id.nav_host_fragment))
    }
}