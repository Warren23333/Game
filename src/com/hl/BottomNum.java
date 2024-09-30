package com.hl;

/**
 * number
 */
public class BottomNum {
    void newNum(){
        for (int i = 1; i<=GameUtil.Map_W; i ++){
            for (int j = 1;j<=GameUtil.Map_H;j++){
                if (GameUtil.Data_BOTTOM[i][j]==-1){
                    for (int k = i-1;k<=i+1;k++){
                        for (int l = j-1;l<=j+1;l++){
                            if (GameUtil.Data_BOTTOM[k][l]>=0){
                                GameUtil.Data_BOTTOM[k][l]++;
                            }
                        }
                    }
                }
            }
        }
    }
}
