package com.americo.ddcharactersheet35.model

import com.j256.ormlite.dao.ForeignCollection
import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.field.ForeignCollectionField
import com.j256.ormlite.table.DatabaseTable

/**
 * Created by Americo on 29/04/2017.
 */

@DatabaseTable(tableName = "character")
class Character() {

    @DatabaseField(columnName = "_id", generatedId = true)
    var id: Int = 0

    @DatabaseField(foreign = true, columnName = "race_id", foreignAutoRefresh = true)
    lateinit var race: Race

    @ForeignCollectionField(eager = true, maxEagerLevel = 2)
    lateinit var characterClasses: ForeignCollection<CharacterClasses>

    @DatabaseField
    lateinit var name: String
    @DatabaseField
    lateinit var alignment: String
    @DatabaseField
    lateinit var deity: String
    @DatabaseField
    lateinit var size: String
    @DatabaseField
    var age: Int = 0
    @DatabaseField
    lateinit var gender: String
    @DatabaseField
    lateinit var height: String
    @DatabaseField
    lateinit var weight: String
    @DatabaseField
    lateinit var eyes: String
    @DatabaseField
    lateinit var hair: String
    @DatabaseField
    lateinit var skin: String
    @DatabaseField
    var level: Int = 0
    @DatabaseField
    var experience: Int = 0
    @DatabaseField
    var strength: Int = 0
    @DatabaseField
    var dexterity: Int = 0
    @DatabaseField
    var constitution: Int = 0
    @DatabaseField
    var intelligence: Int = 0
    @DatabaseField
    var wisdom: Int = 0
    @DatabaseField
    var charisma: Int = 0
    @DatabaseField
    var fortitude: Int = 0
    @DatabaseField
    var reflex: Int = 0
    @DatabaseField
    var will: Int = 0
    @DatabaseField(columnName = "full_hp")
    var fullHp: Int = 0
    @DatabaseField
    var hp: Int = 0
    @DatabaseField
    lateinit var speed: String

    constructor(
            id: Int = 0,
            name: String = "",
            race: Race,
            alignment: String = "",
            deity: String = "",
            size: String = "",
            age: Int = 0,
            gender: String = "",
            height: String = "",
            weight: String = "",
            eyes: String = "",
            hair: String = "",
            skin: String = "",
            level: Int = 1,
            experience: Int = 0,
            strength: Int = 0,
            dexterity: Int = 0,
            constitution: Int = 0,
            intelligence: Int = 0,
            wisdom: Int = 0,
            charisma: Int = 0,
            fortitude: Int = 0,
            reflex: Int = 0,
            will: Int = 0,
            fullHp: Int = 0,
            hp: Int = 0,
            speed: String = ""
    ) : this() {
        this.id = id
        this.name = name
        this.race = race
        this.alignment = alignment
        this.deity = deity
        this.size = size
        this.age = age
        this.gender = gender
        this.height = height
        this.weight = weight
        this.eyes = eyes
        this.hair = hair
        this.skin = skin
        this.level = level
        this.experience = experience
        this.strength = strength
        this.dexterity = dexterity
        this.constitution = constitution
        this.intelligence = intelligence
        this.wisdom = wisdom
        this.charisma = charisma
        this.fortitude = fortitude
        this.reflex = reflex
        this.will = will
        this.fullHp = fullHp
        this.hp = hp
        this.speed = speed
    }
}
