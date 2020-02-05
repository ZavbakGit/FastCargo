package `fun`.gladkikh.app.fastcargo.ui.testactivity

import `fun`.gladkikh.app.fastcargo.App
import `fun`.gladkikh.app.fastcargo.R
import `fun`.gladkikh.app.fastcargo.common.type.ErrorDescriptionFailure
import `fun`.gladkikh.app.fastcargo.common.type.Failure
import `fun`.gladkikh.app.fastcargo.common.ui.BaseActivity
import `fun`.gladkikh.app.fastcargo.common.ui.ext.onEvent
import `fun`.gladkikh.app.fastcargo.presentation.test.TestViewModel
import `fun`.gladkikh.app.fastcargo.ui.route.RouteActivity
import `fun`.gladkikh.app.fastcargo.ui.settings.SettingsActivity
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.test_activity.*

class TestActivity : BaseActivity() {
    override val contentId = R.layout.test_activity

    lateinit var viewModel: TestViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
        viewModel = viewModel {
            onEvent(getMessage(), ::handleMessage)
            onEvent(getError(), ::handleFailure)
            onEvent(getProgressData(),::handleProgress)
        }

        btOpenSettings.setOnClickListener {
            val i = Intent(baseContext, SettingsActivity::class.java)
            startActivity(i)
        }

        btReadSettings.setOnClickListener {
            viewModel.getSettings()
        }

        btSaveAccount.setOnClickListener {
            viewModel.saveAccount()
        }

        btGetAccount.setOnClickListener {
            viewModel.getAccount()
        }

        btSaveTestSettings.setOnClickListener {
            viewModel.saveTestSettings()
        }

        btTestRequestRemote.setOnClickListener {
            viewModel.testRemoteRequest()
        }

        btLogin1.setOnClickListener {
            viewModel.login1()
        }

        btLogin2.setOnClickListener {
            viewModel.login2()
        }

        btLogin3.setOnClickListener {
            viewModel.login3()
        }

        btCleanSetting.setOnClickListener {
            viewModel.clearSettings()
        }
        btCheckSetting.setOnClickListener {
            viewModel.checkSetting()
        }

        btRemoveAccount.setOnClickListener {
            viewModel.removeAccount()
        }

        btCheckAccount.setOnClickListener {
            viewModel.checkAccount()
        }

        btOpenRouteForm.setOnClickListener {
            val i = Intent(baseContext, RouteActivity::class.java)
            startActivity(i)
        }

    }

    private fun handleMessage(message: String?) {
        tvMessage.text = message + "\n\n" + tvMessage.text
    }

    private fun handleProgress(progress: Boolean?) {
        tvMessage.text = "Прогресс: $progress" + "\n\n" + tvMessage.text
    }

    private fun handleFailure(failure: Failure?) {
        when(failure){
            is ErrorDescriptionFailure ->{
                tvMessage.text = "fail: ${failure?.errorDescriptionEntity.description ?: ""}" + "\n\n" + tvMessage.text
            }
            else ->{
                tvMessage.text = "fail: ${failure?.message ?: ""}" + "\n\n" + tvMessage.text
            }
        }
    }
}
