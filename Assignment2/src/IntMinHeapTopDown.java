public class IntMinHeapTopDown implements IntQueue {

    private IntArray heap;

    public IntMinHeapTopDown() {
        heap = new IntArray();
    }

    public IntMinHeapTopDown(int[] values) {
        heap = new IntArray();
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                enqueue(values[i]);
            }
        }
    }

    @Override
    public void enqueue(int value) {
        heap.add(value);
        siftUp(heap.size() - 1);
    }

    @Override
    public int dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        int min = heap.get(0);
        int last = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, last);
            siftDown(0);
        }
        return min;
    }

    @Override
    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        return heap.get(0);
    }

    @Override
    public int size() {
        return heap.size();
    }

    @Override
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    @Override
    public String toString() {
        return heap.toString();
    }

    private void siftUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (heap.get(index) < heap.get(parent)) {
                swap(index, parent);
                index = parent;
            } else {
                break;
            }
        }
    }

    private void siftDown(int index) {
        int size = heap.size();
        while (true) {
            int left = 2 * index + 1;
            if (left >= size) {
                break;
            }
            int smaller = left;
            int right = left + 1;
            if (right < size && heap.get(right) < heap.get(left)) {
                smaller = right;
            }
            if (heap.get(smaller) < heap.get(index)) {
                swap(index, smaller);
                index = smaller;
            } else {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
}