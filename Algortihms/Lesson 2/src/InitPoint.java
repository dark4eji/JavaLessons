import utilities.*;

public class InitPoint {
    public static void main(String[] args) {
        ArrayOperator ao = new ArrayOperator(1000);
        Sortings s = new Sortings();

        ao.getSize();
        ao.findElement(54);
        ao.isBinaryFind(70);

        final long startTime = System.currentTimeMillis();
        s.sortBubble(ao.generateRandArray(100000, 100000));
        final long endTime = System.currentTimeMillis();
        System.out.println("Total bubble sort execution time: " + (endTime - startTime));

        final long startTime2 = System.currentTimeMillis();
        s.sortInsert(ao.generateRandArray(100000, 100000));
        final long endTime2 = System.currentTimeMillis();
        System.out.println("Total bubble sort execution time: " + (endTime2 - startTime2));

        final long startTime3 = System.currentTimeMillis();
        s.sortSelect(ao.generateRandArray(100000, 100000));
        final long endTime3 = System.currentTimeMillis();
        System.out.println("Total bubble sort execution time: " + (endTime3 - startTime3));
    }
}
