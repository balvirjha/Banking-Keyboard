package com.ibm.bankingkeyboard.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ibm.bankingkeyboard.TextKeyboardService;
import com.klinker.android.emoji_keyboard_trial.R;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by BalvirJha on 07-01-2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    String[] values;
    Context context1;
    ArrayList texts;
    private TextKeyboardService textKeyboardService;

    public RecyclerViewAdapter(Context context2, String[] values2) {
        values = values2;
        context1 = context2;
        textKeyboardService = (TextKeyboardService) context2;
        this.texts = new ArrayList<String>(Arrays.asList(values));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;

        public ViewHolder(View v) {
            super(v);
            textView = (TextView) v.findViewById(R.id.textview1);
        }
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view1 = LayoutInflater.from(context1).inflate(R.layout.recycler_view_items, parent, false);

        ViewHolder viewHolder1 = new ViewHolder(view1);

        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(ViewHolder Vholder, final int position) {

        Vholder.textView.setText(texts.get(position).toString());
        Vholder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textKeyboardService.sendText(texts.get(position).toString());
            }
        });
        /*if (position == 20) {
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                    45,
                    75
            );
            params.setMargins(40, 2, 2, 2);
            Vholder.textView.setLayoutParams(params);
        }
        if (position == 28) {
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                    45,
                    75
            );
            params.setMargins(2, 2, 40, 2);
            Vholder.textView.setLayoutParams(params);
        }*/

    }

    @Override
    public int getItemCount() {

        return texts.size();
    }
}