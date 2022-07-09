public import java.util.ArrayList;
import java.util.Stack;

class Node
{
    int prev;
    int next;
    int cur;

    Node(int prev,int next,int cur)
    {
        this.prev = prev;
        this.next = next;
        this.cur = cur;
    }
}


class Solution {
    public String solution(int n, int k, String[] cmd) 
    {
        int[] prev = new int[n];
        int[] next = new int[n];


        //이전 값이 -1이면 처음 
        //다음 값이 -1이면 마지막
        for(int i=0;i<n;i++)
        {
            prev[i] = i-1;
            next[i] = i+1;
        }
        next[n-1] = -1;

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<n;i++)
        {
            sb.append("O");
        }

        Stack<Node> stack = new Stack<>();
        for(String s : cmd)
        {
            char c = s.charAt(0);
            int num = 0;
            if(c == 'U')
            {
                num = Integer.valueOf(s.substring(2));
                while(num-- > 0)
                {
                    k = prev[k];
                }
            }
            else if(c == 'D')
            {
                num = Integer.valueOf(s.substring(2));
                while(num-- > 0)
                {
                    k = next[k];
                }
            }
            else if(c == 'C')
            {
                stack.add(new Node(prev[k],next[k],k));
                if(prev[k] != -1)
                    next[prev[k]] = next[k];
                if(next[k] != -1)
                    prev[next[k]] = prev[k];
                sb.setCharAt(k, 'X');

                if(next[k] != -1)
                {
                    k = next[k];
                }
                else
                {
                    k = prev[k];
                }
            }
            else
            {
                Node node = stack.pop();
                if(node.prev != -1)
                {
                    next[node.prev] = node.cur;
                }
                if(node.next != -1)
                {
                    prev[node.next] = node.cur;
                }
                sb.setCharAt(node.cur, 'O');

            }
        }   
        String answer = sb.toString();
        return answer;
    }

} p81303 {
    
}
