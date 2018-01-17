package com.example.balvirjha.bankingkeyboard.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.View
import android.view.ViewGroup
import com.example.balvirjha.bankingkeyboard.view.IntroSinglePageView
import com.example.balvirjha.bankingkeyboard.view.IntroSinglePageViewSecond
import com.example.balvirjha.bankingkeyboard.view.IntroSinglePageViewThird
import java.util.*

/**
 * Created by BalvirJha on 11-01-2018.
 */
class IntroPagerAdapter(context: Context, pager: ViewPager, keyboardHeight: Int) : PagerAdapter() {
    private val TITLES = arrayOf("firstscreen",
            "secondscreen",
            "thirdscreen")

    private var pager: ViewPager? = null
    private var pages: ArrayList<View>? = null
    private var keyboardHeight: Int = 0

    init {
        this.keyboardHeight = keyboardHeight
        this.pager = pager
        this.pages = ArrayList()
        pages?.add(IntroSinglePageView(context).getView())
        pages?.add(IntroSinglePageViewSecond(context).getView())
        pages?.add(IntroSinglePageViewThird(context).getView())

    }

    override fun instantiateItem(container: ViewGroup, position: Int): View? {
        pager?.addView(pages?.get(position), position, keyboardHeight)
        return pages?.get(position)
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        pager?.removeView(pages?.get(position))
    }

    override fun getPageTitle(position: Int): CharSequence {
        return TITLES[position]
    }

    override fun getCount(): Int {
        return TITLES.size
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view === obj
    }

    fun setHeight(height: Int) {
        pager?.getLayoutParams()?.height = height
        pager?.invalidate()
        pager?.requestFocus()
    }
}