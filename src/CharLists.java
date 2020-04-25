import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class CharLists {
    public static final List<Character> ALPHA = new ArrayList<>();
    public static final List<Character> ALPHA_NUMERIC = new ArrayList<>();
    public static final List<Character> NUMERIC = new ArrayList<>();
    public static final List<Character> ALPHA_NUMERIC_SPECIAL = new ArrayList<>();
    public static String Strength = "Strong";

    /**
     * Constructor for instantiation of the ArrayLists.
     */
    public CharLists(){
        //Adds the uppercase letters.
        for(int i = 65; i <= 90; i++ ) {
            CharLists.ALPHA.add((char)i);
            CharLists.ALPHA_NUMERIC.add((char)i);
        }
        // Adds the lower case letters.
        for(int i = 97; i <= 122; i++ ) {
            CharLists.ALPHA.add((char)i);
            CharLists.ALPHA_NUMERIC.add((char)i);
        }
        //Adds the numbers
        for(int i = 48; i <= 57; i++ ) {
            CharLists.ALPHA_NUMERIC.add((char)i);
            CharLists.NUMERIC.add((char)i);
        }
        //Adds all.
        for(int i = 33; i <= 126; i++ ) {
            CharLists.ALPHA_NUMERIC_SPECIAL.add((char)i);
        }
    }

    /**
     * Setter for the Strength string.
     */
    public static void setStrength(String strength){
        CharLists.Strength = strength;
    }

    /**
     * Generates a random char between 33-128 and assigns that char to the
     * string var to the length of the args argument.
     */
    public static StringBuilder generator(int length) {

        StringBuilder val = new StringBuilder(length);
        int start = 0, finish;
        List<Character> aList;

        if (CharLists.Strength.equals("Medium")) {
            finish = CharLists.ALPHA_NUMERIC.size();
            aList = CharLists.ALPHA_NUMERIC;
        } else if (CharLists.Strength.equals("Weak")) {
            finish = CharLists.ALPHA.size();
            aList = CharLists.ALPHA;
        } else if (CharLists.Strength.equals("Numeric")) {
            finish = CharLists.NUMERIC.size();
            aList = CharLists.NUMERIC;
        } else if (CharLists.Strength.equals("Strong")) {
            finish = CharLists.ALPHA_NUMERIC_SPECIAL.size();
            aList = CharLists.ALPHA_NUMERIC_SPECIAL;
        } else { // Default
            finish = CharLists.ALPHA_NUMERIC.size();
            aList = CharLists.ALPHA_NUMERIC;
        }
        for (int i = 0; i < length; i++) {
            val.append((char) aList.get(ThreadLocalRandom.current().nextInt(start, finish)));
        }
        return val;
    }

}