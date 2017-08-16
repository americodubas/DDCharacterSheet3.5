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
import com.americo.ddcharactersheet35.activity.CharacterActivity
import com.americo.ddcharactersheet35.model.Character

/**
 * Created by Americo on 27/05/2017.
 */
class CharacterAdapter(val context: Context, val characters: List<Character>): BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View

        if (convertView == null){
            view = inflater.inflate(R.layout.view_character, parent, false)
        }else{
            view = convertView
        }

        //TODO create image or icon for characters (maybe by class)

        showName(view, position)

        chooseCharacterListener(view, position)

        return view
    }

    private fun showName(view: View, position: Int) {
        view.findViewById<TextView>(R.id.tv_character_name).text = characters[position].name
    }

    private fun chooseCharacterListener(view: View, position: Int) {
        view.findViewById<ImageView>(R.id.iv_character).setOnClickListener {
            val intent = Intent(context, CharacterActivity::class.java)
            intent.putExtra("id", characters[position].id.toString())
            context.startActivity(intent)
        }
    }

    override fun getItem(position: Int): Any {
        return characters[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return characters.size
    }
}