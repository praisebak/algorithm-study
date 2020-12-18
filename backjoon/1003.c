#include <stdio.h>

int check_zero_arr[41];
int check_one_arr[41];
int main(){
    int num=0;
    int repeat=0;
    int i=0;
    int n=0;
    int test_n = 0;
    init_check_arr();
    
    scanf("%d",&test_n);
    
    for(i=0;i<test_n;i++){
        scanf("%d",&n);
        fibonacci(n);
        printf("%d %d\n",check_zero_arr[n],check_one_arr[n]);
        
    }
    return 0;

}

//두 arr의 인덱스 i에는
//n=i일때 각 요소가 몇개 나왔는지를 저장해둔다
//ex) n=2이면 check_zero_arr[2]에는 n=2일때 check_zero_arr가 몇개나왔는지

void init_check_arr(){
    int i=0;
    for(i=0;i<41;i++){
        check_zero_arr[i] = 0;
        check_one_arr[i] = 0;
    }
        
    
    

}
int fibonacci(int n){
    if(n==0){
       check_zero_arr[0] = 1;

   }else if (n==1){
       check_one_arr[1] = 1;
   }else{
       //무조건 n>=2
	   if(check_zero_arr[n-1] == 0){
           fibonacci(n-1);
       } 
       if(check_one_arr[n-2] == 0){
           fibonacci(n-2);
       }
       check_zero_arr[n] = check_zero_arr[n-1] + check_zero_arr[n-2];
       check_one_arr[n] = check_one_arr[n-1] + check_one_arr[n-2];
   }


    


    
}
