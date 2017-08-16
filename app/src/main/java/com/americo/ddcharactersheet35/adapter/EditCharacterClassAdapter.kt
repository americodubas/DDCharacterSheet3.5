package com.americo.ddcharactersheet35.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import com.americo.ddcharactersheet35.R
import com.americo.ddcharactersheet35.exception.CannotDecreaseCharacterClassesLevel
import com.americo.ddcharactersheet35.model.CharacterClasses
import com.americo.ddcharactersheet35.service.CharacterService

/**
 * Created by Americo on 27/05/2017.
 */
class EditCharacterClassAdapter(val context: Context, var characterClasses: List<CharacterClasses>): BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View
        val char = characterClasses[position]

        if (convertView == null){
            view = inflater.inflate(R.layout.view_edit_character_class, parent, false)
        }else{
            view = convertView
        }

        showCharacterClass(view, char)
        increaseLevelListener(view, char)
        decreaseLevelListener(view, char)
        removeListener(view, char, position)

        return view
    }

    private fun removeListener(view: View, char: CharacterClasses, position: Int) {
        view.findViewById(R.id.ib_remove).setOnClickListener {
            CharacterService(context).deleteCharacterClasses(char)
            characterClasses = characterClasses.minus(char)
            notifyDataSetChanged()
        }
    }

    private fun decreaseLevelListener(view: View, char: CharacterClasses) {
        view.findViewById(R.id.ib_decrease).setOnClickListener {
            try {
                (view.findViewById(R.id.tv_level) as TextView).text =
                        CharacterService(context).decreaseCharacterClassesLevel(char).level.toString()
            }catch (e: CannotDecreaseCharacterClassesLevel){
                Toast.makeText(context,
                        context.getString(R.string.cannot_decrease_level),
                        Toast.LENGTH_LONG)
                        .show()
            }
        }
    }

    private fun increaseLevelListener(view: View, char: CharacterClasses) {
        view.findViewById(R.id.ib_increase).setOnClickListener {
            (view.findViewById(R.id.tv_level) as TextView).text =
                    CharacterService(context).increaseCharacterClassesLevel(char).level.toString()
        }
    }

    private fun showCharacterClass(view: View, char: CharacterClasses) {
        with(char){
            (view.findViewById(R.id.tv_id) as TextView).text = id.toString()
            (view.findViewById(R.id.tv_name) as TextView).text = classes.name
            (view.findViewById(R.id.tv_level) as TextView).text = level.toString()
        }
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