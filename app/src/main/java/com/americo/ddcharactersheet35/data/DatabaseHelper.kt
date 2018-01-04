package com.americo.ddcharactersheet35.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.americo.ddcharactersheet35.model.*
import com.americo.ddcharactersheet35.util.SingletonHolder

/**
 * Created by Americo on 24/12/2017.
 *
 * Class responsible for maintaining the database and providing instances of the DAOs
 *
 */
@Database(
        version = 3,
        exportSchema = false,
        entities = [
        Character::class,
        CharacterClasses::class,
        CharacterItem::class,
        Classes::class,
        Item::class,
        Race::class,
        Spell::class,
        Spellcaster::class,
        SpellLevel::class
        ]
)
abstract class DatabaseHelper : RoomDatabase() {

    abstract fun characterDao(): CharacterDao

    abstract fun characterClassesDao(): CharacterClassesDao

    abstract fun classesDao(): ClassesDao

    abstract fun raceDao(): RaceDao

    abstract fun spellDao(): SpellDao

    companion object : SingletonHolder<DatabaseHelper, Context> ({
        Room.databaseBuilder(it.applicationContext, DatabaseHelper::class.java, CheckDatabase.databaseName).allowMainThreadQueries().build()
    })

}


