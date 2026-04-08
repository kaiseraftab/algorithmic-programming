package nl.nhlstenden.ap.datastructures;

/**
 * Custom List interface that our self-built data structures will implement.
 * Uses generics as covered in Week 5 of the module.
 *
 * @param <T> the type of elements in this list
 */
public interface CustomList<T> {

    void add(T element);

    void add(int index, T element);

    T get(int index);

    /**
     * Replaces the element at the specified index.
     * Essential for in-place sorting algorithms.
     *
     * @param index   the index of the element to replace
     * @param element the new element
     */
    void set(int index, T element);

    T remove(int index);

    boolean contains(T element);

    int indexOf(T element);

    int size();

    boolean isEmpty();

    void clear();
}
