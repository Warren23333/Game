package com.hl;

import java.awt.Graphics;

/**
 * Top map drawing.
 */
public class MapTop {
    int temp_x;
    int temp_y;

    void reGame(){
        for (int i=1;i<=GameUtil.Map_W;i++){
            for (int j=1;j<=GameUtil.Map_H;j++){
                GameUtil.Data_TOP[i][j]=0;
            }
        }
    }

    void logic(){
        temp_x=0;
        temp_y=0;
        if(GameUtil.Mouse_X>GameUtil.Offset&&GameUtil.Mouse_Y>GameUtil.Offset*3.7){
            temp_x=(GameUtil.Mouse_X-GameUtil.Offset)/GameUtil.Square_L+1;
            temp_y=(GameUtil.Mouse_Y-(int)(3.7*GameUtil.Offset))/GameUtil.Square_L+1;
        }
        if(temp_x>=1 && temp_x<=GameUtil.Map_W
           && temp_y>=1 && temp_y<=GameUtil.Map_H){
            if(GameUtil.LEFT==true){
               if(GameUtil.Data_TOP[temp_x][temp_y]==0){
                   GameUtil.Data_TOP[temp_x][temp_y]=-1;
               }
               spaceOpen(temp_x,temp_y);
                GameUtil.LEFT=false;
            }
            if(GameUtil.RIGHT==true){
                if(GameUtil.Data_TOP[temp_x][temp_y]==0){
                    GameUtil.Data_TOP[temp_x][temp_y]=1;
                    GameUtil.Flag_num++;
                } else if (GameUtil.Data_TOP[temp_x][temp_y]==1) {
                    GameUtil.Data_TOP[temp_x][temp_y]=0;
                    GameUtil.Flag_num--;
                } else if (GameUtil.Data_TOP[temp_x][temp_y]==-1) {
                    numberOpen(temp_x,temp_y);
                }
                GameUtil.RIGHT =false;
            }
        }
        boom();
        victory();
    }

    //true for fail, false for not
    boolean boom(){
//            if(GameUtil.Flag_num==GameUtil.Ray_MAX){
//                for(int i =1;i<=GameUtil.Map_W;i++){
//                    for(int j =1;j<=GameUtil.Map_H;j++){
//                        if(GameUtil.Data_TOP[i][j]==0){
//                            GameUtil.Data_TOP[i][j]=-1;
//                        }
//                    }
//                }
//            }
        for(int i =1;i<=GameUtil.Map_W;i++){
            for(int j =1;j<=GameUtil.Map_H;j++){
                if(GameUtil.Data_BOTTOM[i][j]==-1&&GameUtil.Data_TOP[i][j]==-1){
                    GameUtil.state=2;
                    seeBoom();
                    return true;
                }
            }
        }
        return false;
    }


    void seeBoom() {
        for (int i = 1; i <= GameUtil.Map_W; i++) {
            for (int j = 1; j <= GameUtil.Map_H; j++) {
                if (GameUtil.Data_BOTTOM[i][j] == -1 && GameUtil.Data_TOP[i][j] != 1) {
                    GameUtil.Data_TOP[i][j] = -1;
                }
                if (GameUtil.Data_BOTTOM[i][j] != -1 && GameUtil.Data_TOP[i][j] == 1) {
                    GameUtil.Data_TOP[i][j] = 2;
                }
            }
        }
    }
    //open the surrounding blocks for number
    void numberOpen(int x,int y) {
        int acc = 0;
        if (GameUtil.Data_BOTTOM[x][y] > 0) {
            for (int i = x - 1; i <= x + 1; i++) {
                for (int j = y - 1; j <= y + 1; j++) {
                    if (GameUtil.Data_TOP[i][j] == 1) {
                        acc++;
                    }
                }
            }
            if (acc == GameUtil.Data_BOTTOM[x][y]) {
                for (int i = x - 1; i <= x + 1; i++) {
                    for (int j = y - 1; j <= y + 1; j++) {
                        if (GameUtil.Data_TOP[i][j] != 1) {
                            GameUtil.Data_TOP[i][j] = -1;
                        }
                        if(i>=1&&j>=1&&i<=GameUtil.Map_W&&j<=GameUtil.Map_H){
                            spaceOpen(i,j);
                        }
                    }
                }
            }
        }
    }

    //true for victory; false for not
    boolean victory(){
        int acc=0;
        for(int i =1;i<=GameUtil.Map_W;i++){
            for(int j =1;j<=GameUtil.Map_H;j++){
                if(GameUtil.Data_TOP[i][j]!=-1){
                    acc++;
                }
            }
        }
        if (acc==GameUtil.Ray_MAX) {
            GameUtil.state=1;
            for (int i = 1; i <= GameUtil.Map_W; i++) {
                for (int j = 1; j <= GameUtil.Map_H; j++) {
                    if(GameUtil.Data_TOP[i][j]==0){
                        GameUtil.Data_TOP[i][j]=1;
                    }
                }
            }
            return true;
        }
        return false;
    }

    //open the  surrounding blocks
    void spaceOpen(int x,int y){
        if(GameUtil.Data_BOTTOM[x][y]==0){
            for(int i=x-1;i<=x+1;i++){
                for(int j =y-1;j<=y+1;j++){
                    if(GameUtil.Data_TOP[i][j]!=-1){
                        if(GameUtil.Data_TOP[i][j]==1){GameUtil.Flag_num--;}
                        GameUtil.Data_TOP[i][j]=-1;
                        if(i>=1&&j>=1&&i<=GameUtil.Map_W&&j<=GameUtil.Map_H){
                            spaceOpen(i,j);
                        }
                    }
                }
            }
        }
    }
    void printSelf(Graphics g){
        logic();
        for(int i = 1; i <= GameUtil.Map_W; i++){
            for(int j = 1; j <= GameUtil.Map_H; j++){
                //covered ray
                if (GameUtil.Data_TOP[i][j] == 0){
                    g.drawImage(GameUtil.top,
                            GameUtil.Offset + (i-1)*GameUtil.Square_L+1,
                            GameUtil.Offset*3 + (j-1)*GameUtil.Square_L+1,
                            GameUtil.Square_L-2,
                            GameUtil.Square_L-2,
                            null);
                }
                //flag
                if (GameUtil.Data_TOP[i][j] == 1){
                    g.drawImage(GameUtil.flag,
                            GameUtil.Offset + (i-1)*GameUtil.Square_L+1,
                            GameUtil.Offset*3 + (j-1)*GameUtil.Square_L+1,
                            GameUtil.Square_L-2,
                            GameUtil.Square_L-2,
                            null);
                }
                //noflag
                if (GameUtil.Data_TOP[i][j] == 2){
                    g.drawImage(GameUtil.noflag,
                            GameUtil.Offset + (i-1)*GameUtil.Square_L+1,
                            GameUtil.Offset*3 + (j-1)*GameUtil.Square_L+1,
                            GameUtil.Square_L-2,
                            GameUtil.Square_L-2,
                            null);
                }
            }
        }
    }
}
