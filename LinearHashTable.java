//importing our java packages
import java.util.ArrayList;
import java.util.List;

//our LinearHashTable class
public class LinearHashTable<Key, Value> {

    //we declare an array of HashElements
    private HashElement<Key, Value>[] hashTable;

    //we also declare a private int that will contain the size of the HashElement[] we define so we don't have to check its length property all the time
    private int hashTableSize;

    //our LinearHashTable constructor
    public LinearHashTable(int hashTableSize) {

        //we assign our hashTableSize the value of the argument passed to this constructor
        this.hashTableSize = hashTableSize;

        //we assign our array of HashElements of the size passed to this constructor
        hashTable = new HashElement[hashTableSize];
    }

    //a public method that can be called to insert an element into the LinearHashTable
    public void add(Key key, Value value) {

        //we output some info here so that we have better line of sight of what's going on
        System.out.println("Inserting element with key: " + key);

        //firstly, we create a new HashElement with the key and value arguments passed to this method
        HashElement<Key, Value> insertElement = new HashElement<>(key, value);

        //now we hash the key and mod it by the size of the hashtable so we know where to place it
        int index = HashElement.getHashCode(key) % hashTableSize;

        //we output the index result from our hashing algorithm
        System.out.println("Index result from our hashing algorithm: " + index);

        //we begin a for loop that will loop for the size of the table or until the element has found a home
        for (int x = 0; x < hashTableSize; x++) {

            //we check to see if our HashElement array at the index is null
            if (hashTable[index] == null) {

                //if the cell is empty, then we insert the element here
                hashTable[index] = insertElement;

                //we break out of the loop
                break;
            }
            else {

                //if the cell is occupied, then we either increment the index by one and look again, or if we have exhausted the table, then we set the index back to 0 and carry on looking
                index = (index < hashTableSize - 1) ? index + 1 : 0;
            }
        }

        //we output where the element finally ended up
        System.out.println("Final index of element after linear probing: " + index + "\r\n");
    }

    //a public method that can be called to remove an element at a given index
    public void removeAt(int index) {

        //we set the value at the given index to null
        hashTable[index] = null;
    }

    //a  public method that can be called to remove the first occurrence of an element from a LinearHashTable given the key
    public void remove(Key key) {

        //we output some info here so that we have better line of sight of what's going on
        System.out.println("Removing element with key: " + key);

        //we get the hash code of the key and then mod it by the hashTableSize to know where to look for the element
        int index = HashElement.getHashCode(key) % hashTableSize;

        //we output the index result from our hashing algorithm
        System.out.println("Index result from our hashing algorithm: " + index);

        //we begin a for loop that will loop for the size of the table or until the element with the key value passed here has been found
        for (int x = 0; x < hashTableSize; x++) {

            //we check to see if the current cell contains our key and is not null
            if (hashTable[index] != null && hashTable[index].key.equals(key)) {

                //if the key is found, then we set the contents of the cell to null
                hashTable[index] = null;

                //we break out of the loop
                break;
            }
            else {

                //if the cell does not contain our key, then we either increment the index by one and look again, or if we have exhausted the table, then we set the index back to 0 and carry on looking
                index = (index < hashTableSize - 1) ? index + 1 : 0;
            }
        }

        //we output where the element finally ended up deleting the element from
        System.out.println("Final index of element after linear probing: " + index + "\r\n");
    }

    //an override of the toString() method
    public String toString() {

        //we initialize a list that we will convert to a string when we are done
        List<String> hashTableElementsAsStrings = new ArrayList<>();

        //we iterate through our hashTable
        for (HashElement<Key, Value> element :
             hashTable) {

            //we check to see if the element is null
            if (element == null) {

                //if it is null, we add "null" to our string list
                hashTableElementsAsStrings.add("null : null");

                //we continue iterating
                continue;
            }

            //else we add the key and value of the element separated by a colon to our string element list
            hashTableElementsAsStrings.add(element.key + " : " + element.value);
        }

        //we return the elements of tour HashElement array joined by a comma
        return String.join(",\r\n", hashTableElementsAsStrings);
    }
}
