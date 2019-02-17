package exceptions;

public class MyArrayDataException extends Exception {
    public MyArrayDataException(int i, int j){
        System.out.println("Невозможно выполнить преобразование, так как в ячейке "
                + (i + 1) + "х" + (j + 1) + " находится символ, либо текст");
    }
}
