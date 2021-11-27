Rameen Housseini
RXH170130
CS 3345.005 F20

intellij idea community 2020.2.2
Project SDK: Java version 13.0.2


Run >> Edit Configurations >> Application >> main >> Program Arguments: 70 report.txt unsorted.txt sorted.txt

working directory: C:\...\Project5
instead of ... fill in the directory you put Project3 in

https://stackoverflow.com/questions/32951846/run-program-from-intellij-with-command-line-file-input

automatically creates files

all .java files should be placed in \Project5\src\

This is assuming you'll be naming the intellij project: "Project5" as I did.

If you want to see the result of another pivot selection type, change the list in the
enhanced for loop in main
____________________________________________________________
 for(int elem2 : listRandElem){
                sortedWriter.write(elem2 + System.lineSeparator());
            }
_____________________________________________________________
to the corresponding list
listFirstElem = pivot is first element
listRandElem = pivot it random element
listMedThreeRand = pivot is the median of three random indicies
listMedThree = pivot is median of left, middle and last elements

Nanosecond performance for already sorted array...
Array Size = 70
FIRST_ELEMENT : PT0.0001219S
RANDOM_ELEMENT : PT0.0000352S
MEDIAN_OF_THREE_RANDOM_ELEMENTS : PT0.0000453S
MEDIAN_OF_THREE_ELEMENTS : PT0.0000107S

Array Size = 700
FIRST_ELEMENT : PT0.0061878S
RANDOM_ELEMENT : PT0.000631S
MEDIAN_OF_THREE_RANDOM_ELEMENTS : PT0.0009769S
MEDIAN_OF_THREE_ELEMENTS : PT0.0000915S

Array Size = 7000
FIRST_ELEMENT : PT0.1648884S
RANDOM_ELEMENT : PT0.0016256S
MEDIAN_OF_THREE_RANDOM_ELEMENTS : PT0.0092651S
MEDIAN_OF_THREE_ELEMENTS : PT0.0001029S

Nanosecond performance for 90% sorted array...
Array Size = 70
FIRST_ELEMENT : PT0.0000496S
RANDOM_ELEMENT : PT0.0000412S
MEDIAN_OF_THREE_RANDOM_ELEMENTS : PT0.0000451S
MEDIAN_OF_THREE_ELEMENTS : PT0.000016S

Array Size = 700
FIRST_ELEMENT : PT0.0004713S
RANDOM_ELEMENT : PT0.000289S
MEDIAN_OF_THREE_RANDOM_ELEMENTS : PT0.0011249S
MEDIAN_OF_THREE_ELEMENTS : PT0.0000984S

Array Size = 7000
FIRST_ELEMENT : PT0.0063642S
RANDOM_ELEMENT : PT0.0019558S
MEDIAN_OF_THREE_RANDOM_ELEMENTS : PT0.0095679S
MEDIAN_OF_THREE_ELEMENTS : PT0.0004298S



