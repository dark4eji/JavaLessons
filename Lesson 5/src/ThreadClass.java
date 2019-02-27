public class ThreadClass extends Thread {
    private float[] array;
    private long operationTime;

    public ThreadClass(float[] array){
        this.array = array;
    }

    @Override
    public void run() {
            long threadTime1 = System.currentTimeMillis();
            for (int i = 0; i < this.array.length; i++) {
                this.array[i] = (float) (this.array[i]
                        * Math.sin(0.2f + (float) i / 5)
                        * Math.cos(0.2f + (float) i / 5)
                        * Math.cos(0.4f + (float) i / 2));
            }
            long threadTime2 = System.currentTimeMillis();
            operationTime = threadTime2 - threadTime1;
    }

    public float[] getArray(){
        return array;
    }

    public long getOperationTime(){
        return operationTime;
    }
}
