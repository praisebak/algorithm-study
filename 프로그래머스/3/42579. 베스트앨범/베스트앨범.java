import java.util.*;
class Solution {
    class Genre implements Comparable<Genre>{
        int play;
        String genre;
        
        public Genre(String genre,int play){
            this.play=play;
            this.genre=genre;
        }
        
        @Override
        public int compareTo(Genre other){
            return other.play - play;
        }
    }
    
    class Song implements Comparable<Song>{
        int play;
        String genre;
        int idx;
        public Song(int play,String genre,int idx){
            this.play=play;
            this.genre=genre;
            this.idx=idx;
        }
        
        @Override
        public int compareTo(Song other){
            if(play == other.play){
                return idx - other.idx;
            }
            return other.play - play;
        }
    }
    
    public int[] solution(String[] genres, int[] plays) {
        
        Map<String,Genre> counts = new HashMap<>();
        Map<String,List<Song>> songs = new HashMap<>();
        
        for(int i=0;i<plays.length;i++){
            int play = plays[i];
            String genre = genres[i];
        
            Genre cur = counts.getOrDefault(genre,new Genre(genre,0));
            
            cur.play += play;
            
            counts.put(genre,cur);
            List<Song> curSongs = songs.getOrDefault(genre,new ArrayList<>()); 
            curSongs.add(new Song(play,genre,i));
            songs.put(genre,curSongs);
        }
        
        
        List<Genre> genresList = new ArrayList<>(counts.values());
        Collections.sort(genresList);
        
        List<Integer> idxList = new ArrayList<>();
        for(Genre g : genresList){
            String genre = g.genre;
            
            
            List<Song> curSongs = songs.get(genre);
            Collections.sort(curSongs);
            
            for(int i=0;i<Math.min(2,curSongs.size());i++){
                idxList.add(curSongs.get(i).idx);
            }
        }
                
        int[] answer=  new int[idxList.size()];
        int idx = 0;
        for(int i : idxList){
            answer[idx++] = i;
        }
        
        return answer;
    }
}