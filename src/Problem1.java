public class Problem1 {

    public int countFreqBrute(int key, int[] A) {
        if (A == null || A.length == 0) return 0;
        int count = 0;
        for (int num : A) {
            if (num == key) count++;
        }
        return count;
    }

    public int countFreqSmart(int key, int[] A) {
        if (A == null || A.length == 0) return 0;
        int first = findFirst(key, A);
        if (first == -1) return 0;
        int last = findLast(key, A);
        return last - first + 1;
    }

    private int findFirst(int key, int[] A) {
        int left = 0, right = A.length - 1, res = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (A[mid] == key) {
                res = mid;
                right = mid - 1;
            } else if (A[mid] < key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return res;
    }

    private int findLast(int key, int[] A) {
        int left = 0, right = A.length - 1, res = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (A[mid] == key) {
                res = mid;
                left = mid + 1;
            } else if (A[mid] < key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Problem1 solver = new Problem1();
        int[] A = {1, 1, 1, 2, 2, 2, 2, 2, 2, 4, 4, 4, 5, 5, 5, 5};
        System.out.println(solver.countFreqBrute(4, A));
        System.out.println(solver.countFreqSmart(4, A));
    }
}