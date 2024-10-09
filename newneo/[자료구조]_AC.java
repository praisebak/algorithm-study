import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

class Main{
    public static void main(String[] args) throws IOException{
        Solve solve = new Solve();
        solve.solve();
    }
}
class Solve{
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bufferedReader.readLine());
        StringBuilder stringBuilder = new StringBuilder();

        for (int t = 0; t < T; t++) {
            String oper = bufferedReader.readLine();
            int N = Integer.parseInt(bufferedReader.readLine());
            String s = bufferedReader.readLine().replace("[","").replace("]","");
            String[] sArr = s.split(",");
            Deque<Integer> deque = new LinkedList<>();
            for(String numS : sArr){
                if(numS.isBlank()) break;
                deque.addLast(Integer.parseInt(numS));
            }

            boolean isReverse = false;
            boolean isError = false;
            for (int i = 0; i < oper.length(); i++) {
                if(oper.charAt(i) == 'R'){
                    isReverse = !isReverse;
                }else{
                    if(deque.isEmpty()){
                        stringBuilder.append("error");
                        isError = true;
                        break;
                    }
                    if(isReverse){
                        deque.removeLast();
                    }else{
                        deque.removeFirst();
                    }
                }
            }
            if(!isError){
                stringBuilder.append("[");
                while (!deque.isEmpty()){
                    if(isReverse){
                        stringBuilder.append(deque.removeLast());
                    }else{
                        stringBuilder.append(deque.removeFirst());
                    }
                    if(!deque.isEmpty()) stringBuilder.append(",");
                }

                stringBuilder.append("]");
            }
            stringBuilder.append("\n");
        }
        System.out.println(stringBuilder);

    }
}
