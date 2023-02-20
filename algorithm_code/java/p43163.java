import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

class Solution 
{
    String[] w;
    HashSet<String> visit = new HashSet<>();
    class StrDepthPair
    {
        String str;
        Integer depth;
        StrDepthPair(String str,Integer depth)
        {
            this.str = str;
            this.depth = depth;
        }
    }

    public int solution(String begin, String target, String[] words) 
    {
        w = words;
        Queue<StrDepthPair> q = new LinkedList<>();
        
        q.add(new StrDepthPair(begin,0));

        String curStr = "";
        int answer = 0;
        while(q.size() != 0)
        {
            StrDepthPair curPair = q.poll();
            curStr = curPair.str;
            if(curStr.equals(target))
            {
                answer = curPair.depth;
                break;
            }
            addPossibleStr(curPair,q);
        }

        return answer;
    }

    private void addPossibleStr(StrDepthPair curPair, Queue<StrDepthPair> q) 
    {
        String curStr = curPair.str;
        for(int i=0;i<curStr.length();i++)
        {
            StringBuffer sb = new StringBuffer(curStr);
            for(char c='a';c <= 'z';c++)
            {
                sb = sb.replace(i,i+1,c + "");
                for(int j=0;j<w.length;j++)
                {
                    if(sb.toString().equals(w[j]) && !visit.contains(w[j]))
                    {
                        visit.add(w[j]);
                        q.add(new StrDepthPair(w[j],curPair.depth+1) );
                    }
                }
                
            }
        }
    }

    public static void main(String[] args) 
    {
        Solution solu = new Solution();
        solu.solution("hit", "cog", new String[] {"hot", "dot", "dog", "lot", "log", "cog"});
    }
}