package com.example.balvirjha.bankingkeyboard.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.klinker.android.emoji_keyboard_trial.R

/**
 * Created by balvirjha on 1/12/18.
 */
class IntroSinglePageViewSecond (context: Context) {
    private var context: Context? = context

    fun getView(): View {
        val layoutInflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val mroot = layoutInflater.inflate(R.layout.introscreen2, null)
        return mroot

    }
}