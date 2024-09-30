package com.hl;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Difficulty selection
 */
public class GameSelect {

    boolean hard(){
        if(GameUtil.Mouse_X>100&&GameUtil.Mouse_X<400){
            if(GameUtil.Mouse_Y>50&&GameUtil.Mouse_Y<150){
                GameUtil.level=1;
                GameUtil.state=0;
                return true;
            }
            if(GameUtil.Mouse_Y>200&&GameUtil.Mouse_Y<300){
                GameUtil.level=2;
                GameUtil.state=0;
                return true;
            }
            if(GameUtil.Mouse_Y>350&&GameUtil.Mouse_Y<450){
                GameUtil.level=3;
                GameUtil.state=0;
                return true;
            }
        }
        return false;
    }
    void printSelf(Graphics g){
        g.drawRect(100,50,300,100);
        GameUtil.drawWord(g,"Easy",220,100,30, Color.black);

        g.drawRect(100,200,300,100);
        GameUtil.drawWord(g,"Normal",220,250,30, Color.black);

        g.drawRect(100,350,300,100);
        GameUtil.drawWord(g,"Hard",220,400,30, Color.black);
    }

    void hard(int level){
        switch (level){
            case 1:
                GameUtil.Ray_MAX=11;
                GameUtil.Map_W=9;
                GameUtil.Map_H=9;
                break;
            case 2:
                GameUtil.Ray_MAX=40;
                GameUtil.Map_W=16;
                GameUtil.Map_H=16;
                break;
            case 3:
                GameUtil.Ray_MAX=99;
                GameUtil.Map_W=30;
                GameUtil.Map_H=16;
                break;
            default:
        }
    }
}
