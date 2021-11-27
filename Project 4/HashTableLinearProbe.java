import static java.lang.System.out;

public class HashTableLinearProbe<K, V> { //I have chosen to test methods in the main method

    int numElements; // variable to keep track of current size
    HashEntry<K, V> hashtable[]; //hash table is an array of entries; declaration


    public HashTableLinearProbe() { //default constructor

        hashtable = new HashEntry[3]; //creates a new array with size null elements


        numElements = 0; //table starts out empty so initialize this variable to 0

    }

    public HashTableLinearProbe(int a) { //constructor given maximum size

        hashtable = new HashEntry[a]; //creates a new array with size null elements


        numElements = 0; //table starts out empty so initialize this variable to 0

    }


    public boolean insert(K key, V value) { //This function inserts HashEntry, rehashes if the table is full, throw error message
        // if the key is invalid or null and return true upon successful insertion or false if duplicate HashEntry

        try {
            if (key == null) {
                out.println("null key provided");
                return false;
            }

            if (find(key) != null && !hashtable[key.hashCode() % hashtable.length].deleted) { //this checks to make sure the passed key is not already in the table or marked deleted deleted
                out.println("there was a duplicate for passed key and val (new/attempted): " + key + ", " + value + "... clashes with (pre-existing): " + key + ", " + find(key));
                return false; //false is duplicate HashEntry
            }

            int i = getHashValue(key); //set rehashTrigger variable to where the key should be placed

            int rehashTrigger = i; //set i to this also; i will be used for linear collision resolution in case table[i] is already filled

            int counter = 0;

            for (; ; ) { //the only reason this should ever return false is if there is a duplicate, theoretically, if load factor is less
                //than .5, there should always be place for the key, added rehashTrigger variable and loop check just in case I'm wrong

                if (i == rehashTrigger && counter != 0) { //counter to ensure that table doesn't rehash every time insert is called, only
                    // if there's a space that can't be found for the passed key
                    out.println("rehash occurred due to i= " + i + " = " + rehashTrigger + "= temp");
                    rehash();
                }


                if (hashtable[i] == null) { //if the slot is available in the table

                    hashtable[i] = new HashEntry(key, value); //place the given key/value pair in the slot

                    numElements++; //and increase the current size of the amount of elements in the table

                    if (numElements >= hashtable.length / 2) {//if the hashtable is more than 50 percent full (load factor >= .5)

                        out.print("load factor ");
                        rehash(); //expand table size
                    }

                    out.println("insertion success, inserted key: " + key + ", val: " + value + " at index: " + i);
                    return true; //successful insertion, return true

                }

                if (hashtable[i] != null && hashtable[i].deleted) {
                    hashtable[i].deleted = false;
                    hashtable[i].key = key;
                    hashtable[i].value = value;
                    out.println("key was filled in table but was marked as deleted, updated table, insertion successful");
                    return true;
                }

                out.println("collision resolution strategy used, i was: " + key.hashCode() + " % " + hashtable.length + " = " + i);
                int iwas = i;
                i = (i + 1); //linear probing strategy, assuming table[i] is already filled, do this to avoid collision
                i %= hashtable.length;
                out.println("collision resolution strategy used, i is now: " + (iwas + 1) + " % " + hashtable.length + " = " + i);
                counter++;

            }
        } catch (Exception e) {
            throw new IllegalArgumentException("An error occurred with insert, probably something wrong with key...");
        }
    }

    public V find(K key) throws IllegalArgumentException { //This function check if the key exists in the table. If yes, true value of the key, or null if not found
        //assuming function will still return true even if item is marked as deleted

        try {

            for (int i = 0; i < hashtable.length; i++) { //iterate through entire hashtable

                if (hashtable[i] != null && hashtable[i].key == key) { //if table[i] matches value of key and is not null
                    out.println("key " + key + " found, corresponding value is: " + hashtable[i].value);
                    return hashtable[i].value;
                } //return it's value

            }

        } catch (Exception e) {
            out.println("you gave an invalid parameter for the find(" + key + ") command");
        }
        out.println("no value found for key " + key + " in table...");
        return null; //otherwise return null if not found
    }


    public boolean delete(K key) throws IllegalArgumentException {
        //Performs lazy deleting by marking the HashEntry as deleted. Return true if deleted , false if it not found in the hashtable
        try {
            for (HashEntry elem : hashtable) { //iterate through entire table

                if (elem != null && elem.key == key && !elem.deleted) { //if they key is found

                    out.println("key detected, passed key matches iterator key, and iterator key is not marked as deleted !!deletion successful!!");

                    elem.deleted = true; //set its deleted flag to true

                    return true; //return true because they key was deleted

                }
            }
            out.println("key [" + key + "] is either already deleted or is not in table yet");
            return false; //otherwise false because it was never found or was already deleted
        } catch (Exception e) {
            out.println("you gave an invalid parameter for the delete(" + key + ") command");
            return false;

        }
    }


    private int getHashValue(K key) { //Returns the hash value for the given key or return -1 if not found

        try {
            int toReturn = key.hashCode();
            toReturn %= hashtable.length;//because key could be any variable, it would be best to use Java's hashcode function mod hashtable.length
            if (toReturn < 0)//from lecture slides
                toReturn += hashtable.length;
            return toReturn;
        } catch (Exception e) { //return -1 if not found
            return -1;
        }

    }


    private void rehash() { //the rehash function for insert


        out.println("***rehash occured; table size: " + hashtable.length + " current elements: " + numElements + "***");
        out.println("BEOFRE**********************");
        printTable();
        HashEntry<K, V> expandedTable[] = new HashEntry[nextPrime(2 * hashtable.length)]; //create a new array of elements, doubling the capacity


        for (HashEntry oldElement : hashtable) { //for each element of the array
            if (oldElement != null && !oldElement.deleted)//if the old element is not null and it not deleted, copy it to new table
                expandedTable[(oldElement.key.hashCode()) % (2 * hashtable.length)] = oldElement; //theoretically, there should never be a collision here
        }


        hashtable = expandedTable; //assign the table used in this class/object instance to the new array of entries we just created
        out.println("AFTER************************");
        printTable();

    }


    public void printTable() { //for demonstration purposes, made method to print table

        if (numElements == 0) {
            out.println("table is empty...");
            return;
        } else {
            out.println("[BEGIN PRINTING]*********************");

            for (int i = 0; i < hashtable.length; i++) { //iterate through entire table

                out.print("iterator index: " + i + " "); //add the index positions in order to the toReturn string

                if (hashtable[i] == null) {
                    out.print("null");
                } else { //if the element at i is not null

                    out.print("key: " + hashtable[i].key + " value: " + hashtable[i].value + " "); //add the key and its element to the toReturn string

                    if (hashtable[i].deleted) //if the element is marked as deleted; must be in this if statement to avoid
                        //nullpointer exception

                        out.print("*"); //add the deleted label so user knows element is marked as deleted

                }

                out.println(); //make a new line per HashEntry printed

            }
            out.println("[FINISHED PRINTING]******************");
            out.println();
        }
    }

    private boolean checkPrime(int a){
        for(int i=2; i<a; i++){
            if(a%i==0) return false;
        }
        return true;
    }

    private int nextPrime(int a){
        while (!checkPrime(a)){
            a++;
        }
        return a;
    }

    private static class HashEntry<K, V> { //each HashEntry has a generic key and value type
        public HashEntry(K key, V value) { //constructor when passed a key and a value

            this.value = value; //the raw value itself
            this.key = key; //key for where value should be placed


            deleted = false; //deletion flag for lazy deletion; default is false

        }

        boolean deleted;

        V value;

        K key;


    }

}
