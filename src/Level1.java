import java.util.ArrayList;

public class Level1 {

    // Challenge #1
    public static int[] solution(int area) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        int currentArea = area;

        while (currentArea != 0) {
            double root = Math.sqrt(currentArea);

            // Does not have decimal numbers
            if (root - (int) root == 0) {
                list.add((int) currentArea);
                currentArea = area - currentArea;
                area = currentArea;
            } else {
                currentArea--;
            }
        }

         int[] array = new int[list.size()];
         for (int i = 0; i < array.length; ++i) {
             array[i] = list.get(i);
         }

        return array;
    }
}
