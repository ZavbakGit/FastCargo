package `fun`.gladkikh.app.fastcargo

import `fun`.gladkikh.app.fastcargo.di.AppModule
import `fun`.gladkikh.app.fastcargo.di.ServicesModule
import `fun`.gladkikh.app.fastcargo.di.ViewModelModule
import `fun`.gladkikh.app.fastcargo.remote.RequestRemote
import `fun`.gladkikh.app.fastcargo.remote.RequestRemoteImpl
import `fun`.gladkikh.app.fastcargo.ui.login.LoginActivity
import `fun`.gladkikh.app.fastcargo.ui.main.MainActivity
import `fun`.gladkikh.app.fastcargo.ui.route.RouteActivity
import `fun`.gladkikh.app.fastcargo.ui.settings.SettingsFragment
import `fun`.gladkikh.app.fastcargo.ui.testactivity.TestActivity
import android.app.Application
import android.content.Context
import dagger.Component
import javax.inject.Singleton

class App : Application() {

    init {
        instance = this
    }

    companion object {
        lateinit var appComponent: AppComponent
        var instance: App? = null
        var requestRemote: RequestRemote? = null
        fun appContext(): Context? = instance?.applicationContext

        fun initRequestRemote(baseUrl:String){
            requestRemote = RequestRemoteImpl(instance!!, Constants.IS_TEST_BUILD,baseUrl)
        }
    }

    override fun onCreate() {
        super.onCreate()
        initAppComponent()
    }



    private fun initAppComponent() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

    }
}


@Singleton
@Component(
    modules = [AppModule::class, ServicesModule::class, ViewModelModule::class]
)
interface AppComponent {
    fun inject(mainActivity: TestActivity)
    fun inject(mainActivity: SettingsFragment)
    fun inject(routeActivity: RouteActivity)
    fun inject(loginActivity: LoginActivity)
    fun inject(mainActivity: MainActivity)
}

