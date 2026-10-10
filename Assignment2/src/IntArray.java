public class IntArray {
    private int[] data;
    private int size;

    public IntArray() {
        data = new int[4];
        size = 0;
    }

    public void add(int value) {
        if (size == data.length) {
            grow();
        }
        data[size] = value;
        size++;
    }

    public void add(int index, int value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index = " + index);
        }
        if (size == data.length) {
            grow();
        }
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }
        data[index] = value;
        size++;
    }

    public int get(int index) {
        checkIndex(index);
        return data[index];
    }

    public void set(int index, int value) {
        checkIndex(index);
        data[index] = value;
    }

    public int remove(int index) {
        checkIndex(index);
        int removed = data[index];
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
        return removed;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(int[] values) {
        if (values == null) return;
        for (int i = 0; i < values.length; i++) {
            add(values[i]);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(data[i]);
        }
        sb.append("]");
        return sb.toString();
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index = " + index);
        }
    }

    private void grow() {
        int newCapacity = data.length == 0 ? 4 : data.length * 2;
        int[] bigger = new int[newCapacity];
        for (int i = 0; i < size; i++) {
            bigger[i] = data[i];
        }
        data = bigger;
    }
}