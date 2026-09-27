public class Problem5 {

    public String multBrute(String A, String B) {
        if (A.equals("0") || B.equals("0")) {
            return "0";
        }

        int[] result = new int[A.length() + B.length()];

        for (int i = A.length() - 1; i >= 0; i--) {
            for (int j = B.length() - 1; j >= 0; j--) {

                int a = A.charAt(i) - '0';
                int b = B.charAt(j) - '0';

                int pos = i + j + 1;

                int value = a * b + result[pos];

                result[pos] = value % 10;
                result[pos - 1] += value / 10;
            }
        }

        StringBuilder answer = new StringBuilder();

        for (int digit : result) {
            if (answer.length() == 0 && digit == 0) {
                continue;
            }

            answer.append(digit);
        }

        return answer.toString();
    }

    public String multSmart(String A, String B) {
        if (A.equals("0") || B.equals("0")) {
            return "0";
        }

        return multiply(A, B);
    }

    private String multiply(String A, String B) {
        if (A.length() == 1 && B.length() == 1) {
            int a = A.charAt(0) - '0';
            int b = B.charAt(0) - '0';

            return String.valueOf(a * b);
        }

        int n = Math.max(A.length(), B.length());

        while (n % 2 != 0) {
            n++;
        }

        A = addZeros(A, n);
        B = addZeros(B, n);

        int mid = n / 2;

        String a1 = A.substring(0, mid);
        String a0 = A.substring(mid);

        String b1 = B.substring(0, mid);
        String b0 = B.substring(mid);

        String z2 = multiply(a1, b1);
        String z0 = multiply(a0, b0);

        String z1 = multiply(
                add(a1, a0),
                add(b1, b0)
        );

        z1 = subtract(z1, z2);
        z1 = subtract(z1, z0);

        return add(
                add(shift(z2, n), shift(z1, mid)),
                z0
        );
    }

    private String add(String A, String B) {
        StringBuilder result = new StringBuilder();

        int i = A.length() - 1;
        int j = B.length() - 1;
        int carry = 0;

        while (i >= 0 || j >= 0 || carry > 0) {
            int a = i >= 0 ? A.charAt(i--) - '0' : 0;
            int b = j >= 0 ? B.charAt(j--) - '0' : 0;

            int sum = a + b + carry;

            result.append(sum % 10);
            carry = sum / 10;
        }

        return result.reverse().toString();
    }

    private String subtract(String A, String B) {
        StringBuilder result = new StringBuilder();

        int i = A.length() - 1;
        int j = B.length() - 1;
        int borrow = 0;

        while (i >= 0) {
            int a = A.charAt(i--) - '0';
            int b = j >= 0 ? B.charAt(j--) - '0' : 0;

            int value = a - b - borrow;

            if (value < 0) {
                value += 10;
                borrow = 1;
            } else {
                borrow = 0;
            }

            result.append(value);
        }

        return result.reverse().toString();
    }

    private String shift(String A, int zeros) {
        return A + "0".repeat(zeros);
    }

    private String addZeros(String A, int n) {
        return "0".repeat(n - A.length()) + A;
    }

    static void main() {
    }
}