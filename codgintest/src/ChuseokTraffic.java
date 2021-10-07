import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChuseokTraffic {
    final int second = 1000;
    final int minute = second*60;
    final int hour = minute*60;
    final int finish = 23*hour + 59*minute + 60*second -1;

    public int solution(String[] lines) {
        int answer = -1;

        List<Log> starts = new ArrayList<>();

        for(String line : lines){
            starts.add(parseInput(line));
        }

        List<Log> ends = new ArrayList<>(starts);
        Collections.sort(starts, (x, y) -> x.start <= y.start ? -1 : 1);
        Collections.sort(ends, (x, y) -> x.end <= y.end ? -1 : 1);

        for(int i = 0, in = 0, s = 0, e = 0; i + second -1 <= finish; i++){
            int start = i, end = i + second -1;
            while(s < starts.size() && starts.get(s).start <= end){
                in++;
                s++;
            }
            while(e < ends.size() && ends.get(e).end < start){
                in--;
                e++;
            }
            if(in > answer)
                answer = in;
        }

        return answer;
    }

    public Log parseInput(String input){
        String[] temp = input.split(" ");
        String endString = temp[1];
        int term = (int)(Double.parseDouble(temp[2].replace("s", ""))*second);
        int endTime = parseStringToTime(endString);
        int startTime = endTime-term < 0 ? 0 : endTime-term + 1;
        return new Log(startTime, endTime);
    }

    public int parseStringToTime(String time){
        String[] temp = time.split("[:.]");
        int result = 0;
        result += Integer.parseInt(temp[0])*hour;
        result += Integer.parseInt(temp[1])*minute;
        result += Integer.parseInt(temp[2])*second;
        result += Integer.parseInt(temp[3]);
        return result;
    }

}
class Log {
    int start;
    int end;
    Log(int start, int end){
        this.start = start;
        this.end = end;
    }
}
