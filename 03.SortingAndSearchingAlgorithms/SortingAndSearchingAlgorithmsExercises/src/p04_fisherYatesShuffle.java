import java.util.Arrays;
import java.util.Collection;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class p04_fisherYatesShuffle {
    public static void main(String[] args) {


        int[] cards = new int[52];
        for (int i = 1; i <= 52; i++) {
            cards[i - 1] = i;
        }

        Random rnd = new Random();

        for (int i = 0; i < cards.length; i++) {
            // Exchange cards[i] with random element in array[i … n-1]
            int randomIndex = rnd.nextInt(cards.length);

            int temp = cards[i];
            cards[i] = cards[randomIndex];
            cards[randomIndex] = temp;
        }


        System.out.println(Arrays.toString(cards));
        Arrays.sort(cards);
        System.out.println(Arrays.toString(cards));
    }
}
