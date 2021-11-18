package Baekjoon;

import java.util.*;

public class IsTree_6416 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for(int i = 1 ; ; i++){
            boolean isFinish = evaluateCase(i, sc);
            if(isFinish)
                break;
        }
        return;
    }

    static boolean evaluateCase(int caseNum , Scanner sc){
        Map<Integer, Integer> in = new HashMap<>();
        Map<Integer, Integer> out = new HashMap<>();
        while(true){
            int a = sc.nextInt();
            int b = sc.nextInt();
            if(isExit(a,b))
                return true;
            if(isEndOfCase(a, b))
                break;
            out.put(a, out.containsKey(a) ? out.get(a) + 1 : 1);
            in.put(b, in.containsKey(b) ? in.get(b) + 1 : 1);
        }
        boolean isTree = isTree(in, out);
        System.out.println("Case " + caseNum + " is " + (isTree ? "" : "not ") + "a tree." );
        return false;
    }
    static boolean isTree(Map<Integer, Integer> in, Map<Integer, Integer> out){
        List<Integer> root = new ArrayList<>();
        if(in.size() == 0)
            return true;
        for(Integer key : out.keySet()){
            if(!in.containsKey(key)){
                root.add(key);
                continue;
            }
        }
        for(Integer key : in.keySet()){
            if(in.get(key) > 1)
                return false;
        }
        return root.size() == 1;
    }

    static boolean isEndOfCase(int a, int b){
        return  a==0 && b ==0;
    }
    static boolean isExit(int a, int b){
        return a < 0 && b < 0;
    }
}
