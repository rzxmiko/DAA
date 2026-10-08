public class IntLinkedListDeque implements IntDeque{
    // use IntLinkedList as back-end for this deque

    public IntLinkedListDeque() {
        // creates an empty deque
    }

    @Override
    public void addFirst(int value) {

    }

    @Override
    public void addLast(int value) {

    }

    @Override
    public int removeFirst() {
        return 0;
    }

    @Override
    public int removeLast() {
        return 0;
    }

    @Override
    public int peekFirst() {
        return 0;
    }

    @Override
    public int peekLast() {
        return 0;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public String toString() {
        // "[]" for empty deque
        // "[7, 8, 6]" for deque containing elements: 7, 8, 6. (front-first)
        return super.toString();
    }
}