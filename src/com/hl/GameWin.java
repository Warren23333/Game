package com.hl;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameWin extends JFrame {

    private DrawingPanel drawingPanel;

    MapBottom mapBottom = new MapBottom();
    MapTop mapTop = new MapTop();
    GameSelect gameSelect = new GameSelect();

    boolean begin = false;

    int weight = 2*GameUtil.Offset+GameUtil.Map_W*GameUtil.Square_L;
    int height = 4*GameUtil.Offset+GameUtil.Map_H*GameUtil.Square_L;

    void launch() {
        GameUtil.Start_time=System.currentTimeMillis();
        this.setVisible(true);
        if(GameUtil.state==3){
            this.setSize(500,500);
        }else{
            this.setSize(weight,height);
        }
        this.setLocationRelativeTo(null);
        this.setTitle("扫雷");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        drawingPanel = new DrawingPanel();
        this.add(drawingPanel);
        //mouse event
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                switch (GameUtil.state){
                    case 0 : if(e.getButton()==1){
                        GameUtil.Mouse_X=e.getX();
                        GameUtil.Mouse_Y=e.getY();
                        GameUtil.LEFT=true;
                        }
                        if(e.getButton()==3){
                            GameUtil.Mouse_X=e.getX();
                            GameUtil.Mouse_Y=e.getY();
                            GameUtil.RIGHT=true;
                        }

                    case 1 :
                    case 2 :
                        if(e.getButton()==1){
                            if(e.getX()>GameUtil.Offset+GameUtil.Square_L*(GameUtil.Map_W/2)-40&&
                                    e.getX()<GameUtil.Offset+GameUtil.Square_L*(GameUtil.Map_W/2)+GameUtil.Square_L+30&&
                            e.getY()>GameUtil.Offset&&
                            e.getY()<GameUtil.Offset+GameUtil.Square_L+65){
                                mapBottom.reGame();
                                mapTop.reGame();
                                GameUtil.Flag_num=0;
                                GameUtil.Start_time=System.currentTimeMillis();
                                GameUtil.state=0;
                            }
                            break;
                        }
                    case 3:
                        if(e.getButton()==1) {
                            GameUtil.Mouse_X = e.getX();
                            GameUtil.Mouse_Y = e.getY();
                            begin=gameSelect.hard();
                        }
                    default:
                }
            }
        });

        while(true){
            repaint();
            begin();
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    void begin(){
        if(begin){
            begin=false;
            gameSelect.hard(GameUtil.level);
            dispose();
            GameWin gameWin = new GameWin();
            GameUtil.Start_time=System.currentTimeMillis();
            GameUtil.Flag_num=0;
            mapBottom.reGame();
            mapTop.reGame();
            gameWin.launch();
        }
    }

    public static void main(String[] args) {
        GameWin gameWin = new GameWin();
        gameWin.launch();
    }

    public class DrawingPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            if(GameUtil.state==3){
                gameSelect.printSelf(g);
            }else {
                super.paintComponent(g);
                g.setColor(Color.gray);
                g.fillRect(0, 0, weight, height);
                mapBottom.printSelf(g);
                mapTop.printSelf(g);
            }
        }
    }
}
