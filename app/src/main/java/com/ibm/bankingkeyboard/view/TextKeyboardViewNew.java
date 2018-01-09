package com.ibm.bankingkeyboard.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ibm.bankingkeyboard.TextKeyboardService;
import com.ibm.bankingkeyboard.adapter.TextPagerAdapter;
import com.klinker.android.emoji_keyboard_trial.R;

/**
 * Created by BalvirJha on 06-01-2018.
 */

public class TextKeyboardViewNew extends View implements SharedPreferences.OnSharedPreferenceChangeListener {

    private ViewPager viewPager;
    private EditText pagerSlidingTabStrip;
    private RelativeLayout layout;

    private TextPagerAdapter textPagerAdapter;
    private TextKeyboardService textKeyboardService;
    private int width;
    private int height;

    public TextKeyboardViewNew(Context context) {
        super(context);
        initialize(context);
    }

    public TextKeyboardViewNew(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    public TextKeyboardViewNew(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context);
    }

    private void initialize(final Context context) {

        textKeyboardService = (TextKeyboardService) context;

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        layout = (RelativeLayout) inflater.inflate(R.layout.keyboard_main, null);

        viewPager = (ViewPager) layout.findViewById(R.id.emojiKeyboard);

        pagerSlidingTabStrip = (EditText) layout.findViewById(R.id.emojiCategorytab);
        pagerSlidingTabStrip.clearFocus();
        pagerSlidingTabStrip.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show();
                pagerSlidingTabStrip.requestFocus();
            }
        });
        pagerSlidingTabStrip.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                Toast.makeText(context, "Focus changes", Toast.LENGTH_SHORT).show();
                textPagerAdapter.setHeight(800);
            }
        });

        //pagerSlidingTabStrip.setIndicatorColor(getResources().getColor(R.color.pager_color));

        textPagerAdapter = new TextPagerAdapter(context, viewPager, height);

        viewPager.setAdapter(textPagerAdapter);

        setupDeleteButton();

        //pagerSlidingTabStrip.setViewPager(viewPager);

        viewPager.setCurrentItem(1);

        PreferenceManager.getDefaultSharedPreferences(context).registerOnSharedPreferenceChangeListener(this);
    }

    public View getView() {
        return layout;
    }

    public void notifyDataSetChanged() {
        textPagerAdapter.notifyDataSetChanged();
        viewPager.refreshDrawableState();
    }

    private void setupDeleteButton() {

        Button delete = (Button) layout.findViewById(R.id.deleteButton);

        delete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                textKeyboardService.sendDownAndUpKeyEvent(KeyEvent.KEYCODE_DEL, 0);
            }
        });

        delete.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                textKeyboardService.switchToPreviousInputMethod();
                return false;
            }
        });
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);

        Log.d("emojiKeyboardView", width + " : " + height);
        setMeasuredDimension(width, height);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        Log.d("sharedPreferenceChange", "function called on change of shared preferences with key " + key);
        if (key.equals("icon_set")) {
            textPagerAdapter = new TextPagerAdapter(getContext(), viewPager, height);
            viewPager.setAdapter(textPagerAdapter);
            this.invalidate();
        }
    }
}

