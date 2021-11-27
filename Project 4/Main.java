import static java.lang.System.out;

public class Main { //I have chosen to test methods in the main method
    public static void main(String args[]) {

        HashTableLinearProbe<String, String> test2 = new HashTableLinearProbe<>();
        HashTableLinearProbe<Integer, Integer> test = new HashTableLinearProbe<>(8);

        test.printTable();

        test.insert(1, 68);

        test.insert(67, 13);

        test.find(1);
        test.insert(3, 13);
        test.insert(11, 45);

        test.insert(3, 99);

        test.insert(9, 432);
        test.insert(4, 82);


        test.insert(null, 23);

        test.printTable();


        test.find(7);

        test.find(4);


        test.delete(9);
        test.delete(9);
        test.delete(1);

        test.insert(5, 30);


        test.printTable();


        test2.printTable();

        test2.insert("alpha", "beta");

        test2.insert("lol", "hello");

        test2.find("lol");
        test2.insert("hi", "test");

        test2.insert("gamma", "zeta");

        test2.insert("sigmoid", "sigma");

        test2.find("nope");
        test2.insert("table", "turn");

        test2.printTable();
        test2.delete("gamma");

        test2.printTable();
        test2.delete("gamma");

    }

}

