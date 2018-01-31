package com.americo.ddcharactersheet35.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.americo.ddcharactersheet35.R
import com.americo.ddcharactersheet35.activity.EditCharacterActivity
import com.americo.ddcharactersheet35.service.CharacterService
import com.americo.ddcharactersheet35.util.portraits
import com.americo.ddcharactersheet35.util.portraitsName

/**
 * Created by Americo on 30/01/2018.
 *
 * Adapter to show portraits.
 *
 */
class PortraitAdapter(val context: Context, val id: String): BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View

        view = when(convertView){
            null -> inflater.inflate(R.layout.view_portrait, parent, false)
            else -> convertView
        }

        showPortrait(view, position)

        choosePortraitListener(view, position)

        return view
    }

    private fun showPortrait(view: View, position: Int) {
        view.findViewById<TextView>(R.id.tv_name).text = portraitsName[position]
        view.findViewById<ImageView>(R.id.iv_portrait).setImageResource(portraits[position])
    }

    private fun choosePortraitListener(view: View, position: Int) {
        view.findViewById<ImageView>(R.id.iv_portrait).setOnClickListener {
            CharacterService(this.context).updatePortrait(id, portraits[position])

            val intent = Intent(context, EditCharacterActivity::class.java)
            intent.putExtra("id", id)
            context.startActivity(intent)
        }
    }

    override fun getItem(position: Int): Any {
        return portraits[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return portraits.size
    }
}