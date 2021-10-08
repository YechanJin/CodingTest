package Programmers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OpenChat {
    public String[] solution(String[] record) {

        List<Record> records = stringToRecord(record);
        return getResult(records);

    }
    List<Record> stringToRecord(String[] record){
        Map<String, String> uidToNick = new HashMap<>();
        List<Record> recordList = new ArrayList<>();

        for(String r : record){
            String[] temp = r.split(" ");
            if(temp[0].equals("Enter")){
                recordList.add(new Record(temp[1], temp[2], temp[0]));
                uidToNick.put(temp[1], temp[2]);
            }
            else if(temp[0].equals("Leave")){
                recordList.add(new Record(temp[1],uidToNick.get(temp[1]), temp[0]));
            }
            else{
                uidToNick.put(temp[1], temp[2]);
            }
        }

        for(Record r : recordList){
            r.nickName = uidToNick.get(r.uid);
        }
        return recordList;
    }

    String [] getResult(List<Record> records){
        String []result = new String[records.size()];
        int i = 0;
        for(Record record : records){
            result[i++] = record.toString();
        }
        return result;
    }
}
class Record{
    String uid;
    String action;
    String nickName;

    Record(String uid, String nickName, String action){
        this.uid = uid;
        this.nickName = nickName;
        this.action = action;
    }

    public String toString(){
        if(action.equals("Enter")){
            return nickName + "님이 들어왔습니다.";
        }
        else{
            return nickName + "님이 나갔습니다.";
        }
    }
}
