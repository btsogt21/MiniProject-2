import java.lang.IllegalStateException;
import java.util.LinkedList;

class Link <E> {
    public Link<E> next;
    public Link<E> prev;
    public E       value;
}

/**Implement a linked list.*/
public class MyLinkedList <E> implements ListInterface <E> {

    private Link<E> head;
    private int     size;

    /** Constructor to create empty linked list */
    public MyLinkedList () {
        head = new Link<E>();
        Link<E> tail = new Link<E>();
        head.next = tail;
        head.prev = null;
        tail.next = null;
        tail.prev = head;
        size = 0;
    }

    /** Add an element to this list at the given index. Index must already exist or be no greater than 1 above the largest index already present.
     * @param element - Element to be added
     * @param index - Position at which to insert new element
     * @throws IndexOutOfBoundsException if {@code index<0 || list.size <index}
     * @throws IllegalStateException  if list cannot be expanded.*/
    public void add (int index, E element) throws IndexOutOfBoundsException,
            IllegalStateException {
        if (index < 0 || size < index) {
            throw new IndexOutOfBoundsException(index);
        }
        Link<E> p = walk(index);
        Link<E> n = new Link<E>();
        n.next      = p;
        n.prev      = p.prev;
        n.prev.next = n;
        p.prev      = n;
        n.value = element;
        size = size + 1;
    }

    /** Return element at given index
     * @param index - Position to get element from
     * @return the value of element at given index
     * @throws IndexOutOfBoundsException if {@code index < 0 || list.size <= index}*/
    public E get (int index) throws IndexOutOfBoundsException {
        if (index < 0 || size <= index) {
            throw new IndexOutOfBoundsException(index);
        }
        Link<E> p = walk(index);
        return p.value;
    }

    /** Remove an element from the given index and resize list as to account for empty index.
     * @param index - Position at which to remove an element.
     * @return the removed element
     * @throws IndexOutOfBoundsException if {@code index < 0 || list.size <= index}*/
    public E remove (int index) throws IndexOutOfBoundsException {
        if (index < 0 || size <= index) {
            throw new IndexOutOfBoundsException(index);
        }
        Link<E> p     = walk(index);
        E       value = p.value;
        p.prev.next = p.next;
        p.next.prev = p.prev;
        size = size - 1;
        return value;
    }

    /** Replace element at given index with the inputted element and return the element that is removed.
     * @element - New element to place at the given position.
     * @param index - Position at which to replace an element.
     * @return removed element
     * @throws IndexOutOfBoundsException if {@code index < 0 || list.size <= index}*/
    public E set (int index, E element) throws IndexOutOfBoundsException {
        if (index < 0 || size <= index) {
            throw new IndexOutOfBoundsException(index);
        }
        Link<E> p     = walk(index);
        E       value = p.value;
        p.value = element;
        return value;
    }

    /** Return the number of elements in list*/
    public int size () {
        return size;
    }

    /** Walk to a given index and return its value*/
    private Link<E> walk (int index) {
        Link<E> current = head.next;
        for (int i = 0; i < index; i = i + 1) {
            current = current.next;
        }
        return current;
    }
}
