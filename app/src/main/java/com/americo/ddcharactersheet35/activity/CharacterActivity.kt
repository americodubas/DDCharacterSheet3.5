package com.americo.ddcharactersheet35.activity

import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.americo.ddcharactersheet35.R
import com.americo.ddcharactersheet35.adapter.TabAdapter
import com.americo.ddcharactersheet35.service.CharacterService
import com.americo.ddcharactersheet35.util.SlidingTabLayout
import com.americo.ddcharactersheet35.util.find

class CharacterActivity : AppCompatActivity() {

    companion object{
        lateinit var id: String
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character)

        id = intent.getStringExtra("id")

        createToolbar()

        createSlidingTab()
    }

    override fun onResume() {
        setCharacterNameOnToolbar()
        super.onResume()
    }

    private fun setCharacterNameOnToolbar() {
        find<Toolbar>(R.id.in_toolbar).title = CharacterService(this).getCharacter(id).name
    }

    private fun createSlidingTab() {
        val viewPager = find<ViewPager>(R.id.vp_page)
        viewPager.adapter = TabAdapter(supportFragmentManager, id)

        val slidingTab = find<SlidingTabLayout>(R.id.st_tabs)
        slidingTab.setDistributeEvenly(true)
        slidingTab.setSelectedIndicatorColors(ContextCompat.getColor(this, R.color.colorAccent))
        slidingTab.setViewPager(viewPager)
    }

    private fun createToolbar() {
        setSupportActionBar(find<Toolbar>(R.id.in_toolbar))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.it_edit -> {
                val intent = Intent(this, EditCharacterActivity::class.java)
                intent.putExtra("id", id)
                startActivity( intent )
                return true
            }
            R.id.it_change_char -> {
                startActivity( Intent(this, MainActivity::class.java) )
                return true
            }
            R.id.it_settings -> {
                print("settings!")
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
