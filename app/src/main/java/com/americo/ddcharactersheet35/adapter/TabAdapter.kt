package com.americo.ddcharactersheet35.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.americo.ddcharactersheet35.fragment.*

/**
 * Created by Americo on 28/05/2017.
 */
class TabAdapter(fm: FragmentManager, var id: String) : FragmentStatePagerAdapter(fm) {

    private val tabTitle = arrayOf("CHAR", "INV", "FEAT", "SPELL", "NOTE")

    override fun getItem(position: Int): Fragment {

        return when (position) {
            0 -> CharacterFragment.newInstance(id)
            1 -> InventoryFragment()
            2 -> FeatsFragment()
            3 -> SpellsFragment()
            4 -> NotesFragment()
            else -> CharacterFragment()
        }

    }

    override fun getCount(): Int {
        return tabTitle.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return tabTitle[position]
    }
}