import java.util.ArrayList;

public class Foobar {
    public static void main(String []args){
        System.out.println(solution(8));
    }

    //    Challenge #5
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
}
