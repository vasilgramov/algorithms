import java.util.*;

public class p02_cableMerchant {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String[] pricesAsStrings = in.nextLine().split("\\s+");
        int[] cablePrices = new int[pricesAsStrings.length];
        for (int i = 0; i < pricesAsStrings.length; i++) {
            cablePrices[i] = Integer.parseInt(pricesAsStrings[i]);
        }

        int connectorPrice = Integer.parseInt(in.nextLine());

        for (int i = 1; i < cablePrices.length; i++) {
            int cableLength = i + 1;
            int cablePrice = cablePrices[i];
            Set<Integer> usedCables = new HashSet<>();
            for (int j = i - 1 ; j >= 0; j--) {
                if (usedCables.contains(j)) {
                    continue;
                }

                int currentCableLength = j + 1;
                int currentCablePrice = cablePrices[j];

                int secondCablePrice = cablePrices[cableLength - currentCableLength - 1];
                usedCables.add(currentCableLength);
                usedCables.add(cableLength - currentCableLength - 1);

                int bestPrice = currentCablePrice + secondCablePrice - (2 * connectorPrice);
                if (bestPrice > cablePrices[i]) {
                    cablePrices[i] = bestPrice;
                }
            }
        }

        for (int cablePrice : cablePrices) {
            System.out.print(cablePrice + " ");
        }
    }
}
