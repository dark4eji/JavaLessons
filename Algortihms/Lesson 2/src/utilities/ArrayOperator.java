package utilities;

import java.util.Random;

public class ArrayOperator {
    private Random rand = new Random();
    private int arraySize;
    private int[] array;

    public ArrayOperator(int arraySize){
        this.arraySize = arraySize;
        generateArray(this.arraySize);
    }

    private void generateArray(int SIZE) {
        array = new int[SIZE];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
    }

    public void insertElement(int value) {
        array[this.arraySize] = value;
        this.arraySize++;
    }

    public void deleteElement(int element) {
        int i = 0;
        for (i = 0; i < array.length; i++) {
            if (array[i] == element) break;
        }
        for (int j = i; j < array.length - 1; j++) {
            array[j] = array[j + 1];
        }
        this.arraySize--;
    }

    public void getSize(){
        System.out.println("Размер массива: " + this.array.length);
    }

    public void findElement(int element) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == element) {
                System.out.println("Элемент находится по индексу " + i);
                break;
            }
            if (i == array.length - 1) {
                System.out.println("Nope");
                }
            }
        }

    public void isBinaryFind(int element) {
        int low = 0;
        int high = array.length - 1;
        int mid;
        while (low <= high) {
            mid = (low + high) / 2;
            if (element == array[mid]) {
                System.out.println("Элемент находится по индексу " + mid);
                break;
            }
            else {
                if (element < array[mid]) {
                    high = mid;
                } else {
                    low = mid + 1;
                }
            }
        }
    }

    public int[] getArray(){
        return array;
    }

    public int[] generateRandArray(int SIZE, int RAND_BOUND) {
        int[] arr = new int[SIZE];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(RAND_BOUND);
        }
        return arr;
    }
}
