package `fun`.gladkikh.app.fastcargo.ui.print.old

import `fun`.gladkikh.app.fastcargo.App
import `fun`.gladkikh.app.fastcargo.R
import `fun`.gladkikh.app.fastcargo.common.ui.BaseActivity
import `fun`.gladkikh.app.fastcargo.common.ui.base
import `fun`.gladkikh.app.fastcargo.common.ui.ext.onEvent
import `fun`.gladkikh.app.fastcargo.presentation.printdialog.PrintDialogContractOld
import `fun`.gladkikh.app.fastcargo.presentation.printdialog.PrintDialogInteractorOld
import `fun`.gladkikh.app.fastcargo.presentation.printdialog.PrintDialogViewModelOld
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import javax.inject.Inject


class PrintDialogFragmentOld : DialogFragment(){
    companion object {
        const val TAG = "PrintDialog"

        fun newInstance(): PrintDialogFragmentOld {

            val dialog =
                PrintDialogFragmentOld()
            val args = Bundle().apply {
                //putString(EXTRA_TITLE, title)

            }
            dialog.arguments = args
            return dialog
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: PrintDialogViewModelOld
    private lateinit var interactor: PrintDialogInteractorOld

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)


        viewModel = viewModel {

        }

        interactor = viewModel {
            onEvent(getCommandEvent(),{command->
                when(command) {
                    is PrintDialogContractOld.Interactor.Command.ShowMessage -> {
                        viewModel.setMessage(command.message)
                    }
                    is PrintDialogContractOld.Interactor.Command.Close -> {
                        dismiss()
                    }
                }
            })

            setState(PrintDialogContractOld.Interactor.State.ALIVE)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.print_dialog, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }



    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState).apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
        }
    }

    override fun onResume() {
        super.onResume()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    inline fun base(block: BaseActivity.() -> Unit) {
        activity.base(block)
    }

    inline fun <reified T : ViewModel> viewModel(body: T.() -> Unit): T {
        val vm = ViewModelProviders.of(requireActivity(), viewModelFactory)[T::class.java]
        vm.body()
        return vm
    }
}