    package com.study;

    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.StringTokenizer;

    public class B20061
    {
        public static void main(String[] args) throws IOException
        {
            SolveB20061 solveB20061 = new SolveB20061();
            solveB20061.init();
        }
    }

    class SolveB20061{
        int N;
        int[][] blue = new int[4][6];
        int[][] green = new int[6][4];
        int answer = 0;

        void init() throws IOException
        {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            N = Integer.parseInt(stringTokenizer.nextToken());

            for(int i=1;i<=N;i++){
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                int t =Integer.parseInt(stringTokenizer.nextToken());
                int x =Integer.parseInt(stringTokenizer.nextToken());
                int y =Integer.parseInt(stringTokenizer.nextToken());

                if(t == 1){
                    moveOnly(x,y,i);
                }else if(t == 2){
                    move(x,y,i,t);
                }else{
                    move(x,y,i,t);
                }
                checkBlue();
                checkGreen();
            }
            calAnswer();
        }

        private void calAnswer()
        {
            int result = 0;
            for(int i=0;i<6;i++){
                for(int j=0;j<4;j++){
                    if(blue[j][i] != 0) result++;
                    if(green[i][j] != 0) result++;
                }
            }

            System.out.println(answer);
            System.out.println(result);
        }

        private void checkBlue()
        {

            for(int j=5;j>=2;j--){
                boolean isSkip = false;
                for(int i=0;i<4;i++){
                    if(blue[i][j] == 0) isSkip = true;
                }
                if(!isSkip){
                    answer++;
                    leftToRight(j);
                    j++;
                }
            }

            int count = 0;
            if((count = checkHazyBlue()) != 0){
                for (int i = 5; i >= 2; i--)
                {
                    for (int j = 0; j < 4; j++)
                    {
                        blue[j][i] = blue[j][i - count];
                    }
                }

                for (int i = 0; i < 2; i++)
                {
                    for (int j = 0; j < 4; j++)
                    {
                        blue[j][i] = 0;
                    }
                }
            }

        }



        private int checkHazyBlue(){
            int count = 0;
            for(int i=0;i<2;i++){
                for (int j = 0; j < 4; j++)
                {
                    if(blue[j][i] != 0){
                        count++;
                        break;
                    }
                }
            }
            return count;
        }

        private int checkHazyGreen(){
            int count = 0;
            for(int i=0;i<2;i++){
                for (int j = 0; j < 4; j++)
                {
                    if(green[i][j] != 0){
                        count++;
                        break;
                    }
                }
            }
            return count;
        }

        private void checkGreen(){
            for(int i=5;i>=2;i--){
                boolean isSkip = false;
                for(int j=0;j<4;j++){
                    if(green[i][j] == 0) isSkip = true;
                }
                //이거 다 지우고 땡겨와야한디
                if(!isSkip){
                    answer++;
                    upToDown(i);
                    i++;
                }
            }


            int count = 0;
            if((count = checkHazyGreen()) != 0){
                for (int i = 5; i >= 2; i--)
                {
                    for (int j = 0; j < 4; j++)
                    {
                        green[i][j] = green[i - count][j];
                    }
                }

                for (int i = 0; i < 2; i++)
                {
                    for (int j = 0; j < 4; j++)
                    {
                        green[i][j] = 0;
                    }
                }
            }
            

        }

        private void leftToRight(int startIdx)
        {
            for(int j = startIdx; j > 0; j--) {
                for(int i = 0; i < 4; i++) {
                    blue[i][j] = blue[i][j-1];
                }
            }
        }

        private void upToDown(int startIdx){
            for(int i = startIdx; i > 0; i--) {
                for(int j = 0; j < 4; j++) {
                    green[i][j] = green[i-1][j];
                }
            }
        }

        private void moveOnly(int x, int y, int i)
        {
            moveRightOne(x,y,i);
            moveDownOne(x,y,i);
        }

        void move(int x,int y,int val,int t){
            moveRight(x,y,val,t);
            moveDown(x,y,val,t);
        }

        private void moveRightOne(int x,int y,int val){
            int pX = x;
            int pY = 0;
            y = 0;

            while (true){
                if(isValid(x,y+1,blue)){
                    y++;
                    pY =y;
                }else{
                    break;
                }
            }
            blue[pX][pY] = val;
        }

        private void moveDownOne(int x,int y,int val){
            int pX = 0;
            int pY = y;
            x = 0;
            while (true){
                if(isValid(x+1,y,green)){
                    x++;
                    pX = x;
                }else{
                    break;
                }
            }
            green[pX][pY] = val;
        }

        private void moveDown(int x, int y,int val,int t)
        {
            int prevAY = y;
            int prevAX = 0;
            int prevBY = y+1;
            int prevBX = 0;
            //세로
            if(t == 3){
                prevBY = y;
                prevBX = 1;
            }

            int nAY = prevAY;
            int nAX = prevAX;
            int nBY = prevBY;
            int nBX = prevBX;

            while(true){
                nAX++;
                nBX++;
                if(isValid(nAX,nAY,green) && isValid(nBX,nBY,green)){
                    prevAX = nAX;
                    prevBX = nBX;
                }else{
                    break;
                }
            }
            green[prevAX][prevAY] = val;
            green[prevBX][prevBY] = val;
        }

        private void moveRight(int x, int y,int val,int t)
        {
            int prevAY = 0;
            int prevAX = x;
            //가로
            int prevBY = 1;
            int prevBX = x;
            //세로
            if(t == 3){
                prevBY = 0;
                prevBX = x+1;
            }

            int nAY = prevAY;
            int nAX = prevAX;
            int nBY = prevBY;
            int nBX = prevBX;

            while(true){
                nAY++;
                nBY++;
                if(isValid(nAX,nAY,blue) && isValid(nBX,nBY,blue)){
                    prevAY = nAY;
                    prevBY = nBY;
                }else{
                    break;
                }
            }
            blue[prevAX][prevAY] = val;
            blue[prevBX][prevBY] = val;
        }

        private boolean isValid(int aX, int aY,int[][] compArr)
        {
            if(aX >= compArr.length || aY >= compArr[0].length){
                return false;
            }
            if(compArr[aX][aY] != 0){
                return false;
            }
            return true;
        }


    }
