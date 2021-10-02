import java.lang.IllegalStateException;
import java.util.LinkedList;

/**Implement a list using a standard LinkedList.*/
public class ListWrapper <E> implements ListInterface <E> {

    /** Linked list in which the elements will be stored. */
    private LinkedList<E> _storage;

    /** Constructor.  Create an empty list.*/
    public ListWrapper () {
        _storage = new LinkedList<E>();
    }

    /** Add an element to this list at the given index. Index must already exist or be no greater than 1 above the largest index already present.
     * @param element - Element to be added
     * @param index - Position at which to insert new element
     * @throws IndexOutOfBoundsException if {@code index<0 || list.size < index}
     * @throws IllegalStateException  if list cannot be expanded.*/
    public void add (int index, E element) throws IndexOutOfBoundsException,
            IllegalStateException {
        _storage.add(index, element);
    }

    /** Return value/element at given index
     * @param index - Position to get element from
     * @return the value of element at given index
     * @throws IndexOutOfBoundsException if {@code index < 0 || list.size <= index}*/
    public E get (int index) throws IndexOutOfBoundsException {
        return _storage.get(index);
    }

    /** Remove an element from the given index. List size is shrunk, with elements in higher indexes shifting down to fill the gap
     * @param index The position at which to remove an element.
     * @return the removed element.
     * @throws IndexOutOfBoundsException if {@code index < 0 || list.size <= index}*/
    public E remove (int index) throws IndexOutOfBoundsException {
        return _storage.remove(index);
    }

    /** Replace the element at the given index with another, given element.
     * @param index   The position at which to replace an element.
     * @param element The new element to place at the given position.
     * @return the removed element previously at the given position.
     * @throws IndexOutOfBoundsException if {@code index < 0 || size <= index}*/
    public E set (int index, E element) throws IndexOutOfBoundsException {
        return _storage.set(index, element);
    }

    /** Return the number of elements in the list.*/
    public int size () {
        return _storage.size();
    }
}