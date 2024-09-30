package com.hl;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;


/**
 * Draw the bottom map for the game
 */
public class MapBottom {
    BottomRay bottomRay = new BottomRay();
    BottomNum numBottom = new BottomNum();
    {
        bottomRay.newRay();
        numBottom.newNum();
    }

    void reGame(){
        for (int i=1;i<=GameUtil.Map_W;i++){
            for (int j=1;j<=GameUtil.Map_H;j++){
                GameUtil.Data_BOTTOM[i][j]=0;
            }
        }
        bottomRay.newRay();
        numBottom.newNum();
    }
    void printSelf(Graphics g){
            g.setColor(Color.RED);

        for(int i=0; i <= GameUtil.Map_W; i++){
            g.drawLine(GameUtil.Offset + i*GameUtil.Square_L,
                       GameUtil.Offset*3,
                       GameUtil.Offset + i*GameUtil.Square_L,
                       GameUtil.Offset*3 + GameUtil.Square_L*GameUtil.Map_H);
        }

        for(int i=0; i <= GameUtil.Map_H; i++){
            g.drawLine(GameUtil.Offset,
                    GameUtil.Offset*3 + i*GameUtil.Square_L,
                    GameUtil.Offset + GameUtil.Square_L*GameUtil.Map_W,
                    GameUtil.Offset*3 + i*GameUtil.Square_L);
        }

        for(int i = 1; i <= GameUtil.Map_W; i++){
            for(int j = 1; j <= GameUtil.Map_H; j++){
                if (GameUtil.Data_BOTTOM[i][j] == -1){
                    g.drawImage(GameUtil.lei,
                            GameUtil.Offset + (i-1)*GameUtil.Square_L+1,
                            GameUtil.Offset*3 + (j-1)*GameUtil.Square_L+1,
                            GameUtil.Square_L-2,
                            GameUtil.Square_L-2,
                            null);
                }
            }
        }

        for(int i = 1; i <= GameUtil.Map_W; i++){
            for(int j = 1; j <= GameUtil.Map_H; j++){
                if (GameUtil.Data_BOTTOM[i][j] >= 0){
                    g.drawImage(GameUtil.num[GameUtil.Data_BOTTOM[i][j]],
                            GameUtil.Offset + (i-1)*GameUtil.Square_L+8,
                            GameUtil.Offset*3 + (j-1)*GameUtil.Square_L+8,
                            17,
                            25,
                            null);
                }
            }
        }
        //paint number
        GameUtil.drawWord(g,""+(GameUtil.Ray_MAX-GameUtil.Flag_num),GameUtil.Offset,2*GameUtil.Offset,30,Color.red);
        GameUtil.drawWord(g,""+(GameUtil.End_time-GameUtil.Start_time)/1000,
                GameUtil.Offset+GameUtil.Square_L*(GameUtil.Map_W-1),
                2*GameUtil.Offset,30,Color.red);
        switch (GameUtil.state){
            case 0:
                GameUtil.End_time=System.currentTimeMillis();
                g.drawImage(GameUtil.gaming,
                        GameUtil.Offset+GameUtil.Square_L*(GameUtil.Map_W/2)-40,
                        GameUtil.Offset-30,
                        120,
                        120,
                        null);
                break;
            case 1:
                g.drawImage(GameUtil.victory,
                        GameUtil.Offset+GameUtil.Square_L*(GameUtil.Map_W/2)-40,
                        GameUtil.Offset-30,
                        120,
                        120,
                        null);
                break;
            case 2:
                g.drawImage(GameUtil.fail,
                        GameUtil.Offset+GameUtil.Square_L*(GameUtil.Map_W/2)-40,
                        GameUtil.Offset-30,
                        120,
                        120,
                        null);
                break;
        }
    }
}
