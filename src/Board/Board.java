package Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Board extends Frame {
    private final int SIZE = 8;
    private House[][] board;

    public Board() {
        removeAll();
        setLayout(new GridLayout(SIZE,SIZE));
        this.exitCommand();
        this.createHouses();
        this.renderBoard();
    }

    public void createHouses(){
        board = new House[SIZE][SIZE];
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                board[x][y] = new House(x+1,y+1,this);
                this.add(board[x][y]);
            }
        }
    }

    public void renderBoard(){
        this.setPreferredSize(new Dimension(512,512));
        this.setResizable(false);
        this.setVisible(true);
        this.pack();
    }

    private void exitCommand(){
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event){
                System.exit(0);
            }
        });
    }
}
