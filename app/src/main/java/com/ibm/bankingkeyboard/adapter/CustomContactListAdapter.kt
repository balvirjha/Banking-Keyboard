package com.ibm.bankingkeyboard.adapter

import android.app.LauncherActivity.ListItem
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.ibm.bankingkeyboard.ContactModal
import com.klinker.android.emoji_keyboard_trial.R


/**
 * Created by balvirjha on 1/21/18.
 */
class CustomContactListAdapter(context: Context, dataList: List<ContactModal>) : BaseAdapter() {

    private var dataListFresh: List<ContactModal>? = null
    private var layoutInflater: LayoutInflater? = null

    private val VIEW_TYPE_NONE = 0
    private val VIEW_TYPE_SECTION = 1
    private val VIEW_TYPE_ITEM = 2

    init {
        this.dataListFresh = dataList
        this.layoutInflater = LayoutInflater.from(context);
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        if (getItemViewType(position) == VIEW_TYPE_SECTION) {
            return getSectionView(position, convertView, parent!!)
        } else if (getItemViewType(position) == VIEW_TYPE_ITEM) {
            return getItemView(position, convertView, parent!!)
        }
        return null
    }

    override fun getItem(position: Int): ContactModal? {
        if (dataListFresh!!.isEmpty()) {
            return null;
        } else {
            return dataListFresh!!.get(position)
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        if (dataListFresh != null) return dataListFresh!!.size!! else return 0
    }

    private fun getItemView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val itemViewHolder: ItemViewHolder
        if (convertView == null) {
            convertView = layoutInflater?.inflate(R.layout.contacts_list_item, parent, false)
            itemViewHolder = ItemViewHolder(convertView!!)
            convertView!!.tag = itemViewHolder
        } else {
            itemViewHolder = convertView.tag as ItemViewHolder
        }
        val listItem = getItem(position) as ListItem
        itemViewHolder.setMessage(listItem.getMessage())
        itemViewHolder.setMessageLine2(listItem.getMessageLine2())
        return convertView
    }

    private fun getSectionView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val sectionViewHolder: SectionViewHolder
        if (convertView == null) {
            convertView = layoutInflater?.inflate(R.layout.layout_list_section, parent, false)
            sectionViewHolder = SectionViewHolder(convertView)
            convertView!!.tag = sectionViewHolder
        } else {
            sectionViewHolder = convertView.tag as SectionViewHolder
        }
        sectionViewHolder.setTitle((getItem(position) as ListSection).getTitle())
        return convertView
    }

    override fun getItemViewType(position: Int): Int {
        if (count > 0) {
            val listData = getItem(position)
            return if (listData is ListSection) {
                VIEW_TYPE_SECTION
            } else if (listData is ListItem) {
                VIEW_TYPE_ITEM
            } else {
                VIEW_TYPE_NONE
            }
        } else {
            return VIEW_TYPE_NONE
        }
    }

    override fun getViewTypeCount(): Int {
        return 3
    }

    inner class SectionViewHolder(itemView: View) {
        var tvTitle: TextView

        init {
            tvTitle = itemView.findViewById(R.id.text_view_title) as TextView
        }

        fun setTitle(title: String) {
            tvTitle.text = title
        }
    }

    internal inner class ItemViewHolder(itemView: View) {
        var tvMessage: TextView
        var tvMessageLine2: TextView

        init {
            tvMessage = itemView.findViewById(R.id.text_view_message) as TextView
            tvMessageLine2 = itemView.findViewById(R.id.text_view_message_line_2) as TextView
        }

        fun setMessage(message: String) {
            tvMessage.text = message
        }

        fun setMessageLine2(messageLine2: String) {
            tvMessageLine2.text = messageLine2
        }
    }
}