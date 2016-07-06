import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class mMetropolis {

    public static void metropolis(int m, int n, double T) {
        MersenneTwister mers = new MersenneTwister();
        int[][] s = new int[m][n];
        int t = 0;
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                s[i][j] = i < m / 2 ? 1 : -1;
                //   s[i][j] = mers.nextInt(2) == 1 ? 1 : -1;
                t++;
            }
        }
        printArray(s, m, n);

        int y = 0;
        int dE = 0;
        int En = 0;
        while (res < 200000) {
            double p;
            double k = 1;// 1.380648*Math.pow(10,23);

            int i = mers.nextInt(m);
            int j = mers.nextInt(n);

            int prob = s[i][j] == 1 ? -1 : 1;

            if ((i != m - 1) & (j != n - 1)) {
                dE = prob * s[i + 1][j + 1] - s[i][j] * s[i + 1][j + 1];
            }
            if (i == m - 1 & j != n - 1) {
                dE = prob * s[0][j + 1] - s[i][j] * s[0][j + 1];
            }
            if (j == n - 1 & i != m - 1) {
                dE = prob * s[i + 1][0] - s[i][j] * s[i + 1][0];
            }
            if (j == n - 1 & i == m - 1) {
                dE = prob * s[0][0] - s[i][j] * s[0][0];
            }

            if (dE > 0) {
                p = mers.nextDouble();
                if (p < Math.exp(-dE / (k * T))) {
                    s[i][j] = prob;
                    res = 0;
                }
            } else {
                res++;
            }
            y++;

        }
        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                En += s[i][j] * s[i + 1][j + 1] + s[i][j + 1] * s[i + 1][j];
                j++;
            }
            i++;
        }

        System.out.println();
        printArray(s, m, n);
        System.out.println();
        System.out.println(En);

    }

    public static void metropolisP(int m, int n, double T) throws IOException, ParseException {
        InputStream inputStream = new InputStream() {
            @Override
            public int read() throws IOException {
                return 0;
            }
        };
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        JSONParser parser = new JSONParser();
        parser.parse(inputStreamReader);
        MersenneTwister mers = new MersenneTwister();
        int[][] s = new int[m][n];
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
               // s[i][j] = i ==j ? 1 : -1;
                 s[i][j] = mers.nextInt(2) == 1 ? 1 : -1;
            }
        }
        printArray(s, m, n);
        int N = 0;
        int y = 0;
        int dE = 0;
        int En = 0;
        while (res < 200000000) {
            double p;
            double k = 1;// 1.380648*Math.pow(10,23);
            int i = mers.nextInt(m);
            int j = mers.nextInt(n);
            int prob = s[i][j] == 1 ? -1 : 1;
            if ((i != m - 1) & (j != n - 1)) {
                dE = s[i + 1][j + 1] * s[i][j + 1] * s[i + 1][j] * (prob - s[i][j]);
            }
            if (i == m - 1 & j != n - 1) {
                dE = s[0][j + 1] * s[i][j + 1] * s[0][j] * (prob - s[i][j]);
            }
            if (j == n - 1 & i != m - 1) {
                dE = s[i + 1][0] * s[i][0] * s[i + 1][j] * (prob - s[i][j]);
            }
            if (j == n - 1 & i == m - 1) {
                dE = s[0][0] * s[i][0] * s[0][j] * (prob - s[i][j]);
            }

            if (dE > 0) {
                p = mers.nextDouble();
                if (p < Math.exp(-dE / (k * T))) {
                    s[i][j] = prob;
                }
            }
            res++;
            y++;
        }

        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                En += s[i][j] * s[i + 1][j + 1] * s[i][j + 1] * s[i + 1][j];
            }
        }
        System.out.println();
        printArray(s, m, n);
        System.out.println();
        System.out.println(En + "  ");
    }

    public static void printArray(int[][] a, int m, int n) {

        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (a[i][j] == 1) {
                    System.out.print(" " + a[i][j] + "  ");
                } else {
                    System.out.print(a[i][j] + "  ");
                }

            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException, ParseException {
        metropolisP(10, 10, 0.5);
    }
}