package com.ibm.bankingkeyboard.view;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;

import com.klinker.android.emoji_keyboard_trial.R;

public class KeyboardSinglePageView {

    private Context context;
    private BaseAdapter adapter;
    private RecyclerViewAdapter recyclerViewAdapter;

    public KeyboardSinglePageView(Context context, BaseAdapter adapter) {
        this.context = context;
        this.adapter = adapter;
    }

    public KeyboardSinglePageView(Context context, RecyclerViewAdapter adapter) {
        this.context = context;
        this.recyclerViewAdapter = adapter;
    }

    public View getView() {


        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mroot = layoutInflater.inflate(R.layout.keyboard_layout, null);

        //RecyclerView recyclerView = mroot.findViewById(R.id.recycler_view1);

       /* GridLayoutManager recyclerViewLayoutManager = new GridLayoutManager(context, 10);
        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);*/
        /*recyclerViewLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {

                if (position == 19) {
                    return 2;
                } else if (position == 27) {
                    return 2;
                } else {
                    return 1;
                }
            }
        });*/

       /* final GridView gridView = new GridView(context);

        gridView.setColumnWidth((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, context.getResources().getDisplayMetrics()));
        gridView.setNumColumns(GridView.AUTO_FIT);

        gridView.setAdapter(adapter);*/
        return mroot;
    }
}