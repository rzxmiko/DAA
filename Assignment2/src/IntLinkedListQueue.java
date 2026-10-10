public class IntLinkedListQueue implements IntQueue {
    private IntLinkedList list;

    public IntLinkedListQueue() {
        list = new IntLinkedList();
    }

    @Override
    public void enqueue(int value) {
        list.add(value);
    }

    @Override
    public int dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return list.remove(0);
    }

    @Override
    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return list.get(0);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public String toString() {
        return list.toString();
    }
}