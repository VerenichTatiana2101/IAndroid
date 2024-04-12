package com.skillbox.examples.ui.main

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.skillbox.a07_fragments.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}
