package android.com.operationsresearch.GraphTask;

/**
 * Created by Алексей on 20.05.2015.
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class myList<Item> implements Iterable<Item> {
    private int quantityNode;    // кол-во вершин в списке
    private Node<Item> first;    // вершина списка

    // вспомогательный класс для вершины списка
    private static class Node<Item> {
        private Item item;

        private Node<Item> next;
    }

    /**
     * Создание пустого списка
     */
    public myList() {
        first = null;
        quantityNode = 0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return quantityNode;
    }

    /**
     * Adds the item to this bag.
     * @param item the item to add to this bag
     */
    public void add(Item item) {
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldfirst;
        quantityNode++;
    }


    /**
     * Returns an iterator that iterates over the items in the bag in arbitrary order.
     * @return an iterator that iterates over the items in the bag in arbitrary order
     */
    public Iterator<Item> iterator()  {
        return new ListIterator<Item>(first);
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }




}
