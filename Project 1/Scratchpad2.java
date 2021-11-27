//Rameen Housseini
//RXH170130
//CS3345 Section 005
//Sieve of Erotosthenes to Calculate All Prime Numbers Up to Given Number
//Program is pretty straight forward, no comment needed

import java.util.Arrays;
import java.util.Scanner;
import static java.lang.System.*;
import static java.lang.System.out;


public class Scratchpad2 {

    public static void main(String[] args){
        Scanner input = new Scanner(in);
        out.println("Enter the upper bound of the prime numbers to be calculated. Input must be greater than 1");

        int n = input.nextInt();

        boolean[] A = new boolean[n];
        Arrays.fill(A, true);

        for(int i = 2; i < Math.sqrt(n); i++){
            if(A[i] == true){
                int index = 0;
                int j = i*i;
                while(j < n){
                    A[j] = false;
                    index++;
                    j = (i*i)+(index*i);
                }
            }
        }

        int breakCounter = 0;
        for(int i=2; i<A.length; i++){

            if(A[i]==true){
                out.print(i + " ");
                breakCounter++;
            }

            if(breakCounter%4==0 && breakCounter != 0){
                out.println("");
                breakCounter=0;
            }
        }
    }
}