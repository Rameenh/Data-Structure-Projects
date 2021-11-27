

public class IDedLinkedList <AnyType extends IDedObject>{

    class Node {
        private AnyType data;
        private Node next;


        Node(AnyType a, Node b){
            data = a;
            next = b;
        }

        Node(AnyType a){
            data = a;
            next = null;
        }

        public Node getNext(){
            return next;
        }

        public void setNext(Node a){
            next = a;
        }

        public AnyType getData(){
            return data;
        }

    }

    private int length;
    private Node head;

    public IDedLinkedList(){
        head = null;
    }

    void makeEmpty(){
        head = null; //pretty straight forward
    }

    AnyType findID(int ID){
        Node scan = head;

        if(scan == null) return null; //nothing in list
        else {

            while (scan != null) { //basically checks if we've reached the end of the list
                if (scan.getData().getID() == ID) {
                    return scan.getData();  //returns the item if found
                } else
                    scan = scan.getNext(); //move scan down the list by 1
            }
        }

        return null; //if nothing found
    }

    boolean insertAtFront(AnyType x){
        Node toInsert = new Node(x);

        if(head == null){  //if list is empty
            head = toInsert;
            return true;
        }
        else {
            Node scan = head;
            while (scan != null) {
                //System.out.print("head ID: " + head.getData().getID() + " target ID: " + x.getID() + "\n");
                if (scan.getData().getID() == x.getID()) //duplicate found
                    return false;
                else
                    scan = scan.getNext(); //traverse down the list
            }
            toInsert.setNext(head); //no duplicates found!
            head = toInsert;
            return true;
        }
    }


    AnyType deleteFromFront(){
        //System.out.print("4");
        if(head == null) return null;
        else {


            AnyType toReturn = head.getData();
            head = head.getNext();

            return toReturn;
        }

    }

    AnyType delete(int ID){
        //System.out.print("1");
        if(head == null){
            //System.out.print("2");
            return null; //if list is empty
        }
        //System.out.print("head ID: " + head.getData().getID() + " target ID: " + ID + "\n");
        else if(head.getData().getID() == ID){//we're lucky and toDelete happens to be in the first slot
            //System.out.print("3");
            return deleteFromFront();
        }
        else {

            Node nextNodToLink = head.getNext();
            Node curNodToDel = head;
            AnyType toReturn;

            while (nextNodToLink != null) { //look through entire list
                if (nextNodToLink.getData().getID() == ID) { //if you find the target id
                    toReturn = nextNodToLink.getData(); //delete it
                    curNodToDel.setNext(nextNodToLink.getNext());
                    return toReturn;
                } else { //keep looking until you reach the end
                    curNodToDel = nextNodToLink;
                    nextNodToLink = nextNodToLink.getNext();
                }
            }
            toReturn = null;
            return toReturn; //returns null if something found
        }
    }

    int printTotal(){
        if(head == null) return -1;
        else{
            int sum = 0;
            Node temp = head;
            while(temp != null){
                sum = sum + temp.getData().getID();
                temp = temp.getNext();
            }
            return sum;
        }
    }




}


