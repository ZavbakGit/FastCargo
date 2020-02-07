package `fun`.gladkikh.app.fastcargo.ui.print

import `fun`.gladkikh.app.fastcargo.App
import `fun`.gladkikh.app.fastcargo.R
import `fun`.gladkikh.app.fastcargo.common.ui.BaseActivity
import `fun`.gladkikh.app.fastcargo.common.ui.base
import `fun`.gladkikh.app.fastcargo.common.ui.ext.onEvent
import `fun`.gladkikh.app.fastcargo.presentation.printdialog.PrintDialogInteractor
import `fun`.gladkikh.app.fastcargo.presentation.printdialog.PrintDialogViewModel
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
import com.google.gson.Gson
import kotlinx.android.synthetic.main.main_print_fragment.*
import kotlinx.android.synthetic.main.print_dialog.*
import javax.inject.Inject


class PrintDialogFragment : DialogFragment(){
    companion object {
        const val TAG = "PrintDialog"

        fun newInstance(): PrintDialogFragment {

            val dialog = PrintDialogFragment()
            val args = Bundle().apply {
                //putString(EXTRA_TITLE, title)

            }
            dialog.arguments = args
            return dialog
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var gson: Gson

    private lateinit var viewModel: PrintDialogViewModel
    private lateinit var printDialogInteractor: PrintDialogInteractor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)


        viewModel = viewModel {

        }

        printDialogInteractor = viewModel {
            onEvent(getCountEvent(),{
                tvProduct.text = "${it?:"Пусто"}"
            })
            onEvent(date,{
                tvBarcode.text = "${it.toString()}"
            })
        }

        printDialogInteractor.setCount(5)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.print_dialog, container, false)
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
        val vm = ViewModelProviders.of(this, viewModelFactory)[T::class.java]
        vm.body()
        return vm
    }
}