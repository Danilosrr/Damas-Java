package Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Objects;

public class House extends JButton {
    private int coordX;
    private int coordY;
    private Board board;
    private Piece piece = null;

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

    public Piece getPiece() { return this.piece; }

    public void setPiece(Piece piece) { this.piece = piece; }

    private void click() {
        board.resetBoardColor();
        if (board. getHighlightedHouses() != null && board.getHighlightedHouses().contains(this)){ //se for uma jogada, e for válida move para essa casa.
            board.getSelectedHouse().getPiece().move(this);
        }
        else if (!Objects.isNull(piece)){ //mostrar as casa válidas.
            piece.validHouses(board);
        }
        System.out.printf("[%d,%d]%n",this.coordX,this.coordY);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // desenha a dama
        if (piece != null) {
            g.setColor(piece.getColor());
            int circleSize = getWidth() - 20;
            int x = (getWidth() - circleSize) / 2;
            int y = (getHeight() - circleSize) / 2;
            g.fillOval(x, y, circleSize, circleSize);
        }
    }
}
