package people;

import dieRolling.DieRolling;
import tools.General;

public class Types {
    public static final String[] TYPES = {"Artificer", "Barbarian", "Bard", "Cleric", "Druid", "Fighter", "Monk",
                                            "Paladin", "Ranger", "Rogue", "Sorcerer", "Warlock", "Wizard"};

    public static String randomType(){
        return TYPES[DieRolling.pureRandom(TYPES.length) - 1];
    }
}
