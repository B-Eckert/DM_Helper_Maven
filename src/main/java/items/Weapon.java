package items;
import dieRolling.*;

import java.util.ArrayList;
/*
    * Brant Eckert, November 2019
    * Unused, but the idea is that each Weapon has an Attack or Attacks in the array which is a glorified dice roll of a
    * certain type, which is a string. Ex: 2d6 radiant, 1d6 slashing, etc.
*/
public class Weapon extends Item {
    protected ArrayList<Attack> attacks = new ArrayList<>();

    /**
     * Instantiates a blank Weapon
     */
    public Weapon(){
        super();
    }

    /**
     * Instantiates a weapon from a name, description, and Attack field.
     * @param inName Name
     * @param inDesc Description
     * @param inAttack Attack object
     */
    public Weapon(String inName, String inDesc, Attack inAttack){
        super(inName, inDesc);
        attacks.add(inAttack);
    }

    /**
     * Instantiates a weapon from a name, description, and array of Attacks
     * @param inName Name
     * @param inDesc Description
     * @param inAttack Array of Attack objects
     */
    public Weapon(String inName, String inDesc, Attack[] inAttack){
        super(inName, inDesc);
        for(Attack a : inAttack)
            attacks.add(a);
    }

    /**
     * Instantiates a weapon from its name, description, attributes and attack field.
     * @param inName Name
     * @param inDesc Description
     * @param inAtts Attributes
     * @param inAttack Attack
     */
    public Weapon(String inName, String inDesc, String inAtts, Attack inAttack){
        super(inName, inDesc, inAtts);
        attacks.add(inAttack);
    }
    /**
     * Instantiates a weapon from its name, description, attributes and attack array.
     * @param inName Name
     * @param inDesc Description
     * @param inAtts Attributes
     * @param inAttack Attack array
     */
    public Weapon(String inName, String inDesc, String inAtts, Attack[] inAttack){
        super(inName, inDesc, inAtts);
        for(Attack a : inAttack)
            attacks.add(a);
    }

    /**
     * Gets the damage caused by an attack from this weapon
     * @param attackNum Which attack to use (1-indexed)
     * @return 0 if invalid attack, the output if a valid attack.
     */
    public int getDamage(int attackNum){
        attackNum--;
        if(attackNum < attacks.size())
            return attacks.get(attackNum).getOutput();
        else
            return 0;
    }

    /**
     * String representation of an attack.
     * @param attackNum Which attack to use (1-indexed)
     * @return The string of the attack or no attack.
     */
    public String getAttack(int attackNum){
        attackNum--;
        if(attackNum < attacks.size())
            return "Attack with " + name + "\n" + attacks.get(attackNum).getAttack();
        else
            return "No such attack.";
    }

    /**
     * String representation of a weapon.
     * @return The string representation of a weapon.
     */
    public String toString(){
        String end = "";
        end += "Name: " + name + "\n" +
                "Description:\n" + desc + "\n";
        if(!attributes.equals(""))
            end += "Attributes: \n" + attributes + "\n";
        if(!attacks.isEmpty()){
            end += "Attacks: \n";
            int curAtt = 1;
            for(int i = 0; i < attacks.size(); i++)
                end += curAtt++ + ": " + attacks.get(i).toString() + "\n";
        }
        if(cost != 0)
            end += "Cost: "  + cost + "\n";
        return end;
    }
}
