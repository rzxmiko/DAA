public class IntArrayDeque implements IntDeque {
    private IntArray data;
    private int head;
    private int tail;
    private int size;

    public IntArrayDeque() {
        data = createArray(4);
        head = 0;
        tail = 0;
        size = 0;
    }

    @Override
    public void addFirst(int value) {
        if (size == data.size()) {
            grow();
        }
        head = (head - 1 + data.size()) % data.size();
        data.set(head, value);
        size++;
    }

    @Override
    public void addLast(int value) {
        if (size == data.size()) {
            grow();
        }
        data.set(tail, value);
        tail = (tail + 1) % data.size();
        size++;
    }

    @Override
    public int removeFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("Deque is empty");
        }
        int removed = data.get(head);
        head = (head + 1) % data.size();
        size--;
        return removed;
    }

    @Override
    public int removeLast() {
        if (isEmpty()) {
            throw new IllegalStateException("Deque is empty");
        }
        tail = (tail - 1 + data.size()) % data.size();
        size--;
        return data.get(tail);
    }

    @Override
    public int peekFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("Deque is empty");
        }
        return data.get(head);
    }

    @Override
    public int peekLast() {
        if (isEmpty()) {
            throw new IllegalStateException("Deque is empty");
        }
        int lastIndex = (tail - 1 + data.size()) % data.size();
        return data.get(lastIndex);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            int index = (head + i) % data.size();
            sb.append(data.get(index));
        }
        sb.append("]");
        return sb.toString();
    }

    private IntArray createArray(int capacity) {
        IntArray array = new IntArray();
        for (int i = 0; i < capacity; i++) {
            array.add(0);
        }
        return array;
    }

    private void grow() {
        IntArray bigger = createArray(data.size() * 2);
        for (int i = 0; i < size; i++) {
            bigger.set(i, data.get((head + i) % data.size()));
        }
        data = bigger;
        head = 0;
        tail = size;
    }
}