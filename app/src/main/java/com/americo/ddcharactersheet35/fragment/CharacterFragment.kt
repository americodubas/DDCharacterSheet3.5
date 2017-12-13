package com.americo.ddcharactersheet35.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import com.americo.ddcharactersheet35.R
import com.americo.ddcharactersheet35.adapter.CharacterClassesAdapter
import com.americo.ddcharactersheet35.adapter.EditCharacterClassAdapter
import com.americo.ddcharactersheet35.service.CharacterService
import com.americo.ddcharactersheet35.util.find
import com.americo.ddcharactersheet35.util.textString

/**
 * A simple [Fragment] subclass.
 * Use the [CharacterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CharacterFragment : Fragment() {

    private lateinit var id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            id = arguments!!.getString(ID)
        }
    }

    override fun onResume() {
        super.onResume()

        if (context == null){
            return
        }

        val char = CharacterService(context!!).getCharacter(id)
        with(char) {
            find<TextView>(R.id.tv_character_name).text = name
            find<TextView>(R.id.tv_character_race).text = race.name
            find<TextView>(R.id.tv_character_level).textString(level)
            find<TextView>(R.id.tv_character_experience).textString(experience)
            find<TextView>(R.id.tv_character_alignment).text = alignment
            find<TextView>(R.id.tv_character_deity).text = deity
            find<TextView>(R.id.tv_character_gender).text = gender
            find<TextView>(R.id.tv_character_age).textString(age)
            find<TextView>(R.id.tv_character_size).text = size
            find<TextView>(R.id.tv_character_height).text = height
            find<TextView>(R.id.tv_character_weight).text = weight
            find<TextView>(R.id.tv_character_skin).text = skin
            find<TextView>(R.id.tv_character_eyes).text = eyes
            find<TextView>(R.id.tv_character_hair).text = hair
        }

        find<ListView>(R.id.lv_classes).adapter = CharacterClassesAdapter(context!!, char.characterClasses.toList())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_character, container, false)
    }

    companion object {
        private val ID = "id"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.

         * @param id String.
         * *
         * *
         * @return A new instance of fragment CharacterFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(id: String): CharacterFragment {
            val fragment = CharacterFragment()
            val args = Bundle()
            args.putString(ID, id)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor