package com.americo.ddcharactersheet35.model

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

/**
 * Created by Americo on 05/08/2017.
 */
@DatabaseTable(tableName = "item")
class Item() {

    @DatabaseField(columnName = "_id", generatedId = true)
    var id: Int = 0

    @DatabaseField
    lateinit var name: String
    @DatabaseField
    lateinit var type: String
    @DatabaseField(columnName = "sub_type")
    lateinit var subType: String
    @DatabaseField
    var weight = 0
    @DatabaseField
    var price = 0.0
    @DatabaseField
    lateinit var damage: String
    @DatabaseField
    var critical = 0
    @DatabaseField(columnName = "damage_type")
    lateinit var damageType: String
    @DatabaseField
    var range = 0
    @DatabaseField
    var armor = 0
    @DatabaseField(columnName = "max_dexterity")
    var maxDexterity = 0
    @DatabaseField
    var penalty = 0
    @DatabaseField
    var failure = 0
    @DatabaseField
    lateinit var description: String

    constructor(
            id: Int = 0,
            name: String = "",
            type: String = "",
            subType: String = "",
            weight: Int = 0,
            price: Double = 0.0,
            damage: String = "",
            critical: Int = 0,
            damageType: String = "",
            range: Int = 0,
            armor: Int = 0,
            maxDexterity: Int = 0,
            penalty: Int = 0,
            failure: Int = 0,
            description: String = ""
    ): this() {
        this.id = id
        this.name = name
        this.type = type
        this.subType = subType
        this.weight = weight
        this.price = price
        this.damage = damage
        this.critical = critical
        this.damageType = damageType
        this.range = range
        this.armor = armor
        this.maxDexterity = maxDexterity
        this.penalty = penalty
        this.failure = failure
        this.description = description
    }

}