public class Problem2 {

    public double getMedianBrute(int[] A, int[] B) {
        int n = A.length, m = B.length;
        int[] merged = new int[n + m];
        int i = 0, j = 0, k = 0;
        while (i < n && j < m) merged[k++] = (A[i] <= B[j]) ? A[i++] : B[j++];
        while (i < n) merged[k++] = A[i++];
        while (j < m) merged[k++] = B[j++];

        int total = n + m;
        if (total % 2 == 1) return merged[total / 2];
        return (merged[total / 2 - 1] + merged[total / 2]) / 2.0;
    }

    public double getMedianSmart(int[] A, int[] B) {
        int total = A.length + B.length;
        if (total % 2 == 1) {
            return findKth(A, 0, B, 0, total / 2 + 1);
        }
        double left = findKth(A, 0, B, 0, total / 2);
        double right = findKth(A, 0, B, 0, total / 2 + 1);
        return (left + right) / 2.0;
    }

    private double findKth(int[] A, int startA, int[] B, int startB, int k) {
        if (startA == A.length) return B[startB + k - 1];
        if (startB == B.length) return A[startA + k - 1];
        if (k == 1) return Math.min(A[startA], B[startB]);

        int stepA = Math.min(A.length - startA, k / 2);
        int stepB = Math.min(B.length - startB, k / 2);

        if (A[startA + stepA - 1] <= B[startB + stepB - 1]) {
            return findKth(A, startA + stepA, B, startB, k - stepA);
        } else {
            return findKth(A, startA, B, startB + stepB, k - stepB);
        }
    }

    public static void main(String[] args) {
        Problem2 p = new Problem2();
        System.out.println(p.getMedianBrute(new int[]{2,4}, new int[]{3}));
        System.out.println(p.getMedianSmart(new int[]{2,4}, new int[]{3}));
        System.out.println(p.getMedianBrute(new int[]{2,4}, new int[]{3,5}));
        System.out.println(p.getMedianSmart(new int[]{2,4}, new int[]{3,5}));
        System.out.println(p.getMedianSmart(new int[]{}, new int[]{1,2,3}));
    }
}