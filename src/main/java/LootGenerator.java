import com.sun.source.tree.BinaryTree;
import com.sun.source.tree.ExpressionTree;
import com.sun.source.tree.TreeVisitor;
import items.*;
import dieRolling.*;
import tools.General;
/*
 * Brant Eckert, March 2020
 * Generates random loot, still very unfinished.
 */
public class LootGenerator {
    //TODO: Static loot generation methods for item types, effect generators, list of possible effects, effect weighting, and random value determining whether effects are even generated.
    /*
        TODO Weapons: List of possible names for slashing, bludgeoning, and piercing weapons as well as multiple attack types per weapon. (Ex: A slashing and piercing attack for a sword.)
                      List of possible effects for weapons
                            - Additional die or set numbers of elemental effects
                                - Roll a 1d2 (flip a coin essentially) to see which, then roll the die value/value
                                    - range of 1d2-1d6, or +2-+5
                            - DC Condition Imposition (Exhaustion, frightened, poisoned, grappled)
                            - Life drain with necrotic, always a popular one.
                            - Bonus action attack
                            -
                      List of possible augments for weapons
                            - Sharper/Heavier: Damage die above their weight
                            - +1-3 Weapons (nonmagical in my setting)
                            -
        TODO Armor  : List of possible names for various armors (llamellar, plate, chain, etc)
                      List of possible effects for armors
                            - Staged resistances to elemental effects (cold, fire, acid, etc) using my new resistances system
                            - Thorns-like effects (When hit, do 1d2/1d6 + 1-5 damage in response)
                            - Additional speed (+5-20) with weighting towards 5.
                            -
                      Augments for armors
                            - +1-2 AC
                            - Resistance bonus (+1-3 stages of resistance)
        TODO Items  : List of possible item types
                        Potions, trinkets, jewelries, and enchantments thereof.
                            Potions: Effects potions can have, as well as secret ones I don't actually reveal for shitty potions
                            Trinkets: Effects little knicknacks can have, from minor to major.
                            Jewelries: Effects other objects can have
     */

    /**
     * Generates random loot based on certain ranges.
     * @param chestTier The tier of the chest to generate loot for.
     * @param rollBonus The bonus to the loot roll.
     * @return The loot, represented as an item.
     */
    public static Item LootGen(int chestTier, int rollBonus) {
        Item n = new Item();
        int itemType = DieRolling.pureRandom(100, 1) + chestTier*2;
        if(itemType <= 25) { //item (potion, valuable but not magical jewelry, etc)
            n = PettyLootGen(chestTier, rollBonus);
        }
        else if(itemType <= 50) { //trinket
            n = TrinketGen(chestTier, rollBonus);
        }
        else if(itemType <= 75) { //weapon
            n = WeaponGen(chestTier, rollBonus);
        }
        else if (itemType <= 100) { //armor
            n = ArmorGen(chestTier, rollBonus);
        }

        return n;
    }

    /**
     * Petty loot generator. (Valuables, Potion, Scroll or Trinket)
     * @param chestTier The tier of the chest.
     * @param rollBonus The roll bonus
     * @return The randomly generated petty loot.
     */
    public static Item PettyLootGen(int chestTier, int rollBonus){ // valuables, potions, etc.
        Item f = new Item();
        String nameGen = "", descripGen = "", type = "";
        int roll = DieRolling.pureRandom(1, 30) + rollBonus;
        if(roll < 10)
        {
            type = "valuables";
            f = valuablesGen(chestTier, rollBonus);
        }
        else if(roll < 20) {
            type = "potion";
            f = potGen(chestTier, rollBonus);
        }
        else if(roll < 25){
            type = "scroll";

        }
        else if(roll <= 30) {
            type = "trinket";
            f = TrinketGen(chestTier, rollBonus);
        }
        return f;
    }

    /**
     * Valuable generator.
     * @param chestTier The tier of the chest
     * @param rollBonus The rollbonus
     * @return The randomly generated valuables.
     */
    public static Item valuablesGen(int chestTier, int rollBonus){
        String nameGen = "", descripGen = "";
        int price = DieRolling.pureRandom(1000 + (rollBonus * 10), 50 + (rollBonus * 10)) + chestTier * 100;
        String[] namesAny = {"Jewelry", "Table Settings", "Cups", "Bits"};
        String[] namesRich = {"Ornate Gems", "Jewelled Table Settings", "Ancient Relics"};
        if(price > 500){
            namesAny = General.mergeStringArrays(namesAny, namesRich);
        }
        String[] materialsPoor = {"Stone", "Wooden", "Pewter", "Clay"};
        String[] materialsAverage = {"Iron", "Brass", "Bronze", "Copper"};
        String[] materialsRich = {"Gold", "Silver", "Steel", "Platinum"};
        String[] materials = {};
        if(price < 200){
            materials = General.mergeStringArrays(materials, materialsPoor);
        }
        if(price > 100 && price < 600) {
            materials = General.mergeStringArrays(materials, materialsAverage);
        }
        if(price > 500)
        {
            materials = General.mergeStringArrays(materials, materialsRich);
        }
        String nameComp1 = namesAny[DieRolling.pureRandom(namesAny.length-1, 0)];
        String nameComp2 = "";
        if(!nameComp1.equals("Ornate Gems")){
            nameComp2 = materials[DieRolling.pureRandom(materials.length-1, 0)];
        }
        else
            nameComp2 = "Gemstones";
        nameGen = nameComp1;
        if(!nameComp2.equals("")){
            nameGen = nameComp2 + " " + nameComp1;
        }

        descripGen = "Assorted " + nameComp1 + " made out of " + nameComp2 + " coming to a total value of " + price + " shillings.";
        Item f = new Item(nameGen, descripGen);
        f.setCost(price);
        return f;
    }

    /**
     * TODO: Generates a potion.
     * @param chestTier Tier of the chest found.
     * @param rollBonus Bonus to the loot roll.
     * @return A Potion item.
     */
    public static Item potGen(int chestTier, int rollBonus){
        Item f = new Item();

        return f;
    }

    /**
     * TODO: Generates a weapon found as loot.
     * @param chestTier Tier of the chest found.
     * @param rollBonus Bonus to the loot roll.
     * @return A weapon item representing found loot.
     */
    public static Weapon WeaponGen(int chestTier, int rollBonus){
        Weapon n = new Weapon();
        return n;
    }

    /**
     * TODO: Generate armor found as loot.
     * @param chestTier Tier of the chest found.
     * @param rollBonus Bonus to the loot roll.
     * @return An armor item representing found loot.
     */
    public static Armor ArmorGen(int chestTier, int rollBonus) {
        Armor f = new Armor();
        return f;
    }

    /**
     * TODO: Generate a trinket found as loot.
     * Note: Trinkets don't really do anything, and are effectively flavorful valuable items.
     * @param chestTier Tier of the chest found.
     * @param rollBonus Bonus to the loot roll.
     * @return A generic item that represents the trinket found.
     */
    public static Item TrinketGen(int chestTier, int rollBonus) {
        Item f = new Item();

        return f;
    }
}
