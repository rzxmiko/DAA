public class Problem3 {

    public int maxSumBrute(int[] A) {
        int best = A[0];
        for (int i = 0; i < A.length; i++) {
            int sum = 0;
            for (int j = i; j < A.length; j++) {
                sum += A[j];
                if (sum > best) best = sum;
            }
        }
        return best;
    }

    public int maxSumSmart(int[] A) {
        return maxSumRec(A, 0, A.length - 1);
    }

    private int maxSumRec(int[] A, int lo, int hi) {
        if (lo == hi) {
            return A[lo];
        }
        int mid = lo + (hi - lo) / 2;
        int leftMax = maxSumRec(A, lo, mid);
        int rightMax = maxSumRec(A, mid + 1, hi);
        int crossMax = maxCrossingSum(A, lo, mid, hi);
        return Math.max(Math.max(leftMax, rightMax), crossMax);
    }

    private int maxCrossingSum(int[] A, int lo, int mid, int hi) {
        int sum = 0;
        int leftBest = Integer.MIN_VALUE;
        for (int i = mid; i >= lo; i--) {
            sum += A[i];
            if (sum > leftBest) leftBest = sum;
        }

        sum = 0;
        int rightBest = Integer.MIN_VALUE;
        for (int j = mid + 1; j <= hi; j++) {
            sum += A[j];
            if (sum > rightBest) rightBest = sum;
        }

        return leftBest + rightBest;
    }

    static void main() {
        Problem3 p = new Problem3();
        int[] A = {-17, 5, 3, -10, 6, 1, 4, -3, 8, 1, -13, 4};
        System.out.println(p.maxSumBrute(A));
        System.out.println(p.maxSumSmart(A));
    }

    public static void main(String[] args) {
        main();
    }
}