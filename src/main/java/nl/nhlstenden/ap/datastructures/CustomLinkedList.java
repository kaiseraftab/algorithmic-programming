package nl.nhlstenden.ap.datastructures;

/**
 * A singly linked list implementation of the CustomList interface.
 * Built from scratch as required by the assignment.
 *
 * Each element is stored in a Node that holds the data and a reference
 * to the next node in the chain.
 *
 * @param <T> the type of elements in this list
 */
public class CustomLinkedList<T> implements CustomList<T> {

    /**
     * Internal node class representing a single element in the linked list.
     */
    private class Node {
        T data;
        Node next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private int size;

    public CustomLinkedList() {
        this.head = null;
        this.size = 0;
    }

    /**
     * Adds an element to the end of the list.
     * Time complexity: O(n) — must traverse to the end.
     */
    @Override
    public void add(T element) {
        Node newNode = new Node(element);

        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    /**
     * Inserts an element at the specified index.
     * Time complexity: O(n)
     *
     * @throws IndexOutOfBoundsException if index is out of range
     */
    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        Node newNode = new Node(element);

        if (index == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
        size++;
    }

    /**
     * Returns the element at the specified index.
     * Time complexity: O(n)
     *
     * @throws IndexOutOfBoundsException if index is out of range
     */
    @Override
    public T get(int index) {
        checkIndex(index);
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    /**
     * Replaces the element at the specified index.
     * Time complexity: O(n)
     *
     * @throws IndexOutOfBoundsException if index is out of range
     */
    @Override
    public void set(int index, T element) {
        checkIndex(index);
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        current.data = element;
    }

    /**
     * Removes and returns the element at the specified index.
     * Time complexity: O(n)
     *
     * @throws IndexOutOfBoundsException if index is out of range
     */
    @Override
    public T remove(int index) {
        checkIndex(index);

        T removedData;

        if (index == 0) {
            removedData = head.data;
            head = head.next;
        } else {
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            removedData = current.next.data;
            current.next = current.next.next;
        }

        size--;
        return removedData;
    }

    /**
     * Checks if the list contains the specified element.
     * Time complexity: O(n)
     */
    @Override
    public boolean contains(T element) {
        return indexOf(element) != -1;
    }

    /**
     * Returns the index of the first occurrence of the specified element.
     * Time complexity: O(n)
     *
     * @return the index, or -1 if not found
     */
    @Override
    public int indexOf(T element) {
        Node current = head;
        for (int i = 0; i < size; i++) {
            if (current.data.equals(element)) {
                return i;
            }
            current = current.next;
        }
        return -1;
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
    public void clear() {
        head = null;
        size = 0;
    }

    /**
     * Validates that the index is within bounds.
     *
     * @throws IndexOutOfBoundsException if index is out of range
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node current = head;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }
}
