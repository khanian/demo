package com.example.demo.homework;

import java.util.Arrays;

public class MergeSortArray {
    private static int count = 0;
    /*public static void printArr(int[] arr) {
        for (int i=0; i <arr.length; i++){
            System.out.print(arr[i] + "");
        }
        System.out.println();
    }*/

    public static void merge (int[] arrA, int[] arrB, int[] arrC) {
        int iA = 0;
        int iB = 0;
        int iC = 0;

        while (iA < arrA.length) {
            if (iB < arrB.length) {
                if ( arrA[iA] < arrB[iB]) {
                    arrC[iC] = arrA[iA];
                    iA++;
                } else {
                    arrC[iC] = arrB[iB];
                    iB++;
                }
                iC++;
            } else {
                while (iA < arrA.length) {
                    arrC[iC] = arrA[iA];
                    iA++;
                    iC++;
                }
            }
        }

        while (iB < arrB.length) {
            arrC[iC] = arrB[iB];
            iB++;
            iC++;
        }
    }

    public static void merge_sort (int[] arr) {
        int n = arr.length;

        if (n == 1) return;

        int[] arr_temp1 = new int[n/2];
        int[] arr_temp2 = new int[n - n/2];

        for (int i = 0; i< n/2; i++) {
            arr_temp1[i] = arr[i];
        }
        for (int i = 0; i< n - n/2; i++) {
            arr_temp2[i] = arr[i + n/2];
        }
        System.out.print("Array S1: ");
        System.out.println(Arrays.toString(arr_temp1));
        //printArr(arr_temp1);

        System.out.print("Array S2: ");
        System.out.println(Arrays.toString(arr_temp2));
        //printArr(arr_temp2);

        merge_sort(arr_temp1);
        merge_sort(arr_temp2);

        merge(arr_temp1, arr_temp2, arr);
        System.out.print("Array S: ");
        System.out.println(Arrays.toString(arr));
        //printArr(arr);
    }

    private static int[] mergeNew(int[] arrB, int[] arrC, int m, int n, int size) {
        //System.out.println("m=" + m);
        //System.out.println("n=" + n);

        int i = 0;
        int j = 0;
        int k = 0;

        int[] arrA = new int[size];
        while (i < m && j < n) {
            if (arrB[i] <= arrC[j]) {
                arrA[k++] = arrB[i++];
            } else {
                arrA[k++] = arrC[j++];
            }
        }

        for(; i<m; i++) {
            //System.out.println("for k=" + k);
            //System.out.println("for i=" + i);
            arrA[k++] = arrB[i];
        }
        for(; j<n; j++) {
            //System.out.println("for2 k=" + k);
            //System.out.println("for2 j=" + j);
            arrA[k++] = arrC[j];
        }
        System.out.println("arrA merge return : " + Arrays.toString(arrA));
        return arrA;
    }

    public static int[] mergeSort(int[] arr, int n) {
        System.out.println("call count : " + count++);
        System.out.println("mergeSort n = " + n);
        if (n <= 1) return arr;

        int mid = n/2;
        //System.out.println("mid : "+ mid);
        int[] left = new int[mid];
        int[] right = new int[arr.length - mid];
        for (int i=0; i < arr.length; i++) {
            if (i < mid) {
                left[i] = arr[i];
            } else {
                right[i-mid] = arr[i];
            }
        }
        System.out.println("tempLeft : " + Arrays.toString(left));
        System.out.println("tempRight : " + Arrays.toString(right));

        int[] leftArr = mergeSort(left, mid);
        System.out.println("LeftArr after mergeSort : " + Arrays.toString(leftArr));

        int[] rightArr = mergeSort(right, n-mid);
        System.out.println("RightArr after mergeSort: " + Arrays.toString(rightArr));

        return mergeNew(leftArr, rightArr, mid, n-mid, n);
    }

    public static void main (String[] args) {
        int[] arr = {5, 8, 7, 3, 4, 1, 6, 2};

        System.out.print("정렬 전 배열: ");
        //printArr(arr);
        System.out.println(Arrays.toString(arr));
        arr = mergeSort(arr, arr.length);
        //merge_sort(arr);
        System.out.print("정렬된 배열: ");
        System.out.println(Arrays.toString(arr));
        //printArr(arr);
    }
}

