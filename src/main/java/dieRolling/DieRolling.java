package dieRolling;
/*
 * Brant Eckert, 2019
 * Controls the rolling of dice with a bunch of static methods.
 */
public class DieRolling {
    /**
     * Rolls a dice of a certain value and number with modifier.
     * @param dieValue Value of the dice (face)
     * @param dieNumber Number of dice
     * @param modifier The modifier to the roll
     * @return The roll total
     */
    public static int rollDice(int dieValue, int dieNumber, int modifier){
        return rollDice(dieValue, dieNumber) + modifier*dieNumber;
    }

    /**
     * Roll a number of dice of a certain value and number
     * @param dieValue Value of the dice to be rolled
     * @param dieNumber Number of dice to roll
     * @return The roll total.
     */
    public static int rollDice(int dieValue, int dieNumber){
        int total = 0;
        int decrement = dieNumber;
        while(decrement > 0){
            total += rollDie(dieValue);
            decrement--;
        }
        return total;
    }

    /**
     * Rolls a single dice with a modifier.
     * @param dieValue Value to roll
     * @param modifier Modifier to add
     * @return Returns the total value of the dice rolled.
     */
    public static int rollDie(int dieValue, int modifier){
        return rollDie(dieValue) + modifier;
    }

    /**
     * Rolls a single dice of a certain value without a modifier
     * @param dieValue Value to roll.
     * @return Rolled dice.
     */
    public static int rollDie(int dieValue){
        return (int)(Math.random() * dieValue) + 1;
    }

    /**
     * Rolls a number of dice with a modifier and presents its result in an array.
     * @param dieValue Value of the rolled dice.
     * @param dieNumber Number of dice to roll.
     * @param modifier Modifier to add to EACH roll.
     * @return An array of integers, the result of each rolled dice.
     */
    public static int[] rollDieArray(int dieValue, int dieNumber, int modifier){
        int[] total = new int[dieNumber];
        for(int i = 0; i < dieNumber; i++) {
            total[i] = rollDie(dieValue, modifier);
        }
        return total;
    }

    /**
     * Rolls an array of dice without modifier
     * @param dieValue Value of the rolled dice.
     * @param dieNumber Number of dice to roll.
     * @return An array of integers representing the dice rolled.
     */
    public static int[] rollDieArray(int dieValue, int dieNumber){
        int[] total = new int[dieNumber];
        for(int i = 0; i < dieNumber; i++) {
            total[i] = rollDie(dieValue);
        }
        return total;
    }

    /**
     * An abstraction of the random function that rolls between n and 1.
     * @param upperBound Upper bound of the roll.
     * @return The number randomized
     */
    public static int pureRandom(int upperBound){
        return (int)(Math.random() * upperBound+1);
    }

    /**
     * An abstraction of the random function that rolls between n and a lower bound.
     * @param upperBound The upper bound of the roll
     * @param lowerBound The lower bound of the roll.
     * @return The random number generated.
     */
    public static int pureRandom(int upperBound, int lowerBound){
        return (int)(Math.random() * (upperBound-lowerBound+1)) + lowerBound;
    }
}
