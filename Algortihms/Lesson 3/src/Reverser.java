public class Reverser {
    public String reverseString(String s) {

        Stack stack = new Stack(s.length());
        for (Character character : s.toCharArray()) {
            stack.push(character);
        }

        StringBuilder sb = new StringBuilder();
        while (stack.getTop() >= 0) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }
}
