package utilities;

public class Sortings {
    public void sortBubble(int[] mass) {
        int in, out;
        for (out = mass.length - 1; out > 1; out--) {
            for (in = 0; in < out; in++) {
                if (mass[in] > mass[in + 1]) {
                    int temp = mass[in];
                    mass[in] = mass[in + 1];
                    mass[in + 1] = temp;
                }
            }
        }
    }

    public void sortSelect(int[] mass) {
        int out, in, mark;
        for (out = 0; out < mass.length; out++) {
            mark = out;
            for (in = out + 1; in < mass.length; in++) {
                if (mass[in] < mass[mark])
                    mark = in;
            }
            int temp = mass[out];
            mass[out] = mass[mark];
            mass[mark] = temp;
        }
    }

    public void sortInsert(int[] mass) {
        int in, out;
        for (out = 1; out < mass.length; out++) {
            int temp = mass[out];
            in = out;
            while (in > 0 && mass[in - 1] >= temp) {
                mass[in] = mass[in - 1];
                --in;
            }
            mass[in] = temp;
        }
    }
}
