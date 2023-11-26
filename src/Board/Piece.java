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
        ArrayList<House> validMoves = new ArrayList<>();
        ArrayList<House> validAttacks = new ArrayList<>();

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
                boolean grayTurn = (this.color==Color.gray && y<coordY && house.getBoard().getPlayerTurn()==1);
                boolean redTurn = (this.color==Color.red && y>coordY && house.getBoard().getPlayerTurn()!=1);

                if (redTurn || grayTurn) {
                    if (board.board[x-1][y-1].getPiece()==null) { validMoves.add(board.board[x-1][y-1]);} //checa casa livre
                    else {
                        int attackX = x+(x-coordX)-1; int attackY = y+(y-coordY)-1;
                        boolean validHouse = (1 <= attackX && attackX < 8) && (1 <= attackY && attackY < 8); //checa se a casa existe.
                        boolean validPiece = board.board[x-1][y-1].getPiece().getColor()!=this.getColor(); //checa se a peça é do oponente.
                        if (validPiece && validHouse && board.board[attackX][attackY].getPiece()==null) {
                            validMoves.add(board.board[attackX][attackY]);
                            validAttacks.add(board.board[attackX][attackY]);
                        } //checa ataque
                    }
                }
            }
        }

        for (House house:validMoves) {
            house.setBackground(Color.decode("#007F00"));
        }
        board.setHighlightedHouses(validMoves);
        board.setHighlightedAttacks(validAttacks);
        board.setSelectedHouse(this.getHouse());
        System.out.println(board.getHighlightedHouses());
    }

    public void move(House house) {
        Board board = house.getBoard();
        boolean redTurn = (this.getColor() == Color.red && house.getBoard().getPlayerTurn() != 1);
        boolean grayTurn = (this.getColor() == Color.gray && house.getBoard().getPlayerTurn() == 1);
        if(redTurn || grayTurn) {
            house.setPiece(this);this.setHouse(house); //cria nova posição da peça.
            board.getSelectedHouse().setPiece(null); //deleta a antiga posição da casa
            board.setHighlightedHouses(null); //reseta as casas destacadas.
            board.setSelectedHouse(null); //reseta a casa selecionada.
            board.increaseCounter(); //contabiliza a jogada.
        }
        board.repaint();
    }

    public void attack(House house) {
        int deleteX = (this.getHouse().getCoordX()+house.getCoordX())/2 - 1; //encontra coordenada x da casa entre o movimento
        int deleteY = (this.getHouse().getCoordY()+house.getCoordY())/2 - 1; //encontra coordenada y da casa entre o movimento
        house.getBoard().board[deleteX][deleteY].setPiece(null); //remove qualquer peça entre o movimento
        this.move(house);
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
