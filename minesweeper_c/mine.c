#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <stdbool.h>

#include <curses.h>

void main(){
    initscr();
    int x=10;
    int y=10;
    printf("asdf"); 
    move(100,150);
    refresh();
    printf("abc\n");

    endwin();
    

    while(1){

    }

    return 0;

    int const row = 9;
    int const col = 9;
    int tmp = 0;
    int board[9][9];
    char covered_board[9][9];
    printf("9x9 지뢰찾기 입니다\n");
    init(board,row,col,covered_board);
    board_set(board,row,col);
    print_board(covered_board,row,col);
    show_cursor(board,row,col);
    scanf("%d",&tmp);
}



void show_cursor(int (*board)[9], int row,int col){



}



void print_board(char (*board)[9],int row,int col) {
    int i=0;
    int j=0;
    for(i=0;i<row;i++){
        for(j=0;j<col;j++){
            printf("%c ",board[i][j]);
            if(board[i][j] != -1){
                printf(" ");
            }

        }
        printf("\n");
    }
}




void init(int (*board)[9],int row,int col,char (*covered_board)[9]){
    int i=0;
    int j=0;
    for(i=0;i<row;i++){
        for(j=0;j<col;j++){
            board[i][j]=0;
        }
    }

    for(i=0;i<row;i++){
        for(j=0;j<col;j++){
            covered_board[i][j]='O';
        }   
    }
}


void board_set(int (*board)[9],int row,int col){
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
            around_block_add_count(board,rand_row,rand_col,row);
        }

    }

}

void around_block_add_count(int (*board)[9],int cur_row,int cur_col,int size){
    bool is_allow_left_block = cur_row -1 >= 0;
    bool is_allow_right_block = cur_row +1 <= size -1;
    bool is_allow_up_block = cur_col+1 >= 0;
    bool is_allow_down_block = cur_col-1 <= size-1;
    int left_block = cur_row -1;
    int right_block = cur_row +1;
    int up_block = cur_col +1;
    int down_block = cur_col -1;

    //cur_row -1
    //cur_row +1 ok
    
    if(is_allow_left_block){
        if(board[left_block][cur_col] != -1){
            board[left_block][cur_col] += 1;
        }
    }
    if(is_allow_right_block){
        if(board[right_block][cur_col] != -1){
            board[right_block][cur_col] += 1;
        }
    }
    if(is_allow_up_block){
        if(board[cur_row][up_block] != -1){
            board[cur_row][up_block] +=1;
        }
    }
    if(is_allow_down_block){
        if(board[cur_row][down_block] != -1){
            board[cur_row][down_block] +=1;
        }
    }

    //대각선
    if(is_allow_left_block){
        if(is_allow_up_block){
            if(board[left_block][up_block] != -1){
                board[left_block][up_block] +=1;
            }
        }
        if(is_allow_down_block){
            if(board[left_block][down_block] != -1){
                board[left_block][down_block] +=1;
            }
        }
    }
    if(is_allow_right_block){
        if(is_allow_up_block){
            if(board[right_block][up_block] != -1){
                board[right_block][up_block] +=1;
            }
        }
        if(is_allow_down_block){
            if(board[right_block][down_block] != -1){
                board[right_block][down_block] +=1;
            }
        }
    }
}
