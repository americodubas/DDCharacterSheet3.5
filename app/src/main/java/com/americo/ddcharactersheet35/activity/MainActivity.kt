package com.americo.ddcharactersheet35.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.GridView
import com.americo.ddcharactersheet35.R
import com.americo.ddcharactersheet35.adapter.CharacterAdapter
import com.americo.ddcharactersheet35.data.CheckDatabase
import com.americo.ddcharactersheet35.service.CharacterService
import com.americo.ddcharactersheet35.util.find
import com.americo.ddcharactersheet35.util.startWithId

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CheckDatabase(this)

        newCharacterListener()
    }

    override fun onResume() {
        showCharacters()
        super.onResume()
    }

    /**
     * Creates a listener to insert a new character and starts the [CharacterActivity] with the new id.
     */
    private fun newCharacterListener() {
        find<Button>(R.id.bt_new_character).setOnClickListener{
            startWithId<CharacterActivity>(CharacterService(this).insertEmpty().toString())
        }
    }

    /**
     * Show all characters on the [R.id.gv_character].
     */
    private fun showCharacters(){
        find<GridView>(R.id.gv_character).adapter = CharacterAdapter(this, CharacterService(this).getAll())
    }
}
