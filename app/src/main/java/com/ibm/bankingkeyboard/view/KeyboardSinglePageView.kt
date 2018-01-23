package com.ibm.bankingkeyboard.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.BaseAdapter
import com.ibm.bankingkeyboard.TextKeyboardService
import com.klinker.android.emoji_keyboard_trial.R

class KeyboardSinglePageView {

    private var context: Context? = null
    private var adapter: BaseAdapter? = null
    private var recyclerViewAdapter: RecyclerViewAdapter? = null
    private var textKeyboardService: TextKeyboardService? = null

    val view: View
        get() {
            return (context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
                    .inflate(R.layout.keyboard_layout, null)
        }

    constructor(context: Context, adapter: BaseAdapter) {
        this.context = context
        this.adapter = adapter
    }

    constructor(context: Context, adapter: RecyclerViewAdapter) {
        this.context = context
        this.recyclerViewAdapter = adapter
        this.textKeyboardService = context as TextKeyboardService
    }

}