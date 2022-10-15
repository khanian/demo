package com.example.demo.homework;

import java.util.Arrays;
import java.util.Scanner;

public class EditDistance {

    static String A;
    static String B;

    public static void main(String[] args) {
        input();
        System.out.println(solution());
    }

    static void input() {
        Scanner sc = new Scanner(System.in);
        A = sc.nextLine();
        B = sc.nextLine();
    }

    static int solution() {
        int n = A.length();
        int m = B.length();

        int ins = 3; //삽입비용
        int del = 2; //삭제비용
        int chg = 1; //변경비용

        // 다이나믹 프로그래밍을 위한 2차원 DP 테이블 초기화
        int[][] dp = new int[n + 1][m + 1];

    /*
        DP 테이블 초기 설정
        빈 문자열을 가지고 해당 문자를 만들 수 있는 최소 편집 거리로 초기화
        Ex. dp[2][0] 에 저장된 값은
            빈 문자열을 가지고 su 를 만들 수 있는 최소 편집거리를 의미
     */
        for (int i = 1; i <= n; i++) { // 첫 열의 초기화
            //dp[i][0] = i;
            dp[i][0] = dp[i-1][0] + del;
        }
        for (int j = 1; j <= m; j++) { // 첫 행의 초기화
            dp[0][j] = dp[0][j-1] + ins;
        }

        // 최소 편집 거리 계산
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // del=ins=chg=1 일 때
                /*// 문자가 같다면, 왼쪽 위에 해당하는 수를 그대로 대입
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }

                // 문자가 다르다면, 세 가지 경우 중에서 최솟값 찾기
                else { // 삽입(왼쪽), 삭제(위쪽), 교체(왼쪽 위) 중에서 최소 비용을 찾아 대입
                    dp[i][j] = 1 + Math.min(dp[i][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j - 1]));
                }*/

                // 같은 문자는 비용이 0 다른 문자는 변경 비용
                int tempChg = (A.charAt(i-1) == B.charAt(j-1))? 0 : chg;
                dp[i][j] = Math.min(dp[i-1][j]+del, Math.min(dp[i][j-1]+ins, dp[i-1][j-1]+tempChg));
            }
        }

        // 다차원 배열 프린트 deepToString
        System.out.println(Arrays.deepToString(dp));
        return dp[n][m];
    }

}
