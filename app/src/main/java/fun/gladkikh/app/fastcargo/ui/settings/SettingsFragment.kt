package `fun`.gladkikh.app.fastcargo.ui.settings

import `fun`.gladkikh.app.fastcargo.App
import `fun`.gladkikh.app.fastcargo.R
import `fun`.gladkikh.app.fastcargo.common.MyLog
import `fun`.gladkikh.app.fastcargo.preferences.SharedPrefsManager
import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import javax.inject.Inject


class SettingsFragment : PreferenceFragmentCompat(),
    SharedPreferences.OnSharedPreferenceChangeListener {

    @Inject
    lateinit var sharedPrefsManager: SharedPrefsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
    }

    override fun onResume() {
        super.onResume()
        preferenceScreen.sharedPreferences
            .registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        preferenceScreen.sharedPreferences
            .unregisterOnSharedPreferenceChangeListener(this)
    }


    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if (key == SharedPrefsManager.HOST) {
            sharedPrefsManager.getSettings().either({
                MyLog.logE(it.message)
            }, {
                MyLog.logD(it.host)
                App.initRequestRemote(it.host ?: "")
            })

        }
    }

}