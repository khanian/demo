package com.example.demo.homework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChangeCoin {

    static int[] findCoinNumber(int coins[], int n, int T) {
        int result[] = new int [T+1];
        for (int i=1; i <= T; i++){
            result[i] = 99999999;
            for (int j=0; j <= n-1; j++) {
                if (i >= coins[j]) {
                    if (result[i] > result[i-coins[j]] + 1 ) {
                        result[i] = result[i-coins[j]] + 1;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int T = 10;
        int n = 3;
        int coins[] = {4,3,1};
        int result[] = findCoinNumber(coins, n, T);
        System.out.println(Arrays.toString(result));

    }
}
