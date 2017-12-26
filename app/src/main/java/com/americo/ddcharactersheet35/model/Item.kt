package com.americo.ddcharactersheet35.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Americo on 05/08/2017.
 *
 * Entity of D&D Items
 *
 */
@Entity
class Item {

    @ColumnInfo(name = "_id")
    @PrimaryKey
    var id: Long = 0

    @ColumnInfo(name = "max_dexterity")
    var maxDexterity:Int = 0

    @ColumnInfo(name = "sub_type")
    var subType: String = ""

    @ColumnInfo(name = "damage_type")
    var damageType: String = ""

    var weight: Int = 0
    var critical: Int = 0
    var range: Int = 0
    var armor: Int = 0
    var penalty: Int = 0
    var failure: Int = 0
    var price: Double = 0.0
    var name: String = ""
    var type: String = ""
    var damage: String = ""
    var description: String = ""
}