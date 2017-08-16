package com.americo.ddcharactersheet35.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.americo.ddcharactersheet35.R
import com.americo.ddcharactersheet35.model.CharacterClasses

/**
 * Created by Americo on 05/08/2017.
 */
class CharacterClassesAdapter(val context: Context, val characterClasses: List<CharacterClasses>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View

        if (convertView == null) {
            view = inflater.inflate(R.layout.view_character_class, parent, false)
        } else {
            view = convertView
        }

        showCharacter(view, position)

        return view
    }

    private fun  showCharacter(view: View, position: Int) {
        val char = characterClasses[position]
        (view.findViewById(R.id.tv_name) as TextView).text = char.classes.name
        (view.findViewById(R.id.tv_level) as TextView).text = char.level.toString()
    }

    override fun getItem(position: Int): Any {
        return characterClasses[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return characterClasses.size
    }
}