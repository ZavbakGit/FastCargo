package `fun`.gladkikh.app.fastcargo.ui.print

import `fun`.gladkikh.app.fastcargo.App
import `fun`.gladkikh.app.fastcargo.R
import `fun`.gladkikh.app.fastcargo.common.ui.BaseFragment
import `fun`.gladkikh.app.fastcargo.common.ui.ext.onEvent
import `fun`.gladkikh.app.fastcargo.presentation.PrintViewModel
import `fun`.gladkikh.app.fastcargo.presentation.printdialog.PrintDialogInteractor
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.main_print_fragment.*

class MainPrintFragment : BaseFragment() {
    override val layoutId = R.layout.main_print_fragment

    private lateinit var viewModel: PrintViewModel
    private lateinit var printDialogInteractor: PrintDialogInteractor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)

        printDialogInteractor = viewModel {
            onEvent(date,{
                tvMessage.text = "${it.toString()}"
            })
        }


        viewModel = viewModel {

        }



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btShowDialog.setOnClickListener {
            printDialogInteractor.setCount(10)
            PrintDialogFragment.newInstance().show(
                activity!!.supportFragmentManager, PrintDialogFragment.TAG
            )
        }
    }


}