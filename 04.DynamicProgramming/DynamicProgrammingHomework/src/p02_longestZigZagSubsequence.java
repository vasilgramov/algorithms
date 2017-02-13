import java.util.ArrayList;
import java.util.Scanner;

public class p02_longestZigZagSubsequence {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String[] input = in.nextLine().split(",");
        int[] numbers = new int[input.length];
        for (int i = 0; i < input.length; i++)
            numbers[i] = Integer.parseInt(input[i]);

        ArrayList<Integer> zigZag = getLongestZigZagSubsequence(numbers);
        for (int i = 0; i < zigZag.size(); i++) {
            System.out.print(zigZag.get(i) + " ");
        }

        System.out.println();
    }

    // 8,3,5,7,0,8,9,10,20,20,20,12,19,11
    // 8 3 5 0 20 12 19 11

    /*
    8 3 5 0 8
    8 3 5 0 9

     */

    // 1) Every even element is smaller than its neighbors and every odd element is larger than its neighbors, or
    // 2) Every odd element is smaller than its neighbors and every even element is larger than its neighbors

    private static ArrayList<Integer> getLongestZigZagSubsequence(int[] numbers) {
        ArrayList<ArrayList<Integer>> LSL = new ArrayList<ArrayList<Integer>>();
        ArrayList<ArrayList<Integer>> SLS = new ArrayList<ArrayList<Integer>>();

        LSL.add(new ArrayList<>());     // larger smaller larger
        LSL.get(0).add(numbers[0]);

        SLS.add(new ArrayList<>());    // smaller larger smaller
        SLS.get(0).add(numbers[0]);

        int SLSMaxSize = 1;
        int LSLMaxSize = 1;
        for (int i = 1; i < numbers.length; i++) {
            // TOCHECK: if can't add to anyone copy only the longest

            int currentNumber = numbers[i];


            /*
            If current number is added to any ArrayList and it combines the LSL do not do anything.
            If it is added but does not create longest create new ArrayList with the current longest so far.
            Else get only the first longest ArrayList so far, create new ArrayList and add current number.
             */

            boolean isCreatingLSL = false; // does the current element create longest LSL
            for (int j = 0; j < LSL.size(); j++) {
                ArrayList<Integer> currentLSL = LSL.get(j);
                int lastNumLSL = currentLSL.get(currentLSL.size() - 1);
                boolean largerLSL = false; // find what we search

                int prevLastNumIndex = currentLSL.size() - 2;
                if (prevLastNumIndex >= 0) {
                    int prevLastNumLSL = currentLSL.get(prevLastNumIndex);

                    if (prevLastNumLSL > lastNumLSL)
                        largerLSL = true;
                    else
                        largerLSL = false;
                }

                if (!largerLSL && currentNumber < lastNumLSL) {
                    currentLSL.add(currentNumber);
                    if (currentLSL.size() > LSLMaxSize) {
                        LSLMaxSize = currentLSL.size();
                        isCreatingLSL = true;
                    }
                } else if (largerLSL && currentNumber > lastNumLSL) {
                    currentLSL.add(currentNumber);
                    if (currentLSL.size() > LSLMaxSize) {
                        LSLMaxSize = currentLSL.size();
                        isCreatingLSL = true;
                    }
                }
            }

            if (!isCreatingLSL) {
                ArrayList<Integer> toAdd = new ArrayList<>();
                outerLoop:
                for (int j = 0; j < LSL.size(); j++) {
                    ArrayList<Integer> currentArrayList = LSL.get(j);
                    if (currentArrayList.size() == LSLMaxSize) {
                        for (int k = 0; k < currentArrayList.size() - 1; k++) {
                            toAdd.add(currentArrayList.get(k));
                        }

                        break outerLoop;
                    }
                }

                toAdd.add(currentNumber);
                LSL.add(toAdd);
            }

            boolean isCreatingSLS = false; // does the current element create longest SLS
            for (int j = 0; j < SLS.size(); j++) {
                ArrayList<Integer> currentSLS = SLS.get(j);
                int lastNumSLS = currentSLS.get(currentSLS.size() - 1);
                boolean smallerSLS = false; // find what we search

                int prevLastNumIndex = currentSLS.size() - 2;
                if (prevLastNumIndex >= 0) {
                    int prevLastNumSLS = currentSLS.get(prevLastNumIndex);

                    if (prevLastNumSLS > lastNumSLS)
                        smallerSLS = false;
                    else
                        smallerSLS = true;
                }

                if (!smallerSLS && currentNumber < lastNumSLS) {
                    currentSLS.add(currentNumber);
                    if (currentSLS.size() > SLSMaxSize) {
                        SLSMaxSize = currentSLS.size();
                        isCreatingSLS = true;
                    }
                } else if (smallerSLS && currentNumber > lastNumSLS) {
                    currentSLS.add(currentNumber);
                    if (currentSLS.size() > SLSMaxSize) {
                        SLSMaxSize = currentSLS.size();
                        isCreatingSLS = true;
                    }
                }
            }

            if (!isCreatingSLS) {
                ArrayList<Integer> toAdd = new ArrayList<>();
                outerLoop:
                for (int j = 0; j < SLS.size(); j++) {
                    ArrayList<Integer> currentArrayList = SLS.get(j);
                    if (currentArrayList.size() == SLSMaxSize) {
                        for (int k = 0; k < currentArrayList.size() - 1; k++) {
                            toAdd.add(currentArrayList.get(k));
                        }

                        break outerLoop;
                    }
                }

                toAdd.add(currentNumber);
                SLS.add(toAdd);
            }
        }

        ArrayList<Integer> toReturn = new ArrayList<>();
        if (LSLMaxSize >= SLSMaxSize) {
            for (int i = 0; i < LSL.size(); i++) {
                if (LSL.get(i).size() == LSLMaxSize) {
                    toReturn = LSL.get(i);
                    break;
                }
            }
        } else {
            for (int i = 0; i < SLS.size(); i++) {
                if (SLS.get(i).size() == SLSMaxSize) {
                    toReturn = LSL.get(i);
                    break;
                }
            }
        }

        return toReturn;
    }
}
