/*
 * $Id$
 */
package ru.ifmo.cs.bcomp.ui.io;

import ru.ifmo.cs.bcomp.IOCtrl;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

/**
 *
 * @author Dmitry Afanasiev <KOT@MATPOCKuH.Ru>
 */
public class Ticker extends OutputDevice {
    private static final Color LED_OFF = new Color(224, 224, 224);
    private static final Color LED_ON = new Color(0, 160, 0);
    private static final int ELEMENTS_COUNT = 32;
    private static final int ELEMENTS_HEIGHT = 8;
    private static final int ELEMENT_SIZE = 4;
    private static final int ELEMENT_SPACE = 2;
    private static final int ELEMENT_FULL_SIZE = ELEMENT_SIZE + ELEMENT_SPACE;

    private final int elements[] = new int[ELEMENTS_COUNT];
    private int position = ELEMENTS_COUNT - 1;
    private TickerString ticker;

    private class TickerString extends JComponent {
        public TickerString() {
            Dimension d = new Dimension(
                    ELEMENTS_COUNT * ELEMENT_FULL_SIZE + ELEMENT_SPACE,
                    ELEMENTS_HEIGHT * ELEMENT_FULL_SIZE + ELEMENT_SPACE);
            setMinimumSize(d);
            setPreferredSize(d);
        }

        @Override
        public void paintComponent(Graphics g) {
            int width = getWidth();
            int height = getHeight();
            int c = Math.max(1, Math.min(width / ELEMENTS_COUNT, height / ELEMENTS_HEIGHT));
            int s = Math.max(1, (c > 4) ? c - c / 3 : c - 1);
            int xOffset = (width - ELEMENTS_COUNT * c) / 2;
            int yOffset = (height - ELEMENTS_HEIGHT * c) / 2;
            int pad = (c - s) / 2;

            for (int x = 0; x < ELEMENTS_COUNT; x++) {
                int value = elements[(x + position) % ELEMENTS_COUNT];

                for (int y = 0; y < ELEMENTS_HEIGHT; y++) {
                    g.setColor(((value >> (ELEMENTS_HEIGHT - y - 1)) & 1) == 1 ? LED_ON : LED_OFF);
                    g.fillRect(
                            xOffset + x * c + pad,
                            yOffset + y * c + pad,
                            s, s);
                }
            }
        }
    }

    public Ticker(IOCtrl ioctrl) {
        super(ioctrl, "ticker");

        Arrays.fill(elements, 0);
    }

    @Override
    protected Component getContent() {
        JPanel content = new JPanel(new BorderLayout());

        content.add(BorderLayout.CENTER, ticker = new TickerString());

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
        elements[position] = (int)value;
        position = (position + 1) % ELEMENTS_COUNT;
        ticker.repaint();
    }
}