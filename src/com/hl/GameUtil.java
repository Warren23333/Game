package com.hl;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

/**
 * static parameters or methods
 */

public class GameUtil {

    //Number of Ray
    static int Ray_MAX = 100;
    //Height for map
    static int Map_H = 17;
    //Weight for map
    static int Map_W = 36;
    //Offset
    static int Offset = 45;
    //Length of squares
    static int Square_L = 35;
    //list to store data. -1 for ray ; 0 for empty; 1-8 for number
    static int [][] Data_BOTTOM = new int [Map_W+2][Map_H+2];
    //list to store data. -1 for not covered ; 0 for covered; 1 for flag ; 2 for wrong flag
    static int [][] Data_TOP = new int [Map_W+2][Map_H+2];
    //Mouse element
    //Location
    static int Mouse_X;
    static int Mouse_Y;
    //state
    static boolean LEFT = false;
    static boolean RIGHT = false;
    //game state  0 for gaming  1 for victory   2 for fail 3 for difficulty choose
    static int state=3;
    //number of flag
    static int Flag_num = 0;
    //time counter
    static long Start_time;
    static long End_time;
    //difficulty
    static int level;

    //Add images
    static Image lei = Toolkit.getDefaultToolkit().getImage("/Users/senorita/Java game/Minesweeper/imgs/lei.png");
    static Image top = Toolkit.getDefaultToolkit().getImage("/Users/senorita/Java game/Minesweeper/imgs/top.gif");
    static Image flag = Toolkit.getDefaultToolkit().getImage("/Users/senorita/Java game/Minesweeper/imgs/flag.gif");
    static Image noflag = Toolkit.getDefaultToolkit().getImage("/Users/senorita/Java game/Minesweeper/imgs/noflag.png");
    static Image victory = Toolkit.getDefaultToolkit().getImage("/Users/senorita/Java game/Minesweeper/imgs/victory.png");
    static Image fail = Toolkit.getDefaultToolkit().getImage("/Users/senorita/Java game/Minesweeper/imgs/fail.png");
    static Image gaming = Toolkit.getDefaultToolkit().getImage("/Users/senorita/Java game/Minesweeper/imgs/gaming.png");
    static Image[] num = new Image[9];
    static {
        for (int i = 0;i<=8;i++){
            num[i] = Toolkit.getDefaultToolkit().getImage("/Users/senorita/Java game/Minesweeper/imgs/num/"+i+".png");
        }
    }
    static void drawWord(Graphics g, String str,int x,int y,int size, Color color){
        g.setColor(color);
        g.setFont(new Font("仿宋",Font.BOLD,size));
        g.drawString(str,x,y);
    }
}
