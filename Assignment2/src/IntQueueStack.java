public class IntQueueStack implements IntStack {
    // use two queues as back-end
    private IntLinkedListQueue q1;
    private IntLinkedListQueue q2;

    public IntQueueStack() {
        this.q1 = new IntLinkedListQueue();
        this.q2 = new IntLinkedListQueue();
    }

    @Override
    public void push(int value) {
        q2.enqueue(value);
        while (!q1.isEmpty()) {
            q2.enqueue(q1.dequeue());
        }
        IntLinkedListQueue temp = q1;
        q1 = q2;
        q2 = temp;
    }

    @Override
    public int pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return q1.dequeue();
    }

    @Override
    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return q1.peek();
    }

    @Override
    public int size() {
        return q1.size();
    }

    @Override
    public boolean isEmpty() {
        return q1.isEmpty();
    }

    @Override
    public String toString() {
        // returns String representation of the stack
        // "[]" for empty stack
        // "[7, 8, 6]" for non-empty one (bottom-element-first)
        if (isEmpty()) {
            return "[]";
        }
        IntLinkedListQueue temp = new IntLinkedListQueue();
        IntArray values = new IntArray();

        while (!q1.isEmpty()) {
            int val = q1.dequeue();
            values.add(val);
            temp.enqueue(val);
        }
        while (!temp.isEmpty()) {
            q1.enqueue(temp.dequeue());
        }

        StringBuilder sb = new StringBuilder("[");
        for (int i = values.size() - 1; i >= 0; i--) {
            sb.append(values.get(i));
            if (i > 0) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}