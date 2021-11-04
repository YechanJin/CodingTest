package Programmers;

import java.util.ArrayList;
import java.util.List;

public class ExpressN {

    public static void main(String [] args){
        List<Integer>[] l = new ArrayList[10];

        for(int i = 0 ; i <10; i++){
            System.out.println(l[i]);
        }
        return;
    }

    public int solution(int N, int number) {
        int answer = 9;
        List<List<Integer>> nums = new ArrayList<>();

        int Ns = N;
        for(int i = 1; i <= 8; i++){
            nums.add(new ArrayList<>());
            nums.get(i-1).add(Ns);
            if(Ns == number)
                answer = Math.min(answer, i);
            Ns = Ns*10 + N;
            for(int j = 1; j < i; j++){
                for(Integer first : nums.get(j-1)){
                    for(Integer second : nums.get(i-j-1)){
                        for(int k = 0 ; k < 4; k++){
                            int result = operate(first, second, k);
                            if(result == number)
                                answer = Math.min(answer, i);
                            nums.get(i-1).add(result);
                        }
                    }
                }
            }
        }
        return answer > 8 ? -1 : answer;
    }

    int operate(int first, int second, int op){
        int ret = 0;
        switch(op){
            case 0:
                ret = first+second;
                break;
            case 1:
                ret = first-second;
                break;
            case 2:
                if(second != 0)
                    ret = first/second;
                break;
            case 3:
                ret = first*second;
                break;
        }
        return ret;
    }
}
