package Board;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Piece {
    private Color color;
    private House house;

    Piece(Color color, House house){

        this.house = house;
        this.color = color;
        house.setPiece(this);
    }

    public void validHouses(Board board){
        int coordX = this.house.getCoordX(); int coordY = this.house.getCoordY();

        ArrayList<House> neighborsValid = new ArrayList<>();
        int[][] neighbors = {
                {coordX-1, coordY+1},     //diagonal superior esquerda.
                {coordX-1, coordY-1},     //diagonal inferior esquerda.
                {coordX+1, coordY+1},     //diagonal superior direita.
                {coordX+1, coordY-1},     //diagonal inferior direita.
        };

        for(int[] neighbor : neighbors){
            int x = neighbor[0];
            int y = neighbor[1];
            if (1 <= x && x <= 8 && 1 <= y && y <= 8){ //checa se a casa existe.
                if (this.color==Color.red && y>coordY) {//checa a direção do movimento.
                    if (board.board[x-1][y-1].getPiece()==null) { neighborsValid.add(board.board[x-1][y-1]);} //checa casa livre
                    //if de checagem de ataque.
                }
                if (this.color==Color.gray && y<coordY) {//checa a direção do movimento.
                    if (board.board[x-1][y-1].getPiece()==null) { neighborsValid.add(board.board[x-1][y-1]);} //checa casa livre
                    //if de checagem de ataque.
                }
            }
        }

        for (House house:neighborsValid) {
            house.setBackground(Color.decode("#007F00"));
        }
        board.setHighlightedHouses(neighborsValid);
        board.setSelectedHouse(this.getHouse());
        System.out.println(board.getHighlightedHouses());
    }

    public void move(House house) {
        boolean redTurn = (this.getColor() == Color.red && house.getBoard().getPlayerTurn() != 1);
        boolean grayTurn = (this.getColor() == Color.gray && house.getBoard().getPlayerTurn() == 1);
        if(redTurn || grayTurn) {
            house.setPiece(this);this.setHouse(house); //cria nova posição da peça.
            house.getBoard().getSelectedHouse().setPiece(null); //deleta a antiga posição da casa
            house.getBoard().setHighlightedHouses(null); //reseta as casas destacadas.
            house.getBoard().setSelectedHouse(null); //reseta a casa selecionada.
            house.getBoard().increaseCounter(); //contabiliza a jogada.
        }
        house.getBoard().repaint();
    }

    public Color getColor() {
        return this.color;
    }

    public House getHouse() {
        return this.house;
    }

    public void setHouse(House house) {
        this.house = house;
    }
}
