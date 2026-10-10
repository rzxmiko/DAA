import java.util.Arrays;

public class Problem5 {

    public String multBrute(String A, String B) {
        int[] a = toDigits(A);
        int[] b = toDigits(B);
        int[] result = new int[a.length + b.length];

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                result[i + j] += a[i] * b[j];
            }
        }
        carry(result);
        return toStringResult(result);
    }

    public String multSmart(String A, String B) {
        int[] a = toDigits(A);
        int[] b = toDigits(B);
        int[] result = karatsuba(a, b);
        return toStringResult(result);
    }

    private int[] karatsuba(int[] x, int[] y) {
        int n = Math.max(x.length, y.length);

        if (n <= 32) {
            int[] result = new int[x.length + y.length];
            for (int i = 0; i < x.length; i++) {
                for (int j = 0; j < y.length; j++) {
                    result[i + j] += x[i] * y[j];
                }
            }
            carry(result);
            return result;
        }

        int half = n / 2;
        int[] xl = subArray(x, 0, Math.min(half, x.length));
        int[] xh = subArray(x, Math.min(half, x.length), x.length);
        int[] yl = subArray(y, 0, Math.min(half, y.length));
        int[] yh = subArray(y, Math.min(half, y.length), y.length);

        int[] z0 = karatsuba(xl, yl);
        int[] z2 = karatsuba(xh, yh);
        int[] xlxh = addArrays(xl, xh);
        int[] ylyh = addArrays(yl, yh);
        int[] z1temp = karatsuba(xlxh, ylyh);
        int[] z1 = subtractArrays(subtractArrays(z1temp, z2), z0);

        int[] result = new int[x.length + y.length + 4];
        addShifted(result, z0, 0);
        addShifted(result, z1, half);
        addShifted(result, z2, 2 * half);
        carry(result);
        return result;
    }

    private int[] toDigits(String s) {
        int n = s.length();
        int[] d = new int[n];
        for (int i = 0; i < n; i++) {
            d[i] = s.charAt(n - 1 - i) - '0';
        }
        return d;
    }

    private int[] subArray(int[] a, int from, int to) {
        if (from >= to) return new int[0];
        return Arrays.copyOfRange(a, from, to);
    }

    private int[] addArrays(int[] a, int[] b) {
        int n = Math.max(a.length, b.length);
        int[] result = new int[n + 1];
        for (int i = 0; i < n; i++) {
            int av = (i < a.length) ? a[i] : 0;
            int bv = (i < b.length) ? b[i] : 0;
            result[i] += av + bv;
        }
        carry(result);
        return result;
    }

    private int[] subtractArrays(int[] a, int[] b) {
        int[] result = new int[a.length];
        int borrow = 0;
        for (int i = 0; i < a.length; i++) {
            int av = a[i];
            int bv = (i < b.length) ? b[i] : 0;
            int diff = av - bv - borrow;
            if (diff < 0) {
                diff += 10;
                borrow = 1;
            } else {
                borrow = 0;
            }
            result[i] = diff;
        }
        return result;
    }

    private void addShifted(int[] dest, int[] src, int shift) {
        for (int i = 0; i < src.length; i++) {
            int idx = i + shift;
            if (idx < dest.length) {
                dest[idx] += src[i];
            }
        }
    }

    private void carry(int[] a) {
        int carryVal = 0;
        for (int i = 0; i < a.length; i++) {
            int val = a[i] + carryVal;
            a[i] = val % 10;
            carryVal = val / 10;
        }
    }

    private String toStringResult(int[] a) {
        int last = a.length - 1;
        while (last > 0 && a[last] == 0) last--;
        StringBuilder sb = new StringBuilder();
        for (int i = last; i >= 0; i--) {
            sb.append(a[i]);
        }
        return sb.toString();
    }

    static void main() {
        Problem5 p = new Problem5();
        String A = "12345678987654321";
        String B = "98765432123456789";
        System.out.println(p.multBrute(A, B));
        System.out.println(p.multSmart(A, B));
    }

    public static void main(String[] args) {
        main();
    }
}