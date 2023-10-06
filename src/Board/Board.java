package Board;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Board extends Frame {
    private final int cellSize;

    public Board(int screen, int size) {
        if (size <= 0 || screen < size) {
            throw new IllegalArgumentException("Invalid screen/size dimension");
        }

        this.cellSize = screen / size;

        Panel cel = new Panel() {
            @Override
            public void paint(Graphics g) {
                for (int x = 0; x < size; x++) {
                    for (int y = 0; y < size; y++) {
                        if ((x + y) % 2 == 0) {
                            g.setColor(Color.black);
                        } else {
                            g.setColor(Color.white);
                        }
                        g.fillRect(cellSize * x, cellSize * y, cellSize, cellSize);
                    }
                }
            }
        };

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event){
                System.exit(0);
            }
        });

        this.setBounds(0,0,screen, screen);
        this.add(cel);
        this.setVisible(true);
        this.setPreferredSize(new Dimension(screen,screen+this.getInsets().top)); // adds the title border to the height
        this.pack();
    }

}
