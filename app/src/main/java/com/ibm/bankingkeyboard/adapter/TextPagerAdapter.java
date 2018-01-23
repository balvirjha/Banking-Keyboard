package com.ibm.bankingkeyboard.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.ibm.bankingkeyboard.TextKeyboardService;
import com.ibm.bankingkeyboard.constants.UTFTexts;
import com.ibm.bankingkeyboard.view.KeyboardSinglePageView;
import com.ibm.bankingkeyboard.view.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class TextPagerAdapter extends PagerAdapter {

    private final String[] TITLES = {"recent"/*,
                                    "people",
                                    "things",
                                    "nature",
                                    "places",
                                    "symbols" */};

    private ViewPager pager;
    private ArrayList<View> pages;
    private int keyboardHeight;
    private TextKeyboardService textKeyboardService;

    public TextPagerAdapter(Context context, ViewPager pager, int keyboardHeight) {
        super();
        textKeyboardService = (TextKeyboardService) context;
        this.pager = pager;
        this.keyboardHeight = keyboardHeight;
        this.pages = new ArrayList<View>();

        ArrayList list1 = new ArrayList(Arrays.asList(UTFTexts.numberTexts));
        list1.addAll(Arrays.asList(UTFTexts.capsAlphabetsTexts));
        Object[] objects = list1.toArray();
        pages.add(new KeyboardSinglePageView(context,
                new RecyclerViewAdapter(context,
                        Arrays.copyOf(objects, objects.length, String[].class))).getView());
    }

    public void setHeight(int height) {
        pager.getLayoutParams().height = height;
        pager.invalidate();
        pager.requestFocus();
    }

    @Override
    public View instantiateItem(ViewGroup container, int position) {
        pager.addView(pages.get(position), position, keyboardHeight);
        return pages.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        pager.removeView(pages.get(position));
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }

    @Override
    public int getCount() {
        return TITLES.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

}
