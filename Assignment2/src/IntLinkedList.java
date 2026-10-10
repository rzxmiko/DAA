public class IntLinkedList {
    Node head;
    Node tail;
    int size;

    // internal class that represents a list node.
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

    public IntLinkedList(){
        // creates an empty list
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void add(int value){
        // adds a value at the end
        Node newNode = new Node(value, tail, null);
        if (isEmpty()) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        size++;
    }

    public void add(int index, int value){
        // adds a value at index
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        if (index == size) {
            add(value);
            return;
        }
        if (index == 0) {
            Node newNode = new Node(value, null, head);
            if (isEmpty()) {
                tail = newNode;
            } else {
                head.prev = newNode;
            }
            head = newNode;
            size++;
            return;
        }
        Node current = getNode(index);
        Node newNode = new Node(value, current.prev, current);
        current.prev.next = newNode;
        current.prev = newNode;
        size++;
    }

    public int get(int index){
        // returns a value at index
        checkIndex(index);
        return getNode(index).value;
    }

    public void set(int index, int value){
        // updates a value at index
        checkIndex(index);
        getNode(index).value = value;
    }

    public int remove(int index){
        // removes and returns value at index
        checkIndex(index);
        Node nodeToRemove = getNode(index);

        if (nodeToRemove.prev != null) {
            nodeToRemove.prev.next = nodeToRemove.next;
        } else {
            head = nodeToRemove.next;
        }

        if (nodeToRemove.next != null) {
            nodeToRemove.next.prev = nodeToRemove.prev;
        } else {
            tail = nodeToRemove.prev;
        }

        size--;
        return nodeToRemove.value;
    }

    public int size(){
        // returns size of list
        return size;
    }

    public boolean isEmpty(){
        // returns whether list is empty
        return size == 0;
    }

    @Override
    public String toString() {
        // returns String representation of list
        // "[]" for empty list
        // "[7, 8, 6]" for non-empty list
        if (isEmpty()) {
            return "[]";
        }
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
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
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