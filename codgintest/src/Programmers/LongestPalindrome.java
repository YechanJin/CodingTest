package Programmers;

public class LongestPalindrome {
    public int solution(String s)
    {
        int answer = 1;
        int size = s.length();
        boolean[][] dp = new boolean[size][size];

        for(int i = 0 ; i < size ; i++){
            dp[i][i] = true;
        }

        for(int i = 1 ; i < size ; i++){
            for(int j = 0 ; j + i < size; j++){
                char start = s.charAt(j);
                char end = s.charAt(i+j);
                if(start != end) continue;
                if(i > 1 && !dp[j+1][i+j-1]) continue;

                dp[j][j+i] = true;
                answer = i+1;
            }
        }

        return answer;
    }
}
