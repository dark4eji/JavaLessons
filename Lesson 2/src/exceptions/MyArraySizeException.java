package exceptions;

public class MyArraySizeException extends Exception {
    public MyArraySizeException(){
        System.out.println("Некорректный размер массива. Корректный размер — 4х4");
    }
}
