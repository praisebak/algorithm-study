//https://www.acmicpc.net/problem/14725
import javax.print.DocFlavor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main{
    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }
}


class Trie{
    HashMap<String, Trie> map = new HashMap<>();
    private String depthPrint = "--";

    public void insert(List<String> stringList) {
        Trie trie = this;
        for(String s : stringList){
            trie.map.putIfAbsent(s,new Trie());
            trie = trie.map.get(s);
        }
    }

    public void print(Trie trie, int depth) {
        if(trie.map == null){
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            stringBuilder.append(depthPrint);
        }

        List<String> list = new ArrayList<>(trie.map.keySet());
        Collections.sort(list);

        for(String s: list){
            System.out.print(stringBuilder.toString());
            System.out.println(s);
            print(trie.map.get(s), depth+1);
        }
    }
}
class Solve{
    Trie trie = new Trie();
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s =  bufferedReader.readLine();
        int N = Integer.parseInt(s);
        for (int i = 0; i < N; i++) {
            String[] sArr = bufferedReader.readLine().split(" ");
            int count = Integer.parseInt(sArr[0]);
            List<String> stringList = new ArrayList<>();
            for (int j = 1; j <= count; j++) {
                stringList.add(sArr[j]);
                trie.insert(stringList);
            }
        }

        trie.print(trie,0);
    }
}
