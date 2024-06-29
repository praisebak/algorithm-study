package newneo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;


public class B9935 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String string = bufferedReader.readLine();
        String boomStr = bufferedReader.readLine();
        int boomStrSize = boomStr.length();

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < string.length(); i++) {
            stack.push(string.charAt(i));

            if(stack.size() >= boomStrSize){

                boolean deleteFlag = false;
                for (int j = 0; j < boomStrSize; j++) {
                    if(boomStr.charAt(j) != stack.get(stack.size() - boomStrSize + j)) break;
                    if(j == boomStrSize-1){
                        deleteFlag = true;
                    }
                }

                if(deleteFlag){
                    for (int j = 0; j < boomStrSize; j++) {
                        stack.pop();
                    }
                }
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (Character c : stack)
            stringBuilder.append(c);

        System.out.println(stringBuilder.toString().length() == 0 ? "FRULA" : stringBuilder.toString());
    }
}