package com.hl;

/**
 * Initialize Ray
 */
public class BottomRay {
    //Store location
    static int[] rays = new int[GameUtil.Ray_MAX*2];
    //Location of ray
    int x,y;
    //Whether this location has ray or not
    boolean isLocated = true;
    void newRay(){
        for (int i = 0; i<GameUtil.Ray_MAX*2;i=i+2){
            x=(int) (Math.random()*GameUtil.Map_W+1);//1-12
            y=(int) (Math.random()*GameUtil.Map_H+1);//1-12


            for (int j = 0; j <=i ; j = j+2){
                if (x==rays[j]&&y==rays[j+1]){
                    i=i-2;
                    isLocated=false;
                    break;
                }
            }
            if (isLocated){
                rays[i]=x;
                rays[i+1]=y;
            }
            isLocated=true;
        }

        for (int i = 0; i < GameUtil.Ray_MAX*2;i=i+2){
            GameUtil.Data_BOTTOM[rays[i]][rays[i+1]] = -1;

        }
    }

}
