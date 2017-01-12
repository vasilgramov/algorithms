public class p03_combinations01 {
    public static void main(String[] args) {
        int startNum = 4;
        int endNum = 8;

        int[] arr = new int[3];
        createComb(arr, 0, startNum, endNum);
    }

    private static void createComb(int[] arr, int i, int startNum, int endNum) {
        if (i == arr.length) {
            print(arr);
            return;
        }

        for (int j = startNum; j <= endNum; j++) {
            arr[i] = j;
            createComb(arr, i + 1, j, endNum);
        }
    }

    private static void print(int[] arr) {
        System.out.println(arr[0] + " " + arr[1] + " " + arr[2]);
    }
}
