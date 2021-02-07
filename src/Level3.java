import java.math.BigInteger;

public class Level3 {

    //    Challenge #4
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
}
