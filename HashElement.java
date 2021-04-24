//our HashElement class
public class HashElement<Key, Value> {

    //our generic key property
    Key key;

    //our generic value property
    Value value;

    //our HashElement constructor
    public HashElement(Key key, Value value) {

        //we set our local properties to the values passed to this constructor
        this.key = key;

        this.value = value;
    }

    //our hashing method
    //we use Math.abs on some of the returns as the integer code returned could have overflowed and therefore be negative
    public static int getHashCode(Object key) {

        //we want to hash our keys differently based on the key's datatype. we use a switch statement
        switch (key.getClass().getSimpleName()) {

            //for Byte, Short, Integer and Character keys we just want to return the key cast to an int
            case "Integer":

                //we return the key cast to an int
                return (int)key;

            case "Character":

                //we return the key cast to an int
                return (int)((char)key);

            case "Byte":

                //we return the key cast to an int
                return (int)(byte)key;

            case "Short":

                //we return the key cast to an int
                return (int)(short)key;

                //we check to see if the key is a Float
            case "Float":

                //if the key is a Float, then we return the int value of our float using the floatToIntBits method
                return Math.abs(Float.floatToIntBits((float)key));

            //we check to see if the key is a Long
            case "Long":

                //if the key is a Long, then we want to return the folded long value cast to an int
                return Math.abs((int)((long)key ^ ((long)key >> 32)));

            //we check to see if the key is a Double
            case "Double":

                //if the key is a Double, then we need to get the double as a long and then return the folded result cast to int
                long bits = Double.doubleToLongBits((double)key);

                //we return the folded result cast to int
                return Math.abs((int)(bits ^ (bits >> 32)));

            //we check to see if the key is a String
            case "String":

                //if the key is a String, then we want to call our rollingPolyHash algorithm
                return Math.abs(rollingPolyHash(key.toString().toCharArray(), key.toString().length()));

            //we check to see if the key is of any other type
            default:

                //we want to call the hashCode() method on it and alter it slightly by multiplying the result by a prime number, 31
                return Math.abs(31 * key.hashCode());
        }
    }

    //our private recursive method that hashes string keys
    private static int rollingPolyHash(char[] key, int keyLength) {

        //our base case
        if (keyLength == 2) {

            //we return key[0] * 31 + key[1] for our base case
            return key[0] * 31 + key[1];
        }

        //we call our rollingPolyHash function again, just decrementing the keyLength by 1
        return key[keyLength - 1] + 31 * (rollingPolyHash(key, keyLength - 1));
    }
}
