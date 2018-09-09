package com.example.AndroidBombSample.ui

import android.os.Bundle
import android.support.v7.preference.Preference
import android.support.v7.preference.PreferenceFragmentCompat
import android.support.v7.preference.PreferenceManager
import com.example.AndroidBombSample.R

class PrefFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.pref_general, rootKey)

        findPreference("pref_loadcount").bindValueToSummury()
    }

    private val preferenceChangeListener = Preference.OnPreferenceChangeListener { preference, any ->
        preference.summary = "$any"
        true
    }

    private fun Preference.bindValueToSummury() {
        this.onPreferenceChangeListener = preferenceChangeListener
        preferenceChangeListener.onPreferenceChange(this, PreferenceManager
                .getDefaultSharedPreferences(this.context)
                .getString(this.key, "")
        )
    }
}