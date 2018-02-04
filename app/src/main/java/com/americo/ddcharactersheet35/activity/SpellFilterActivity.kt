package com.americo.ddcharactersheet35.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import com.americo.ddcharactersheet35.R
import com.americo.ddcharactersheet35.base.BaseActivity
import com.americo.ddcharactersheet35.util.find

class SpellFilterActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spell_filter)

        find<Button>(R.id.bt_search_spell).setOnClickListener{searchSpell()}
    }

    private fun searchSpell() {
        val intent = Intent(this, DisplaySpellListActivity::class.java)
        intent.putExtra("name", find<EditText>(R.id.tv_spell_name).text.toString() )
        intent.putExtra("class", find<Spinner>(R.id.sp_spell_class).selectedItem.toString())
        intent.putExtra("circle", find<Spinner>(R.id.sp_spell_circle).selectedItem.toString())
        startActivity(intent)
    }


}

