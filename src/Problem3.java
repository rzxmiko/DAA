public class Problem3 {

    public int maxSumBrute(int[] A) {
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < A.length; i++) {
            int sum = 0;

            for (int j = i; j < A.length; j++) {
                sum += A[j];

                if (sum > max) {
                    max = sum;
                }
            }
        }

        return max;
    }

    public int maxSumSmart(int[] A) {
        return maxSum(A, 0, A.length - 1);
    }

    private int maxSum(int[] A, int left, int right) {
        if (left == right) {
            return A[left];
        }

        int mid = (left + right) / 2;

        int leftSum = maxSum(A, left, mid);
        int rightSum = maxSum(A, mid + 1, right);

        int sum = 0;
        int bestLeft = Integer.MIN_VALUE;

        for (int i = mid; i >= left; i--) {
            sum += A[i];
            bestLeft = Math.max(bestLeft, sum);
        }

        sum = 0;
        int bestRight = Integer.MIN_VALUE;

        for (int i = mid + 1; i <= right; i++) {
            sum += A[i];
            bestRight = Math.max(bestRight, sum);
        }

        int middleSum = bestLeft + bestRight;

        return Math.max(Math.max(leftSum, rightSum), middleSum);
    }

    public static void main(String[] args) {
        Problem3 solver = new Problem3();
        int[] A = {-17, 5, 3, -10, 6, 1, 4, -3, 8, 1, -13, 4};

        System.out.println(solver.maxSumBrute(A));
        System.out.println(solver.maxSumSmart(A));
    }
}