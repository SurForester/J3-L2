package com.geekbrains.hw;

public class OwnLinkedList {
    private long size;
    private Node head;
    private Node tail;

    public OwnLinkedList() {
        this.size = 0;
        this.head = null;
        this.tail = null;
    }

    public void addNode(String data) {
        Node newNode = new Node(data);

        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
        }

        tail = newNode;
        size++;
    }

    public void display() {
        Node current = head;

        if (head == null) {
            System.out.println("Односвязный список пуст");
            return;
        }

        while (current != null) {
            System.out.println(current.data + " ");
            current = current.next;
        }

        System.out.println();
    }

    public void display(int index) {
        if (index > size) {
            System.out.println("Размер списка больше чем заданный индекс");
            return;
        }

        Node current = head;

        for (int i = 1; i <= index; i++) {
            current = current.next;
        }

        System.out.println(current.data);
    }

    public void addToIndex(String data, int index) {
        Node newNode = new Node(data);
        Node current = head;
        int currentIndex = 1;

        if (head == null) {
            addNode(data);
            return;
        }

        if (index == 0) {
            newNode.next = current;
            head = newNode;
            return;
        }

        while (head.next != null) {
            if (index == currentIndex++) {
                newNode.next = current.next;
                current.next = newNode;
                return;
            }
            current = current.next;
        }

    }

    public void deleteToIndex(int index) {
        Node current = head;
        int currentIndex = 1;

        if (head == null) {
            return;
        }

        if (index == 0) {
            head = current.next;
            return;
        }

        while (current.next != null) {
            if (index == currentIndex++) {
                current.next = current.next.next;
                return;
            }
            current = current.next;
        }
    }


    private class Node {
        String data;
        Node next;

        public Node(String data) {
            this.data = data;
            this.next = null;
        }
    }
}