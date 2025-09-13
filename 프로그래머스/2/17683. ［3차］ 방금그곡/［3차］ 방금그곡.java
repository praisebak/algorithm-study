import java.util.*;
class Solution {
    
    class Song implements Comparable<Song>{
        String str;
        String name;
        int idx;
        int len;
        public Song(String str,String name,int idx,int len){
            this.str=str;
            this.name=name;
            this.idx=idx;
            this.len=len;
        }
        
        public int compareTo(Song other){
            if(len == other.len){
                return idx - other.idx;
            }
            
            return other.len - len;
        }
    }
    
    public String solution(String m, String[] musicinfos) {
        
        StringBuilder mSB = new StringBuilder();
        for(int i=0;i<m.length()-1;i++){
            if(m.charAt(i+1) == '#'){
                mSB.append("," + m.charAt(i) + "#");
                i++;
                continue;
            }
            mSB.append(m.charAt(i) + "");
        }
        if(m.charAt(m.length()-1) != '#'){
            mSB.append(m.charAt(m.length()-1) + "");
        }
        m = mSB.toString();
        
        
        List<Song> answer = new ArrayList<>();
        List<Song> songs = new ArrayList<>();
        int idx= 0;
        for(String ss : musicinfos){
            String[] s = ss.split(",");
            String start = s[0];
            int startMin = parseMin(start);
            String end = s[1]; 
            int endMin = parseMin(end);
            
            String songName = s[2];
            String melody = s[3];
            List<String> melodys = parseMelodys(melody);
            StringBuilder resultMelody = new StringBuilder();
            for(int i=0;i<endMin-startMin;i++){
                resultMelody.append(melodys.get(i % melodys.size()));
            }
            if(resultMelody.toString().contains(m)){
                songs.add(new Song(resultMelody.toString(),songName,idx++,endMin-startMin));
            }
        }
        
        Collections.sort(songs);
        
        if(songs.size() == 0){
            return "(None)";
        }
        
        
        return songs.get(0).name;
    }
    
    public List<String> parseMelodys(String s){ 
        List<String> result = new ArrayList<>();
        for(int i=0;i<s.length()-1;i++){
            if(s.charAt(i+1)== '#'){
                result.add(","+s.charAt(i) + "#");
                i++;
                continue;
            }
            result.add(s.charAt(i) + "");
        }
        if(s.charAt(s.length()-1) != '#'){
            result.add(s.charAt(s.length()-1) + "");
        }
        return result;
    }
    
    public int parseMin(String s){
        String[] sArr = s.split(":"); 
        int h = Integer.parseInt(sArr[0]) * 60;
        int min = Integer.parseInt(sArr[1]);
        
        return h + min;
    }
}