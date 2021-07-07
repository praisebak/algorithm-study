import java.util.Stack;

class p42584 {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length]; 

        Stack<Integer> stack = new Stack<Integer>();
        
        for(int i=0;i<prices.length;i++)
        {
            while(!stack.empty() && prices[stack.peek()] > prices[i])
            {
                int tmp = stack.pop();
                answer[tmp] = i - tmp;
            }
            stack.push(i);
        }
        
        while(!stack.empty())
        {
            int tmp = stack.pop();
            answer[tmp] = prices.length - 1 - tmp;
        }

        return answer;

    }

}
