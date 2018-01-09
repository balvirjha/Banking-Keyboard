package com.ibm.bankingkeyboard.adapter;

import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ibm.bankingkeyboard.TextKeyboardService;
import com.klinker.android.emoji_keyboard_trial.R;

import java.util.ArrayList;

public abstract class BaseTextAdapter extends BaseAdapter {

    protected TextKeyboardService textKeyboardService;
    protected ArrayList<String> emojiTexts;
    protected ArrayList<Integer> iconIds;

    public BaseTextAdapter(TextKeyboardService textKeyboardService) {
        this.textKeyboardService = textKeyboardService;
    }

    @Override
    public int getCount() {
        return emojiTexts.size();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final TextView textView;
        if (convertView == null) {
            textView = new TextView(textKeyboardService);
            int scale = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, textKeyboardService.getResources().getDisplayMetrics());
            textView.setPadding(scale, (int) (scale * 1.2), scale, (int) (scale * 1.2));
            textView.setTextColor(ContextCompat.getColor(textKeyboardService, R.color.textColor));
            textView.setGravity(Gravity.CENTER);
            textView.setBackgroundResource(R.drawable.rectdraw);
        } else {
            textView = (TextView) convertView;
        }
        if (emojiTexts != null) {
            textView.setText(emojiTexts.get(position));
        }
        //textView.setBackgroundResource(R.drawable.btn_background);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textKeyboardService.sendText(emojiTexts.get(position));
            }
        });

        return textView;
    }

    @Override
    public Object getItem(int arg0) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}
