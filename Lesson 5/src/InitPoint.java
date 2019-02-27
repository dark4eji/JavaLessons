public class InitPoint {
    static final int SIZE = 10000000;
    static final int h = SIZE / 2;


    public static void main(String[] args) {
        operateArrayNoThreads();
        System.out.println("*****");
        operateArrayThreads();
    }

    public static void operateArrayNoThreads(){
        float[] array  = new float[SIZE];


        for (int i = 0; i < array.length; i++) {
            array[i] = 1.0f;
        }

        long time1 = System.currentTimeMillis();
        for (int i = 0; i < array.length; i++) {
            array[i] = (float)(array[i]
                    * Math.sin(0.2f + (float)i / 5)
                    * Math.cos(0.2f + (float)i / 5)
                    * Math.cos(0.4f + (float)i / 2));
        }
        long time2 = System.currentTimeMillis();
        System.out.println("Operation Time: " + (time2 - time1));
    }

    public static void operateArrayThreads() {
        float[] mainArray = new float[SIZE];
        float[] newArray1 = new float[h];
        float[] newArray2 = new float[h];

        for (int i = 0; i < mainArray.length; i++) {
            mainArray[i] = 1.0f;
        }

        long divisionTime1 = System.currentTimeMillis();
        System.arraycopy(mainArray, 0, newArray1, 0, h);
        System.arraycopy(mainArray, h, newArray2, 0, h);
        long divisionTime2 = System.currentTimeMillis();
        System.out.println("Division result: " + (divisionTime2 - divisionTime1));

        ThreadClass thread1 = new ThreadClass(mainArray);
        ThreadClass thread2 = new ThreadClass(mainArray);


        new Thread(thread1).start();
        new Thread(thread2).start();

        try{
            Thread.sleep(10000);
        } catch (InterruptedException e){

        }

        System.out.println("Operation Time: "
                + ((thread1.getOperationTime()
                + thread2.getOperationTime()) - 10000));

        newArray1 = thread1.getArray();
        newArray2 = thread2.getArray();

        long glueTime1 = System.currentTimeMillis();
        System.arraycopy(newArray1, 0, mainArray, 0, h);
        System.arraycopy(newArray2, 0, mainArray, h, h);
        long glueTime2 = System.currentTimeMillis();
        System.out.println("Glue result: " + (glueTime2 - glueTime1));

       System.out.println("General time: " + ((divisionTime2 - divisionTime1)
               + (glueTime2 - glueTime1)));
    }
}
