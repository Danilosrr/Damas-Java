package Board;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class Piece {
    private int coordX;
    private int coordY;
    private Color color;

    Piece(Color color, House house){
        this.coordX = house.getCoordX();
        this.coordY = house.getCoordY();
        this.color = color;
        house.setPiece(this);
    }

    public void showValid(Board board){
        ArrayList<int[]> neighborsValid = new ArrayList<>();
        int[][] neighbors = {
                {this.coordX-1, this.coordY+1},     //diagonal superior esquerda.
                {this.coordX-1, this.coordY-1},     //diagonal inferior esquerda.
                {this.coordX+1, this.coordY+1},     //diagonal superior direita.
                {this.coordX+1, this.coordY-1},     //diagonal inferior direita.
        };

        for(int[] neighbor : neighbors){
            int x = neighbor[0];
            int y = neighbor[1];
            if (1 <= x && x <= 8 && 1 <= y && y <= 8){ //checa se a casa existe.
                if (this.color==Color.red && y>this.coordY) {//checa a direção do movimento.
                    if (board.board[x-1][y-1].getPiece()==null) { neighborsValid.add(neighbor);} //checa casa livre
                    //if de checagem de ataque.
                    System.out.println(x+","+y);
                }
                if (this.color==Color.gray && y<this.coordY) {//checa a direção do movimento.
                    if (board.board[x-1][y-1].getPiece()==null) { neighborsValid.add(neighbor);} //checa casa livre
                    // if de checagem de ataque.
                    System.out.println(x+","+y);
                }
            }
        }
        System.out.print(neighborsValid);
    }

    public void move(Board board) {
    }

    public Color getColor() {
        return this.color;
    }
}
