package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RemovingGCD_14476 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int [] nums = new int[n];
        StringTokenizer stk = new StringTokenizer(br.readLine(), " ");

        for(int i = 0 ; stk.hasMoreTokens(); i++){
            nums[i] = Integer.parseInt(stk.nextToken());
        }

        int [] leftGcd = new int[n];
        int [] rightGcd = new int[n];
        leftGcd[0] = nums[0];
        rightGcd[n-1] = nums[n-1];
        for(int i = 1, j = n-2 ; i < n; i++, j--){
            leftGcd[i] = getGCD(leftGcd[i-1], nums[i]);
            rightGcd[j] = getGCD(rightGcd[j+1], nums[j]);
        }

        int answer = -1;
        int K = -1;

        for(int i = 0 ; i < n ; i++){
            int newGCD;
            if(i == 0)
                newGCD = rightGcd[i+1];
            else if(i == n-1)
                newGCD = leftGcd[i-1];
            else
                newGCD = getGCD(leftGcd[i-1],  rightGcd[i+1]);

            if(nums[i] % newGCD != 0 && answer < newGCD){
                answer = newGCD;
                K = nums[i];
            }
        }

        String result = Integer.toString(answer);
        if(answer > 0)
            result = result + " " + K;
        System.out.println(result);
        return;
   }
   public static int getGCD(int x, int y){
        int a = Math.max(x,y), b = Math.min(x,y);
        while(b > 0){
            int remainder = a % b;
            a = b;
            b = remainder;
        }
        return a;
   }

}
