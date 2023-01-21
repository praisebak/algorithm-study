import java.util.ArrayList;
<<<<<<< HEAD
import java.util.Collections;

class Solution
{

    public String solution(int n, int t, int m, String[] timetable)
    {
        ArrayList<Integer> list = new ArrayList<>();
        for(String str : timetable)
        {
            list.add(convertTimeToSec(str));
        }
        Collections.sort(list);

        int curTimeSec = 9 * 3600;
        int secAddUnit = 60 * t;
        int shuttleSeat = 0;

        for(Integer sec : list)
        {
            if(sec <= curTimeSec)
            {
                shuttleSeat++;
            }

            if(shuttleSeat == m)
            {
                shuttleSeat = 0;
            }

        }

        String answer = "";
        return answer;
    }

    private int convertTimeToSec(String str)
    {
        int hourToSec = Integer.parseInt(str.split(":")[0]) * 3600;
        int minToSec = Integer.parseInt(str.split(":")[1])  * 60;
        int sec = hourToSec + minToSec;
        return sec;
    }

}
=======
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;


class Node
{
    int val;
    int x;
    int y;
    Node left;
    Node right;
    Node parent;

    boolean isLeftEmpty()
    {
        return false;
    }

    boolean isRightEmpty()
    {
        return false;
    }

    Node(int val,int x,int y)
    {
        this.val = val;
        this.x = x;
        this.y = y;
        this.left = null;
        this.right = null;
    }
}

class Solution {
    Node root = null;
    
    public int[][] solution(int[][] nodeinfo) {
        Node[] nodeInfo = new Node[nodeinfo.length];
        init(nodeinfo,nodeInfo);

        Arrays.sort(nodeInfo,new Comparator<Node>(){

            @Override
            public int compare(Node o1, Node o2) {

                if(o2.y > o1.y)
                {
                    return 1;
                }
                else if(o1.y> o2.y)
                {
                    return -1;
                }
                else
                {
                    return o1.x > o2.x ? 1 : -1;
                }
            }
        });

        ArrayList<ArrayList<Node>> list = new ArrayList<>();
        
        ArrayList<Integer> lis = new ArrayList<>();

        connectNodes(nodeInfo);
        postOrder(root,lis,0);
        int[][] answer = new int[2][lis.size()];
        for(int i=0;i<lis.size();i++)
        {
            answer[0][i] = lis.get(i);
        }
        lis = new ArrayList<>();
        inOrder(root,lis,0);
        for(int i=0;i<lis.size();i++)
        {
            answer[1][i] = lis.get(i);
        }
        return answer;
    }

    private void inOrder(Node node, ArrayList<Integer> list, int idx) 
    {
        if(node == null)
        {
            return;
        }

        inOrder(node.left,list,++idx);
        //System.out.println(node.val);
        inOrder(node.right,list,idx);
        list.add(node.val);

    }

    private void postOrder(Node node,ArrayList<Integer> list,int idx) 
    {
        if(node == null)
        {
            return;
        }

        list.add(node.val);
        // System.out.println(node.val);
        // System.out.println(node.val + "-> 왼쪽");
        postOrder(node.left,list,++idx);
        // System.out.println(node.val + "-> 오른쪽");
        postOrder(node.right,list,idx);
    }

    private void init(int[][] nodeinfo, Node[] nodeInfo2) 
    {
        for(int i=0;i<nodeinfo.length;i++)
        {
            int x = nodeinfo[i][0];
            int y = nodeinfo[i][1];
            int val = i+1;
            nodeInfo2[i] = new Node(val, x, y);
        }
    }

    private void connectNodes(Node[] list) 
    {
        for(Node node : list)
        {
            if(root == null)
            {
                root = node;
            }
            connectNode(root,node);
        }
    }

    private boolean connectNode(Node parent,Node newNode) 
    {
        //왼쪽에 두기 
        if(newNode.x < parent.x && parent.left == null)
        {
            // System.out.println(parent.val + " 왼쪽에 " + newNode.val);
            parent.left = newNode;
            return true;
        }
        //오른쪽
        if(newNode.x > parent.x && parent.right == null)
        {
            // System.out.println(parent.val + " 오른쪽에 " + newNode.val);
            parent.right = newNode;
            return true;
        }
        
        if(newNode.x < parent.x)
        {   
            connectNode(parent.left,newNode);
        }
        if(newNode.x > parent.x)
        {
            connectNode(parent.right,newNode);
        }

        return false;
    }

    public static void main(String[] args) 
    {
        Solution solution = new Solution();
        solution.solution(new int[][] {{5,3},{11,5},{13,3},{3,5},{6,1},{1,3},{8,6},{7,2},{2,2}}	);
    }
}
>>>>>>> 7464d50f206a9faf927532070748e174e344aa89
