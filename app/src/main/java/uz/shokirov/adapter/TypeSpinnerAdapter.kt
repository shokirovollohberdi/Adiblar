package uz.shokirov.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.item_spinner.view.*
import uz.shokirov.adiblarhaqida.R
import uz.shokirov.adiblarhaqida.databinding.ItemSpinnerBinding

class TypeSpinnerAdapter(var list: ArrayList<String>) : BaseAdapter() {
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var itemView: View
        if (convertView == null) {
            itemView =
                LayoutInflater.from(parent?.context).inflate(R.layout.item_spinner, parent, false)
        } else {
            itemView = convertView
        }

        itemView.tvType.text = list[position]

        return itemView
    }
}