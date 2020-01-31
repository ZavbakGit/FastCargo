package `fun`.gladkikh.app.fastcargo.di

import `fun`.gladkikh.app.fastcargo.common.presentation.ViewModelFactory
import `fun`.gladkikh.app.fastcargo.presentation.test.TestViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(TestViewModel::class)
    abstract fun bindMainViewModel(viewModel: TestViewModel): ViewModel
}
