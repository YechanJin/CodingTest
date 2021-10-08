import java.util.ArrayList;
import java.util.List;

public class GroupPicture {
    final char[] alph = {'A','C','F','J', 'M','N','R','T'};
    final int[] mapping = new int[26];
    List<Cond> conds = new ArrayList<>();

    public void init(){
        for(int i = 0 ; i < alph.length; i++){
            mapping[alph[i]-'A'] = i;
        }
    }

    public int solution(int n, String[] data) {
        int answer = 0;
        init();

        for(String s : data){
            conds.add(parseCondFromInput(s));
        }
        for(int i = 0 ; i < 8; i++){
            int[] seq = new int[8];
            seq[i] = 1;
            answer += dfs(2, seq);
        }

        return answer;
    }

    public int dfs(int num, int[] seq){
        int result = 0;
        if(!passCond(seq)){
            return result;
        }
        if(num > 8){
            return result+1;
        }
        for(int i = 0 ; i < 8; i++){
            if(seq[i] != 0 )
                continue;
            seq[i] = num;
            result += dfs(num+1, seq);
            seq[i] = 0;
        }
        return result;
    }

    public boolean passCond(int [] seq){
        for(Cond c : conds){
            if(seq[c.from] == 0 || seq[c.to] == 0)
                continue;
            switch(c.cond){
                case '>':
                    if(Math.abs(seq[c.from]-seq[c.to]) <= c.num+1)
                        return false;
                    break;
                case '<':
                    if(Math.abs(seq[c.from]-seq[c.to]) >= c.num+1)
                        return false;
                    break;
                case '=':
                    if(Math.abs(seq[c.from]-seq[c.to]) != c.num+1)
                        return false;
                    break;
            }
        }
        return true;
    }

    public Cond parseCondFromInput(String data){
        int from = mapping[data.charAt(0) - 'A'];
        int to = mapping[data.charAt(2) - 'A'];
        char cond = data.charAt(3);
        int num = data.charAt(4)-'0';
        return new Cond(from, to, cond, num);
    }
}

class Cond{
    int from;
    int to;
    char cond;
    int num;
    Cond(int from, int to, char cond, int num){
        this.from = from;
        this.to = to;
        this.cond = cond;
        this.num = num;
    }
}