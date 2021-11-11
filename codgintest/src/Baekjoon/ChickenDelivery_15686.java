package Baekjoon;

import java.util.*;

public class ChickenDelivery_15686 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        List<Loc> houses = new ArrayList<>();
        List<Loc> chickens = new ArrayList<>();
        for(int i = 0 ; i < n; i++){
            for(int j = 0 ; j < n; j++){
                int map = sc.nextInt();
                if(map == 1){
                    houses.add(new Loc(i,j));
                }
                else if(map == 2){
                    chickens.add(new Loc(i,j));
                }
            }
        }
        Solution sol = new Solution(n, m, houses, chickens);
        System.out.println(sol.solve());
        return;
    }

    static class Solution{

        final static int INF = 2123456789;
        int n,m;
        List<Loc> houses, chickens;
        int [][] distance;
        boolean [] remain;

        public Solution(int n, int m,  List<Loc> houses, List<Loc> chickens) {
            this.n = n;
            this.m = m;
            this.houses = houses;
            this.chickens = chickens;
            this.remain = new boolean[chickens.size()];
            this.distance = new int[houses.size()][chickens.size()];
        }

        int solve(){
            getAllDistance();
            return dfs(0,0);
        }

        void getAllDistance(){
            for(int i = 0 ; i < houses.size(); i++){
                for(int j = 0; j < chickens.size(); j++){
                    distance[i][j] = getChickenDistance(houses.get(i), chickens.get(j));
                }
            }
        }

        int getChickenDistance(Loc l1, Loc l2){
            return Math.abs(l1.r-l2.r) + Math.abs(l1.c-l2.c);
        }

        int dfs(int now, int cnt){
            int size = chickens.size();
            if(cnt > m)
                return INF;
            if(now >= size)
                return getCityDistance();

            int result = INF;
            result = Math.min(result, dfs(now+1, cnt));
            remain[now] = true;
            result = Math.min(result, dfs(now+1, cnt+1));
            remain[now] = false;

            return result;
        }

        int getCityDistance(){
            int result = 0;
            for(int i = 0 ; i < houses.size(); i++){
                int min = INF;
                for(int j = 0 ; j < chickens.size() ; j++){
                    if(!remain[j]) continue;
                    min = Math.min(min, distance[i][j]);
                }
                if(min == INF) continue;
                result += min;
            }
            return result == 0 ? INF : result;
        }


    }

    static class Loc{
        int r, c;

        public Loc(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
