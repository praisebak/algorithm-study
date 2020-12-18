#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main(){
    int const row = 9;
    int const col = 9;
    int board[9][9];
    
    printf("9x9 지뢰찾기 입니다\n");
    
    init(board,row,col);
    bomb_set(board,row,col);
    //미구현 폭탄 개수 세서 테이블값 set
    board_set(board,row,col);
    print_board(board,row,col);
    //미구현 커서 기능 넣을 것
    cursor(board,row,col);
    
    return 0;

}

void board_set(int (*board)[9],int row,int col) {
    int i=0;
    int j=0;
    for(i=0;i<row;i++){
        for(j=0;j<col;j++){
            if(board[i][j] != -1){
                if(i-1 != -1){
                    
                }
                if(i != row){
                    
                }

            }


}

void print_board(int (*board)[9],int row,int col) {
    int i=0;
    int j=0;
    for(i=0;i<row;i++){
        for(j=0;j<col;j++){
            printf("%d ",board[i][j]);
            if(board[i][j] != -1){
                printf(" ");
            }

        }
        printf("\n");
    }
}




void init(int (*board)[9],int row,int col){
    int i=0;
    int j=0;
    for(i=0;i<row;i++){
        for(j=0;j<col;j++){
           board[i][j]=0;
        }
    }





}


void bomb_set(int (*board)[9],int row,int col){
    int i=0;
    int j=0;
    srand((int) time(NULL));

    int count = 0;
    int rand_row = 0;
    int rand_col = 0;
    for(count=0;count<10;count++){
        rand_row = (int) rand() % 9;
        rand_col = (int) rand() % 9;
        if(board[rand_row][rand_col] == -1){
            count--;
        }else{
            board[rand_row][rand_col] = -1;
        }
        
    }

}



