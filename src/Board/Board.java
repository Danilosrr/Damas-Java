package Board;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Board extends Frame {
    private final int SIZE = 8;
    House[][] board;

    public Board() {
        removeAll();
        setLayout(new GridLayout(SIZE,SIZE));
        this.exitCommand();
        this.createHouses();
        this.createPieces();
        this.renderBoard();
    }

    private void createHouses() {
        board = new House[SIZE][SIZE];
        for (int y = 8; y >=1; y--) {
            for (int x = 0; x < SIZE; x++) {
                board[x][y-1] = new House(x+1,y,this);
                this.add(board[x][y-1]);
            }
        }
    }

    private void createPieces(){
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                if(y<3 && (x+y)%2==0) {
                    board[x][y].setPiece(new Piece(Color.red, board[x][y]));
                }
                else if(y>4 && (x+y)%2==0){
                    board[x][y].setPiece(new Piece(Color.gray, board[x][y]));
                }
            }
        }
    }

    private void renderBoard(){
        this.setPreferredSize(new Dimension(512,512));
        this.setResizable(false);
        this.setVisible(true);
        this.pack();
    }

    public void resetBoardColor(){
        for (House[] houses: board) {
            for (House house: houses) {
                house.setBackground(house.getColor());
            }
        }
    }

    private void exitCommand(){
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event){
                System.exit(0);
            }
        });
    }
}
