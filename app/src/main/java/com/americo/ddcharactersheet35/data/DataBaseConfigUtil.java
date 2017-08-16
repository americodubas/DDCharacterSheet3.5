package com.americo.ddcharactersheet35.data;

import com.americo.ddcharactersheet35.model.Character;
import com.americo.ddcharactersheet35.model.CharacterClasses;
import com.americo.ddcharactersheet35.model.CharacterItem;
import com.americo.ddcharactersheet35.model.Item;
import com.americo.ddcharactersheet35.model.Race;
import com.americo.ddcharactersheet35.model.Spell;
import com.americo.ddcharactersheet35.model.SpellLevel;
import com.americo.ddcharactersheet35.model.Spellcaster;
import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Americo on 29/04/2017.
 */

public class DataBaseConfigUtil extends OrmLiteConfigUtil {

    /**
     * Java class because it's easier to run a main
     */
    public static void main(String[] agrs) throws IOException, SQLException {
        Class<?>[] classes = new Class[]
                {Character.class, CharacterClasses.class, Race.class, Spell.class,
                        SpellLevel.class, Spellcaster.class, Item.class,
                        CharacterItem.class};
        writeConfigFile("ormlite_config.txt", classes);
    }

}
