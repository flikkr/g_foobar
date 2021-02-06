import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;

public class Foobar {
    public static void main(String []args){
//        System.out.println(122 % 123);
        System.out.println(solution("123", "2342324223"));
    }

    //    Problem #4
    /*  [1,1]   - 0     [4,7] - 0       [12,5]
        [2,1]   - 1     [4,3] - 1       [7,5]
        [2,3]   - 2     [1,3] - 2       [2,5]
        [2,5]   - 3     [1,2] - 3       [2,3]
        [7,5]           [1,1] - 4       [2,1]
                                        [1,1]

        problem cases:
        [6666,666]

        impossible:
        [5,0]
        [0,5]
        [15,5]
        [5,5]

    */
    public static String solution(String x, String y) {
        long generations = 0;
        BigInteger xInt = new BigInteger(x);
        BigInteger yInt = new BigInteger(y);

        if (xInt.equals(BigInteger.ONE) && yInt.equals(BigInteger.ONE))
            return String.valueOf(generations);
        if (xInt.equals(BigInteger.ZERO)
                || yInt.equals(BigInteger.ZERO)
                || (xInt.mod(yInt).equals(BigInteger.ZERO) && !yInt.equals(BigInteger.ONE))
                || (yInt.mod(xInt).equals(BigInteger.ZERO) && !xInt.equals(BigInteger.ONE)))
            return "impossible";

        // start from the final value and work your way down
        while (true) {
            if (xInt.equals(BigInteger.ONE) && yInt.equals(BigInteger.ONE))
                return String.valueOf(generations);

            if ((xInt.mod(yInt).equals(BigInteger.ZERO) && !yInt.equals(BigInteger.ONE))
                    || (yInt.mod(xInt).equals(BigInteger.ZERO) && !xInt.equals(BigInteger.ONE))) {
                int i = 0;
            } else {
                int i = 0;
            }

            if ((!xInt.mod(yInt).equals(BigInteger.ZERO) && !yInt.equals(BigInteger.ONE))
                    || (!yInt.mod(xInt).equals(BigInteger.ZERO) && !xInt.equals(BigInteger.ONE))) {
                int comparison = xInt.compareTo(yInt);
                if (comparison == 1) {
                    xInt = xInt.subtract(yInt);
                } else {
                    yInt = yInt.subtract(xInt);
                }
                generations++;
            } else
                return "impossible";
        }
    }

//    public static String replicate() {
//
//    }


    // Problem #3
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

//    public static int compareNextLevel(int[] split1, int[] split2, int level) {
//        if (split1[level] == split2[level] && level < 3) {
//            int nextLevelLength = level + 2;
//            if (split1.length >= nextLevelLength && split2.length >= nextLevelLength) {
//                return compareNextLevel(split1, split2, level + 1);
//            } else {
//                return split1.length >= nextLevelLength ? 1 : -1;
//            }
//        } else {
//            return Integer.compare(split1[level], split2[level]);
//        }
//    }

//    public static String[] solution(String[] l) {
//        Comparator<String> comparator = (s1, s2) -> {
//            int[] split1 = Arrays.stream(s1.split("\\.")).mapToInt(Integer::parseInt).toArray();
//            int[] split2 = Arrays.stream(s2.split("\\.")).mapToInt(Integer::parseInt).toArray();
//
//            return compareNextLevel(split1, split2, 0);
//        };
//
//        Arrays.sort(l, comparator);
//
//        return l;
//    }
//
//    public static int compareNextLevel(int[] split1, int[] split2, int level) {
//        if (split1[level] == split2[level] && level < 3) {
//            if (split1.length >= level + 2 && split2.length >= level + 2) {
//                return compareNextLevel(split1, split2, level + 1);
//            } else {
//                return split1.length >= level + 2 ? 1 : -1;
//            }
//        } else {
//            return Integer.compare(split1[level], split2[level]);
//        }
//    }


    // Problem #2
    //    Input:
    //    Solution.solution({1, 2, 3, 4}, 15)
    //    Output:
    //    -1,-1
    //
    //    Input:
    //    Solution.solution({4, 3, 10, 2, 8}, 12)
    //    Output:
    //    2,3
//    public static int[] solution(int[] l, int t) {
//        int[] result = new int[2];
//        boolean success = false;
//
//        // outer loop
//        for (int i = 0; i < l.length; ++i) {
//            int currentValue = l[i];
//
//            if (currentValue == t) {
//                result[0] = i;
//                result[1] = i;
//                success = true;
//                break;
//            } else if (currentValue > t) {
//                break;
//            }
//
//            // inner loop
//            for (int j = i + 1; j < l.length; ++j) {
//                currentValue += l[j];
//                if (currentValue == t) {
//                    result[0] = i;
//                    result[1] = j;
//                    success = true;
//                    break;
//                } else if (currentValue > t) {
//                    break;
//                }
//            }
//        }
//
//        if (!success) {
//            result[0] = -1;
//            result[1] = -1;
//        }
//
//        return result ;
//    }



    // Problem #1
//    public static int[] solution(int area) {
//        ArrayList<Integer> list = new ArrayList<Integer>();
//        int currentArea = area;
//
//        while (currentArea != 0) {
//            double root = Math.sqrt(currentArea);
//
//            // Does not have decimal numbers
//            if (root - (int) root == 0) {
//                list.add((int) currentArea);
//                currentArea = area - currentArea;
//                area = currentArea;
//            } else {
//                currentArea--;
//            }
//        }
//
//         int[] array = new int[list.size()];
//         for (int i = 0; i < array.length; ++i) {
//             array[i] = list.get(i);
//         }
//
//        return array;
//    }
}
