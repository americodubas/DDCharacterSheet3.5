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

const val ID = "id"

/**
 * Character portraits
 */
val portraits = arrayListOf(R.drawable.barbute, R.drawable.battle_gear, R.drawable.black_knight_helm,
        R.drawable.brutal_helm, R.drawable.chess_king, R.drawable.chess_queen,
        R.drawable.closed_barbute, R.drawable.cowled, R.drawable.cracked_helm,
        R.drawable.crested_helmet, R.drawable.crown, R.drawable.crown_coin,
        R.drawable.crowned_skull, R.drawable.dwarf_helmet, R.drawable.dwarf_king,
        R.drawable.elf_helmet, R.drawable.heavy_helm, R.drawable.hood,
        R.drawable.jewel_crown, R.drawable.laurel_crown, R.drawable.laurels,
        R.drawable.overlord_helm, R.drawable.pirate_hat, R.drawable.pointy_hat,
        R.drawable.pope_crown, R.drawable.queen_crown, R.drawable.robin_hood_hat,
        R.drawable.samurai_helmet, R.drawable.sharp_crown, R.drawable.spartan_helmet,
        R.drawable.tiara, R.drawable.viking_helmet, R.drawable.visored_helm ,
        R.drawable.warlock_hood)

/**
 * Character portraits name
 */
val portraitsName = arrayListOf("Barbute", "Battle Gear", "Black Knight Helm",
        "Brutal Helm", "Chess King", "Chess Queen",
        "Closed Barbute", "Cowled", "Cracked Helm",
        "Crested Helmet", "Crown", "Crown Coin",
        "Crowned Skull", "Dwarf Helmet", "Dwarf King",
        "Elf Helmet", "Heavy Helm", "Hood",
        "Jewel Crown", "Laurel Crown", "Laurels",
        "Overlord Helm", "Pirate Hat", "Pointy Hat",
        "Pope Crown", "Queen Crown", "Robin Hood Hat",
        "Samurai Helmet", "Sharp Crown", "Spartan Helmet",
        "Tiara", "Viking Helmet", "Visor Helm",
        "Warlock Hood")


/**
 * Convert an long to D&D currency (copper, silver, gold, platinum)
 * Table to Exchange Value	CP		SP		GP		PP
 * Copper piece (cp) =		1		1/10	1/100	1/1,000
 * Silver piece (sp) =		10		1		1/10	1/100
 * Gold piece (gp) =		100		10		1		1/10
 * Platinum piece (pp) =	1,000	100		10		1
 */

private val piecesValues = arrayOf(1000, 100, 10, 1)
private val piecesNames = arrayOf(" PP", " GP", " SP", " CP")

fun convertLongToPieces(value: Long): String {
    if (value <= 0) return "0"

    val pieces = StringBuilder()
    var i = 0
    var rest = value
    piecesValues.forEach {
        val amount = rest / it
        if (amount > 0) pieces.append(amount.toString() + piecesNames[i] + ",")
        rest %= it
        i++
    }
    return removeLastComma(pieces.toString())
}

fun removeLastComma(s: String): String {
    return when(s.last() == ','){
        true -> s.slice(0..s.length-2)
        false -> s
    }
}

private fun multiply(s: String): Int{
    var i = 0
    val piece = piecesNames.find {
        i++
        s.contains(it)
    }
    return s.replace(piece!!,"").toInt() * piecesValues[i-1]
}

fun convertPiecesToLong(pieces: String): Long {
    if (pieces.length == 1) return 0

    var value = 0
    pieces.split(",").forEach {
        value += multiply(it)
    }
    return value.toLong()
}

/**
 * Shortcut to create an [Intent] and call startActivity()
 */
inline fun <reified  T> Activity.startWithId(id: String) {
    startActivity(
            Intent(this, T::class.java)
                    .putExtra(ID, id)
    )
}

/**
 * Shortcut to create an [Intent] and call startActivity()
 */
@Suppress("unused")
inline fun <reified  T> Fragment.startWithId(id: String) {
    startActivity(
            Intent(this.context, T::class.java)
                    .putExtra(ID, id)
    )
}

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
inline fun <F, reified T> convert(e: F): T = ModelMapper().map(e, T::class.java)

/**
 * Returns a [ArrayList] from [T] class with properties from the [ArrayList] of the [F] class
 * using [ModelMapper.map] function.
 *
 * Works as an universal list converter between Entities and DTOs.
 */
inline fun <F, reified T> convert(f: List<F>): List<T> {
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