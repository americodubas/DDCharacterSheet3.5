package com.americo.ddcharactersheet35.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.americo.ddcharactersheet35.R
import com.americo.ddcharactersheet35.activity.EditCharacterActivity
import com.americo.ddcharactersheet35.dto.CharacterClassesDto
import com.americo.ddcharactersheet35.exception.CannotDecreaseCharacterClassesLevel
import com.americo.ddcharactersheet35.service.CharacterClassesService
import com.americo.ddcharactersheet35.util.find
import com.americo.ddcharactersheet35.util.toast

/**
 * Created by Americo on 27/05/2017.
 *
 * Adapter to show the character classes and to provide three options to edit the class
 * 1- Increase class level
 * 2- Decrease class level
 * 3- Delete class
 *
 */
class EditCharacterClassAdapter(val context: Context, var characterClasses: List<CharacterClassesDto>): BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View
        val char = characterClasses[position]

        view = when(convertView == null) {
            true -> inflater.inflate(R.layout.view_edit_character_class, parent, false)
            false -> convertView as View
        }

        showCharacterClass(view, char)
        increaseLevelListener(view, char)
        decreaseLevelListener(view, char)
        deleteListener(view, char)

        return view
    }

    /**
     * Delete the relation of the characterClass.
     */
    private fun deleteListener(view: View, char: CharacterClassesDto) {
        view.findViewById<TextView>(R.id.bt_remove).setOnClickListener {
            CharacterClassesService(context).deleteCharacterClasses(char)
            characterClasses = characterClasses.minus(char)
            notifyDataSetChanged()
        }
    }

    private fun changeCharacterLevel(amount: Int) {
        if(context is EditCharacterActivity) {
            val levelTv = context.find<TextView>(R.id.tv_character_level)
            var level = levelTv.text.toString().toInt()
            level += amount
            levelTv.text = level.toString()
        }
    }

    /**
     * Decrease the level of the characterClass.
     */
    private fun decreaseLevelListener(view: View, char: CharacterClassesDto) {

        view.findViewById<TextView>(R.id.tv_decrease).setOnClickListener {
            try {
                view.findViewById<TextView>(R.id.tv_level).text =
                        CharacterClassesService(context).decreaseCharacterClassesLevel(char).level.toString()
                changeCharacterLevel(-1)
            }catch (e: CannotDecreaseCharacterClassesLevel){
                toast(context, context.getString(R.string.cannot_decrease_level))
            }
        }
    }

    /**
     * Increase the level of the characterClass.
     */
    private fun increaseLevelListener(view: View, char: CharacterClassesDto) {
        view.findViewById<TextView>(R.id.tv_increase).setOnClickListener {
            view.findViewById<TextView>(R.id.tv_level).text =
                    CharacterClassesService(context).increaseCharacterClassesLevel(char).level.toString()
            changeCharacterLevel(1)
        }
    }

    /**
     * Show the data of the characterClass.
     */
    private fun showCharacterClass(view: View, char: CharacterClassesDto) {
        with(char){
            view.findViewById<TextView>(R.id.tv_id).text = id.toString()
            view.findViewById<TextView>(R.id.tv_name).text = char.classesName
            view.findViewById<TextView>(R.id.tv_level).text = level.toString()
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