
public class LazyBinarySearchTree {
    
    public TreeNode root;

    public LazyBinarySearchTree() //default constructor
    {
        root = null;
    }

    private boolean inRange(int key) //checks to see if key is in range between 1 and 99
    {
        return ((key >= 1) && (key <= 99));
    }

    private boolean isEmpty() //checks to see if tree is empty
    {
        return (this.root == null);
    }

    private int height(TreeNode node) //returns height of tree
    {
        return (node != null ? node.height : 0); //is tree not empty? if so, return private height variable, otherwise return 0
    }



    public int height() //basically get method for assignment
    {

        int toReturn; //variable to return when size found

        toReturn = (isEmpty() ? -1 : this.root.height - 1); //is tree empty? if so, return -1, if not, return height variable

        return toReturn;
    }


    public boolean insert(int key) throws IllegalArgumentException { //insertion method, takes int as key value

        boolean toReturn; //initialize to return boolean to see if insertion was successful
        if (!inRange(key)) //if the key is out of range
            throw new IllegalArgumentException("Error in insert: IllegalArgumentException raised"); //throw error

        else if (isEmpty()) //otherwise, check if tree is empty
        {
            this.root = new TreeNode(key); //if it's empty, make a new tree with this key in its root node
            toReturn = true; //insertion successful
        }
        else //otherwise
        {
            toReturn = insert(this.root, key); //recursive call given the root of tree calling method and key
        }
        return toReturn; //shows whether insertion was successful not not
    }


    private boolean insert(TreeNode thisNode, int key)
    {
        boolean goodInsert = false;

        int signum = Long.signum((long)key - thisNode.key);

        switch (signum){

            case -1: //if given key is less then the root key
            {
                if (thisNode.leftChild != null) //if the node to the left of root is not empty
                    goodInsert = insert(thisNode.leftChild, key); //recursive call to insert in left branch
                else //otherwise
                {
                    thisNode.leftChild = (new TreeNode(key)); // create a new node given the key and place it into root's left node
                    goodInsert = true;
                }
                break; //get out
            }

            case 1: //if key is greater than node; should be placed to the right of node
            {
                if (thisNode.rightChild != null) //if node to the right is not empty
                    goodInsert = insert(thisNode.rightChild, key); //recursive call to insert in right branch
                else //if there is no child right node to the current node
                {
                    thisNode.rightChild = (new TreeNode(key)); //create a new node given and key and place it to the right of the current node
                    goodInsert = true;
                }
                break; //get out of case
            }
            case 0:
            {
                goodInsert = (thisNode.deleted ? true : false); //if the node is deleted set its deletion flag to false

                if (thisNode.deleted)
                {
                    thisNode.deleted = false;

                }
                break;

            }
        }


        int leftSubtreeHeight = height(thisNode.leftChild); //find height of left subtree
        int rightSubtreeHeight = height(thisNode.rightChild); //find height of right subtree
        int newHeight = Math.max(leftSubtreeHeight, rightSubtreeHeight); //find the max between the two
        thisNode.height = (newHeight+1); //set the tree height
        return goodInsert;
    }



    public boolean delete(int key) throws IllegalArgumentException {

        if (!inRange(key)) //is the key in range?
            throw new IllegalArgumentException("Error in delete: IllegalArgumentException raised"); //if not, throw error

        return (isEmpty() ? false : delete(this.root, key)); //if tree is empty, return false because you didn't delete anything, otherwise recursive call
    }


    private boolean delete(TreeNode thisNode, int key) { //this is the recursive call
        boolean deleted=false; //initialize successful delete flag

        int signum = Long.signum((long) key - thisNode.key); //setup for case switch

        switch (signum) {

            case -1://if key is less than current node
            {
                deleted = (thisNode.leftChild != null ? delete(thisNode.leftChild, key) : false); //recursive call to left child
                break;

            }
            case 1: //if key is less than current node
            {
                deleted = (thisNode.rightChild != null ? delete(thisNode.rightChild, key) : false); //recursive call to right child
                break;

            }
            case 0: //current node key and key to delete are the same, find value to delete
            {
                if (thisNode.deleted == true) //if already deleted
                    deleted = false; //deleted = false (we didn't delete anything)
                else {

                    thisNode.deleted = true; //make node deleted
                    deleted = true; //we did delete something
                }
                break;
            }
        }
        return deleted;
    }


