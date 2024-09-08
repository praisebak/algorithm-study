import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
    public static void main(String[] args)  throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }
}
class Solve{
    int N;
    int[] inOrder;
    int[] postOrder;
    private int[] tree;

    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s = bufferedReader.readLine();
        N = Integer.parseInt(s);
        inOrder = new int[N];
        s = bufferedReader.readLine();
        StringTokenizer stringTokenizer = new StringTokenizer(s," ");

        tree = new int[N];
        for(int i=0;i<N;i++){
            int num = Integer.parseInt(stringTokenizer.nextToken());
            inOrder[i] = num;
        }

        postOrder = new int[N];

        s = bufferedReader.readLine();
        stringTokenizer = new StringTokenizer(s," ");

        for(int i=0;i<N;i++){
            int num = Integer.parseInt(stringTokenizer.nextToken());
            postOrder[i] = num;
        }


        divideAndConquer(0,N-1,0,N-1);
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < N; i++) {
            stringBuilder.append(tree[i] + " ");
        }
        System.out.println(stringBuilder);
    }

    int idx = 0;

    private void divideAndConquer(int postStart, int postEnd ,int inStart,int inEnd) {
        if(postStart <= postEnd && inStart <= inEnd){
            int root = postOrder[postEnd];
            tree[idx++] = root;
            int rootIdx = 0;

            //find root at inOrder
            for(int i = inStart;i<=inEnd;i++){
                if(inOrder[i] == root){
                    rootIdx= i;
                    break;
                }
            }


            divideAndConquer(postStart,postStart + rootIdx -inStart-1,inStart,rootIdx-1);
            divideAndConquer(postStart + rootIdx - inStart,postEnd-1,rootIdx+1,inEnd);
        }

    }
}
