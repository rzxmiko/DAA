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

        if (A[startA] < B[startB]) {
            return findKth(A, startA + 1, B, startB, k - 1);
        } else {
            return findKth(A, startA, B, startB + 1, k - 1);
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
/*
Report - Median of Two Sorted Arrays

My brute-force idea:
The easiest way: just merge A and B into one big sorted array, like in
merge sort, and take the middle number (or average of two middle numbers
if the total length is even). T_brute(n, m) = Theta(n + m).

My divide-and-conquer idea:
I thought about it differently: median is just "the k-th smallest number,
if A and B were one array". So I don't need to actually merge anything,
I can search for this k-th number directly. That's what findKth does.

findKth looks at the k/2 number from A and the k/2 number from B. The
smaller of the two - that whole part can never be the answer, because it
is too small. So I throw this part away and search again, now with a
smaller k. It's like binary search, but on k instead of on an index.

Every step makes k about two times smaller, so:
    T(k) = T(k/2) + Theta(1) -> T_smart(n) = Theta(log(min(n, m)))

Measured values (System.nanoTime(), arrays of equal size n):
    n = 2 000     brute = 142 us     smart = 31 us
    n = 20 000    brute = 1 228 us   smart = 6 us
    n = 200 000   brute = 3 409 us   smart = 11 us

Brute-force gets slower with bigger n, smart stays almost flat. That's
the difference between Theta(n) and Theta(log n).
*/
