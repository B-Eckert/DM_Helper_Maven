import people.*;
import java.io.IOException;
import java.util.Scanner;

/*
 * Brant Eckert, September 2019
 * Main is just updated whenever I need outputs, controls NPC generation methods
 */

public class Main {
    private static Scanner jin = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        menu();
    }

    /**
     * Generates random NPCs
     * @param number The number of random NPCs to generate.
     * @throws IOException Throws an IOException if it fails to log the character generator
     */
    public static void genRandoms(int number) throws IOException {
        for(int i = 0; i < number; i++) {
            NPC example = new NPC("Random NPC", "A randbob", "Level 0 Rando", "Human");
            example.randomRace(Races.genTiers(true, true, true, true, true, true));
            example.randomType();
            example.setType("Level 0 " + example.getType());
            ChargenLog.logWithTime(example.toString());
        }
    }

    /**
     * Generates random NPCs with an additional field of 'broken' to determine if they are boss-level characters
     * @param number The number of random NPCs to generate
     * @param broken True if stats > 20, false if stats < 20
     * @throws IOException Exception thrown when the fails to log the character generator
     */
    public static void genRandoms(int number, boolean broken) throws IOException {
        for(int i = 0; i < number; i++) {
            NPC example = new NPC("Random NPC", "A randbob", "Level 0 Rando", "Human");
            example.randomRace(Races.genTiers(true, true, true, true, true, true));
            example.randomType();
            example.setType("Level 0 " + example.getType());
            if(broken){
                example.randomStatsPowerful();
            }
            ChargenLog.logWithTime(example.toString());
        }
    }

    /**
     * Generates a character of a specific race in D&D
     * @param race The race of the character to generate
     * @param number Number of characters to generate
     * @throws IOException Thrown when ChargenLog fails to log the character.
     */
    public static void genSpecificRace(String race, int number) throws IOException{
        for(int i = 0; i < number; i++) {
            NPC example = new NPC("Random " + race, "A random " + race, "Level 0 " + Types.randomType(), race);
            ChargenLog.logWithTime(example.toString());
        }
    }

    /**
     * Generates a number of possibly broken characters of a specific race
     * @param race The race of the character to generate
     * @param number The number of characters to generate
     * @param broken True if stats > 20 false if stats < 20
     * @throws IOException Thrown when ChargenLog fails to log the character.
     */
    public static void genSpecificRace(String race, int number, boolean broken) throws IOException{
        for(int i = 0; i < number; i++) {
            NPC example = new NPC("Random " + race, "A random " + race, "Level 0 " + Types.randomType(), race);
            if(broken){
                example.randomStatsPowerful();
            }
            ChargenLog.logWithTime(example.toString());
        }
    }

    /**
     * Generates random valuables for random loot purposes.
     * @param chestTier The tier of the chest
     * @param rollBonus The bonus to the loot roll
     * @param number The number of valuables to generate
     * @throws IOException Thrown when ChargenLog fails to log the character.
     */
    public static void genValbs (int chestTier, int rollBonus, int number) throws IOException {
        for(int i = 0; i < number; i++){
            ChargenLog.logWithTime(LootGenerator.valuablesGen(chestTier, rollBonus).toString());
        }
    }

    /**
     * Added menu functionality.
     * @return Returns 0 if all is well, -1 if there is an error
     * @throws IOException Thrown when ChargenLog fails to log the character.
     */
    public static int menu() throws IOException {
        boolean operating = true;
        while(operating){
            System.out.println("" +
                    " 1) Random NPC\n" +
                    "-1) Quit");
            int opt = jin.nextInt();
            jin.nextLine();
            if(opt == 1){
                System.out.println("1) Include name\n" +
                        "2) Include name & desc\n" +
                        "3) Include name, desc and class\n" +
                        "4) Include name, desc, class and race\n" +
                        "5) No details (Default person)\n");
                opt = jin.nextInt();
                jin.nextLine();
                NPC generated = new NPC();
                if(opt == 1) {
                    System.out.print("Name:");
                    System.out.flush();
                    String name = jin.nextLine();
                    generated = new NPC(name);
                }
                else if(opt == 2){
                    System.out.print("Name:");
                    String name = jin.nextLine();
                    System.out.print("Desc:");
                    String desc = jin.nextLine();
                    generated = new NPC(name, desc);
                }
                System.out.println(generated.toString());
                ChargenLog.logWithTime(generated.toString());
            }
            else if(opt == -1)
                operating = false;
        }
        return 0;
    }
}
