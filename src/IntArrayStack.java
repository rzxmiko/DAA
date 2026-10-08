public class IntArrayStack implements IntStack {
    // uses IntArray as back-end
    private IntArray array;

    public IntArrayStack() {
        // creates empty stack
        this.array = new IntArray();
    }

    @Override
    public void push(int value) {
        // pushes value on top of stack
        array.add(value);
    }

    @Override
    public int pop() {
        // pops a value from top of stack
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return array.remove(array.size() - 1);
    }

    @Override
    public int peek() {
        // returns the value from top of stack without removing it
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return array.get(array.size() - 1);
    }

    @Override
    public int size() {
        // returns the size of the stack
        return array.size();
    }

    @Override
    public boolean isEmpty() {
        // returns whether the stack is empty
        return array.isEmpty();
    }

    @Override
    public String toString() {
        // returns String representation of the stack
        // "[]" for empty stack
        // "[7, 8, 6]" for non-empty one (bottom-element-first)
        return array.toString();
    }
}