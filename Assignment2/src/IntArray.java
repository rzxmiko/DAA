public class IntArray {
    private int[] data;
    private int size;

    public IntArray() {
        // creates an empty array
        this.data = new int[10];
        this.size = 0;
    }

    public void add(int value) {
        // adds value at the end
        if (size == data.length) {
            int[] newData = new int[data.length * 2];
            for (int i = 0; i < size; i++) {
                newData[i] = data[i];
            }
            data = newData;
        }
        data[size++] = value;
    }

    public void add(int index, int value) {
        // adds value at index
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (size == data.length) {
            int[] newData = new int[data.length * 2];
            for (int i = 0; i < size; i++) {
                newData[i] = data[i];
            }
            data = newData;
        }
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }
        data[index] = value;
        size++;
    }

    public int get(int index) {
        // returns value at index
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return data[index];
    }

    public void set(int index, int value) {
        // updates value at index
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        data[index] = value;
    }

    public int remove(int index) {
        // removes and returns value at index
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        int removedValue = data[index];
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
        return removedValue;
    }

    public int size() {
        // returns the current size of array
        return size;
    }

    public boolean isEmpty() {
        // returns whether array is empty
        return size == 0;
    }

    public void add(int[] values) {
        // adds entire array values
        if (values == null) return;
        for (int value : values) {
            add(value);
        }
    }

    @Override
    public String toString() {
        // return String representation of array
        // "[]" empty array
        // "[7, 8, 6]" non-empty array
        if (size == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}