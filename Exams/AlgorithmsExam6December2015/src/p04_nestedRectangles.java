import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p04_nestedRectangles {
    
    private static List<Rectangle> rectangles;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        rectangles = new ArrayList<>();

        String command = in.readLine();
        while (!"End".equals(command)) {
            String[] rectArgs = command.split(": ");
            String name = rectArgs[0];
            int[] coords = stringsToIntegers(rectArgs[1].split("\\s+"));

            Rectangle rectangle = new Rectangle(name, coords[0], coords[1], coords[2], coords[3]);
            rectangles.add(rectangle);

            command = in.readLine();
        }

        Rectangle best = null;
        for (int i = 0; i < rectangles.size(); i++) {
            Rectangle rectangle = rectangles.get(i);
            findNestedRectangles(rectangle);

            if (best == null ||
                    rectangle.depth > best.depth ||
                    rectangle.depth == best.depth && rectangle.name.compareTo(best.name) < 0) {
                best = rectangle;
            }
        }

        StringBuilder result = new StringBuilder();
        while (best != null) {
            result.append(best).append(" < ");
            best = best.bestNested;
        }

        System.out.println(result.substring(0, result.length() - 3));
    }

    private static void findNestedRectangles(Rectangle rectangle) {
        if (rectangle.depth > 0) {
            return;
        }

        Rectangle bestNested = null;

        rectangle.depth = 1;

        for (int i = 0; i < rectangles.size(); i++) {
            Rectangle current = rectangles.get(i);
            if (!current.equals(rectangle) && 
                    isInside(current, rectangle)) {
                findNestedRectangles(current);
                if (bestNested == null ||
                        current.depth > bestNested.depth ||
                        (current.depth == bestNested.depth && (current.name.compareTo(bestNested.name)) < 0)) {
                    bestNested = current;
                }
            }
        }

        if (bestNested != null) {
            rectangle.depth = bestNested.depth + 1;
            rectangle.bestNested = bestNested;
        }
    }

    private static boolean isInside(Rectangle current, Rectangle rectangle) {
        return current.x1 >= rectangle.x1 &&
                current.y1 <= rectangle.y1 &&
                current.x2 <= rectangle.x2 &&
                current.y2 >= rectangle.y2;
    }

    private static int[] stringsToIntegers(String[] numbersAsString) {
        int[] parsedNumbers = new int[numbersAsString.length];

        for (int i = 0; i < numbersAsString.length; i++) {
            parsedNumbers[i] = Integer.parseInt(numbersAsString[i]);
        }


        return parsedNumbers;
    }

    private static class Rectangle {
        private String name;
        private int x1;
        private int y1;
        private int x2;
        private int y2;
        private int depth;

        private Rectangle bestNested;

        public Rectangle(String name, int x1, int y1, int x2, int y2) {
            this.name = name;
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }
}
