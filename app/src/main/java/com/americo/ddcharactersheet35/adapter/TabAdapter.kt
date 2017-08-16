package com.americo.ddcharactersheet35.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.americo.ddcharactersheet35.fragment.*

/**
 * Created by Americo on 28/05/2017.
 */
class TabAdapter(fm: FragmentManager, var id: String) : FragmentStatePagerAdapter(fm) {

    private val tabTitle = arrayOf("CHAR", "INV", "FEATS", "SPELLS", "NOTES")

    override fun getItem(position: Int): Fragment {

        val fragment: Fragment

        when (position) {
            0 -> fragment = CharacterFragment.newInstance(id)
            1 -> fragment = InventoryFragment()
            2 -> fragment = FeatsFragment()
            3 -> fragment = SpellsFragment()
            4 -> fragment = NotesFragment()
            else -> fragment = CharacterFragment()
        }

        return fragment

    }

    override fun getCount(): Int {
        return tabTitle.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return tabTitle[position]
    }
}