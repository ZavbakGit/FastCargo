package `fun`.gladkikh.app.fastcargo.di

import `fun`.gladkikh.app.fastcargo.common.presentation.ViewModelFactory
import `fun`.gladkikh.app.fastcargo.presentation.LoginViewModel
import `fun`.gladkikh.app.fastcargo.presentation.MainViewModel
import `fun`.gladkikh.app.fastcargo.presentation.PrintViewModel
import `fun`.gladkikh.app.fastcargo.presentation.RouteViewModel
import `fun`.gladkikh.app.fastcargo.presentation.print.MainPrintViewModel
import `fun`.gladkikh.app.fastcargo.presentation.printdialog.PrintDialogInteractorOld
import `fun`.gladkikh.app.fastcargo.presentation.printdialog.PrintDialogViewModelOld
import `fun`.gladkikh.app.fastcargo.presentation.test.TestViewModel
import `fun`.gladkikh.app.fastcargo.ui.print.PrintMainFragment
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
    abstract fun bindTestViewModel(viewModel: TestViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(RouteViewModel::class)
    abstract fun bindRouteViewModel(viewModel: RouteViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PrintViewModel::class)
    abstract fun bindPrintViewModel(viewModel: PrintViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PrintDialogViewModelOld::class)
    abstract fun bindPrintDialogViewModel(viewModel: PrintDialogViewModelOld): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PrintDialogInteractorOld::class)
    abstract fun bindPrintDialogInteractor(viewModel: PrintDialogInteractorOld): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainPrintViewModel::class)
    abstract fun bindMainPrintViewModel(viewModel: MainPrintViewModel): ViewModel

}
