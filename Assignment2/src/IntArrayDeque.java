public class IntArrayDeque implements IntDeque {
    // use IntArray as a circular array as back-end for this deque
    private IntArray array;
    private int head;
    private int tail;

    public IntArrayDeque() {
        // creates an empty deque
        this.array = new IntArray();
        this.head = 0;
        this.tail = 0;
    }

    @Override
    public void addFirst(int value) {
        if (array.size() == 0) {
            array.add(value);
            head = 0;
            tail = 0;
        } else {
            head = (head - 1 + array.size()) % array.size();
            if (head == tail) {
                array.add(head, value);
                tail = (tail + 1) % array.size();
            } else {
                array.set(head, value);
            }
        }
    }

    @Override
    public void addLast(int value) {
        if (array.size() == 0) {
            array.add(value);
            head = 0;
            tail = 0;
        } else {
            int capacity = array.size();
            tail = (tail + 1) % capacity;
            if (tail == head) {
                array.add(tail, value);
            } else {
                array.set(tail, value);
            }
        }
    }

    @Override
    public int removeFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("Deque is empty");
        }
        int value = array.get(head);
        if (size() == 1) {
            array.remove(head);
            head = 0;
            tail = 0;
        } else {
            head = (head + 1) % array.size();
        }
        return value;
    }

    @Override
    public int removeLast() {
        if (isEmpty()) {
            throw new IllegalStateException("Deque is empty");
        }
        int value = array.get(tail);
        if (size() == 1) {
            array.remove(tail);
            head = 0;
            tail = 0;
        } else {
            tail = (tail - 1 + array.size()) % array.size();
        }
        return value;
    }

    @Override
    public int peekFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("Deque is empty");
        }
        return array.get(head);
    }

    @Override
    public int peekLast() {
        if (isEmpty()) {
            throw new IllegalStateException("Deque is empty");
        }
        return array.get(tail);
    }

    @Override
    public int size() {
        if (array.isEmpty()) {
            return 0;
        }
        return (tail - head + array.size()) % array.size() + 1;
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public String toString() {
        // "[]" for empty deque
        // "[7, 8, 6]" for deque containing elements: 7, 8, 6. (front-first)
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        int sz = size();
        for (int i = 0; i < sz; i++) {
            int index = (head + i) % array.size();
            sb.append(array.get(index));
            if (i < sz - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}