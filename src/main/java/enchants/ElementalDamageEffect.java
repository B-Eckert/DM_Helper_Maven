package enchants;

import dieRolling.DieRoll;
import dieRolling.DieRolling;

import java.util.ArrayList;
/*
 * Brant Eckert, September 2019
 * Unused but the basic idea was that there'd be an elemental effect that's just damage and certain power level that would correspond to a numerical bonus in the roll.
 * Was planned to be abstracted so it had a die roll, n, that implemented the roll.
 */
public class ElementalDamageEffect implements Enchantment {
    DieRoll n = new DieRoll(1, 1);

    public ElementalDamageEffect(){

    }
    public ElementalDamageEffect(int power){
        detEnchEffec(power);
    }

    @Override
    public void enchEffec() {

    }

    @Override
    public void detEnchEffec(int power) {
        if(power <= 0)
            power = 1;
        //ok so power is gonna be like tier, if we have a very powerful object its gonna be tier 7 whereas weak is like tier 1
        //bonus damage die = 1d4, 6, 8, 10, and 12, so fundamentally a difference of 4*2 (1 - 5) ; ( 2 - 10 ) ; (4 - 12)
        // Tier 1 : 1d4 bonus // 1:4
        // Tier 2 : 1d6 bonus // 1:6
        // Tier 3 : 1d8 bonus // 1:8
        // Tier 4 : 2d4 / 1d10 bonus // 2:8;1:10
        // Tier 5 : 1d12 bonus // 1:12
        // Tier 6 : 2d6 bonus // 2:12 // 14
        // Tier 7 : 3d4 // 16
        // Tier 8 : 2d8 bonus // 2:16 // 18
        // Tier 9 : 3:16 // 20
        // Tier 10 : 4d4 / 3d6 / 2d10 //22
        // Tier 11 : DNE
        // Tier 12 : 2d12
        // Tier 13 : 5d4 / 3d8
        // So basically the way im dividing tier is by (mindmg-1)*2 + max damage = (t+1) * 2
        // Ex: 1:10 , 2:8
        // (2-1)*2 + 8 = 10 = 5*2 = (4+1)*2
        // or
        // 10 = 10 = 5*2 = (4+1)*2
        // power = tier;
        //3d4
        // 4 + 12 = 16
        /**
         * So every die has a range, right?
         * So we take the min (n * 1) and the max (n * d) and then we put that number together for all die numbers less than that on our scale, which we've calculated is (2-6)*2.
         * (n-1)*2 + dn = (t+1)*2
         * (min-1)*2 + max = (t+1)*2
         * n-1 + dn/2 = t+1
         * dn/2 + n - 1 = t + 1
         * (n)(d/2 + 1) = t + 2
         * n = (t+2)/(d/2+1)
         */
        ArrayList<Integer> numbers = new ArrayList<>();
        ArrayList<Integer> dieFaces = new ArrayList<>();
        for(int i = 2; i <= 6; i++){
            //System.out.println((power+1) + " : "  + i);
            int j = i * 2;
            double targ = (double)(power+2)/(((double)j/2)+1);
            //System.out.println(targ);
            if(targ % 1 <= 0.000000001){
                numbers.add((power+2)/((j/2)+1));
                dieFaces.add(j);
            }
        }
        if(numbers.size() == 0){

            System.out.println("Degraded: " + power );
            this.detEnchEffec(power-1);
        }
        else {
            //System.out.println("Size: " + numbers.size());
            int chosen = DieRolling.pureRandom(numbers.size() - 1, 0);
            n = new DieRoll(numbers.get(chosen), dieFaces.get(chosen));
            ArrayList<DieRoll> rolls = new ArrayList<>();
            for (int i = 0; i < numbers.size(); i++) {
                rolls.add(new DieRoll(numbers.get(i), dieFaces.get(i)));
            }
            System.out.print(power + " : ");
            for (DieRoll i : rolls) {
                System.out.print(i.toString() + " : ");
            }
            System.out.println("\b\b");
        }
    }
}
