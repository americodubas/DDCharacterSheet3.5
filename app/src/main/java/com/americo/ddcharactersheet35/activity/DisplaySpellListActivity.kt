package com.americo.ddcharactersheet35.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.TextView
import com.americo.ddcharactersheet35.R
import com.americo.ddcharactersheet35.service.SpellService
import com.americo.ddcharactersheet35.util.find

class DisplaySpellListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_spell_list)
        showSpells()
    }

    private fun showSpells() {

        val spells = SpellService(this).getSpellsByNameClassCircle(
                intent.getStringExtra("name"),
                intent.getStringExtra("class"),
                intent.getStringExtra("circle")
        )

        val adapter = SimpleAdapter(
                this,
                spells,
                R.layout.view_spell,
                arrayOf("id", "name", "description"),
                intArrayOf(R.id.tv_spell_id, R.id.tv_spell_name, R.id.tv_spell_description)
        )


        find<ListView>(R.id.lv_spells).adapter = adapter
        find<ListView>(R.id.lv_spells).onItemClickListener =
                AdapterView.OnItemClickListener { _, view, _, _ -> displaySpell(view) }
    }

    fun displaySpell(view: View) {
        val intent = Intent(this, DisplaySpellActivity::class.java)
        intent.putExtra("id", view.findViewById<TextView>(R.id.tv_spell_id).text.toString())
        startActivity(intent)
    }

}
