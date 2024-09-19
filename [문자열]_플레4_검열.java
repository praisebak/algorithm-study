import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class Main{

    public static void main(String[] args) throws IOException{
        Solve solve = new Solve();
        solve.solve();
    }
}

class Solve{
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String obj =bufferedReader.readLine();
        String s =bufferedReader.readLine();

        int left = 0;
        int right = s.length()-1;

        StringBuilder leftSB = new StringBuilder();
        StringBuilder rightSB = new StringBuilder();

        while (left <= right){
            while (left <= right){
                leftSB.append(s.charAt(left++));
                if(leftSB.length() >= obj.length()){
                    int start = leftSB.length()-obj.length();
                    int end = leftSB.length();
                    if(leftSB.substring(start,end).equals(obj)){
                        leftSB.delete(start,end);
                        break;
                    }
                }
            }

            while (left <= right){
                rightSB.insert(0, s.charAt(right));
                right--;
                if(rightSB.length() >= obj.length()){
                    int start = 0;
                    int end = obj.length();
                    if(rightSB.substring(start,end).equals(obj)){
                        rightSB.delete(start,end);
                        break;
                    }
                }
            }
        }
        leftSB = leftSB.append(rightSB);
        while (leftSB.indexOf(obj) != -1){
            int idx = leftSB.indexOf(obj);
            leftSB.delete(idx,idx+obj.length());
        }
        System.out.println(leftSB);


    }
}
