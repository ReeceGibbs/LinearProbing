//our Main class
public class Main {

    //our main method
    //the processes performed in this class serve as a demonstration of our removal of entries from a hash table using linear probing
    public static void main(String[] args) {

        //for the sake of this demonstration we will be using Strings as keys and values, but all data types are supported
        //we begin by creating an instance of a LinearHashTable with a size of 10
        LinearHashTable<String, String> linearHashTable = new LinearHashTable<>(10);

        //now, in order to demonstrate the removal of elements, we have to add some elements first
        linearHashTable.add("FirstElement", "FirstValue");
        linearHashTable.add("SecondElement", "SecondValue");
        linearHashTable.add("ThirdElement", "ThirdValue");
        linearHashTable.add("FourthElement", "FourthValue");
        linearHashTable.add("FifthElement", "FifthValue");

        //we allow duplicate keys
        linearHashTable.add("FirstElement", "FirstValue");

        //we now print our hashtable so that we have line of sight of our starting point
        //we use our toString method in LinearHashTable
        System.out.println(linearHashTable.toString() + "\r\n");

        //we have two ways to remove elements
        //the first is passing a specific integer index
        linearHashTable.removeAt(1);

        //here's where we are
        System.out.println(linearHashTable.toString() + "\r\n");

        //the second way to remove elements is by passing a key. We use linear probing to remove the first occurrence of that key from our hashtable
        linearHashTable.remove("FirstElement");
        linearHashTable.remove("FourthElement");

        //here is the result
        System.out.println(linearHashTable.toString() + "\r\n");
    }
}
