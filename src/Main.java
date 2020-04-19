import java.util.Random;

public class Main {

    private Random rand;

    public static void main(String[] args){
        new Main(8);
    }

    public Main(int args){
        this.rand = new Random();
        System.out.println(this.generator(args));
    }

    /**
     * Sets a random int to the
     */
    public String generator(int args){
        String val = "";
        for(int i = 0; i < args; i++){
            val = val + Integer.toHexString(rand.nextInt(15));
        }
        return val;
    }
}
