import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class p14891 {
    public static void main(String[] args) {
        p14891 solve = new p14891();
        solve.solve();
    }

    List<ArrayList<Integer>> queList = new ArrayList<>();
    int K = 0;
    List<Integer> problemList = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    void init(){
        for(int i=0;i<4;i++){
            ArrayList<Integer> que = new ArrayList<>();
            String s = sc.nextLine();
            for(int j=0;j<8;j++){
                Integer l = Integer.valueOf(s.charAt(j)) - 48;
                que.add(l);
            }
            queList.add(que);
        }

        K = sc.nextInt();
        for(int i=0;i<K*2;i++){
            problemList.add(sc.nextInt());
        }
    }

    public void solve(){
        init();
//        System.out.println("초기");
//        print();
//        System.out.println();

        for(int size = 0;size<problemList.size()-1;size+=2){
            int tooth = problemList.get(size)-1;
            int dir = problemList.get(size+1);
            ArrayList<Integer> que = queList.get(tooth);
            propaganda(tooth,dir,0);

        }

        int result = 0;
        int j =0;
        for(int i=1;i<=8;i*=2){
            result += queList.get(j++).get(0) * i;
        }
        System.out.println(result);
//        System.out.println();
//        print();
    }

    void print(){
        for(int i=0;i<4;i++){
            for(int j=0;j<queList.get(i).size();j++){
                System.out.print(queList.get(i).get(j));
            }
            System.out.println();
        }
    }

    public void rotate(ArrayList<Integer> que,int dir){
        //시계방향
        if(dir == 1 ){
            Integer last = que.get(que.size()-1);
            que.remove(que.size()-1);
            que.add(0, last);
        }else{
            Integer first = que.get(0);
            que.remove(0);
            que.add(first);
        }
    }

    private void propaganda(int tooth, int dir,int proDir) {
//        System.out.println((tooth+1) + " 번째 " + dir + "회전" + " 프로파간다 :" + proDir);

        ArrayList<Integer> cur = queList.get(tooth);
        if (isRangeOk(tooth - 1) && (proDir == -1 || proDir == 0)) {
            ArrayList<Integer> left = queList.get(tooth-1);
            if(cur.get(6) != left.get(2)){
                propaganda(tooth-1,dir * -1,-1);
            }

        }
        if(isRangeOk(tooth + 1) && (proDir == 1 || proDir == 0)){
            ArrayList<Integer> right = queList.get(tooth+1);
            if(cur.get(2) != right.get(6)){
                propaganda(tooth+1,dir * -1,1);
            }
        }
//        System.out.println((tooth+1) + " 번째 ㅎㅈ " + dir + "회전" + " 프로파간다 :" + proDir);

        rotate(cur,dir);

    }


    public boolean isRangeOk(int idx){
        if(idx <= -1 || idx >= queList.size()){
            return false;
        }
        return true;

    }

}

