package acquire;

// @author Austin Lyman
// Code for Nodes taken from pages 126 and 127 in the book.

public class SinglyLinkedList <E extends Comparable> {
    private int size = 0;
    private Node head = null;
    private Node tail = null;

    private static class Node<E>{
        private E element;
        private Node<E> next;
        public Node(E e, Node<E> n){
            element = e;
            next = n;
        }


        //returns the value
        public E getElement(){
            return element;
        }

        //goes to the next
        public Node<E> getNext(){
            return next;
        }

        //sets the value in the node
        public void setNext(Node<E> n){
            next = n;
        }
    }

    public Object first() {
        if (this.isEmpty()) {
            System.out.println("The List is empty");
            return null;
        } else {

            return head.getElement();
        }
    }

    public Object last() {
        if(this.isEmpty()){
            System.out.println("The List is empty");
            return null;
        }
        return tail.getElement();
    }

    public void addLast(Object element) {
        //creates a new node, sets the previous tail point to the new tail, and makes current tail point to new tail.
        if(this.valueNotNull(element)) {
            if (this.headExists()) {
                Node toAdd = new Node(element, null);
                tail.setNext(toAdd);
                tail = toAdd;
                tail.setNext(null);
                this.size++;
            } else {
                addFirst(element);
            }
        }

    }

    public void addFirst(Object element) {
        //makes a new element that points to the head, and makes it the new head
        if(this.valueNotNull(element)) {
            if (this.headExists()) {
                Node toAdd = new Node(element, head);
                head = toAdd;
                size++;
            } else {
                head = new Node(element, null);
                tail = head;
                this.size++;
            }
        }

    }

    public Object removeFirst() {
        //sets the head to be the one following it, and decreases the size
        if(this.isEmpty()){
            System.out.println("The List is empty");
            return null;
        }
        head = head.getNext();
        this.size--;
        return 0;
    }

    public Object removeLast() {
        //starts at the beginning and works until it is 2 from the null end, then sets the current one to be the end
        if(this.isEmpty()){
            System.out.println("The List is empty");
            return null;
        }
        int length = size;
        Node current = head;
        while(length > 2){
            current = current.getNext();
            length --;
        }
        Node toRemove = current.getNext();
        current.setNext(null);
        this.size--;
        return toRemove.getElement();
    }

    public void insert(Object element, int index) {

        if (this.valueNotNull(element)) {
            Node newNode = new Node(element, null);

            if (index > size){
                this.addLast(element);
            }

            //NEED TO ADD ERROR CHECKING HERE (what size is the index)
            if (!(this.sizeOk(index))) {
                return;
            }

            //goes through until 1 before the insertion point, then inserts
            Node toInsert = head;
            while (index > 1) {
                index--;
                toInsert = toInsert.getNext();
            }
            //must set both to point at the same, then point the first to point at the new
            newNode.setNext(toInsert.getNext());
            toInsert.setNext(newNode);
            this.size++;

        }
    }

    public Object remove(int index) {
        //goes through the list subtracting one, then removes the element, repoint the remaining elements, and subtracts one from size
        if ((this.sizeOk(index)) && !(this.isEmpty())) {
            Node beforeRemove = head;
            while (index > 2) {
                index--;
                beforeRemove = beforeRemove.getNext();
            }

            //sets the Node before the removed one to point to the one after the removed one.
            Node toRemove = beforeRemove.getNext();
            beforeRemove.setNext(toRemove.getNext());
            this.size--;
            return toRemove.getElement();
        } else {
            return null;
        }
    }

    public Object get(int index) {

        if ((this.sizeOk(index)) && !this.isEmpty()) {
            System.out.println("test passed.");
            //goes through the list subtracting one until 1, then return element
            Node search = head;
            while (index > 0) {
                search = search.getNext();
                index--;
            }
            return search.getElement();
        } else {
            return null;
        }
    }

    public int size() {

        return this.size;
    }

    public boolean isEmpty() {
        if (size <= 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public void printList() {
        //goes through each value ( until it hits the null ) and prints the element

        //checks to make sure head exists
        if (head == null) {
            System.out.println("There is nothing in the list");
        } else {
            Node current = head;
            for (int i = 0; i < size; i++) {
                System.out.println(current.getElement());
                current = current.getNext();
            }

        }
    }

    private boolean sizeOk(int index){
        //returns a bool telling whether or not the list can have something inserted there.

        //checks if the index is larger than the size or less than 0
        if(index >= this.size || index < 0){
            System.out.println("List is not that big and the index and size are respective " + index + this.size);
            return false;
        }else{
            return true;
        }
    }

    private boolean headExists(){
        if(this.head != null){
            return true;
        }else{
            return false;
        }
    }

    private boolean valueNotNull(Object element){
        if(element != null){
            return true;
        }else{
            return false;
        }

    }
}
