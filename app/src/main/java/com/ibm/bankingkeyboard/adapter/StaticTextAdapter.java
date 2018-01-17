package com.ibm.bankingkeyboard.adapter;

import android.content.Context;

import com.ibm.bankingkeyboard.TextKeyboardService;

import java.util.ArrayList;
import java.util.Arrays;

public class StaticTextAdapter extends BaseTextAdapter {

    public StaticTextAdapter(Context context, String[] emojiTextsAsStrings, ArrayList<Integer> iconIds) {
        super((TextKeyboardService) context);
        this.emojiTexts = new ArrayList<String>(Arrays.asList(emojiTextsAsStrings));
        this.iconIds = iconIds;
    }
}