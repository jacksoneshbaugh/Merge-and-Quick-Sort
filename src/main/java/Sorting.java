import java.util.Arrays;
import java.util.Random;

import org.jetbrains.annotations.NotNull;

/**
 * Implementations of sorting algorithms for understanding
 * of material.
 * CS 202: Analysis of Algorithms
 * Lafayette College
 *
 * @author Jackson Eshbaugh
 * @version 09/10/2024
 */
public class Sorting {

    static Random r = new Random();

    public static void main(String[] args) {

        int n = 10000;

        // generate array of n integers
        int[] A = new int[n];
        int[] B = new int[n];

        // int[] C = new int[n];

        for (int i = 0; i < n; i++) A[i] = B[i] = r.nextInt(n);

        System.out.println("Array: " + Arrays.toString(A));
        long t1 = System.currentTimeMillis();
        quickSort(A, 0, n);
        System.out.println("Quick Sort => " + (System.currentTimeMillis() - t1) + "ms");
        System.out.println("Array: " + Arrays.toString(A));

        System.out.println("Array: " + Arrays.toString(B));
        long t2 = System.currentTimeMillis();
        mergeSort(B, 0, n);
        System.out.println("Merge Sort => " + (System.currentTimeMillis() - t2) + "ms");
        System.out.println("Array: " + Arrays.toString(B));
    }

    /**
     * An implementation of the merge sort (in place) algorithm based on code given in lecture.
     * Sorts an array {@code A} of integers.
     * Analysis:
     * T(n) = 2T(n/2) + O(n)
     * => O(nlog n) (by the master theorem)
     *
     * @param A an array of numbers to sort
     */
    public static void mergeSort(@NotNull int[] A, int start, int end) {
        if(start >= end - 1) return;
        int mid = start + (end - start) / 2;
        mergeSort(A, start, mid);
        mergeSort(A, mid, end);
        merge(A, start, mid, end);
    }

    /**
     * Merges two sorted "arrays" in {@code A}
     * (from {@code start} to {@code half - 1} and from {@code half} to {@code end}) in place
     * Analysis:
     * O(n) because each value is accessed only once during a run.
     *
     * @param A the array to merge in
     * @param start where to begin the merge
     * @param half where the second sorted list starts
     * @param end where to end the merge
     */
    private static void merge(@NotNull int[] A, int start, int half, int end) {
        int[] B = Arrays.copyOfRange(A, start, half);

        int i = 0; // index in B
        int j = half; // index in 2nd half of A
        int k = start; // index in merged A

        while(i < B.length && j < end)
            if(B[i] <= A[j]) A[k++] = B[i++];
            else A[k++] = A[j++];

        while(i < B.length) A[k++] = B[i++];
    }


    /**
     * An implementation of the quick sort algorithm based on
     * pseudocode given in lecture.
     * Analysis:
     * T(n) = T(k) + T(n - k) + θ(n)
     *
     * @param A the array of integers to sort
     * @param start where to begin the sorting
     * @param end where to end the sorting
     */
    public static void quickSort(@NotNull int[] A, int start, int end) {

        if(start >= end - 1) return; // base case

        int pivotIndex = partition(A, start, end); // θ(n)

        quickSort(A, start, pivotIndex); // T(k)
        quickSort(A, pivotIndex + 1, end); // T(k - n)

    }

    /**
     * Partitions an array {@code A} (from {@code A[start]} to {@code A[end]}) into two partitions
     * separated by a pivot. Each entry that comes before the pivot will be less than it,
     * and each entry after will be greater than or equal to it.
     * Analysis:
     * O(n) (each number in the list is examined one time—at most 2 times).
     *
     * @param A the array of integers to partition
     * @param start where to begin partitioning
     * @param end where to end partitioning
     * @return index of the pivot selected
     */
    private static int partition(@NotNull int[] A, int start, int end) {

        int pivotIndex = (int) (Math.random() * (end - 1 - start)) + start;
        int pivotValue = A[pivotIndex];
        int i = start + 1, j = end - 1;

        // Move pivot value to keep it away from i and j
        swap(A, start, pivotIndex);

        while(true) {

            while(i < end && A[i] <= pivotValue) i++;
            while(A[j] > pivotValue) j--;

            if(i >= j) break;
            swap(A, i, j);
        }
        swap(A, start, j);
        return j; // i.e., the new pivot index
    }

    /**
     * Swap two elements' positions in an array {@code A}.
     *
     * @param A the array to swap in
     * @param i index of an element to swap
     * @param j index of an element to swap
     */
    private static void swap(@NotNull int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

}
