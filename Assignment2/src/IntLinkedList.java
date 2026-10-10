public class IntLinkedList {
    Node head;
    Node tail;
    int size;

    static class Node {
        int value;
        Node next;
        Node prev;

        Node() {
            value = 0;
            next = null;
            prev = null;
        }

        Node(int value) {
            this.value = value;
            next = null;
            prev = null;
        }

        Node(int value, Node prev, Node next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }

    public IntLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public void add(int value) {
        Node node = new Node(value, tail, null);
        if (tail == null) {
            head = node;
        } else {
            tail.next = node;
        }
        tail = node;
        size++;
    }

    public void add(int index, int value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index = " + index);
        }
        if (index == size) {
            add(value);
            return;
        }
        Node current = getNode(index);
        Node node = new Node(value, current.prev, current);
        if (current.prev == null) {
            head = node;
        } else {
            current.prev.next = node;
        }
        current.prev = node;
        size++;
    }

    public int get(int index) {
        checkIndex(index);
        return getNode(index).value;
    }

    public void set(int index, int value) {
        checkIndex(index);
        getNode(index).value = value;
    }

    public int remove(int index) {
        checkIndex(index);
        Node node = getNode(index);
        if (node.prev == null) {
            head = node.next;
        } else {
            node.prev.next = node.next;
        }
        if (node.next == null) {
            tail = node.prev;
        } else {
            node.next.prev = node.prev;
        }
        size--;
        return node.value;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node current = head;
        while (current != null) {
            sb.append(current.value);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index = " + index);
        }
    }

    private Node getNode(int index) {
        Node current;
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        }
        return current;
    }
}