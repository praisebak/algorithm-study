import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;

class Main{

    public static void main(String[] args) throws IOException {
        Solve solve = new Solve();
        solve.solve();
    }
}
class Solve{
    private int N;
    private int M;
    private long x;
    private long y;
    private String objM;

    public void solve() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s = bufferedReader.readLine();
        N = Integer.parseInt(s.split(" ")[0]);
        objM = s.split(" ")[1];

        s = bufferedReader.readLine();
        x = Long.parseLong(s.split(" ")[0]);
        y = Long.parseLong(s.split(" ")[1]);

        //좌표 찾기
        long curSize = (long)Math.pow(2, N);
        findLocation(curSize,objM);

        curY -= y;
        curX += x;



        if(!isValid()) {
            System.out.println(-1);
            return;
        }
        find(curY,curX,curSize);


    }

    StringBuilder stringBuilder = new StringBuilder();
    private void find(long curY, long curX, long curSize) {
        if(curSize == 1){
            System.out.println(stringBuilder);
            return;
        }
        long newSize = curSize / 2;
        if(curX -newSize < 0 && curY - newSize < 0){
            stringBuilder.append("2");
            find(curY,curX,newSize);
        } else if(curX -newSize < 0 && curY - newSize >= 0){
            stringBuilder.append("3");
            find(curY-newSize,curX,newSize);
        }else if(curX -newSize >= 0 && curY - newSize >= 0){
            stringBuilder.append("4");
            find(curY-newSize,curX - newSize,newSize);
        }else{
            stringBuilder.append("1");
            find(curY,curX - newSize,newSize);
        }


    }

    private boolean isValid() {
        if(curY < 0 || curY >= Math.pow(2,N)) return false;
        if(curX < 0 || curX >= Math.pow(2,N)) return false;
        return true;
    }

    long curY = 0;
    long curX = 0;
    private void findLocation(long curSize, String obj) {
        for(int i=0;i<obj.length();i++){
            curSize /= 2;
            char cur = obj.charAt(i);
            if(cur == '1'){
                curX += curSize;
            }else if(cur == '3'){
                curY += curSize;
            }else if(cur== '4'){
                curX += curSize;
                curY += curSize;
            }
        }
    }

}
