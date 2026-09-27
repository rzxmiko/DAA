public class Problem2 {

    public double getMedianBrute(int[] A, int[] B) {
        int[] C = new int[A.length + B.length];
        int index = 0;
        for (int i = 0; i < A.length; i++) {
            C[index++] = A[i];
        }
        for (int i = 0; i < B.length; i++) {
            C[index++] = B[i];
        }
        for (int i = 0; i < C.length - 1; i++) {
            for (int j = i + 1; j < C.length; j++) {
                if (C[i] > C[j]) {
                    int temp = C[i];
                    C[i] = C[j];
                    C[j] = temp;
                }
            }
        }
        int n = C.length;
        if (n % 2 == 1) {
            return C[n / 2];
        }
        return (C[n / 2 - 1] + C[n / 2]) / 2.0;
    }

    public double getMedianSmart(int[] A, int[] B) {
        if (A.length > B.length) {
            return getMedianSmart(B, A);
        }

        int left = 0;
        int right = A.length;
        int total = A.length + B.length;

        while (left <= right) {
            int partitionA = (left + right) / 2;
            int partitionB = (total + 1) / 2 - partitionA;

            int leftA = partitionA == 0 ? Integer.MIN_VALUE : A[partitionA - 1];
            int rightA = partitionA == A.length ? Integer.MAX_VALUE : A[partitionA];

            int leftB = partitionB == 0 ? Integer.MIN_VALUE : B[partitionB - 1];
            int rightB = partitionB == B.length ? Integer.MAX_VALUE : B[partitionB];

            if (leftA <= rightB && leftB <= rightA) {
                if (total % 2 == 1) {
                    return Math.max(leftA, leftB);
                }
                return (Math.max(leftA, leftB) + Math.min(rightA, rightB)) / 2.0;
            }

            if (leftA > rightB) {
                right = partitionA - 1;
            } else {
                left = partitionA + 1;
            }
        }

        return 0.0;
    }

    public static void main(String[] args) {
        Problem2 solver = new Problem2();
        System.out.println(solver.getMedianBrute(new int[]{1, 2, 3}, new int[]{3, 4, 5}));
        System.out.println(solver.getMedianSmart(new int[]{1, 2, 3}, new int[]{3, 4, 5}));
    }
}