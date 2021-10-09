package Programmers;

public class SkillTree {

    public int solution(String skill, String[] skill_trees) {
        int answer = 0;

        int [] proc = new int[26];
        boolean [] learn;

        for(int i = 0 ; i < 26; i ++){
            proc[i] = -1;
        }

        for(int i = 1 ; i< skill.length(); i++){
            int prev = skill.charAt(i-1) - 'A';
            int now = skill.charAt(i) - 'A';
            proc[now] = prev;
        }

        for(String st : skill_trees){
            learn = new boolean[26];
            int possible = 1;
            for(int i = 0 ; i < st.length(); i++){
                int s = st.charAt(i)-'A';
                if(proc[s] >= 0 && !learn[proc[s]]){
                    possible--;
                    break;
                }
                learn[s] = true;
            }
            answer += possible;
        }

        return answer;
    }

    //다른 사람의 풀이 참고
    public int solution2(String skill, String[] skill_trees) {
        int answer = 0 ;

        for(String st : skill_trees){
            int i = skill.indexOf(st.replaceAll("[^]" + skill + "]", ""));
            if(i == 0)
                answer++;
        }
        return answer;
    }
}
