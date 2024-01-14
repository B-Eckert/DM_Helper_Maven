package enchants;

/*
 * Brant Eckert, October 2019
 * Unused but basically just the interface for an enchantment with an EnchEffect and a detEnchEffect.
 */
public interface Enchantment {
    /**
     * Do the enchantment effect.
     */
    public void enchEffec();

    /**
     * Determine the enchantment effect.
     * @param power Magnitude of the effect.
     */
    public void detEnchEffec(int power);
}
