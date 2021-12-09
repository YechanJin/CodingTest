package Baekjoon;

import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;

public class SayMiddle_1655 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> leftHeap = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> rightHeap = new PriorityQueue<>();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i = 0 ; i < n; i++){
            int num = Integer.parseInt(br.readLine());

            if(leftHeap.isEmpty() || num <= leftHeap.peek())
                leftHeap.add(num);
            else
                rightHeap.add(num);

            if(leftHeap.size() - rightHeap.size() > 1)
                rightHeap.add(leftHeap.poll());
            else if(rightHeap.size() > leftHeap.size())
                leftHeap.add(rightHeap.poll());

            bw.write(leftHeap.peek()+"\n");
        }
        bw.flush();
        bw.close();
        return;
    }

}
