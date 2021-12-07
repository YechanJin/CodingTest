package Baekjoon;

import java.io.*;
import java.util.*;

public class Ecology_4358 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Integer> ec = new HashMap<>();
        List<Species> speciesList = new ArrayList<>();

        String input;
        int totalNum = 0;
        while((input = br.readLine()) != null){
            totalNum++;
            if(ec.containsKey(input)){
                int index = ec.get(input);
                speciesList.get(index).count++;
                continue;
            }
            ec.put(input, speciesList.size());
            speciesList.add(new Species(input, 1));
        }
        speciesList.sort(Comparator.comparing(x -> x.name));

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (Species species : speciesList) {
            bw.write(species.name +" " + String.format("%.4f", (double)species.count*100/ totalNum) +"\n");
        }
        bw.flush();
        bw.close();

        return;

    }
    static class Species{
        String name;
        int count;

        public Species(String name, int count) {
            this.name = name;
            this.count = count;
        }
    }
}
