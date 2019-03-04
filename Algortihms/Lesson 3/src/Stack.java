public class Stack {
    private int maxSize;
    private char[] stack;
    private int top;

    public Stack(int size) {
        this.maxSize = size;
        this.stack = new char[maxSize];
        this.top = -1;
    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public boolean isFull() {
        return (top == maxSize - 1);
    }

    public void push(char i) {
        this.stack[++this.top] = i;
    }

    public char pop() {
        return this.stack[this.top--];
    }

    public char peek() {
        return this.stack[this.top];
    }

    public int getTop(){
        return top;
    }
}
