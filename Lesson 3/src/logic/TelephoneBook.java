package logic;

import java.util.HashMap;


public class TelephoneBook {

    private HashMap<String, String> telBook = new HashMap<>();

    public void add(String name, String number) {

        String formattedNumber = number + "\n";

        if (telBook.containsKey(name)) {
            telBook.replace(name, telBook.get(name), telBook.get(name)
                    + formattedNumber);
        } else {
            telBook.put(name, formattedNumber);
        }
    }

    public void get(String name) {
        System.out.println(telBook.get(name));
    }
}