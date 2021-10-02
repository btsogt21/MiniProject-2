import java.lang.IllegalStateException;

/**Implement an array list.*/
public class MyArrayList <E> implements ListInterface <E> {

    private Object[] _storage;
    private int _size;
    public MyArrayList () {
        _storage = new Object[0];
        _size    = 0;
    }

    /** Add an element to this list at the given index. Index must already exist or be no greater than 1 above the largest index already present.
     * @param element - Element to be added
     * @param index - Position at which to insert new element
     * @throws IndexOutOfBoundsException if {@code index<0 || list.size <index}
     * @throws IllegalStateException  if list cannot be expanded.*/
    public void add (int index, E element) throws IndexOutOfBoundsException,
            IllegalStateException {
        if (index < 0 || _size < index) {
            throw new IndexOutOfBoundsException(index);
        }
        _size += 1;
        if (_size >= _storage.length) {
            try {
                expandCapacity();
            } catch (OutOfMemoryError e) {
                throw new IllegalStateException("Allocation failed");
            }
        }
        for (int i = _size; i > index; i -= 1) {
            _storage[i] = _storage[i-1];
        }
        _storage[index] = element;
    }

    /** Return element at given index
     * @param index - Position to get element from
     * @return the value of element at given index
     * @throws IndexOutOfBoundsException if {@code index < 0 || list.size <= index}*/
    public E get (int index) throws IndexOutOfBoundsException {
        if (index < 0 || _size <= index) {
            throw new IndexOutOfBoundsException(index);
        }
        return (E)_storage[index];
    }

    /** Remove an element from the given index and resize list as to account for empty index.
     * @param index - Position at which to remove an element.
     * @return the removed element
     * @throws IndexOutOfBoundsException if {@code index < 0 || list.size <= index}*/
    public E remove (int index) throws IndexOutOfBoundsException {
        if (index < 0 || _size <= index) {
            throw new IndexOutOfBoundsException(index);
        }
        _size -= 1;
        E element = (E)_storage[index];
        for (int i = index; i < _size; i += 1) {
            _storage[i] = _storage[i+1];
        }
        return element;
    }

    /** Replace element at given index with the inputted element and return the element that is removed.
     * @element - New element to place at the given position.
     * @param index - Position at which to replace an element.
     * @return removed element
     * @throws IndexOutOfBoundsException if {@code index < 0 || list.size <= index}*/
    public E set (int index, E element) throws IndexOutOfBoundsException {
        if (index < 0 || _size <= index) {
            throw new IndexOutOfBoundsException(index);
        }
        E oldElement = (E)_storage[index];
        _storage[index] = element;
        return oldElement;
    }

    /** Return the number of elements in list*/
    public int size () {
        return _size;
    } // size ()


    private void expandCapacity () {
        if(_storage.length == 0) {
            _storage = new Object[2];
        }
        else {
            Object[] newStorage = new Object[_storage.length * 2];
            for (int i = 0; i < _storage.length; i += 1) {
                newStorage[i] = _storage[i];
            }
            _storage = newStorage;
        }
    }

}

