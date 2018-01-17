package com.example.balvirjha.bankingkeyboard.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.klinker.android.emoji_keyboard_trial.R

/**
 * Created by BalvirJha on 11-01-2018.
 */
class IntroSinglePageView(context: Context) {
    private var context: Context? = context

    fun getView(): View {
        val layoutInflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val mroot = layoutInflater.inflate(R.layout.introscreen1, null)
        return mroot

    }
}