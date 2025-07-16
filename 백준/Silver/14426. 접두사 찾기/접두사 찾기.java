import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;


class TrieNode{
    HashMap<Character,TrieNode> childMap = new HashMap<>();
    boolean isEnd;
    public TrieNode(){
        isEnd = false;
    }
}
class Main{
    public static void main(String[] args) throws IOException{
        Solve solve = new Solve();
        solve.solve();

    }
}
class Solve{
    TrieNode root = new TrieNode();
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] s =bufferedReader.readLine().split(" ");
        int N = Integer.parseInt(s[0]);
        int M = Integer.parseInt(s[1]);
        for(int i=0;i<N;i++){
            String string = bufferedReader.readLine();
            insert(string);
        }

        int answer = 0;
        for(int i=0;i<M;i++){
            String obj = bufferedReader.readLine();
            if(searchPrefix(obj)) answer++;
        }
        System.out.println(answer);
    }

    private void insert(String string) {
        TrieNode trieNode = root;
        for (char c : string.toCharArray()){
            trieNode.childMap.putIfAbsent(c,new TrieNode());
            trieNode = trieNode.childMap.get(c);
        }
        trieNode.isEnd = true;
    }
    private boolean searchPrefix(String obj){
        TrieNode trieNode = root;
        for (char c : obj.toCharArray()){
            if(trieNode.childMap.get(c) == null) return false;
            trieNode = trieNode.childMap.get(c);
        }
        return true;
    }

}
