package dieRolling;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Attack extends DieRoll {
    // protected String damageTypes[] = {"defaulto"};
    protected ArrayList<String> damageTypes = new ArrayList<>();

    /**
     * Instantiates new attack.
     */
    public Attack(){
        super();
    }

    /**
     * Instantiates new attack with parameters.
     * Example usage: Shortsword of Radiant Energy, new Attack(1, 6, "radiant")
     * @param dieNum The number of dice.
     * @param dieFace The face of the dice.
     * @param dmgType The damage type of the dice
     */
    public Attack(int dieNum, int dieFace, String dmgType){
        super(dieNum, dieFace);
        damageTypes.add(dmgType);
    }

    /**
     * Instantiates a complex attack with multiple types of attacks.
     * Example usage: Longsword of Radiant Energy, new Attack({1,1}, {8,10}, {"radiant", "radiant"})
     * @param dieNums The number of dice.
     * @param dieFaces The face of the dice.
     * @param dmgTypes The damage types of the different attacks.
     */
    public Attack(int[] dieNums, int[] dieFaces, String[] dmgTypes){
        super(dieNums, dieFaces);
        for(String i : dmgTypes)
            damageTypes.add(i);
    }

    /**
     * Rolls an example attack from the dice.
     * @return Text output that shows the roll, numbers, and damage along with damage type.
     */
    public String getAttack(){
        String end = "";
        int sum = 0;
        for(int i = 0; i < numbers.size(); i++) {
            int roll = DieRolling.rollDice(faces.get(i), numbers.get(i));
            sum += roll;
            end += roll + " " + damageTypes.get(i);
            if(i != numbers.size() - 1)
                end += " + ";
            else
                end += " damage";
        }
        end += "\nTotal: " + sum + " damage.";
        return end;
    }

    /**
     * Overrides the 'addDie' method of DiceRoll to include damage type.
     * @param dieNum Number of dice
     * @param dieFace Face of the dice
     */
    @Override
    public void addDie(int dieNum, int dieFace){
        numbers.add(dieNum);
        faces.add(dieFace);
        damageTypes.add("default");
    }

    /**
     * Independent 'addDie' method that includes damage type.
     * @param dieNum Number of dice
     * @param dieFace Face of dice
     * @param damageType Damage type.
     */
    public void addDie(int dieNum, int dieFace, String damageType){
        numbers.add(dieNum);
        faces.add(dieFace);
        damageTypes.add(damageType);
    }

    /**
     * Stringification of the attack.
     * @return A string that details the number, dice, and damage type in order to show how much damage an attack does.
     */
    public String toString(){
        String end = "";
        for(int i = 0; i < numbers.size(); i++){
            end += numbers.get(i) + "d" + faces.get(i) + " " + damageTypes.get(i);
            if(i != numbers.size() - 1)
                end += " + ";
            else
                end += " damage.";
        }
        return end;
    }
}
