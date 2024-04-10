package newneo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


class B7682 {

    int dotCount = 0;
    int xCount = 0;
    int oCount = 0;
    char[][] map = new char[3][3];
    public void solve() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        String line = bufferedReader.readLine();
        //종료아니면
        while (!line.equals("end")){
            xCount = 0;
            oCount = 0;
            dotCount = 0;
            set2DArr(line);

            //System.out.println(xCount + "," + oCount);

            //둘다 이기는경우는 없음
            if(isWin('X') && isWin('O')){
                sb.append("invalid\n");
            }else{
                //다 찼음
                if(xCount + oCount == 9){
                    if(oCount != 4){
                        sb.append("invalid\n");
                    }else if(isWin('X') && xCount == 5) {
                        //System.out.println("1번케이스");
                        sb.append("valid\n");
                    }else if(isWin('O')){
                        sb.append("invalid\n");
                    }else{
                        sb.append("valid\n");
                    }
                }else{

                    //X가 이긴 경우
                    if(xCount == oCount+1 && isWin('X')){
                        //System.out.println("3번케이스");
                        sb.append("valid\n");
                    //o가 이긴 경우
                    }else if(oCount == xCount && isWin('O')){
                        //System.out.println("4번케이스");
                        sb.append("valid\n");
                    }else{
                        //System.out.println("5번케이스");
                        sb.append("invalid\n");
                    }
                }

            }
            line = bufferedReader.readLine();

        }
        System.out.println(sb.toString());
    }

    private boolean isWin(char ch) {

        //System.out.println("현재=" + ch );
//        for(int i=0;i<3;i++){
//            for (int j = 0; j < 3; j++) {
//                //System.out.print(map[i][j] + " ");
//            }
//            //System.out.println();
//        }

        //가로
        for(int i=0;i<3;i++){
            int count =0;
            for (int j = 0; j <3 ; j++) {
                if(ch == map[i][j]){
                    count++;
                }
            }
            if(count == 3){
                //System.out.println("가로");
                return true;
            }
        }

        //세로
        for(int i=0;i<3;i++){
            int count =0;
            for (int j = 0; j <3 ; j++) {
                if(ch == map[j][i]){
                    count++;
                }
            }
            if(count == 3){
                //System.out.println("세로");

                return true;
            }
        }

        //대각선
        int count = 0;
        //map[0][0] [1][1] [2][2]
        for (int j = 0; j < 3; j++) {
            if(map[j][j] == ch) {
                count++;
            }
        }

        if(count == 3){
            //System.out.println("대각선1");


            return true;
        }

        count = 0;
        for (int j = 0; j < 3; j++) {
            if(map[j][2-j] == ch) {
                count++;
            }
        }

        if(count == 3){
            for(int i=0;i<3;i++){
                for (int j = 0; j < 3; j++) {
                    //System.out.print(map[i][j] + " ");
                }
                //System.out.println();
            }
            //System.out.println("대각선2");
            return true;
        }

        return false;
    }

    public boolean isValid(int i,int j){
        if((i < 3 && i >= 0) && (j < 3 && j >= 0 )) return true;
        return false;
    }

    private void set2DArr(String line) {
        int count = 0;

        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                char ch = line.charAt(count);
                if(ch == '.'){
                    dotCount++;
                }else if(ch == 'X'){
                    xCount++;
                }else{
                    oCount++;
                }
                map[i][j] = line.charAt(count++);
            }
        }
    }
    
}

