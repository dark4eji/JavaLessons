package mainlogic;

import exceptions.*;


public class MainLogic {

    public void operateArray(String[][] stringArray) throws MyArraySizeException, MyArrayDataException {
        int leftValue = 0;  //Хранит левое значение
        int rightValue;  //Хранит правое значение
        int rowsSumValues = 0;  //Хранит общее количество элементов для вычисления правого значения

        // Cбор информации о массиве
        for (int i = 0; i < stringArray.length; i++){
            leftValue++;
            for(int j = 0; j < stringArray[i].length; j++){
                rowsSumValues++;
            }
        }

        rightValue = rowsSumValues / leftValue;  //Вычисление правого значения

        //Проверка на соответствие формату массива
        if (leftValue == 4 && rightValue == 4) {
            int leftNumber = 0; //Хранит левое число для вывода на экран
            int rightNumber = 0;  //Хранит правое число для вывода на экран

            //Операции с массивом
            try {
                int result = 0;  //Хранит результат сложения ячеек для вывода на экран
                for (int i = 0; i < stringArray.length; i++){
                    leftNumber = i;
                    for(int j = 0; j < stringArray[i].length; j++){
                        rightNumber = j;
                        result += Integer.parseInt(stringArray[i][j]);
                    }
                }

                System.out.println("Сумма значений ячеек равна " + result);

            } catch (NumberFormatException e) {
                throw new MyArrayDataException(leftNumber, rightNumber);
            }
        } else {
            throw new MyArraySizeException();
        }
    }
}
