package Baekjoon;

import java.util.*;
import java.util.stream.Collectors;

public class CastleDefense_17135 {

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        Solution sol = new Solution();
        int n = sc.nextInt();
        int m = sc.nextInt();
        int d = sc.nextInt();
        int [][] map = new int[n][m];

        for(int i = 0; i< n;i++){
            for(int j = 0 ; j < m; j++){
                map[i][j] = sc.nextInt();
            }
        }

        sol.n =n;
        sol.m = m;
        sol.d = d;
        sol.map = map;
        System.out.println(sol.solve());
        return;
    }
    static class Solution{
        int n,m,d;
        int [][] map;

        int solve(){
            int answer = 0;
            PriorityQueue<Loc> a = new PriorityQueue<>((x,y) -> x.r < y.r ? -1 : 1);
            Deque<Integer> archer = new ArrayDeque<>();
            for(int i = 0 ; i < m; i++){
                archer.addLast(i);
                int temp = dfs(i,1,archer);
                archer.pollLast();
                answer = Math.max(temp,answer);
            }
            return answer;
        }

        int dfs(int now, int cnt, Deque<Integer> archer){
            if(now >=m) return 0;
            if(cnt == 3){
                return simulation(archer);
            }
            int max = 0;
            for(int i = now+1 ; i < m; i++){
                archer.addLast(i);
                int temp = dfs(now+1, cnt+1, archer);
                archer.pollLast();
                max = Math.max(temp,max);
            }
            return max;
        }

        int simulation(Deque<Integer> archer){
            int answer = 0;
            List<Loc> enemies = getEnemies();
            while(!enemies.isEmpty()){
                answer += attack(archer, enemies);
                enemies = move(enemies);
            }
            return answer;
        }

        List<Loc> getEnemies(){
            List<Loc> result = new ArrayList<>();
            for(int i = 0 ; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if(map[i][j] == 1){
                        result.add(new Loc(i,j));
                    }
                }
            }
            return result;
        }

        int attack(Deque<Integer> archer, List<Loc> enemies){
            int [] distance = new int[m];
            int [] min = new int[m];
            for(int i = 0 ; i<m; i++)
                distance[i] = n*m;

            for(int i = 0 ; i < enemies.size(); i++){
                Loc e = enemies.get(i);
                for(Integer a : archer){
                    int dist = Math.abs(n-e.r) + Math.abs(a-e.c);
                    if(dist > d) continue;
                    if(dist > distance[a]) continue;
                    if(dist < distance[a] || enemies.get(min[a]).c > e.c) {
                        min[a] = i;
                        distance[a] = dist;
                    }
                }
            }

            Set<Loc> set = new HashSet<>();
            for(Integer a : archer) {
                if(distance[a] == n*m) continue;
                set.add(enemies.get(min[a]));
            }

            int answer = 0;
            for(Loc l : set){
               enemies.remove(l);
               answer++;
           }

           return answer;
        }

        List<Loc> move(List<Loc> enemies){
            return enemies.stream()
                    .filter(x -> x.r+1 < n)
                    .map(x -> {
                        x.r++;
                        return x;
                    })
                    .collect(Collectors.toList());
        }
        static class Loc{
            int r,c;
            Loc(int r, int c){
                this.r = r;
                this.c = c;
            }

        }
    }
}

