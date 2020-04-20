
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String[] args){
        new Main(8); // temporary magic number
    }

    public Main(int args){
        System.out.println(this.generator(args));
    }

    /**
     * Generates a random char between 33-128 and assigns that char to the
     * string var to the length of the args argument.
     */
    public String generator(int args){
        String val = "";
        for(int i = 0; i < args; i++){
            val = val + (char)ThreadLocalRandom.current().nextInt(33, 128);
        }
        return val;
    }

}
