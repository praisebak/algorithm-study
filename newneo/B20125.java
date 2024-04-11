package newneo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;



class B20125 {
    class Node{
        int y;
        int x;
        Node(int y,int x){
            this.y = y;
            this.x = x;
        }
    }

    //왼팔 오른팔 허리 왼쪽다리 오른쪽다리
    int[] dx = {-1,1,0,0,0};
    int[] dy = {0,0,1,1,1};
    int N;
    int[][] arr;

    List<Integer> result = new ArrayList<>();
    public void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1][N+1];
        Node heart = new Node(0,0);
        boolean isHead = true;

        for(int i=1;i<=N;i++){
            String s = br.readLine();
            for(int j=0;j<s.length();j++){
                char val = s.charAt(j);
                if(val == '_'){
                    arr[i][j+1] = 0;
                }else if(val == '*'){
                    arr[i][j+1] = 1;
                    if(isHead){
                        isHead = false;
                        heart = new Node(i+1,j+1);
                    }
                }
            }
        }
        Node leftHandStart = new Node(heart.y,heart.x-1);
        Node rightHandStart = new Node(heart.y,heart.x+1);

        result.add(checkIndex(leftHandStart,0));
        result.add(checkIndex(rightHandStart,1));

        Node waistStart = new Node(heart.y+1,heart.x);
        int waistDir = 2;

        int count = checkIndex(waistStart,waistDir);
        result.add(count);
        Node waistEnd = new Node(heart.y+count,heart.x);


        Node leftLegStart = new Node(waistEnd.y+1,waistEnd.x-1);
        count = checkIndex(leftLegStart,3);
        result.add(count);


        Node rightLegStart = new Node(waistEnd.y+1,waistEnd.x+1);
        count = checkIndex(rightLegStart,4);
        result.add(count);
        System.out.println(heart.y + " " + heart.x);
        for(Integer resultInt : result){
            System.out.print(resultInt + " ");
        }
        System.out.println();
    }

    public int checkIndex(Node node,int dir){
        int sY = node.y;
        int sX = node.x;

        int count = 0;
        //유효하고 값이 *라면 지속
        while(isValid(sY,sX) && arr[sY][sX] == 1){
            count++;

            sY += dy[dir];
            sX += dx[dir];
        }
        return count;
    }

    public boolean isValid(int sY,int sX){
        Deque<Integer> deque = new LinkedList<Integer>();
         if(sY < 0 || sX < 0 || sX > N || sY > N){
            return false;
        }
        return true;
    }
}



