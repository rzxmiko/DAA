public class Problem1 {
    public int countFreqBrute(int key, int [] A){
        int count = 0;
        for(int i = 0; i < A.length; i++){
            if (A[i] == key){
                count++;
            }
        }
        return count;
    }
    public int countFreqSmart(int key, int [] A){
        if (A.length == 0) return 0;

        int first = findFirst(A, key);
        if (first == -1) return 0;

        int last = findLast(A, key);
        return last - first + 1;
    }
    private int findFirst(int [] A, int key){
        int lo = 0, hi = A.length -1, result = -1;
        while (lo <= hi){
            int mid = lo + (hi - lo)/2;
            if (A[mid] == key){
                result = mid;
                hi = mid - 1;
            }
            else if (A[mid] < key){
                lo = mid + 1;
            }
            else{
                hi = mid - 1;
            }
        }
        return result;
    }

    private int findLast(int [] A, int key){
        int lo = 0, hi = A.length -1, result = -1;
        while (lo <= hi){
            int mid = lo + (hi - lo)/2;
            if (A[mid] == key){
                result = mid;
                lo = mid + 1;
            }
            else if (A[mid] < key){
                lo = mid + 1;
            }
            else{
                hi = mid - 1;
            }
        }
        return result;
    }
    static void main() {
        Problem1 p = new Problem1();
        int[] A = {1,1,1,2,2,2,2,2,2,4,4,4,5,5,5,5};
        System.out.println(p.countFreqBrute(4, A));
        System.out.println(p.countFreqSmart(4, A));
        System.out.println(p.countFreqBrute(3, A));
        System.out.println(p.countFreqSmart(3, A));
    }

    public static void main(String[] args) {
        main();

        Problem1 p = new Problem1();
        System.out.println(p.countFreqSmart(1, new int[]{}));
        System.out.println(p.countFreqSmart(5, new int[]{5}));
    }
}