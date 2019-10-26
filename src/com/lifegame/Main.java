package com.lifegame;
import javax.swing.JFrame;  

public class Main extends JFrame{
    /**
	 * 
	 */
	private static final long serialVersionUID = 123456L;

	Main(){  
		ChessBoard cb=new ChessBoard();
        cb.showNextChessBoard();
        this.setSize(408,437);  
        this.setTitle("生命游戏[LifeGame]DEMO");  
        this.add(cb);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        this.setLocationRelativeTo(null);  
        this.setResizable(false); 

    }  
      
    public static void main(String[] args){ 
        Main game = new Main();  
        game.setVisible(true);
    }  
}
