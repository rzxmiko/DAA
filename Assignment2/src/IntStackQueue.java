public class IntStackQueue implements IntQueue {

    private IntArrayStack inStack;
    private IntArrayStack outStack;

    public IntStackQueue() {
        inStack = new IntArrayStack();
        outStack = new IntArrayStack();
    }

    @Override
    public void enqueue(int value) {
        inStack.push(value);
    }

    @Override
    public int dequeue() {
        shift();
        if (outStack.isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return outStack.pop();
    }

    @Override
    public int peek() {
        shift();
        if (outStack.isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return outStack.peek();
    }

    @Override
    public int size() {
        return inStack.size() + outStack.size();
    }

    @Override
    public boolean isEmpty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }

    @Override
    public String toString() {
        IntArrayStack temp = new IntArrayStack();
        StringBuilder sb = new StringBuilder("[");
        boolean first = true;

        while (!outStack.isEmpty()) {
            int val = outStack.pop();
            if (!first) {
                sb.append(", ");
            }
            sb.append(val);
            first = false;
            temp.push(val);
        }
        while (!temp.isEmpty()) {
            outStack.push(temp.pop());
        }

        while (!inStack.isEmpty()) {
            temp.push(inStack.pop());
        }
        while (!temp.isEmpty()) {
            int val = temp.pop();
            if (!first) {
                sb.append(", ");
            }
            sb.append(val);
            first = false;
            inStack.push(val);
        }

        sb.append("]");
        return sb.toString();
    }

    private void shift() {
        if (outStack.isEmpty()) {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }
    }
}