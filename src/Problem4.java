import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Problem4 {

    public double minDistBrute(double[][] p) {
        double best = Double.MAX_VALUE;
        for (int i = 0; i < p.length; i++) {
            for (int j = i + 1; j < p.length; j++) {
                double d = dist(p[i], p[j]);
                if (d < best) best = d;
            }
        }
        return best;
    }

    public double minDistSmart(double[][] p) {
        double[][] byX = p.clone();
        Arrays.sort(byX, Comparator.comparingDouble(a -> a[0]));
        return closestRec(byX);
    }

    private double closestRec(double[][] p) {
        int n = p.length;
        if (n <= 3) {

            double best = Double.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    best = Math.min(best, dist(p[i], p[j]));
                }
            }
            return best;
        }

        int mid = n / 2;
        double midX = p[mid][0];
        double[][] left = Arrays.copyOfRange(p, 0, mid);
        double[][] right = Arrays.copyOfRange(p, mid, n);

        double dLeft = closestRec(left);
        double dRight = closestRec(right);
        double d = Math.min(dLeft, dRight);

        List<double[]> strip = new ArrayList<>();
        for (double[] point : p) {
            if (Math.abs(point[0] - midX) < d) {
                strip.add(point);
            }
        }

        strip.sort(Comparator.comparingDouble(a -> a[1]));

        for (int i = 0; i < strip.size(); i++) {

            for (int j = i + 1; j < strip.size() && (strip.get(j)[1] - strip.get(i)[1]) < d; j++) {
                d = Math.min(d, dist(strip.get(i), strip.get(j)));
            }
        }
        return d;
    }

    private double dist(double[] a, double[] b) {
        double dx = a[0] - b[0];
        double dy = a[1] - b[1];
        return Math.sqrt(dx * dx + dy * dy);
    }

    static void main() {
        Problem4 solver = new Problem4();
        double[][] p = {{0, 0}, {3, 4}, {-5, -3}};
        System.out.println(solver.minDistBrute(p));
        System.out.println(solver.minDistSmart(p));
    }

    public static void main(String[] args) {
        main();
    }
}