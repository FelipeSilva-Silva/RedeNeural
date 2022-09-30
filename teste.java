import java.util.Random;

public class teste {

    public static void main(String[] args) {
        
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            System.out.println(random.nextDouble() * 2 - 1);
        }


    }
}