import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

class Main{
    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();

    }

}

class Solve{
    int[] arr;
    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());

        arr = new int[(int) Math.pow(2,N)];
        String[] sArr = bufferedReader.readLine().split(" ");

        for (int i = 0; i < arr.length-1; i++) {
            arr[i] = Integer.parseInt(sArr[i]);
        }
        treeTravel(0,arr.length-1,0);
        StringBuilder stringBuilder = new StringBuilder();
        for(Integer key : depthToStrMap.keySet()){
            stringBuilder.append(depthToStrMap.get(key) + "\n");
        }
        System.out.println(stringBuilder.toString());

    }
    HashMap<Integer,String> depthToStrMap = new HashMap<>();

    private void treeTravel(int start,int end, int depth) {
        if(start >= end) return;
        int mid = (start + end ) /2 ;
        int val = arr[mid];
        treeTravel(start,mid,depth+1);
        depthToStrMap.put(depth,depthToStrMap.getOrDefault(depth,"") + val + " ");
        treeTravel(mid+1,end,depth+1);

    }
}
