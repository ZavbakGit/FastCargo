package `fun`.gladkikh.app.fastcargo.ui.print

import `fun`.gladkikh.app.fastcargo.App
import `fun`.gladkikh.app.fastcargo.R
import `fun`.gladkikh.app.fastcargo.common.ui.BaseFragment
import `fun`.gladkikh.app.fastcargo.common.ui.ext.onEvent
import `fun`.gladkikh.app.fastcargo.presentation.print.MainPrintViewModel
import `fun`.gladkikh.app.fastcargo.ui.print.old.PrintDialogFragmentOld
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.main_print_fragment.*

class PrintMainFragment : BaseFragment() {
    override val layoutId = R.layout.main_print_fragment
    private lateinit var viewModel: MainPrintViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)

        viewModel = viewModel {
            onEvent(getStartDialog(), {
                //Старт диалога
                PrintDialogFragment.newInstance()
                    .show(
                        activity!!.supportFragmentManager,
                        PrintDialogFragment.TAG
                    )
            })

            onEvent(getdialogCount(), {
                base {
                    showMessage(it.toString())
                }
            })
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btShowDialog.setOnClickListener {
            viewModel.setBarcode((0..100).random().toString())
        }
    }


}