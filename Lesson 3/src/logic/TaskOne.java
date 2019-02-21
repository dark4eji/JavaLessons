package logic;

import java.util.List;
import java.util.LinkedList;
import java.util.HashMap;


public class TaskOne {

    private List<String> list = new LinkedList<>();
    private HashMap<String, Integer> hm = new HashMap<>();

    public TaskOne() {
        list.add("Meow");
        list.add("Wow");
        list.add("Bee");
        list.add("Growl");
        list.add("String");
        list.add("Nek");
        list.add("Nek");
        list.add("Ret");
        list.add("Chop");
        list.add("Meow");
        list.add("Bee");
        list.add("Meow");
    }

    public void printArrayData() {
        for (String o : list) {
            if (hm.containsKey(o)) {
                hm.replace(o, hm.get(o), hm.get(o) + 1);
            } else {
                hm.put(o, 1);
            }
        }
        for (String o : hm.keySet()) {
            System.out.println(o + " - " + hm.get(o));
        }
        //System.out.println(hm.entrySet());
    }
}

