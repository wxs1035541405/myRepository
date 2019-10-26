package com.lifegame;
import java.awt.Graphics;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

public class ChessBoard extends JPanel{
	private final static long serialVersionUID=1234546L;
	private final static int LENGTH=20;								// ���̳���
	private final static int WIDTH=20;								// ���̿��
	private final static char YES='*';								// ��*����ʾϸ�����
	private final static char NONE=' ';								// ' '��ʾϸ������
	private char[][]chessBoard=new char[LENGTH][WIDTH];				// ��������
	private char[][]tempBoard=new char[LENGTH][WIDTH];
	private static int count=0;
	public ChessBoard(){											// �޲������캯��
		// ��ʼ������
		super();
    	tempBoard[3][3]=tempBoard[3][4]=tempBoard[3][5]=tempBoard[4][4]='*';	
    	tempBoard[3][14]=tempBoard[3][15]=tempBoard[3][16]=tempBoard[4][15]='*';
    	tempBoard[5][3]=tempBoard[5][4]=tempBoard[5][5]=tempBoard[6][4]='*';
    	tempBoard[5][14]=tempBoard[5][15]=tempBoard[5][16]=tempBoard[6][15]='*';
    	tempBoard[14][3]=tempBoard[14][4]=tempBoard[14][5]=tempBoard[13][4]='*';
    	tempBoard[14][14]=tempBoard[14][15]=tempBoard[14][16]=tempBoard[13][15]='*';
    	tempBoard[16][3]=tempBoard[16][4]=tempBoard[16][5]=tempBoard[15][4]='*';
    	tempBoard[16][14]=tempBoard[16][15]=tempBoard[16][16]=tempBoard[15][15]='*';
    	chessBoard=tempBoard;
	}
	
    /** 
     * ��ͼ�ν��� 
     *  
     */  
    protected void paintComponent(final Graphics graphics) {  
        super.paintComponent(graphics);  
        for (int i = 0; i < WIDTH; i++) {  
            for (int j = 0; j < LENGTH; j++) {  
                if (chessBoard[i][j] == YES) {  
                    graphics.fillRect( j * LENGTH, i * WIDTH, LENGTH, WIDTH);  
                   
                } else {  
                	 graphics.drawRect( j * LENGTH, i * WIDTH, LENGTH, WIDTH); 
                }  
            }  
        } 
    } 
	
	/**
	 *  �жϸ�ϸ���Ƿ���
	 * 
	 */
	public int cellState(final int row,final int col){
		int result=0;
		if(chessBoard[row][col]==YES){
			result=1;
		}
		return result;
	}
	
	/**
	 * �ж��ھӻ�ϸ������
	 * 
	 */
	public int getNeighbors(final int row,final int col){
		int count=0;
		if(row>0&&row<WIDTH-1&&col>0&&col<LENGTH-1){
		count+=cellState(row-1,col-1);
		count+=cellState(row-1,col);
		count+=cellState(row-1,col+1);
		count+=cellState(row,col-1);
		count+=cellState(row,col+1);
		count+=cellState(row+1,col-1);
		count+=cellState(row+1,col);
		count+=cellState(row+1,col+1);
		}
		if(row==0&&col==0){
			count+=cellState(row+1,col);
			count+=cellState(row+1,col+1);
			count+=cellState(row,col+1);
		}
		if(row==WIDTH-1&&col==LENGTH-1){
			count+=cellState(row,col-1);
			count+=cellState(row-1,col-1);
			count+=cellState(row-1,col);
		}
		if(row==0&&col>0&&col<LENGTH-1){
			count+=cellState(row,col-1);
			count+=cellState(row,col+1);
			count+=cellState(row+1,col-1);
			count+=cellState(row+1,col);
			count+=cellState(row+1,col+1);
		}
		if(row==WIDTH-1&&col>0&&col<LENGTH-1){
			count+=cellState(row,col-1);
			count+=cellState(row,col+1);
			count+=cellState(row-1,col-1);
			count+=cellState(row-1,col);
			count+=cellState(row-1,col+1);
		}
		if(col==0&&row>0&&row<WIDTH-1){
			count+=cellState(row-1,col);
			count+=cellState(row-1,col+1);
			count+=cellState(row,col+1);
			count+=cellState(row+1,col+1);
			count+=cellState(row+1,col);
		}
		if(col==LENGTH-1&&row>0&&row<WIDTH-1){
			count+=cellState(row-1,col);
			count+=cellState(row-1,col-1);
			count+=cellState(row,col-1);
			count+=cellState(row+1,col-1);
			count+=cellState(row+1,col);
		}
		return count;
	}
	
	/**
	 * �ж�ϸ����һ��״̬
	 * 
	 */
	public void nextChessBoard(){
		char[][]temp=new char[LENGTH][WIDTH];
		for(int i=0;i<WIDTH;i++){
			for(int j=0;j<LENGTH;j++){
				final int neighbors=getNeighbors(i,j);
				if(neighbors==3){
					temp[i][j]=YES;
				}else if(neighbors==2&&chessBoard[i][j]==YES){
					temp[i][j]=YES;
				}else{
					temp[i][j]=NONE;
				}
			}
		}
		chessBoard=temp;
	}
	
	/**
	 * չʾ��һ�׶�����
	 * 
	 */
    public void showNextChessBoard(){
    	final Timer timer=new Timer();
    	timer.schedule(new TimerTask(){
    		public void run(){
    			if(count++>0){
    				nextChessBoard();
    			}
       			repaint();
    		}
    	},0,800);
    }
 
    public char[][] getChessBoard(){
    	char[][]chessBoard1=new char[LENGTH][WIDTH];
    	chessBoard1=chessBoard;
    	return chessBoard1;
    }
    
    public char[][] getTempBoard(){
    	char[][]tempBoard1=new char[LENGTH][WIDTH];
    	tempBoard1=tempBoard;
    	return tempBoard1;
    }

    
    public void changeBoard(){
    	this.chessBoard=this.tempBoard;
    }
    
	/* ����̨չʾ�������
	public void print(){
		for(int i=0;i<WIDTH;i++){
			for(int j=0;j<LENGTH;j++){
				char c=(chessBoard[i][j]==Y)?Y:N;
				System.out.print(c);
			}
			System.out.println("");
		}
	}
	*/
    
	/* ����̨չʾ������Ϸ������
	public static void main(String[]args){
		final ChessBoard chessboard=new ChessBoard();
		Timer timer = new Timer();
		timer.schedule(new TimerTask(){
			public void run(){
				chessboard.print();
				chessboard.nextChessBoard();
			}
		},0,500);
	}
*/
}
