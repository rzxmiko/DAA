public class Problem4 {

    public double minDistBrute(double[][] p) {
        double min = Double.MAX_VALUE;

        for (int i = 0; i < p.length; i++) {
            for (int j = i + 1; j < p.length; j++) {
                double d = distance(p[i], p[j]);

                if (d < min) {
                    min = d;
                }
            }
        }

        return min;
    }

    public double minDistSmart(double[][] p) {
        return closest(p, 0, p.length - 1);
    }

    private double closest(double[][] p, int left, int right) {
        if (right - left <= 1) {
            return distance(p[left], p[right]);
        }

        int mid = (left + right) / 2;

        double leftMin = closest(p, left, mid);
        double rightMin = closest(p, mid + 1, right);

        double min = Math.min(leftMin, rightMin);

        for (int i = left; i <= right; i++) {
            for (int j = i + 1; j <= right; j++) {
                min = Math.min(min, distance(p[i], p[j]));
            }
        }

        return min;
    }

    private double distance(double[] a, double[] b) {
        double x = a[0] - b[0];
        double y = a[1] - b[1];

        return Math.sqrt(x * x + y * y);
    }

    static void main() {
    }
}