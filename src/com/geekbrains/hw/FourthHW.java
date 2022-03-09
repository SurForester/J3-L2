package com.geekbrains.hw;

public class FourthHW {
    public static void main(String[] args) {
        OwnLinkedList ownLinkedList = new OwnLinkedList();
        ownLinkedList.addNode("1");
        ownLinkedList.addNode("2");
        ownLinkedList.addToIndex("3", 1);
        ownLinkedList.addToIndex("4", 1);
        ownLinkedList.addToIndex("5", 0);
        ownLinkedList.display();

        ownLinkedList.deleteToIndex(1);
        ownLinkedList.display();
    }
}
