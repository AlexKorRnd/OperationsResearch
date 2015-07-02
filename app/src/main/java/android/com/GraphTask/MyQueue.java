package android.com.GraphTask;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyQueue<Item> implements Iterable<Item> {
    private int numberElems;      // кол-во элементов в очереди
    private Node<Item> first;    // начало очереди
    private Node<Item> last;     // конец очереди

    // вспомогательный класс для элемента очереди
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    //Инициализация пустой очереди
    public MyQueue() {
        first = null;
        last  = null;
        numberElems = 0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return numberElems;
    }

    // Возвращение первого добавленного элемента
    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Очередь пуста!");
        return first.item;
    }


    // Добавление элемента в очередь
    public void push(Item item) {
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        if (isEmpty()){
            first = last;
        }
        else{
            oldlast.next = last;
        }
        numberElems++;
    }

    //
    public Item pop() {
        if (isEmpty()){
            throw new NoSuchElementException("Очередь пуста!");
        }
        Item item = first.item;
        first = first.next;
        numberElems--;
        if (isEmpty()){
            last = null;
        }
        return item;
    }


    //Итератор, который перебирает элементы согласно правилу FIFO
    public Iterator<Item> iterator()  {
        return new ListIterator<Item>(first);
    }

    // внутренний класс, определяющий итератор
    private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }


}