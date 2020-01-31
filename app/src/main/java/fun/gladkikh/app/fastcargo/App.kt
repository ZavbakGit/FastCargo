package `fun`.gladkikh.app.fastcargo

import `fun`.gladkikh.app.fastcargo.di.AppModule
import `fun`.gladkikh.app.fastcargo.di.ServicesModule
import `fun`.gladkikh.app.fastcargo.di.ViewModelModule
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
        fun appContext(): Context? = instance?.applicationContext
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
}

