import java.util.ArrayList;
import java.util.Scanner;

class p14888{
    int[] numbers;
    int[] operators;
    ArrayList<String> list = new ArrayList<>();
    int max = Integer.MIN_VALUE;
    int min = Integer.MAX_VALUE;

    void init(){
        int N;
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        numbers = new int[N];
        operators = new int[4];
        for(int i=0;i<N;i++){
            numbers[i] = sc.nextInt();
        }
         
        //+ - * /
        for(int i=0;i<4;i++){
            operators[i] = sc.nextInt();
        }
    }

    void makeCases(int length,String s,int[] operators){

        if(s.length() == length){
            list.add(s);
            return;
        }

        for(int i=0;i<operators.length;i++){
            if(operators[i] != 0){
                operators[i]--;
                makeCases(length,s+i+"",clone(operators));
                operators[i]++;
            }
        }
    }


    void solve(){
        for(int i=0;i<4;i++){
            if(operators[i] != 0){
                operators[i]--;
                makeCases(this.numbers.length-1,i+"",clone(operators));
                operators[i]++;
            }
        } 

        for(String s : list){
            int[] tmpNumbers = clone(numbers);

            for(int i=0;i<s.length();i++){
                char c = s.charAt(i);
                if(c == '0'){
                    numbers[i+1] = numbers[i] + numbers[i+1];
                }else if(c == '1'){
                    numbers[i+1] = numbers[i] - numbers[i+1];

                }else if(c == '2'){
                    numbers[i+1] = numbers[i] * numbers[i+1];
                    
                }else{
                    numbers[i+1] = numbers[i] / numbers[i+1];
                }
            }
            min = Math.min(numbers[s.length()],min);
            max = Math.max(numbers[s.length()],max);
            numbers = tmpNumbers;
        }
        System.out.println(max);
        System.out.println(min);

    }

    int[] clone(int[] tmpBoard){
        int[] resultBoard = new int[tmpBoard.length];
        for(int i=0;i<tmpBoard.length;i++){
            resultBoard[i] = tmpBoard[i];
        }
        return resultBoard;
    }

    public static void main(String[] args) {
        p14888 ps = new p14888();
        ps.init();
        ps.solve();
    }
}



