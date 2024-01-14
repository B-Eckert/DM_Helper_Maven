package people;

import dieRolling.DieRolling;
import people.Races;
import people.Skills;
import people.Stats;
import tools.General;

import java.util.ArrayList;

/*
 * Brant Eckert, December 2019
 * Stats, bonuses, proficiency bonus, proficiencies and race of an NPC
 */

public class NPC {
    // All the base features of a n NPC (name, desc, race, etc)
    protected String name = "Unnamed", desc = "An unnamed adventurer", race = "Faceless", type = "Classless"; //type = class
    protected int stats[] = {0, 0, 0, 0, 0, 0}; //STR - DEX - CON - WIS - INT - CHA
    protected int bonuses[] = {0, 0, 0, 0, 0, 0}; // above
    protected int profBonus = 1, level = 0;
    protected ArrayList<String> profs = new ArrayList<>();
    //random stat dudes

    /**
     * Instantiates a randomly decided NPC
     */
    public NPC(){
        randomStats();
        boolean[] defTiers = {true, true, false, false, false, false};
        randomRace(defTiers);
        randomProfs(7);
        randomType();
    }

    /**
     * Instantiates a randomly decided NPC with a specific name.
     * @param inName Name of the NPC
     */
    public NPC(String inName){
        this();
        name = inName;
    }

    /**
     * Instantiates a randomly decided NPC with a certain name and description.
     * @param inName Name of the npc
     * @param inDesc Description of the npc
     */
    public NPC(String inName, String inDesc){
        this(inName);
        desc = inDesc;
    }

    /**
     * Instantiates a randomly decided NPC with a certain name, description, and type (Type is short for Class because class is a protected word in java)
     * @param inName Name
     * @param inDesc Description
     * @param inType Class
     */
    public NPC(String inName, String inDesc, String inType){
        this(inName,inDesc);
        type = inType;
    }

    /**
     * Generates a randomly decided NPC with a certain name, description, type and race.
     * @param inName Name of the npc
     * @param inDesc Description of the npc
     * @param inType Class of the npc
     * @param inRace Race of the npc
     */
    public NPC(String inName, String inDesc, String inType, String inRace){
        this(inName,inDesc,inType);
        setRace(inRace);
    }

    //known stat dudes

    /**
     * Generates an NPC with specific stats
     * @param inName Name
     * @param inStats Stat array, STR-DEX-CON-WIS-INT-CHA
     */
    public NPC(String inName, int[] inStats){
        name = inName;
        stats = inStats;
        calcStatBonuses();
    }

    /**
     * Generates an NPC of a certain race with specific stats
     * @param inName Name
     * @param inRace Race
     * @param inStats Stat array, STR-DEX-CON-WIS-INT-CHA
     */
    public NPC(String inName, String inRace, int[] inStats){
        this(inName,inStats);
        setRace(inRace);
    }

    /**
     * Generates an NPC of a certain race and class with specific stats
     * @param inName Name
     * @param inRace Race
     * @param inType class
     * @param inStats Stat array, STR-DEX-CON-WIS-INT-CHA
     */
    public NPC(String inName, String inRace, String inType, int[] inStats){
        this(inName, inRace, inStats);
        type = inType;
    }
    /**
     * Generates an NPC of a certain race and class with specific stats
     * @param inName Name
     * @param inRace Race
     * @param inType class
     * @param inStats Stat array, STR-DEX-CON-WIS-INT-CHA
     */
    public NPC(String inName, String inRace, String inType, String inDesc, int[] inStats){
        this(inName,inRace,inType,inStats);
        desc = inDesc;
    }

    /**
     * Generates random stats for the character using the 4d6kh3 scheme (4d6, keep the highest 3)
     */
    public void randomStats(){
        int[][] statArrays = new int[6][4];
        for(int i = 0; i < 6; i++){
            statArrays[i] = DieRolling.rollDieArray(6, 4);
        }
        for(int i = 0; i < statArrays.length; i++){
            int min = statArrays[i][0];
            int rowSum = 0;
            for(int j = 0; j < statArrays[i].length; j++){
                if(min > statArrays[i][j])
                    min = statArrays[i][j];
                rowSum+=statArrays[i][j];
            }
            stats[i] = rowSum - min;
        }
        int[] raceBonus = Races.racialBonuses(race);
        for(int i = 0; i < stats.length; i++){
            stats[i] += raceBonus[i];
        }
        calcStatBonuses();
    }

    /**
     * Generates weak random stats for a character (uses the 3d6kh2 scheme)
     */
    public void randomStatsIncompetent(){
        int[][] statArrays = new int[6][3];
        for(int i = 0; i < 6; i++){
            statArrays[i] = DieRolling.rollDieArray(6, 3);
        }
        for(int i = 0; i < statArrays.length; i++){
            int min = statArrays[i][0];
            int rowSum = 0;
            for(int j = 0; j < statArrays[i].length; j++){
                if(min > statArrays[i][j])
                    min = statArrays[i][j];
                rowSum+=statArrays[i][j];
            }
            stats[i] = rowSum - min;
        }
        int[] raceBonus = Races.racialBonuses(race);
        for(int i = 0; i < stats.length; i++){
            stats[i] += raceBonus[i];
        }
        calcStatBonuses();
    }

