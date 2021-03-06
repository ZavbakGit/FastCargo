package `fun`.gladkikh.app.fastcargo.ui.print

import `fun`.gladkikh.app.fastcargo.App
import `fun`.gladkikh.app.fastcargo.R
import `fun`.gladkikh.app.fastcargo.common.presentation.StateDialog
import `fun`.gladkikh.app.fastcargo.common.ui.BaseActivity
import `fun`.gladkikh.app.fastcargo.common.ui.base
import `fun`.gladkikh.app.fastcargo.common.ui.ext.onEvent
import `fun`.gladkikh.app.fastcargo.presentation.print.MainPrintViewModel
import `fun`.gladkikh.app.fastcargo.ui.print.old.PrintDialogFragmentOld
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
import kotlinx.android.synthetic.main.print_dialog.*
import javax.inject.Inject

class PrintDialogFragment : DialogFragment() {
    companion object {
        const val TAG = "PrintDialog"

        fun newInstance(): PrintDialogFragment {

            val dialog =
                PrintDialogFragment()
            val args = Bundle().apply {
                //putString(EXTRA_TITLE, title)

            }
            dialog.arguments = args
            return dialog
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: MainPrintViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)

        viewModel = viewModel {
            onEvent(getdialogBarcode(), {
                tvBarcode.text = it ?: "Пусто"
            }

            )

            setStateDialog(StateDialog.Open)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.print_dialog, container, false)
    }

    override fun onStop() {
        super.onStop()
        viewModel.setStateDialog(StateDialog.Close)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btSubmit.setOnClickListener {
            viewModel.setCount(editText.text.toString().toIntOrNull()?:0)
            dismiss()
        }
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