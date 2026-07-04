/*
 * $Id$
 */
package ru.ifmo.cs.bcomp.ui.io;

import ru.ifmo.cs.bcomp.IOCtrl;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Dmitry Afanasiev <KOT@MATPOCKuH.Ru>
 */
public class SevenSegmentDisplay extends OutputDevice {
    private static final Color LED_OFF = new Color(224, 224, 224);
    private static final Color LED_ON = new Color(0, 160, 0);
    private static final int COUNT = 8;
    private static final int SEGMENT_LENGTH = 16;
    private static final int SEGMENT_WIDTH = 2;
    private static final Dimension DIMS = new Dimension(
            SEGMENT_LENGTH + 4 * SEGMENT_WIDTH,
            2 * SEGMENT_LENGTH + 5 * SEGMENT_WIDTH);

    // Верх, середина, низ, левый верх, левый низ, правый верх, правый низ
    private static final boolean NUMBERS[][] = new boolean[][] {
            {true, false, true, true, true, true, true}, // 0
            {false, false, false, false, false, true, true}, // 1
            {true, true, true, false, true, true, false}, // 2
            {true, true, true, false, false, true, true}, // 3
            {false, true, false, true, false, true, true}, // 4
            {true, true, true, true, false, false, true}, // 5
            {true, true, true, true, true, false, true}, // 6
            {true, false, false, false, false, true, true}, // 7
            {true, true, true, true, true, true, true}, // 8
            {true, true, true, true, false, true, true}, // 9
            {false, true, false, false, false, false, false}, // A "-"
            {false, false, false, false, false, false, false}, // B
            {false, false, false, false, false, false, false}, // C
            {false, false, false, false, false, false, false}, // D
            {false, false, false, false, false, false, false}, // E
            {false, false, false, false, false, false, false} // F
    };
    private static final int COORDINATES[][] = new int[][] {
            {pos(0, 2), pos(0, 1), SEGMENT_LENGTH, SEGMENT_WIDTH}, // Верх
            {pos(0, 2), pos(1, 2), SEGMENT_LENGTH, SEGMENT_WIDTH}, // Середина
            {pos(0, 2), pos(2, 3), SEGMENT_LENGTH, SEGMENT_WIDTH}, // Низ
            {pos(0, 1), pos(0, 2), SEGMENT_WIDTH, SEGMENT_LENGTH}, // Левый верх
            {pos(0, 1), pos(1, 3), SEGMENT_WIDTH, SEGMENT_LENGTH}, // Левый низ
            {pos(1, 2), pos(0, 2), SEGMENT_WIDTH, SEGMENT_LENGTH}, // Правый верх
            {pos(1, 2), pos(1, 3), SEGMENT_WIDTH, SEGMENT_LENGTH}, // Правый низ
    };
    private final SSD ssd[] = new SSD[COUNT];

    private class SSD extends JComponent {
        private int value = 0xf;

        public SSD() {
            setMinimumSize(DIMS);
            setPreferredSize(DIMS);
        }


        @Override
        public void paintComponent(Graphics g) {
            int W = getWidth();
            int H = getHeight();
            int k = Math.max(1, Math.min(W / 24, H / 42));
            int segLength = 16 * k;
            int segWidth = 2 * k;
            int digitW = segLength + 4 * segWidth;
            int digitH = 2 * segLength + 5 * segWidth;
            int xOffset = (W - digitW) / 2;
            int yOffset = (H - digitH) / 2;

            int coordinates[][] = new int[][] {
                {xOffset + 2 * segWidth, yOffset + segWidth, segLength, segWidth}, // Верх
                {xOffset + 2 * segWidth, yOffset + segLength + 2 * segWidth, segLength, segWidth}, // Середина
                {xOffset + 2 * segWidth, yOffset + 2 * segLength + 3 * segWidth, segLength, segWidth}, // Низ
                {xOffset + segWidth, yOffset + 2 * segWidth, segWidth, segLength}, // Левый верх
                {xOffset + segWidth, yOffset + segLength + 3 * segWidth, segWidth, segLength}, // Левый низ
                {xOffset + segLength + 2 * segWidth, yOffset + 2 * segWidth, segWidth, segLength}, // Правый верх
                {xOffset + segLength + 2 * segWidth, yOffset + segLength + 3 * segWidth, segWidth, segLength}, // Правый низ
            };

            for (int i = 0; i < coordinates.length; i++) {
                g.setColor(NUMBERS[value][i] ? LED_ON : LED_OFF);
                g.fillRect(
                        coordinates[i][0], coordinates[i][1],
                        coordinates[i][2], coordinates[i][3]);
            }
        }
    }

    public SevenSegmentDisplay(IOCtrl ioctrl) {
        super(ioctrl, "ssd");
    }

    private static int pos(int length, int width) {
        return length * SEGMENT_LENGTH + width * SEGMENT_WIDTH;
    }

    @Override
    protected Component getContent() {
        JPanel content = new JPanel(new BorderLayout());

        JPanel center = new JPanel(new GridLayout(1, COUNT, 5, 5));
        for (int i = COUNT; i > 0; center.add(ssd[--i] = new SSD()));
        content.add(BorderLayout.CENTER, center);

        JPanel north = new JPanel(new FlowLayout(FlowLayout.LEFT));
        north.add(getSleepSlider());
        north.add(getPowerChkBox());
        north.add(new JLabel("Готовность:"));
        north.add(new FlagIndicator(ioctrl, 30));
        content.add(BorderLayout.NORTH, north);

        return content;
    }

    @Override
    protected void actionPerformed(long value) {
        int pos = ((int)value >> 4) & 0x7;

        ssd[pos].value = ((int)value) & 0xf;
        ssd[pos].repaint();
    }
}