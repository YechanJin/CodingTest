package Programmers;

public class EntranceJudge {

    final static long max = 1000000000;
    int [] times;
    long n;
    public long solution(int n, int[] times) {
        this.n = n;
        this.times = times;

        long s = 0, e = max*max;
        while(s < e){
            long mid =(s+e)/2;

            if(isPossible(mid))
                e = mid;
            else
                s = mid + 1;
        }
        return s;
    }
    boolean isPossible(long time){
        long sum = 0;
        for(int i = 0 ; i < times.length; i++){
            sum += time/times[i];
        }
        return sum >= n;
    }
}
