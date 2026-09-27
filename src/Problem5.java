import java.math.BigInteger;
public class Problem5 {

    public String multBrute(String A, String B) {
        if (A.equals("0") || B.equals("0")) return "0";

        int n = A.length(), m = B.length();
        int[] result = new int[n + m];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                int mul = (A.charAt(i) - '0') * (B.charAt(j) - '0');
                int sum = mul + result[i + j + 1];

                result[i + j + 1] = sum % 10;
                result[i + j] += sum / 10;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int p : result) {
            if (!(sb.length() == 0 && p == 0)) {
                sb.append(p);
            }
        }

        return sb.length() == 0 ? "0" : sb.toString();
    }

    public String multSmart(String A, String B) {
        if (A.equals("0") || B.equals("0")) return "0";

        int n = Math.max(A.length(), B.length());

        if (n <= 4) {
            return String.valueOf(Long.parseLong(A) * Long.parseLong(B));
        }

        while (A.length() < n) A = "0" + A;
        while (B.length() < n) B = "0" + B;

        int half = n / 2;

        String a1 = A.substring(0, n - half);
        String a0 = A.substring(n - half);
        String b1 = B.substring(0, n - half);
        String b0 = B.substring(n - half);

        String p2 = multSmart(a1, b1);
        String p0 = multSmart(a0, b0);
        String p1 = multSmart(addStrings(a1, a0), addStrings(b1, b0));

        String mid = subStrings(subStrings(p1, p2), p0);

        String term2 = p2.equals("0") ? "0" : p2 + "0".repeat(2 * half);
        String term1 = mid.equals("0") ? "0" : mid + "0".repeat(half);

        String res = addStrings(addStrings(term2, term1), p0);
        return cleanZeros(res);
    }

    private String addStrings(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1, j = b.length() - 1, carry = 0;

        while (i >= 0 || j >= 0 || carry > 0) {
            int sum = carry;
            if (i >= 0) sum += a.charAt(i--) - '0';
            if (j >= 0) sum += b.charAt(j--) - '0';
            sb.append(sum % 10);
            carry = sum / 10;
        }

        return sb.reverse().toString();
    }

    private String subStrings(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1, j = b.length() - 1, borrow = 0;

        while (i >= 0) {
            int diff = (a.charAt(i--) - '0') - borrow;
            if (j >= 0) diff -= (b.charAt(j--) - '0');

            if (diff < 0) {
                diff += 10;
                borrow = 1;
            } else {
                borrow = 0;
            }
            sb.append(diff);
        }

        return cleanZeros(sb.reverse().toString());
    }

    private String cleanZeros(String s) {
        int i = 0;
        while (i < s.length() - 1 && s.charAt(i) == '0') i++;
        return s.substring(i);
    }

    public static void main(String[] args) {
        Problem5 solver = new Problem5();

        String A = "12345678987654321";
        String B = "98765432123456789";

        BigInteger a = new BigInteger(A);
        BigInteger b = new BigInteger(B);
        System.out.println("Expected: " + a.multiply(b));

        System.out.println("Brute:    " + solver.multBrute(A, B));
        System.out.println("Smart:    " + solver.multSmart(A, B));
    }
}