package com.americo.ddcharactersheet35.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.text.SpannableStringBuilder
import android.view.MenuItem
import android.view.View
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import com.americo.ddcharactersheet35.R
import org.modelmapper.ModelMapper

//File to keep functions to make my life easier and to try new stuff

/**
 * Shortcut to call findViewById on [Activity].
 */
inline fun <reified T: View> Activity.find(id: Int): T = findViewById(id)

/**
 * Shortcut to call findViewById on [Fragment].
 */
inline fun <reified T: View> Fragment.find(id: Int): T = activity!!.findViewById(id)

/**
 * Returns an object from [T] class with properties from the object of the [F] class
 * using [ModelMapper.map] function.
 *
 * Works as an universal converter between Entities and DTOs.
 */
inline fun <F, reified T> convertFromTo( e: F): T = ModelMapper().map(e, T::class.java)

/**
 * Returns a [ArrayList] from [T] class with properties from the [ArrayList] of the [F] class
 * using [ModelMapper.map] function.
 *
 * Works as an universal list converter between Entities and DTOs.
 */
inline fun <F, reified T> convertFromTo(f: List<F>): List<T> {
    val t = ArrayList<T>(f.size)
    val m = ModelMapper()
    f.forEach {
        t.add(m.map(it, T::class.java))
    }
    return t
}

/**
 * Shortcut to set a [String] on a [TextView].
 */
fun TextView.textString(s: String){
    this.text = SpannableStringBuilder(s)
}

/**
 * Shortcut to set an [Int] on a [TextView].
 */
fun TextView.textString(s: Int){
    this.text = SpannableStringBuilder(s.toString())
}

/**
 * Function used to create the toolbar on all the [AppCompatActivity].
 * It will find the [R.id.in_toolbar] and sets the [title] from the parameter
 * so that the user knows in witch screen he is.
 */
fun AppCompatActivity.createToolbar(title: String) {
    val toolbar = this.find<Toolbar>(R.id.in_toolbar)
    toolbar.title = title
    setSupportActionBar(toolbar)
}

/**
 * Function responsible to control the option that was clicked on the menu.
 *
 * If [R.id.it_save] was clicked it will start the new [Activity] from the [intent].
 */
fun AppCompatActivity.optionSaveSelected(item: MenuItem?, id: String, intent: Intent): Boolean {
    return when (item?.itemId) {
        R.id.it_save -> {
            intent.putExtra("id", id)
            startActivity( intent )
            return true
        }
        else -> false
    }
}

/**
 * Function to set the [ListView] height based on it's children plus it's padding.
 * Fix the problem of a [ListView] inside a ScrollView.
 *
 * @param list ListView
 */
fun setListViewHeight(list: ListView) {
    val adapter = list.adapter
    var adapterCount = adapter.count
    if (adapterCount == 0) return

    var height = list.paddingTop + list.paddingBottom
    adapterCount -= 1

    for (i in 0 .. adapterCount) {
        val item = adapter.getView(i, null, list)
        item.measure(0 ,0)
        height += item.measuredHeight
    }

    val params = list.layoutParams
    params.height = height + (list.dividerHeight * (adapter.count) - 1)

    list.layoutParams = params
    list.requestLayout()
}

/**
 * Shortcut o show a [Toast], using [Toast.LENGTH_LONG].
 */
@Suppress("unused")
fun BaseAdapter.toast(context: Context , message: CharSequence){
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}