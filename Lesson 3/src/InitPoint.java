import logic.*;

public class InitPoint {
    public static void main(String[] args) {
        TaskOne t1 = new TaskOne();
        TelephoneBook tb = new TelephoneBook();

        System.out.println("********** Task 1 **********");

        t1.printArrayData();

        System.out.println("********** Task 2 **********");

        tb.add("Petrov", "+9843121234");
        tb.add("Sidorov", "+8453452344");
        tb.add("Petrov", "+656232365");

        tb.get("Petrov");
    }
}
