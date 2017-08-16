package com.americo.ddcharactersheet35.model

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

/**
 * Created by Americo on 05/08/2017.
 */
@DatabaseTable(tableName = "character_item")
class CharacterItem() {

    @DatabaseField(columnName = "_id", generatedId = true)
    var id = 0
    @DatabaseField(foreign = true, columnName = "character_id", foreignAutoRefresh = true)
    lateinit var character: Character
    @DatabaseField(foreign = true, columnName = "item_id", foreignAutoRefresh = true)
    lateinit var item: Item
    @DatabaseField
    var quantity = 0

    constructor(
            character: Character,
            item: Item,
            quantity: Int = 1
    ): this() {
        this.character = character
        this.item = item
        this.quantity = quantity
    }

}