package com.americo.ddcharactersheet35.data

import com.americo.ddcharactersheet35.model.*
import com.j256.ormlite.android.apptools.OrmLiteConfigUtil

/**
 * Create OrmLite config txt with the classes from the model package
 */
fun createOrmLiteConfigFile() {
    //TODO get all classes from model package
    val classes = arrayOf(Character::class.java, CharacterClasses::class.java, Race::class.java, Spell::class.java, SpellLevel::class.java, Spellcaster::class.java, Item::class.java, CharacterItem::class.java)
    OrmLiteConfigUtil.writeConfigFile("ormlite_config.txt", classes)
}
