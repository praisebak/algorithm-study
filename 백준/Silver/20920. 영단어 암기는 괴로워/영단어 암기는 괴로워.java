import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Main{
    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }
}

class Solve{

    class Word implements Comparable<Word>{
        String word;
        int count;

        public Word(String word, int count) {
            this.word = word;
            this.count = count;
        }

        public void addCount(){
            count++;
        }

        @Override
        public int compareTo(Word o) {
            if(count == o.count){
                if(word.length() == o.word.length()){
                    return word.compareTo(o.word);
                }
                return o.word.length() - word.length();
            }
            return o.count - count;
        }
    }

    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] sArr = bufferedReader.readLine().split(" ");
        int N = Integer.parseInt(sArr[0]);
        int M = Integer.parseInt(sArr[1]);

        Map<String,Word> wordFindMap = new HashMap<>();
        List<Word> words = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String word = bufferedReader.readLine();

            Word curWord = new Word(word, 1);

            if(wordFindMap.containsKey(word)){
                wordFindMap.get(word).addCount();
            }else{
                wordFindMap.put(word,curWord);
                words.add(curWord);
            }
        }
        Collections.sort(words);
        StringBuilder stringBuilder = new StringBuilder();

        for(Word word : words){
            if(word.word.length() < M){
                continue;
            }
            stringBuilder.append(word.word).append("\n");
        }

        System.out.print(stringBuilder.toString());
    }
}
