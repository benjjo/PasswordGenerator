
import java.util.*;

public class CharLists {
    public static final List<Character> ALPHA = new ArrayList<>();
    public static final List<Character> ALPHA_NUMERIC = new ArrayList<>();
    public static final List<Character> NUMERIC = new ArrayList<>();
    public static final List<Character> ALPHA_NUMERIC_SPECIAL = new ArrayList<>();

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

}
