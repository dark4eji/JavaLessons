import exceptions.MyArrayDataException;
import exceptions.MyArraySizeException;
import mainlogic.MainLogic;


public class InitPoint {
    public static void main(String[] args) {
        String[][] stringArray = {{"2", "2", "3", "3"},
                                  {"1", "2", "3", "3"},
                                  {"1", "8", "3", "3"},
                                  {"1", "2", "3", "56"},
                                  };

        MainLogic ml = new MainLogic();
        try {
            ml.operateArray(stringArray);
        } catch (MyArraySizeException es){

        } catch (MyArrayDataException ed){

        }
    }
}
