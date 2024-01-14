package tools;
/*
 * Brant Eckert, September 2019
 * A general "toolbox" of all of the helper methods that Java usually doesn't have that I enjoy using.
 */
public class General {
    /**
     * Merges two arrays of ints together
     * @param n first array
     * @param f second array
     * @return output array
     */
    public static int[] mergeIntArrays(int[] n, int[] f){
        int[] q = new int[n.length + f.length];
        int numDiff = 0;
        if(n.length > 0) {
            for (int i = 0; i < n.length; i++) {
                q[i] = n[i];
            }
            numDiff = n.length;
        }
        if(f.length > 0) {
            for (int i = 0; i < f.length; i++) {
                q[i + numDiff] = f[i];
            }
        }
        return q;
    }

    /**
     * Merges two string arrays together
     * @param n the first array
     * @param f the second array
     * @return the merged first and second array
     */
    public static String[] mergeStringArrays(String[] n, String[] f){
        String[] q = new String[n.length + f.length];
        int numDiff = 0;
        if(n.length > 0) {
            for (int i = 0; i < n.length; i++) {
                q[i] = n[i];
            }
            numDiff = n.length;
        }
        if(f.length > 0) {
            for (int i = 0; i < f.length; i++) {
                q[i + numDiff] = f[i];
            }
        }
        return q;
    }

    /**
     * Checks to see if a string f exists in a string array n
     * @param f The string to look for
     * @param n the string array to search
     * @return Whether or not the string f exists in n
     */
    public static boolean exists(String f, String[] n){
        for(String i:n){
            if(f.equals(i))
                return true;
        }
        return false;
    }

    /**
     * Gets the index of a string f in a string array n
     * @param f The string to look for
     * @param n The string array to search
     * @return The index of f in n
     */
    public static int indexOf(String f, String[] n){
        for(int i = 0; i < n.length; i++){
            if(f.equals(n[i]))
                return i;
        }
        return -1;
    }

    /**
     * An array stretcher, similar to a breastplate stretcher. Increases the length to twice the current length plus one.
     * @param arr Array to stretch
     * @return The array stretched to 2n+1, with n being its former size.
     */
    public static int[] stretchIntArray(int[] arr){
        int[] f = new int[arr.length * 2 + 1];
        for(int i = 0; i < arr.length; i++){
            f[i] = arr[i];
        }
        return f;
    }

    /**
     * An array stretcher, similar to a breastplate stretcher. Increases the length to twice the current length plus one.
     * @param arr Array to stretch
     * @return The array stretched to 2n+1, with n being its former size.
     */
    public static String[] stretchStringArray(String[] arr){
        String[] f = new String[arr.length * 2 + 1];
        for(int i = 0; i < arr.length; i++){
            f[i] = arr[i];
        }
        return f;
    }
}
