import java.util.Scanner;

public class p02_rectangleIntersection {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = Integer.parseInt(in.nextLine());

        Rectangle[] rectangles = new Rectangle[n];
        for (int i = 0; i < n; i++) {
            String[] rectArgs = in.nextLine().split(" ");
            int x1 = Integer.parseInt(rectArgs[0]);
            int y1 = Integer.parseInt(rectArgs[2]);
            int x2 = Integer.parseInt(rectArgs[1]);
            int y2 = Integer.parseInt(rectArgs[3]);

            Rectangle rectangle = new Rectangle(x1, y1, x2, y2);

            rectangles[i] = rectangle;
        }

        int[][] matrix = new int[2001][2001];
        for (int i = 0; i < rectangles.length; i++) {
            Rectangle rect = rectangles[i];

            int startX = rect.x1 + 1000;
            int endX = rect.x2 + 1000;
            int startY = rect.y1 + 1000;
            int endY = rect.y2 + 1000;

            for (int x = startX; x < endX; x++) {
                for (int y = startY; y < endY; y++) {
                    matrix[x][y]++;
                }
            }
        }

        int SI = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] > 1) {
                    SI++;
                }
            }
        }

        System.out.println(SI);
    }

    private static class Rectangle {
        private int x1;
        private int y1;
        private int x2;
        private int y2;

        public Rectangle(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }
}