public class Power {
    public static void main(String[] args) {
        System.out.println(getPower(3, 2));
    }

    public static int getPower(int number, int power) {
        if (power != 1) {
            return number * getPower(number, power - 1);
        } else {
            return number;
        }
    }
}
