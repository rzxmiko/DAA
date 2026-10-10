public class IntQueueStack implements IntStack {

    private IntLinkedListQueue main = new IntLinkedListQueue();
    private IntLinkedListQueue helper = new IntLinkedListQueue();

    @Override
    public void push(int value) {
        helper.enqueue(value);
        while (!main.isEmpty()) {
            helper.enqueue(main.dequeue());
        }
        IntLinkedListQueue temp = main;
        main = helper;
        helper = temp;
    }

    @Override
    public int pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return main.dequeue();
    }

    @Override
    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return main.peek();
    }

    @Override
    public int size() {
        return main.size();
    }

    @Override
    public boolean isEmpty() {
        return main.isEmpty();
    }

    @Override
    public String toString() {
        IntArray items = new IntArray();
        int n = main.size();
        for (int i = 0; i < n; i++) {
            int value = main.dequeue();
            items.add(value);
            main.enqueue(value);
        }
        StringBuilder sb = new StringBuilder("[");
        for (int i = items.size() - 1; i >= 0; i--) {
            sb.append(items.get(i));
            if (i > 0) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}