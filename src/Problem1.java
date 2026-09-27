public class Problem1 {
    public int countFreqBrute(int key, int[] A) {
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] == key) {
                count++;
            }
        }
        return count;
    }

    public int countFreqSmart(int key, int[] A) {
        int first = findFirst(key, A, 0, A.length - 1);
        if (first == -1) {
            return 0;
        }
        int last = findLast(key, A, 0, A.length - 1);
        return last - first + 1;
    }
    private int findFirst(int key, int[] A, int left, int right) {
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        if (A[mid] == key) {
            int result = findFirst(key, A, left, mid - 1);
            if (result == -1) {
                return mid;
            }
            return result;
        }
        if (A[mid] < key) {
            return findFirst(key, A, mid + 1, right);
        }
        return findFirst(key, A, left, mid - 1);
    }

    private int findLast(int key, int[] A, int left, int right) {
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        if (A[mid] == key) {
            int result = findLast(key, A, mid + 1, right);
            if (result == -1) {
                return mid;
            }
            return result;
        }
        if (A[mid] < key) {
            return findLast(key, A, mid + 1, right);
        }
        return findLast(key, A, left, mid - 1);
    }

    public static void main(String[] args) {
        Problem1 solver = new Problem1();
        int[] A = {1, 1, 1, 2, 2, 2, 2, 2, 2, 4, 4, 4, 5, 5, 5, 5};
        System.out.println(solver.countFreqBrute(4, A));
        System.out.println(solver.countFreqSmart(4, A));
    }
}