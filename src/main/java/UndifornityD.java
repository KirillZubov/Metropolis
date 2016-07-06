
import java.util.Arrays;

public class UndifornityD {
int x = 1;
    public static double uniformityD(double[] number, int n) {
        double result = 0.0;
        Arrays.sort(number);

        int length = 1;
        double num = number[0];
        for (int i = 0; i < number.length; i++) {
            if (num != number[i]) {
                length += 1;
            }
            num = number[i];
        }
        double[] S = new double[length];
        num = number[0];
        int s = 1;
        double size = 0.0;
        for (int i = 0; i < number.length; i++) {
            if (num != number[i]) {
                S[s - 1] = size;
                size = 0;
                s += 1;
            }

            size += 1;

            if (i == number.length - 1) {
                S[s - 1] = size;
            }
            num = number[i];
        }
        double N = (double) number.length;
        double si = (double) length;
        for (int i = 0; i < S.length; i++) {
            result += (Math.pow(((S[i] - N / si)), 2))
                    / (N / si);
        }

        return result;
    }

    public static double uniformityDI(int[] number, int n) {
        double result = 0.0;
        Arrays.sort(number);

        int length = 1;
        int num = number[0];
        for (int i = 0; i < number.length; i++) {
            if (num != number[i]) {
                length += 1;
            }
            num = number[i];
        }
        int[] S = new int[length];
        num = number[0];
        int s = 1;
        int size = 0;
        for (int i = 0; i < number.length; i++) {
            if (num != number[i]) {
                S[s - 1] = size;
                size = 0;
                s += 1;
            }

            size += 1;

            if (i == number.length - 1) {
                S[s - 1] = size;
            }
            num = number[i];
        }

        for (int i = 0; i < S.length; i++) {
            result += (Math.pow((double) ((S[i] - (double) number.length / (double) length)), 2))
                    / ((double) number.length / (double) length);
        }

        return result;
    }

    public static void main(String args[]) {
        MersenneTwister mers = new MersenneTwister();

        int n = 100000000;
        int[] number = new int[n];
        int[] degree = {2, 5, 10, 100, 1000, 10000, 100000};
        for (int j = 0; j < 1; j++) {
            System.out.print(degree[j] + "  ");
            for (int k = 0; k < 5; k++) {
                for (int i = 0; i < number.length; i++) {
                    number[i] = mers.nextInt(degree[j]);
                }
                System.out.print(uniformityDI(number, n) + "  ");
            }
            System.out.println();
        }

    }
}

