public class KakaoFriendsColoringBook {

    final int[] dr = {0,1,0,-1};
    final int[] dc = {1,0,-1,0};
    int M,N;

    boolean [][] visit;

    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        M=m;
        N=n;

        visit = new boolean[m][n];

        for(int i = 0 ; i < m; i++){
            for(int j = 0 ; j < n ; j++){
                if(picture[i][j] != 0 && !visit[i][j]){
                    int count = dfs(i,j, picture);
                    if(count > maxSizeOfOneArea){
                        maxSizeOfOneArea = count;
                    }
                    numberOfArea++;
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }

    public int dfs(int r, int c, int[][] picture){
        int count = 0;
        visit[r][c] = true;
        for(int i = 0 ; i<4;i++){
            int nr = dr[i] + r;
            int nc = dc[i] + c;
            if(nr < 0 || nr >= M) continue;
            if(nc < 0 || nc >= N) continue;
            if(visit[nr][nc] || (picture[nr][nc] != picture[r][c])) continue;

            count += dfs(nr,nc, picture);
        }
        return count + 1;
    }
}
