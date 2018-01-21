package com.ibm.bankingkeyboard.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Build;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.balvirjha.bankingkeyboard.adapter.IntroPagerAdapter;
import com.ibm.bankingkeyboard.TextKeyboardService;
import com.ibm.bankingkeyboard.adapter.TextPagerAdapter;
import com.klinker.android.emoji_keyboard_trial.R;

import java.util.ArrayList;

/**
 * Created by BalvirJha on 06-01-2018.
 */

public class TextKeyboardViewNew extends View implements SharedPreferences.OnSharedPreferenceChangeListener {

    private ViewPager viewPager, introPager;
    private EditText pagerSlidingTabStrip;
    private RelativeLayout layout;

    private TextPagerAdapter textPagerAdapter;
    private IntroPagerAdapter introPagerAdapter;
    private TextKeyboardService textKeyboardService;
    private int width;
    private int height;
    private View btn_finish, btn_next, btn_last, introLayout;
    private TextView skipOrDone;
    private ListView mobile_list;
    ArrayList<String> StoreContacts;
    ArrayAdapter<String> arrayAdapter;
    Cursor cursor;
    String name, phonenumber;
    View topLayout, intropagesLayout;

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

    public void GetContactsIntoArrayList() {

        cursor = textKeyboardService.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);

        while (cursor.moveToNext()) {

            name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

            phonenumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            StoreContacts.add(name + " " + ":" + " " + phonenumber);
        }

        cursor.close();

    }

    private void initialize(final Context context) {

        textKeyboardService = (TextKeyboardService) context;

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        layout = (RelativeLayout) inflater.inflate(R.layout.keyboard_main, null);
        mobile_list = (ListView) layout.findViewById(R.id.mobile_list);
        topLayout = (View) layout.findViewById(R.id.topLayout);
        intropagesLayout = (View) layout.findViewById(R.id.intropagesLayout);
        StoreContacts = new ArrayList<>();
        GetContactsIntoArrayList();
        if (StoreContacts != null) {
            arrayAdapter = new ArrayAdapter<String>(
                    context,
                    android.R.layout.simple_list_item_1, android.R.id.text1, StoreContacts);

            mobile_list.setAdapter(arrayAdapter);
        } else {

        }
        viewPager = (ViewPager) layout.findViewById(R.id.emojiKeyboard);
        introPager = (ViewPager) layout.findViewById(R.id.intropages);

        pagerSlidingTabStrip = (EditText) layout.findViewById(R.id.emojiCategorytab);
        btn_finish = (View) layout.findViewById(R.id.btn_finish);
        btn_last = (View) layout.findViewById(R.id.btn_last);
        btn_next = (View) layout.findViewById(R.id.btn_next);
        skipOrDone = (TextView) layout.findViewById(R.id.skipOrDone);
        intropagesLayout = (View) layout.findViewById(R.id.intropagesLayout);
        skipOrDone.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                intropagesLayout.setVisibility(GONE);
            }
        });
        pagerSlidingTabStrip = (EditText) layout.findViewById(R.id.emojiCategorytab);
        pagerSlidingTabStrip = (EditText) layout.findViewById(R.id.emojiCategorytab);
        pagerSlidingTabStrip.clearFocus();
        pagerSlidingTabStrip.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show();
                pagerSlidingTabStrip.requestFocus();
                viewPager.setVisibility(VISIBLE);
                introPager.setVisibility(VISIBLE);
                if (intropagesLayout != null) {
                    intropagesLayout.setVisibility(VISIBLE);
                }
            }
        });
        pagerSlidingTabStrip.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                Toast.makeText(context, "Focus changes", Toast.LENGTH_SHORT).show();
                //textPagerAdapter.setHeight(800);
                //introPagerAdapter.setHeight(850);
            }
        });

        //pagerSlidingTabStrip.setIndicatorColor(getResources().getColor(R.color.pager_color));

        textPagerAdapter = new TextPagerAdapter(context, viewPager, height);
        introPagerAdapter = new IntroPagerAdapter(context, introPager, height);

        viewPager.setAdapter(textPagerAdapter);
        introPager.setAdapter(introPagerAdapter);

        introPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onPageSelected(int position) {
                Toast.makeText(context, "Position: " + position, Toast.LENGTH_SHORT).show();
                if (position == 0) {
                    btn_finish.setBackground(context.getResources().getDrawable(R.drawable.selecteditem_dot));
                    btn_next.setBackground(context.getResources().getDrawable(R.drawable.nonselecteditem_dot));
                    btn_last.setBackground(context.getResources().getDrawable(R.drawable.nonselecteditem_dot));
                    skipOrDone.setText("SKIP");
                } else if (position == 1) {
                    btn_finish.setBackground(context.getResources().getDrawable(R.drawable.nonselecteditem_dot));
                    btn_next.setBackground(context.getResources().getDrawable(R.drawable.selecteditem_dot));
                    btn_last.setBackground(context.getResources().getDrawable(R.drawable.nonselecteditem_dot));
                    skipOrDone.setText("SKIP");

                } else if (position == 2) {
                    btn_finish.setBackground(context.getResources().getDrawable(R.drawable.nonselecteditem_dot));
                    btn_next.setBackground(context.getResources().getDrawable(R.drawable.nonselecteditem_dot));
                    btn_last.setBackground(context.getResources().getDrawable(R.drawable.selecteditem_dot));
                    skipOrDone.setText("Done");
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        setupDeleteButton();

        //pagerSlidingTabStrip.setViewPager(viewPager);

        viewPager.setCurrentItem(1);
        introPager.setCurrentItem(0);
        viewPager.setVisibility(GONE);
        introPager.setVisibility(GONE);
        //intropagesLayout.setVisibility(GONE);

        mobile_list.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                viewPager.setVisibility(GONE);
                introPager.setVisibility(GONE);
                intropagesLayout.setVisibility(GONE);
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {

            }
        });
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

