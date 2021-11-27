import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.io.FileWriter;
import java.util.Collections;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        try {
            ArrayList<Integer> listFirstElem = QuickSorter.generateRandomList(Integer.parseInt(args[0]));
            ArrayList<Integer> listRandElem = new ArrayList<>(listFirstElem);
            ArrayList<Integer> listMedThreeRand = new ArrayList<>(listFirstElem);
            ArrayList<Integer> listMedThree = new ArrayList<>(listFirstElem);
            // ArrayList<Integer> readmeTest = new ArrayList<>(listFirstElem);
            //Collections.sort(readmeTest);
            // for(int i=0; i<=readmeTest.size()*.1; i++){ //randomizes 10 percent of array
            //  Random r = new Random();
            // int pos1 = r.nextInt(readmeTest.size());
            //  int pos2 = r.nextInt(readmeTest.size());

            //  int temp = readmeTest.get(pos1);
            // readmeTest.set(pos1, readmeTest.get(pos2));
            //  readmeTest.set(pos2, temp);
            //  }

            // ArrayList<Integer> readmeTest2 = new ArrayList<>(readmeTest);
            // ArrayList<Integer> readmeTest3 = new ArrayList<>(readmeTest);
            // ArrayList<Integer> readmeTest4 = new ArrayList<>(readmeTest);

            File unsortedOut = new File(args[2]);
            if (unsortedOut.createNewFile())
                System.out.println("Created unsortedOut");
            File sortedOut = new File(args[3]);
            if (sortedOut.createNewFile())
                System.out.println("Created sortedOut");
            File report = new File(args[1]);
            if (report.createNewFile())
                System.out.println("created report file");

            FileWriter unsortedWriter = new FileWriter(args[2]);
            for (int elem : listFirstElem) {
                unsortedWriter.write(elem + System.lineSeparator());
            }
            unsortedWriter.close();

            Duration a = QuickSorter.timedQuickSort(listFirstElem, QuickSorter.PivotStrategy.FIRST_ELEMENT);
            Duration b = QuickSorter.timedQuickSort(listRandElem, QuickSorter.PivotStrategy.RANDOM_ELEMENT);
            Duration c = QuickSorter.timedQuickSort(listMedThreeRand, QuickSorter.PivotStrategy.MEDIAN_OF_THREE_RANDOM_ELEMENTS);
            Duration d = QuickSorter.timedQuickSort(listMedThree, QuickSorter.PivotStrategy.MEDIAN_OF_THREE_ELEMENTS);


            FileWriter reportWriter = new FileWriter(args[1]);
            reportWriter.write("Array Size = " + args[0] + "\nFIRST_ELEMENT : " + a + "\nRANDOM_ELEMENT : " + b + "\nMEDIAN_OF_THREE_RANDOM_ELEMENTS : " + c +
                    "\nMEDIAN_OF_THREE_ELEMENTS : " + d);

            //   reportWriter.write("\nArray Size = " + args[0] + "\nFIRST_ELEMENT : " + QuickSorter.timedQuickSort(readmeTest, QuickSorter.PivotStrategy.FIRST_ELEMENT) + "\nRANDOM_ELEMENT : " + QuickSorter.timedQuickSort(readmeTest2, QuickSorter.PivotStrategy.RANDOM_ELEMENT) + "\nMEDIAN_OF_THREE_RANDOM_ELEMENTS : " +  QuickSorter.timedQuickSort(readmeTest3, QuickSorter.PivotStrategy.MEDIAN_OF_THREE_RANDOM_ELEMENTS) +
            //         "\nMEDIAN_OF_THREE_ELEMENTS : " + QuickSorter.timedQuickSort(readmeTest4, QuickSorter.PivotStrategy.MEDIAN_OF_THREE_ELEMENTS));
            reportWriter.close();

            FileWriter sortedWriter = new FileWriter(args[3]);
            for (int elem2 : listRandElem) {
                sortedWriter.write(elem2 + System.lineSeparator());
            }
            sortedWriter.close();


        } catch (IOException e) {
            System.out.println("Error with main");
            e.printStackTrace();
        }


    }
}
