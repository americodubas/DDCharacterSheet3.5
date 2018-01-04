package com.americo.ddcharactersheet35.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Americo on 05/08/2017.
 *
 * Entity of the Character's Itens
 *
 */
@Entity(
        tableName = "character_item",
        foreignKeys = [
            (ForeignKey(entity = Character::class,
                    parentColumns = ["_id"],
                    childColumns = ["character_id"])),
            (ForeignKey(entity = Item::class,
                    parentColumns = ["_id"],
                    childColumns = ["item_id"]))
        ]
)
class CharacterItem {

    @ColumnInfo(name = "_id")
    @PrimaryKey(autoGenerate = true)
    var id = 0

    @ColumnInfo(name = "character_id")
    var characterId: Int = 0

    @ColumnInfo(name = "item_id")
    var itemId: Int = 0

    var quantity:Int = 0

}