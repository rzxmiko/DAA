import java.util.Arrays;
import java.util.Comparator;

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
        if (p == null || p.length < 2) return 0.0;
        double[][] sortedP = p.clone();
        Arrays.sort(sortedP, Comparator.comparingDouble(a -> a[0]));
        return closest(sortedP, 0, sortedP.length - 1);
    }

    private double closest(double[][] p, int left, int right) {
        if (right - left <= 2) {
            double min = Double.MAX_VALUE;
            for (int i = left; i <= right; i++) {
                for (int j = i + 1; j <= right; j++) {
                    min = Math.min(min, distance(p[i], p[j]));
                }
            }
            return min;
        }

        int mid = (left + right) / 2;
        double min = Math.min(closest(p, left, mid), closest(p, mid + 1, right));

        for (int i = left; i <= right; i++) {
            if (Math.abs(p[i][0] - p[mid][0]) >= min) continue;
            for (int j = i + 1; j <= right; j++) {
                if (p[j][0] - p[i][0] >= min) break;
                if (Math.abs(p[j][1] - p[i][1]) < min) {
                    min = Math.min(min, distance(p[i], p[j]));
                }
            }
        }

        return min;
    }

    private double distance(double[] a, double[] b) {
        double x = a[0] - b[0];
        double y = a[1] - b[1];

        return Math.sqrt(x * x + y * y);
    }

    public static void main(String[] args) {
        Problem4 solver = new Problem4();
        double[][] p = {{0, 0}, {3, 4}, {-5, -3}};

        System.out.println(solver.minDistBrute(p));
        System.out.println(solver.minDistSmart(p));
    }
}