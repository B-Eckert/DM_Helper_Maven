package dieRolling;

import java.util.ArrayList;

/*
 * Brant Eckert, September 2019
 * Dice rolling functions
 */

public class DieRoll {
  //  protected int[] numbers = {1};
  //  protected int[] faces = {4};
    // The number of different dice amounts
    protected ArrayList<Integer> numbers = new ArrayList<Integer>();
    // The corresponding faces
    protected ArrayList<Integer> faces = new ArrayList<Integer>();

    /**
     * Initializes a DieRoll object
     */
    public DieRoll(){ }

    /**
     * Initializes a DieRoll object with a number of 1 type of dice
     * @param inNum number of dice
     * @param inFaces face of the dice
     */
    public DieRoll(int inNum, int inFaces){
        numbers.add(inNum);
        faces.add(inFaces);
    }

    /**
     * Initializes a DieRoll object with any number of any type of dice.
     * @param inNums Number of dice in an array
     * @param inFaces Faces of the dice  in an array
     */
    public DieRoll(int[] inNums, int[] inFaces){
        for(int i : inNums)
            numbers.add(i);
        for(int i : inFaces)
            faces.add(i);
    }

    /**
     * Rolls all the dice in the DiceRoll and returns the summed output
     * @return Returns the sum of all rolled values
     */
    public int getOutput(){
        int sum = 0;
        for(int i = 0; i < numbers.size(); i++){
            sum += DieRolling.rollDice(faces.get(i), numbers.get(i));
        }
        return sum;
    }

    /**
     * Adds a new die to the diceroll.
     * @param dieNum Number of dice
     * @param dieFace Face of the dice
     */
    public void addDie(int dieNum, int dieFace){
        numbers.add(dieNum);
        faces.add(dieFace);
    }

    /**
     * Translates the dice roll into a string.
     * @return The dice roll represented as a string.
     */
    public String toString(){
        String end = "";
        for(int i = 0; i < numbers.size(); i++){
            end += numbers.get(i) + "d" + faces.get(i);
            if(i != numbers.size() - 1){
                end += " + ";
            }
        }
        return end;
    }
}
