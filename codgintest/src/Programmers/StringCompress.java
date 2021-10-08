package Programmers;

import java.util.ArrayList;
import java.util.List;

public class StringCompress {

    public int solution(String s) {
        int answer = 2000;
        for(int i = 1; i <=s.length();i++){
            int  res = compress(s, i);
            if(res < answer)
                answer = res;
        }
        return answer;
    }

    public int compress(String s, int num){
        List<String> cut = new ArrayList<>();
        for(int i = 0 ; i < s.length(); i+=num){
            cut.add(s.substring(i,Math.min(i+num, s.length())));
        }
        String result = compressCut(cut);
        return result.length();
    }

    public String compressCut(List<String> cut){
        StringBuilder result = new StringBuilder();
        int i = 0;
        while(i < cut.size()){
            String prev = cut.get(i);
            i++;
            int cnt = 0;
            while(i < cut.size()){
                String str = cut.get(i);
                if(prev.equals(str)){
                    cnt++;
                    i++;
                }
                else
                    break;
            }
            if(cnt > 0) {
                result.append(cnt + 1);
            }
            result.append(prev);
        }
        return result.toString();
    }
}
