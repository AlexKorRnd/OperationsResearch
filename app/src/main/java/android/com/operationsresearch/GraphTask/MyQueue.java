package android.com.operationsresearch.GraphTask;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyQueue<Item> implements Iterable<Item> {
    private int numberElems;      // ���-�� ��������� � �������
    private Node<Item> first;    // ������ �������
    private Node<Item> last;     // ����� �������

    // ��������������� ����� ��� �������� �������
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    //������������� ������ �������
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

    // ����������� ������� ������������ ��������
    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("������� �����!");
        return first.item;
    }


    // ���������� �������� � �������
    public void enqueue(Item item) {
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
    public Item dequeue() {
        if (isEmpty()){
            throw new NoSuchElementException("������� �����!");
        }
        Item item = first.item;
        first = first.next;
        numberElems--;
        if (isEmpty()){
            last = null;
        }
        return item;
    }


    //��������, ������� ���������� �������� �������� ������� FIFO
    public Iterator<Item> iterator()  {
        return new ListIterator<Item>(first);
    }

    // ���������� �����, ������������ ��������
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