    /**
     * Generates powerful stats for a character (uses the 5d6kh4 scheme)
     */
    public void randomStatsPowerful(){
        int[][] statArrays = new int[6][5];
        for(int i = 0; i < 6; i++){
            statArrays[i] = DieRolling.rollDieArray(6, 5);
        }
        for(int i = 0; i < statArrays.length; i++){
            int min = statArrays[i][0];
            int rowSum = 0;
            for(int j = 0; j < statArrays[i].length; j++){
                if(min > statArrays[i][j])
                    min = statArrays[i][j];
                rowSum+=statArrays[i][j];
            }
            stats[i] = rowSum - min;
        }
        int[] raceBonus = Races.racialBonuses(race);
        for(int i = 0; i < stats.length; i++){
            stats[i] += raceBonus[i];
        }
        calcStatBonuses();
    }

    /**
     * Calculates the stat bonuses from the stats.
     */
    public void calcStatBonuses(){
        for(int i = 0; i < stats.length; i++){
            bonuses[i] = Stats.statBonusCalc(stats[i]);
        }
    }

    /**
     * Generates random proficiencies for the character.
     * @param maxPossible Max possible number of proficiencies.
     */
    public void randomProfs(int maxPossible){
        int profNum = DieRolling.pureRandom(maxPossible);
        while(profNum > 0){
            String potSkill = Skills.SKILLS[DieRolling.pureRandom(Skills.SKILLS.length-1)];
            if(profs.indexOf(potSkill) == -1)
                profs.add(potSkill);
            else
                profNum++;
            profNum--;
        }
    }

    public void randomType(){
        this.type = Types.randomType();
    }

    /**
     * Teaches a skill to a character
     * @param skill The string corresponding to a skill.
     */
    public void learnSkill(String skill){
        if(General.exists(skill, Skills.SKILLS_AND_SAVES)){
            if(profs.indexOf(skill) == -1){
                profs.add(skill);
            }
        }
    }


    /**
     * Perform a check for a certain stat, being a d20 + the modifier.
     * @param skill The skill to check the stat bonus for
     * @return The stat bonus, or 0 if failure.
     */
    public int statCheck(String skill){
        int mod = 0;
        if(General.exists(skill, Skills.SKILLS_AND_SAVES)){
            if(profs.indexOf(skill) != -1){
                mod += profBonus;
            }
            mod += bonuses[Stats.stringToIndex(Skills.SKILLS_AND_SAVES_ATTS[General.indexOf(skill, Skills.SKILLS_AND_SAVES)])];
        }
        return DieRolling.rollDie(20, mod);
    }

    /**
     * Generate a random race.
     * @param tiers The 'rarity tier' array of the race.
     */
    public void randomRace(boolean[] tiers){
        int[] raceBonus = Races.racialBonuses(race);
        for(int i = 0; i < stats.length; i++){
            stats[i] -= raceBonus[i];
        }
        race = Races.randomRace(tiers);
        raceBonus = Races.racialBonuses(race);
        for(int i = 0; i < stats.length; i++){
            stats[i] += raceBonus[i];
        }
        calcStatBonuses();
    }

    /**
     * Sets the race of the character.
     * @param inRace The race to set the character to.
     */
    public void setRace(String inRace){
        int[] raceBonus = Races.racialBonuses(race);
        for(int i = 0; i < stats.length; i++){
            stats[i] -= raceBonus[i];
        }
        race = inRace;
        raceBonus = Races.racialBonuses(race);
        for(int i = 0; i < stats.length; i++){
            stats[i] += raceBonus[i];
        }
        calcStatBonuses();
    }

    /**
     * Getter for the race of a character.
     * @return The race string of a character
     */
    public String getRace(){
        return race;
    }

    /**
     * Getter for the description of a character
     * @return The description of a character
     */
    public String getDesc(){
        return desc;
    }

    /**
     * Setter for the description of a character
     * @param nDesc The new description for a character
     */
    public void setDesc (String nDesc){
        desc = nDesc;
    }

    /**
     * Getter for the class of a character
     * @return The class of a character
     */
    public String getType(){
        return type;
    }

    /**
     * Setter for the type of a character
     * @param nType New class for the character
     */
    public void setType(String nType) {
        type = nType;
    }

    /**
     * Getter for the name of a character
     * @return The name of the character
     */
    public String getName(){
        return name;
    }

    /**
     *  Setter for the name of a character
     * @param nName Sets the name of the character to nName
     */
    public void setName(String nName){
        name = nName;
    }

    /**
     * Represents the character as a string.
     * @return Formatted string representing a character object.
     */
    public String toString(){
        String end = "";
        end+="Name: " + name + "\nDesc: " + desc + "\nRace: " + race + "\nClass: Level " + level + " " + type;
        for(int i = 0; i < stats.length; i++){
            if(i%2 == 0)
                end += "\n";
            else
                end += "\t";
            end+= Stats.STAT_ABBREVIATIONS[i] + ": " + stats[i] + "\tBonus: " + bonuses[i];
        }
        if(!profs.isEmpty()) {
            end+="\nProficiencies: ";
            for(int i = 0; i < profs.size(); i++){
                end+= profs.get(i);
                if(i != profs.size()-1)
                    end+=",";
                end+= " ";
                if(i % 4 == 3){
                    end+="\n";
                }
            }
        }
        return end;
    }
}
