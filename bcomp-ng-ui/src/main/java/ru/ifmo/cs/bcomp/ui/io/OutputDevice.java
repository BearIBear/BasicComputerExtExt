/*
 * $Id$
 */

package ru.ifmo.cs.bcomp.ui.io;

import ru.ifmo.cs.bcomp.IOCtrl;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 *
 * @author Dmitry Afanasiev <KOT@MATPOCKuH.Ru>
 */
public abstract class OutputDevice extends IODevice {
    private Thread timer = null;
    private long sleeptime = 100;
    private volatile boolean poweredon = true;
    private boolean isUpdating = false;

    public OutputDevice(final IOCtrl ioctrl, final String title) {
        super(ioctrl, title);
    }

    protected abstract void actionPerformed(long value);

    protected Component getSleepSlider() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));

        JLabel delayLabel = new JLabel("Задержка:");
        panel.add(delayLabel);

        final JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 10000, (int)sleeptime);
        slider.setPreferredSize(new Dimension(200, 45));
        slider.setMajorTickSpacing(2500);
        slider.setMinorTickSpacing(500);
        slider.setPaintTicks(true);

        java.util.Hashtable<Integer, JLabel> labelTable = new java.util.Hashtable<>();
        labelTable.put(0, new JLabel("1"));
        labelTable.put(2500, new JLabel("2500"));
        labelTable.put(5000, new JLabel("5000"));
        labelTable.put(7500, new JLabel("7500"));
        labelTable.put(10000, new JLabel("10000"));

        Font labelFont = new Font("Arial", Font.PLAIN, 10);
        for (JLabel label : labelTable.values()) {
            label.setFont(labelFont);
        }
        slider.setLabelTable(labelTable);
        slider.setPaintLabels(true);
        panel.add(slider);

        final JTextField textField = new JTextField(5);
        textField.setText(String.valueOf(sleeptime));
        panel.add(textField);

        JLabel msLabel = new JLabel("мс");
        panel.add(msLabel);

        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (isUpdating) return;
                isUpdating = true;
                try {
                    int val = slider.getValue();
                    sleeptime = Math.max(1, val);
                    textField.setText(String.valueOf(sleeptime));
                } finally {
                    isUpdating = false;
                }
            }
        });

        textField.getDocument().addDocumentListener(new DocumentListener() {
            private void update() {
                if (isUpdating) return;
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        if (isUpdating) return;
                        isUpdating = true;
                        try {
                            String text = textField.getText().trim();
                            if (!text.isEmpty()) {
                                try {
                                    int val = Integer.parseInt(text);
                                    int cappedVal = Math.min(10000, Math.max(1, val));
                                    sleeptime = cappedVal;
                                    slider.setValue(cappedVal);
                                } catch (NumberFormatException ex) {
                                    // Ignore invalid input while typing
                                }
                            }
                        } finally {
                            isUpdating = false;
                        }
                    }
                });
            }

            @Override
            public void insertUpdate(DocumentEvent e) { update(); }

            @Override
            public void removeUpdate(DocumentEvent e) { update(); }

            @Override
            public void changedUpdate(DocumentEvent e) { update(); }
        });

        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (isUpdating) return;
                isUpdating = true;
                try {
                    textField.setText(String.valueOf(sleeptime));
                } finally {
                    isUpdating = false;
                }
            }
        });

        return panel;
    }

    protected Component getPowerChkBox() {
        JCheckBox power = new JCheckBox("Вкл", true);
        power.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                switch(e.getStateChange()) {
                    case ItemEvent.SELECTED:
                        poweredon = true;
                        break;

                    case ItemEvent.DESELECTED:
                        poweredon = false;
                        break;
                }
            }
        });

        return power;
    }

    @Override
    public void activate() {
        super.activate();

        if (timer == null) {
            timer = new Thread(new Runnable() {
                @Override
                public void run() {
                    ioctrl.setReady();

                    for (;;) {
                        try {
                            Thread.sleep(Math.max(1, sleeptime));
                        } catch (Exception e) { }

                        if (!poweredon)
                            continue;

                        if (!ioctrl.isReady()) {
                            actionPerformed(ioctrl.getData());
                            ioctrl.setReady();
                        }
                    }
                }
            }, title);

            timer.start();
        }
    }
}