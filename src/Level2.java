import java.util.Arrays;
import java.util.Comparator;

public class Level2 {

    // Challenge #3
    //    Input:
    //        Solution.solution({"1.11", "2.0.0", "1.2", "2", "0.1", "1.2.1", "1.1.1", "2.0"})
    //    Output:
    //        0.1,1.1.1,1.2,1.2.1,1.11,2,2.0,2.0.0
    //
    //    Input:
    //        Solution.solution({"1.1.2", "1.0", "1.3.3", "1.0.12", "1.0.2"})
    //    Output:
    //        1.0,1.0.2,1.0.12,1.1.2,1.3.3
    public static String[] solution(String[] l) {
        Comparator<String> comparator = (s1, s2) -> {
            int[] split1 = Arrays.stream(s1.split("\\.")).mapToInt(Integer::parseInt).toArray();
            int[] split2 = Arrays.stream(s2.split("\\.")).mapToInt(Integer::parseInt).toArray();
            int level = 0;

            do {
                // check if current level values are equal
                if (split1[level] == split2[level]) {
                    int nextLevelLength = level + 2;
                    // check if its safe to read next level's values (to avoid out of bounds exception)
                    if (split1.length >= nextLevelLength && split2.length >= nextLevelLength) {
                        level++;
                    } else {
                        // order based on length
                        return split1.length >= nextLevelLength ? 1 : -1;
                    }
                } else break;
            } while (level < 3);

            // compare value based on the last checked level
            return Integer.compare(split1[level], split2[level]);
        };

        Arrays.sort(l, comparator);
        return l;
    }

    // Challenge #2
    //    Input:
    //    Solution.solution({1, 2, 3, 4}, 15)
    //    Output:
    //    -1,-1
    //
    //    Input:
    //    Solution.solution({4, 3, 10, 2, 8}, 12)
    //    Output:
    //    2,3
    public static int[] solution(int[] l, int t) {
        int[] result = new int[2];
        boolean success = false;

        // outer loop
        for (int i = 0; i < l.length; ++i) {
            int currentValue = l[i];

            if (currentValue == t) {
                result[0] = i;
                result[1] = i;
                success = true;
                break;
            } else if (currentValue > t) {
                break;
            }

            // inner loop
            for (int j = i + 1; j < l.length; ++j) {
                currentValue += l[j];
                if (currentValue == t) {
                    result[0] = i;
                    result[1] = j;
                    success = true;
                    break;
                } else if (currentValue > t) {
                    break;
                }
            }
        }

        if (!success) {
            result[0] = -1;
            result[1] = -1;
        }

        return result ;
    }
}
