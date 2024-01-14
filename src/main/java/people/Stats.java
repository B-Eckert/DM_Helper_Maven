package people;
/*
 * Brant Eckert, November 2019
 * All of the stats and the methods relating to them.
 */
public class Stats {
    public static final String[] STAT_NAMES = {"Strength", "Dexterity", "Constitution", "Wisdom", "Intelligence", "Charisma"};
    public static final String[] STAT_ABBREVIATIONS = {"STR","DEX","CON","WIS","INT","CHA"};

    /**
     * Calculates a stat bonus given a number
     * @param stat The stat's value
     * @return The stat bonus
     */
    public static int statBonusCalc(int stat){
        return (int)Math.floor(((double)stat - 10.0) / 2.0);
    }

    /**
     * Turns the string of a statistic into its index in a standard stat array
     * @param stat The statistic to find
     * @return The index in a standard stat array.
     */
    public static int stringToIndex(String stat){
        for(int i = 0; i < STAT_NAMES.length; i++){
            if(stat.equals(STAT_NAMES[i]) || stat.equals(STAT_ABBREVIATIONS[i])){
                return i;
            }
        }
        return -1;
    }
}
