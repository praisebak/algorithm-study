import java.util.*;

//16:00 ~

class Room implements Comparable<Room>{
    int startMin;
    int endMin;
    
    public Room(int startMin,int endMin){
        this.startMin=startMin;
        this.endMin=endMin;
    }
    
    @Override
    public int compareTo(Room other){
        return startMin - other.startMin;
    }
}

class Solution {
    List<Room> rooms = new ArrayList<>();
    public int solution(String[][] book_time) {
        int answer = 0;
        PriorityQueue<Room> que = new PriorityQueue<>((o1,o2) -> o1.endMin - o2.endMin);
        List<Room> rooms = new ArrayList<>();
        
        for(String[] b : book_time){
            String[] sArr = b[0].split(":");
            int left = Integer.parseInt(sArr[0]) * 60;
            int right = Integer.parseInt(sArr[1]);
            sArr = b[1].split(":");
            int l2 = Integer.parseInt(sArr[0]) * 60;
            int r2 = Integer.parseInt(sArr[1]);
            
            rooms.add(new Room(left+right,l2+r2+10));
        }
        
        Collections.sort(rooms);

        for(Room room : rooms){
            if(que.isEmpty()){
                answer++;
                que.add(room);
                continue;
            }
            
            Room c = que.peek();
            
            if(c.endMin <= room.startMin){
                que.poll();
                que.add(room);                
            }else{
                answer++;                
                que.add(room);                
            }
        }
        
        return answer;
    }
}