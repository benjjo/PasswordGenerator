import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class CharLists {
    public static final List<Character> ALPHA = new ArrayList<>();
    public static final List<Character> ALPHA_NUMERIC = new ArrayList<>();
    public static final List<Character> NUMERIC = new ArrayList<>();
    public static final List<Character> ALPHA_NUMERIC_SPECIAL = new ArrayList<>();
    public static final List<Character> BAD_CAF_LIST = new ArrayList<>();
    public static final List<Character> REQUIRED_CAF_LIST = new ArrayList<>();
    public static List<Character> CAF_LIST;
    public static String Strength = "CAF Type";

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
        //Adds keyboard chars to the ALPHA_NUMERIC_SPECIAL
        for(int i = 33; i <= 126; i++ ) {
            CharLists.ALPHA_NUMERIC_SPECIAL.add((char)i);
        }

        // Sets up the BAD_CAF_LIST
        CharLists.BAD_CAF_LIST.add('+');
        CharLists.BAD_CAF_LIST.add('&');
        CharLists.BAD_CAF_LIST.add('\"');
        CharLists.BAD_CAF_LIST.add('\'');
        CharLists.BAD_CAF_LIST.add('`');

        // Sets up the REQUIRED_CAF_LIST
        CharLists.REQUIRED_CAF_LIST.add('!');
        CharLists.REQUIRED_CAF_LIST.add('@');
        CharLists.REQUIRED_CAF_LIST.add('#');
        CharLists.REQUIRED_CAF_LIST.add('$');
        CharLists.REQUIRED_CAF_LIST.add('%');
        CharLists.REQUIRED_CAF_LIST.add('?');

        // Sets up the GOOD_CAF_LIST
        CharLists.CAF_LIST = new ArrayList<>(CharLists.ALPHA_NUMERIC_SPECIAL);
        for(Character i : CharLists.BAD_CAF_LIST){
            CharLists.CAF_LIST.remove(i);
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
    public static String generator(int length) {

        int start = 0, finish;
        List<Character> aList;
        StringBuilder val = new StringBuilder(length);
        String password;

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
        } else if (CharLists.Strength.equals("CAF Type")) {
            finish = CharLists.CAF_LIST.size();
            aList = CharLists.CAF_LIST;
        } else { // Default - How did we even get here?
            finish = CharLists.ALPHA_NUMERIC.size();
            aList = CharLists.ALPHA_NUMERIC;
        }

        password = CharLists.generatorLoop(aList, length, start, finish, val);
        while(CharLists.CAFIntegrityCheck(password)){
            val.delete(start, finish);
            password = CharLists.generatorLoop(aList, length, start, finish, val);
        }
        return password;
    }

    /**
     * Filters out the CAF non compliant passwords and generates another until the specification is met.
     *
     * @return false if tests passed or strength is not of "CAF Type", true otherwise.
     */
    public static boolean CAFIntegrityCheck(String password){

        if(!CharLists.Strength.equals("CAF Type")){
            return false;
        } else {
            return !(CharLists.testForFourCharacters(password) &&
                    CharLists.testForRepeatedChars(password) &&
                    CharLists.testForRequiredChar(password) &&
                    CharLists.testForNumericChar(password));
        }
    }

    /**
     * Tests to see if the password has at least 4 numeric characters
     *
     * @return true if test passed, false otherwise.
     */
    public static boolean testForFourCharacters(String password){
        int alphaCount = 0;
        for(Character alpha : CharLists.ALPHA) {
            if (password.contains(alpha.toString())) {
                alphaCount++;
            }
        }
        // Test for alpha character count < 4
        if(alphaCount < 4){
            return false;   // Fails
        }
        return true;        // Passes test
    }

    /**
     * Tests for repeated characters. More than 2 repeated characters are rejected.
     *
     * @return true if test passed, false otherwise.
     */
    public static boolean testForRepeatedChars(String password){
        char[] charArray = password.toCharArray();
        for(Character repeated : charArray){
            long count = password.chars().filter(ch -> ch == repeated).count();
            if(count > 2){
                return false;   // Fails test
            }
        }
        return true;            // Passes test
    }

    /**
     * Tests that at least one required character is present.
     *
     * @return true if test passed, false otherwise.
     */
    public static boolean testForRequiredChar(String password){
        for(Character good : CharLists.REQUIRED_CAF_LIST){
            if(password.contains(good.toString())){
                return true;
            }
        }
        return false;
    }

    /**
     * Tests for at least one numeric character.
     *
     * @return true if test passed, false otherwise.
     */
    public static boolean testForNumericChar(String password){
        for(Character num : CharLists.NUMERIC){
            if(password.contains(num.toString())){
                return true;
            }
        }
        return false;
    }

    /**
     * Generates a random password into a StringBuilder
     */
    public static String generatorLoop(List<Character> aList, int length, int start, int finish, StringBuilder val){
        for (int i = 0; i < length; i++) {
            val.append((char) aList.get(ThreadLocalRandom.current().nextInt(start, finish)));
        }
        return val.toString();
    }
}