    private int findMin(TreeNode node)
    {
        TreeNode temp = node; //make a temp node
        if(node == null)
        { //preliminary check to see if tree is empty
            return -1; //if it is, return -1
        }
        int min=node.key; //set the min value to the initial node key
        while(temp.leftChild != null)
        { //while there is a left child (since left is lesser than and this is findMin)
            temp = temp.leftChild; //set the tmp node to left child
            if(temp.key < min && !temp.deleted) //if temp's key is smaller than the min value
                min = temp.key;    //set min value to temp's key
        }
        return min;
    }

    public int findMin()
    {
        return findMin(this.root);
    }

    private int findMax(TreeNode node) //basically the same as findmin, except it doesn't work sometimes for some reason
    {
        TreeNode temp = node;
        if(node == null)
        {
            return -1;
        }
        int max = node.key;
        if(temp.rightChild != null)
        {
            while(temp.rightChild != null){

                if(max < temp.key && !temp.deleted){  max = temp.key;}
                temp = temp.rightChild;
                //System.out.println("current max " + max + " current node key " + temp.key + " node key deleted? " + temp.deleted);
            }
        }
        return temp.key;
    }

    public int findMax()
    {
        //System.out.println("******************");
        return findMax(this.root);
    }

    private int size(TreeNode node) //size method, return total number of elements in tree
    {
        return (node == null ? 0 : size(node.leftChild)  + 1 + size(node.rightChild)); //if tree is empty return 0, otherwise recursive call and iterate by 1 every call
    }

    public int size() {
        return (isEmpty() ? 0 : size(this.root)); //passes call to sibling method with root node
    }



    public boolean contains(int key) throws IllegalArgumentException
    {
        boolean toReturn; //boolean for whether or not we found value

        if (!inRange(key)) //if key is out of range
            throw new IllegalArgumentException("Error in contains: IllegalArgumentException raised"); //throw error


        toReturn = (isEmpty() ? false : contains(this.root, key)); //if tree is empty, return false, otherwise, recursive call to expanded method below


        return toReturn;
    }

    private boolean contains(TreeNode thisNode, int key) {
        boolean found = false; //whether ot not we found key in tree

        int signum = Long.signum((long) key - thisNode.key); //setup for case, given key vs root key

        switch (signum) {

            case -1://if key is less than current node
            {
                found = (thisNode.leftChild != null ? contains(thisNode.leftChild, key) : false); // recursive call to the left of the tree
                break;
            }
            case 1: { //if key is greater than current node
                found = (thisNode.rightChild != null ? contains(thisNode.rightChild, key) : false); //recursive call to the right of tree
                break;
            }
            case 0: {
                found = (thisNode.deleted ? false : true); //is node deleted, if no, found it, otherwise, didn't find it
                break;
            }
        }
        return found; //did we find key in tree?
    }

    public String toString()
    {
        StringBuffer pre_order = new StringBuffer(); //string won't work because it's immutable, stringbuffer isn't immutable
        pre_order = StringAssist(this.root, pre_order); //method call to stringassist method giving root node and string buffer
        return pre_order.toString();
    }





    public StringBuffer StringAssist(TreeNode node, StringBuffer pre_order) {
        if (node != null) { //if tree (root node) isn't empty

            String toAdd = ""; //initialize string
            toAdd = toAdd + (node.deleted ? "*" + node.key + " " : node.key + " "); //add element to string and add indicator if deleted

            pre_order.append(toAdd); //append string to string buffer


            StringAssist(node.leftChild, pre_order); //left recursive call


            StringAssist(node.rightChild, pre_order); //right recursive call
        }
        return pre_order;
    }

   


    private class TreeNode {

        public int height; //this is the depth of the tree
        public TreeNode rightChild; //this is the right node
        public TreeNode leftChild; //this is the left node
        public boolean deleted; //this is the lazy delete variable
        public int key; //this is where the integer is stored
        @Override
        public String toString()
        {
            return String.valueOf(this.key);
        }
        public TreeNode(int key) //this is the initializing constructor
        {
            this.key = key; //given key is passed
            this.height = 1; //initial depth
            this.rightChild = null; //right child is non-existant
            this.deleted = false; //it's not deleted
            this.leftChild = null; //same as right child
        }


    }
}

