package com.ibm.bankingkeyboard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.klinker.android.emoji_keyboard_trial.R


/**
 * Created by balvirjha on 1/21/18.
 */
class CustomContactListAdapter(context: Context) : BaseAdapter() {

    private val TYPE_ITEM = 0
    private val TYPE_SEPARATOR = 1

    private var mData = ArrayList<String>()
    private var sectionHeader = ArrayList<Any>()
    private var mInflater: LayoutInflater? = null

    init {
        mInflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    fun addItem(item: String) {
        mData.add(item)
        notifyDataSetChanged()
    }

    fun addSectionHeaderItem(item: String) {
        mData.add(item)
        sectionHeader.add(mData.size - 1)
        notifyDataSetChanged()
    }


    override fun getItemViewType(position: Int): Int {
        if (sectionHeader.contains(position)) return TYPE_SEPARATOR else return TYPE_ITEM
    }

    override fun getViewTypeCount(): Int {
        return 2
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var holder: ViewHolder? = null
        var rowType = getItemViewType(position)
        var convertviewVar = convertView

        if (convertviewVar == null) {
            holder = ViewHolder()
            when (rowType) {
                TYPE_ITEM -> {
                    convertviewVar = mInflater?.inflate(R.layout.contacts_list_item, null)
                    holder!!.textView = convertviewVar?.findViewById(R.id.phoneNumber) as TextView

                }
                TYPE_SEPARATOR -> {
                    convertviewVar = mInflater?.inflate(R.layout.snippet_item1, null)
                    holder!!.textView = convertviewVar?.findViewById(R.id.text) as TextView
                }
            }
            convertviewVar?.setTag(holder)
        } else {
            holder = convertviewVar?.tag as ViewHolder
        }
        holder!!.textView?.setText(mData[position])

        return convertviewVar!!
    }

    override fun getItem(position: Int): Any {
        return mData.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return mData.size
    }

    class ViewHolder {
        var textView: TextView? = null
    }
}