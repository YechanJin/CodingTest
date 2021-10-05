public class LottoMaxMinRank {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];

        int zeroNum = countZero(lottos);
        int matchNum = countMatch(lottos, win_nums);
        answer[0] = numToWin(matchNum+zeroNum);
        answer[1] = numToWin(matchNum);
        return answer;
    }
    public int countZero(int[] lottos){
        int res = 0;
        for(int i : lottos){
            if(i == 0)
                res++;
        }
        return res;
    }
    public int countMatch(int[] lottos, int[] win_nums){
        int res = 0;
        for(int i : win_nums){
            for(int j : lottos){
                if(i == j)
                    res++;
            }
        }
        return res;
    }
    public int numToWin(int num){
        if(num < 2)
            return 6;
        return 7-num;
    }
}
