import java.util.*;

class Solution {
    
    class FileName implements Comparable<FileName>{
        String head;
        String tail;
        int index;
        int number;
        String origin;
        public FileName(String head,String tail,int index,int number,String origin){
            this.head=head;
            this.tail=tail;
            this.index=index;
            this.number=number;
            this.origin = origin;
        }
        
        @Override
        public int compareTo(FileName otherFile){
            String ch = head.toLowerCase();
            String oh = otherFile.head.toLowerCase();
            
            if(!oh.equals(ch)) return ch.compareTo(oh);
            
            if(number != otherFile.number){
                return number - otherFile.number;
            }
            
            return index - otherFile.index;
        }
    }
    public String[] solution(String[] file) {
        List<FileName> files = new ArrayList<>();
        int idx = 0;
        
        for(String s : file){
            String head = "";
            int number = -1;
            String tail = "";
            StringBuilder tmp = new StringBuilder();
            for(int i=0;i<s.length();i++){    
                if(head.length() == 0 && Character.isDigit(s.charAt(i))){
                    head = tmp.toString();
                    tmp = new StringBuilder();
                }else if(head.length() != 0 && number == -1 && !Character.isDigit(s.charAt(i))){
                    number = Integer.parseInt(tmp.toString());
                    tmp = new StringBuilder();
                }
                 tmp.append(s.charAt(i) + "");
            }
            
            if(number == -1){
                number = Integer.parseInt(tmp.toString());
            }
            files.add(new FileName(head,tail,idx,number,s));
            idx++;
        }
        
        Collections.sort(files);
        return files.stream()
            .map(f -> f.origin)
            .toArray(String[]::new);

    }
}