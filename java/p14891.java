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

            for(int size = 0;size<problemList.size()-1;size+=2){
                int tooth = problemList.get(size);
                int dir = problemList.get(size+1);
                ArrayList<Integer> que = queList.get(tooth-1);
                rotate(que,dir);
                propaganda(tooth,dir,-1);
                propaganda(tooth,dir,1);
            }

            int result = 0;
            for(int i=0;i<4;i++){
                for (int j = 0; j < queList.get(i).size(); j++) {
                    System.out.print(queList.get(i).get(j) + " ");
                }
                System.out.println();
                result += queList.get(i).get(0);
            }
            System.out.println(result);

        }

        public void rotate(ArrayList<Integer> que,int dir){
            if(dir == 1 ){
                Integer last = que.get(que.size()-1);
                que.remove(que.size()-1);
                que.add(0, last);
            }else{
                Integer first = que.get(0);
                que.add(first);
            }
        }

        private void propaganda(int tooth, int dir,int propagandaDir) {
            if(tooth + propagandaDir == -1 || tooth + propagandaDir == queList.size()){
                return;
            }
            ArrayList<Integer> cur = queList.get(tooth);
            ArrayList<Integer> next = queList.get(tooth + propagandaDir);
            //왼쪽이면 cur의 6과 next의 2
            if(propagandaDir == -1){
                //반대방향으로 회전해야함
                if(cur.get(5) != next.get(1)){
                    rotate(next,dir * -1);
                }
            }else{
                if(cur.get(1) != next.get(5)){
                    rotate(next,dir * -1);
                }
            }

            propaganda(tooth + propagandaDir,dir,propagandaDir);
        }

    }

