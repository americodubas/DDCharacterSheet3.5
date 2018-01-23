package com.americo.ddcharactersheet35.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Americo on 21/06/2017.
 *
 * Entity of D&D Classes
 *
 */
@Entity
class Classes {

    @ColumnInfo(name = "_id")
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    var spellcaster: Int = 0
    var name: String = ""
    var description: String = ""

}