package Board;

import java.awt.*;
import java.awt.event.ActionEvent;

public class House extends Button {
    private int coordX;
    private int coordY;
    ;
    private Board board;

    House (int coordX, int coordY, Board board){
        this.coordX = coordX;
        this.coordY= coordY;
        this.board = board;

        setBackground(getColor());
        setPreferredSize(new Dimension(64,64));
        addActionListener((ActionEvent e) -> {
            click();
        });
    }

    public int getCoordX() {
        return coordX;
    }

    public int getCoordY() {
        return coordY;
    }

    public Board getBoard() {
        return board;
    }

    public Color getColor() {
        return (coordX + coordY) % 2 == 0 ? Color.BLACK : Color.WHITE;
    }

    private void click() {
        System.out.printf("[%d,%d]%n",this.getCoordX(),this.getCoordY());
    }
}
