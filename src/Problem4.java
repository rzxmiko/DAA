import java.util.Arrays;

public class Problem4 {

    public double minDistBrute(double[][] p) {
        double minD = Double.MAX_VALUE;
        for (int i = 0; i < p.length; i++) {
            for (int j = i + 1; j < p.length; j++) {
                minD = Math.min(minD, dist(p[i], p[j]));
            }
        }
        return minD;
    }

    public double minDistSmart(double[][] p) {
        double[][] points = p.clone();
        Arrays.sort(points, (a, b) -> Double.compare(a[0], b[0]));
        return solve(points, 0, points.length);
    }

    private double solve(double[][] p, int l, int r) {
        if (r - l <= 3) {
            double minD = Double.MAX_VALUE;
            for (int i = l; i < r; i++) {
                for (int j = i + 1; j < r; j++) {
                    minD = Math.min(minD, dist(p[i], p[j]));
                }
            }
            return minD;
        }

        int mid = (l + r) / 2;
        double midX = p[mid][0];

        double d = Math.min(solve(p, l, mid), solve(p, mid, r));

        for (int i = l; i < r; i++) {
            if (Math.abs(p[i][0] - midX) < d) {
                for (int j = i + 1; j < r && (p[j][0] - p[i][0]) < d; j++) {
                    d = Math.min(d, dist(p[i], p[j]));
                }
            }
        }
        return d;
    }

    private double dist(double[] a, double[] b) {
        double dx = a[0] - b[0];
        double dy = a[1] - b[1];
        return Math.sqrt(dx * dx + dy * dy);
    }

    public static void main(String[] args) {
        Problem4 solver = new Problem4();
        double[][] p = {{0, 0}, {3, 4}, {-5, -3}};
        System.out.println(solver.minDistBrute(p));
        System.out.println(solver.minDistSmart(p));
    }
}