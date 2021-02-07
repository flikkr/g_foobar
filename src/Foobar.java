import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;

public class Foobar {
    public static void main(String []args){
        System.out.println(solution(8));
    }

    //    Problem #5
//    -- Java cases --
//    Input:
//            Solution.solution(3)
//    Output:
//            1
//
//    Input:
//            Solution.solution(200)
//    Output:
//            487067745
    static int iterations = 0;
    public static int solution(int n) {
        checkNextLevel(n, 1);
        return iterations;
    }
    public static int checkNextLevel(int bricks, int level) {
        int nextLevelBricks = 0;

        // can break it down further?
        while (bricks >= 3 && nextLevelBricks <= bricks - 3) {
            bricks--;
            nextLevelBricks = checkNextLevel(++nextLevelBricks, level + 1);
        }

        if (level != 1)iterations++;
        // go back up and check
        return bricks;
    }
    //    Problem #4
    /*  [1,1]   - 0     [4,7] - 0       [12,5]
        [2,1]   - 1     [4,3] - 1       [7,5] - 1
        [2,3]   - 2     [1,3] - 2       [2,5] - 2
        [2,5]   - 3     [1,2] - 3       [2,3] - 3
        [7,5]           [1,1] - 4       [2,1] - 4
                                        [1,1] - 5

        problem cases:
        [6666,666]
        ["123", "2342324223"]
        ["12312314231234","434562436456234563"]

        impossible:
        [5,0]
        [0,5]
        [15,5]
        [5,5]

    */

    public static String solution(String x, String y) {
        try {
            return replicate(new BigInteger(x), new BigInteger(y), BigInteger.ZERO);
        } catch (IllegalStateException ise) {
            return "impossible";
        }
    }

    // Recursive function to calculate the number of generations required
    public static String replicate(BigInteger x, BigInteger y, BigInteger gen) {
        // base case that checks if either x or y is one, then calculates how much to add to gen
        if (x.equals(BigInteger.ONE) || y.equals(BigInteger.ONE))
            return String.valueOf(gen.add(x.max(y).subtract(x.min(y))));
            // if x or y is equally divisble by the other, throw exception
        else if (x.mod(y).equals(BigInteger.ZERO)
                || y.mod(x).equals(BigInteger.ZERO))
            throw new IllegalStateException();
        else {
            BigInteger quotient;
            if (x.compareTo(y) == 1) {
                quotient = calcQuotient(x, y);
                x = x.subtract(quotient.multiply(y));
            } else {
                quotient = calcQuotient(y, x);
                y = y.subtract(quotient.multiply(x));
            }
            return replicate(x, y, gen.add(quotient));
        }
    }

    // Calculate quotient and remainder between two numbers
    public static BigInteger calcQuotient(BigInteger dividend, BigInteger divisor) {
        BigInteger[] quotient = dividend.divideAndRemainder(divisor);
        // check if remainder is zero, which means the quotient will always be zero
        if (quotient[1].equals(BigInteger.ZERO)) throw new IllegalStateException();
        return quotient[0];
    }


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
