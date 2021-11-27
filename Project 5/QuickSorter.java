import java.time.Duration;
import java.util.ArrayList;
import java.util.Random;

public class QuickSorter {

    enum PivotStrategy {
        FIRST_ELEMENT,
        RANDOM_ELEMENT,
        MEDIAN_OF_THREE_ELEMENTS,
        MEDIAN_OF_THREE_RANDOM_ELEMENTS
    }

    public static ArrayList<Integer> generateRandomList(int size) throws IllegalArgumentException {
        try {
            if (size < 0)
                throw new IllegalArgumentException();
            ArrayList<Integer> toPass = new ArrayList<Integer>(size);
            Random toFill = new Random();


            for (int i = 0; i < size; i++) {
                toPass.add(toFill.nextInt());
            }
            return toPass;

        } catch (IllegalArgumentException i) {
            System.out.print("You gave a negative size, please give a positive size");
            throw i;
        }
    }

    public static <E extends Comparable> Duration timedQuickSort(ArrayList<E> list, PivotStrategy pivotStrategy) throws NullPointerException {
        try {
            long start = System.nanoTime();
            if (pivotStrategy == null || list == null)
                throw new NullPointerException();

            if (list.size() < 20) { //Remember the quicksort will use the recursive strategy for small array (<20 elements) use insertion sort methodology.
                int j; //credits: powerpoint

                for (int p = 1; p < list.size(); p++) {
                    E tmp = list.get(p);
                    for (j = p; j > 0 && tmp.compareTo(list.get(j - 1)) < 0; j--)
                        list.set(j, list.get(j - 1));
                    list.set(j, tmp);
                }
            }
            quickSort(list, 0, list.size() - 1, pivotStrategy);


            long end = System.nanoTime();


            return Duration.ofNanos(end - start);
        } catch (NullPointerException n) {
            System.out.println("You passed a null argument for timedQuickSort, please pass a valid argument");
            throw n;
        }

    }


    private static <E> void swapReferences(ArrayList<E> a, int left, int right) {
        E temp = a.get(left);
        a.set(left, a.get(right));
        a.set(right, temp);
    }


    private static <E extends Comparable<? super E>> E getPivot(ArrayList<E> a, int left, int right, PivotStrategy pivotStrategy) {
        E pivot = null;
        switch (pivotStrategy) {
            case FIRST_ELEMENT:
            case RANDOM_ELEMENT:
                E tempPivot = a.get(right);
                int i = left - 1;
                for (int j = left; j < right; j++) {
                    if (a.get(j).compareTo(tempPivot) <= 0) {
                        i++;
                        swapReferences(a, i, j);
                    }
                }
                swapReferences(a, i + 1, right);
                pivot = a.get(i + 1);
                break;
            case MEDIAN_OF_THREE_ELEMENTS:
                int center = (left + right) / 2;
                if (a.get(center).compareTo(a.get(left)) < 0)
                    swapReferences(a, left, center);
                if (a.get(right).compareTo(a.get(left)) < 0)
                    swapReferences(a, left, right);
                if (a.get(right).compareTo(a.get(center)) < 0)
                    swapReferences(a, center, right);

                //Place pivot at position right - 1
                swapReferences(a, center, right - 1);
                pivot = a.get(right - 1);
                break;
            case MEDIAN_OF_THREE_RANDOM_ELEMENTS:
                Random rand2 = new Random();
                ArrayList<Integer> lrc = new ArrayList<Integer>();

                lrc.add(rand2.nextInt(right - left));
                lrc.add(rand2.nextInt(right - left));
                lrc.add(rand2.nextInt(right - left));

                insertionSort(lrc, 0, lrc.size() - 1);
                //System.out.println(lrc.get(0) + " " + lrc.get(1) + " " + lrc.get(2));
                int l = lrc.get(0);
                int c = lrc.get(1);
                int r = lrc.get(2);

                if (a.get(c).compareTo(a.get(l)) < 0)
                    swapReferences(a, l, c);
                if (a.get(r).compareTo(a.get(l)) < 0)
                    swapReferences(a, l, r);
                if (a.get(r).compareTo(a.get(c)) < 0)
                    swapReferences(a, c, r);

                //Place pivot at position right - 1
                swapReferences(a, c, r - 1);
                pivot = a.get(r - 1);
                break;

        }
        return pivot;
    }


    private static <E extends Comparable<E>> void quickSort(ArrayList<E> a, int left, int right, PivotStrategy pivotStrategy) {
        if (left + 20 <= right) { //cutoff is 20
            if (pivotStrategy == PivotStrategy.FIRST_ELEMENT) {
                if (left > right - 1) {
                    return;
                }
                swapReferences(a, left, right);
                E pivot = getPivot(a, left, right, pivotStrategy);
                int pivotIndex = a.indexOf(pivot);
                quickSort(a, left, pivotIndex - 1, PivotStrategy.FIRST_ELEMENT);
                quickSort(a, pivotIndex + 1, right, PivotStrategy.FIRST_ELEMENT);
                return;
            }
            if (pivotStrategy == PivotStrategy.RANDOM_ELEMENT) {
                if (left > right - 1) {
                    return;
                }
                Random r = new Random();
                int randIndex = r.nextInt(right - left) + left;
                swapReferences(a, randIndex, right);
                E pivot = getPivot(a, left, right, PivotStrategy.RANDOM_ELEMENT);
                int pivotIndex = a.indexOf(pivot);
                quickSort(a, left, pivotIndex - 1, PivotStrategy.RANDOM_ELEMENT);
                quickSort(a, pivotIndex + 1, right, PivotStrategy.RANDOM_ELEMENT);
                return;
            }


            E pivot = getPivot(a, left, right, pivotStrategy);  //credits: powerpoint

            //begin partitioning
            int i = left, j = right - 1;
            for (; ; ) {
                while (i != a.size() && a.get(++i).compareTo(pivot) < 0) {
                }
                while (a.get(--j).compareTo(pivot) > 0) {
                }
                if (i < j)
                    swapReferences(a, i, j);
                else
                    break;
            }
            swapReferences(a, i, right - 1);

            quickSort(a, left, i - 1, pivotStrategy);
            quickSort(a, i + 1, right, pivotStrategy);
        } else
            insertionSort(a, left, right);
    }


    private static <E extends Comparable<? super E>> void insertionSort(ArrayList<E> a, int left, int right) {

        int j; //credits: powerpoint

        for (int p = left + 1; p <= right; p++) {
            E tmp = a.get(p);
            for (j = p; j > 0 && tmp.compareTo(a.get(j - 1)) < 0; j--)
                a.set(j, a.get(j - 1));
            a.set(j, tmp);
        }
    }
}

