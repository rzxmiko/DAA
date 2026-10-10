public class IntStackQueue implements IntQueue {
    // use two stacks as back-end
    private IntArrayStack inStack;
    private IntArrayStack outStack;

    public IntStackQueue() {
        // creates an empty queue
        this.inStack = new IntArrayStack();
        this.outStack = new IntArrayStack();
    }

    @Override
    public void enqueue(int value) {
        inStack.push(value);
    }

    @Override
    public int dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        shiftStacks();
        return outStack.pop();
    }

    @Override
    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        shiftStacks();
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
        // "[]" for empty queue
        // "[7, 8, 6]" for queue containing elements: 7, 8, 6. (head-first)
        if (isEmpty()) {
            return "[]";
        }
        IntArray values = new IntArray();

        // Сначала собираем элементы из outStack (они идут от head к tail)
        IntArrayStack tempOut = new IntArrayStack();
        while (!outStack.isEmpty()) {
            int val = outStack.pop();
            values.add(val);
            tempOut.push(val);
        }
        while (!tempOut.isEmpty()) {
            outStack.push(tempOut.pop());
        }

        // Затем собираем элементы из inStack (они лежат в обратном порядке)
        IntArrayStack tempIn = new IntArrayStack();
        IntArray inValues = new IntArray();
        while (!inStack.isEmpty()) {
            int val = inStack.pop();
            inValues.add(val);
            tempIn.push(val);
        }
        while (!tempIn.isEmpty()) {
            inStack.push(tempIn.pop());
        }
        for (int i = inValues.size() - 1; i >= 0; i--) {
            values.add(inValues.get(i));
        }

        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < values.size(); i++) {
            sb.append(values.get(i));
            if (i < values.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    private void shiftStacks() {
        if (outStack.isEmpty()) {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }
    }
}