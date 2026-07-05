package ru.ifmo.cs.bcomp.ui.components;

import ru.ifmo.cs.bcomp.CPU;
import ru.ifmo.cs.bcomp.MCDecoder;
import ru.ifmo.cs.bcomp.Reg;
import ru.ifmo.cs.bcomp.ui.GUI;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;

public class MicrocommandsView extends BCompPanel {
    private final CPU cpu;
    private final JTable table;
    private final MicrocommandsTableModel tableModel;
    private long currentMP = -1;
    private boolean showWarning = true;
    private final java.util.concurrent.atomic.AtomicBoolean updatePending = new java.util.concurrent.atomic.AtomicBoolean(false);
    private final javax.swing.Timer updateTimer;
    private volatile long pendingMP = -1;

    public MicrocommandsView(GUI gui) {
        super(gui.getComponentManager(), null, null);
        this.cpu = gui.getCPU();
        this.currentMP = cpu.getRegister(Reg.MP).getValue();
        setLayout(new BorderLayout());

        tableModel = new MicrocommandsTableModel();
        table = new JTable(tableModel);

        updateTimer = new javax.swing.Timer(40, e -> {
            updatePending.set(false);
            long targetMP = pendingMP;
            if (targetMP != currentMP) {
                long oldMP = currentMP;
                currentMP = targetMP;
                if (oldMP >= 0 && oldMP < 256) {
                    tableModel.fireTableRowsUpdated((int) oldMP, (int) oldMP);
                }
                if (currentMP >= 0 && currentMP < 256) {
                    tableModel.fireTableRowsUpdated((int) currentMP, (int) currentMP);
                    table.scrollRectToVisible(table.getCellRect((int) currentMP, 0, true));
                }
            }
        });
        updateTimer.setRepeats(false);
        table.setBackground(DisplayStyles.COLOR_BACKGROUND);
        table.setForeground(DisplayStyles.COLOR_TEXT);
        table.setGridColor(new Color(50, 60, 70));
        table.setRowHeight(22);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setBackground(DisplayStyles.COLOR_TITLE);
        table.getTableHeader().setForeground(DisplayStyles.COLOR_TEXT);
        table.getTableHeader().setFont(new Font("Roboto", Font.BOLD, 12));

        MicrocommandsCellRenderer renderer = new MicrocommandsCellRenderer();
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }

        // Adjust column widths
        TableColumn colArrow = table.getColumnModel().getColumn(0);
        colArrow.setPreferredWidth(30);
        colArrow.setMaxWidth(40);

        TableColumn colAddr = table.getColumnModel().getColumn(1);
        colAddr.setPreferredWidth(60);
        colAddr.setMaxWidth(80);

        TableColumn colLabel = table.getColumnModel().getColumn(2);
        colLabel.setPreferredWidth(120);
        colLabel.setMaxWidth(200);

        TableColumn colHex = table.getColumnModel().getColumn(3);
        colHex.setPreferredWidth(120);
        colHex.setMaxWidth(200);

        TableColumn colOp = table.getColumnModel().getColumn(4);
        colOp.setPreferredWidth(450);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(DisplayStyles.COLOR_BACKGROUND);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane, BorderLayout.CENTER);

        // Context Menu (RMB popup menu)
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem resetAllItem = new JMenuItem("Сбросить все микрокоманды (00-FF)");
        JMenuItem resetSelectedItem = new JMenuItem("Сбросить выбранные микрокоманды");
        JMenuItem resetBaseItem = new JMenuItem("Сбросить только базовые микрокоманды (00-DF)");

        popupMenu.add(resetAllItem);
        popupMenu.add(resetSelectedItem);
        popupMenu.add(resetBaseItem);

        table.setComponentPopupMenu(popupMenu);

        // Select row on RMB press before showing menu
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    int r = table.rowAtPoint(e.getPoint());
                    if (r >= 0 && r < table.getRowCount()) {
                        if (!table.isRowSelected(r)) {
                            table.setRowSelectionInterval(r, r);
                        }
                    }
                }
            }
        });

        resetAllItem.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(
                table,
                "Вы действительно хотите сбросить все микрокоманды и их метки?",
                "Подтверждение",
                JOptionPane.YES_NO_OPTION
            );
            if (choice == JOptionPane.YES_OPTION) {
                for (int i = 0; i < 256; i++) {
                    resetMicrocommand(i);
                }
                tableModel.fireTableDataChanged();
            }
        });

        resetSelectedItem.addActionListener(e -> {
            int[] selectedRows = table.getSelectedRows();
            if (selectedRows.length == 0) return;
            int choice = JOptionPane.showConfirmDialog(
                table,
                "Вы действительно хотите сбросить выбранные микрокоманды?",
                "Подтверждение",
                JOptionPane.YES_NO_OPTION
            );
            if (choice == JOptionPane.YES_OPTION) {
                for (int row : selectedRows) {
                    resetMicrocommand(row);
                }
                tableModel.fireTableDataChanged();
            }
        });

        resetBaseItem.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(
                table,
                "Вы действительно хотите сбросить базовые микрокоманды (00-DF) и их метки?",
                "Подтверждение",
                JOptionPane.YES_NO_OPTION
            );
            if (choice == JOptionPane.YES_OPTION) {
                for (int i = 0x00; i <= 0xDF; i++) {
                    resetMicrocommand(i);
                }
                tableModel.fireTableDataChanged();
            }
        });
    }

    private void resetMicrocommand(int addr) {
        try {
            long defVal = cpu.getMicroCodeSource().getOriginalMicroCommand(addr);
            cpu.getMicroCode().setValue(addr, defVal);
            String defLabel = cpu.getMicroCodeSource().getOriginalLabel(addr);
            cpu.getMicroCodeSource().setLabel(addr, defLabel);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void panelActivate() {
        super.panelActivate();
        SwingUtilities.invokeLater(() -> {
            currentMP = cpu.getRegister(Reg.MP).getValue();
            tableModel.fireTableDataChanged();
            if (currentMP >= 0 && currentMP < 256) {
                table.scrollRectToVisible(table.getCellRect((int) currentMP, 0, true));
            }
            table.requestFocusInWindow();
        });
    }

    @Override
    public void panelDeactivate() {
        super.panelDeactivate();
    }

    @Override
    public void stepFinish() {
        super.stepFinish();
        pendingMP = cpu.getRegister(Reg.MP).getValue();
        if (updatePending.compareAndSet(false, true)) {
            SwingUtilities.invokeLater(() -> {
                if (!updateTimer.isRunning()) {
                    updateTimer.start();
                }
            });
        }
    }

    @Override
    public String getPanelName() {
        return "Микрокоманды";
    }

    @Override
    public void redrawArrows() {
    }

    private class MicrocommandsTableModel extends AbstractTableModel {
        private final String[] columnNames = {"", "Адрес", "Метка", "Код (Hex)", "Операция"};

        @Override
        public int getRowCount() {
            return 256;
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return rowIndex == currentMP ? ">" : "";
                case 1:
                    return String.format("%02X", rowIndex);
                case 2:
                    String label = cpu.getMicroCodeSource().getLabel(rowIndex);
                    return label != null ? label : "";
                case 3:
                    long val = cpu.getMicroCode().getValue(rowIndex);
                    return ru.ifmo.cs.components.Utils.toHex(val, 40);
                case 4:
                    String[] decoded = MCDecoder.decodeMC(cpu, rowIndex);
                    return decoded[2] != null ? decoded[2] : "";
                default:
                    return "";
            }
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return columnIndex == 2 || columnIndex == 3;
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            if (columnIndex == 3) {
                String strVal = ((String) aValue).trim();
                try {
                    if (strVal.toLowerCase().startsWith("0x")) {
                        strVal = strVal.substring(2);
                    }
                    long newCode = Long.parseLong(strVal, 16);

                    if (rowIndex >= 0x00 && rowIndex <= 0xDF && showWarning) {
                        JCheckBox checkbox = new JCheckBox("Больше не показывать");
                        Object[] params = {
                            "Изменение базовых микрокоманд (адреса 00-DF) может нарушить работу компьютера.\nВы уверены, что хотите продолжить?",
                            checkbox
                        };
                        int choice = JOptionPane.showConfirmDialog(
                            table,
                            params,
                            "Предупреждение",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE
                        );
                        if (choice != JOptionPane.YES_OPTION) {
                            return;
                        }
                        if (checkbox.isSelected()) {
                            showWarning = false;
                        }
                    }

                    cpu.getMicroCode().setValue(rowIndex, newCode);
                    fireTableCellUpdated(rowIndex, 3);
                    fireTableCellUpdated(rowIndex, 4);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(table, "Неверный формат шестнадцатеричного числа: " + strVal, "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            } else if (columnIndex == 2) {
                String newLabel = ((String) aValue).trim();

                if (newLabel.isEmpty()) {
                    cpu.getMicroCodeSource().setLabel(rowIndex, null);
                    fireTableDataChanged();
                    return;
                }

                if (!newLabel.matches("^[a-zA-Z]+$")) {
                    JOptionPane.showMessageDialog(table, "Метка должна состоять только из английских букв!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Check uniqueness
                for (int i = 0; i < 256; i++) {
                    if (i != rowIndex) {
                        String existingLabel = cpu.getMicroCodeSource().getLabel(i);
                        if (existingLabel != null && existingLabel.equalsIgnoreCase(newLabel)) {
                            JOptionPane.showMessageDialog(table, "Метка '" + newLabel + "' уже используется!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                }

                cpu.getMicroCodeSource().setLabel(rowIndex, newLabel);
                fireTableDataChanged();
            }
        }
    }

    private class MicrocommandsCellRenderer extends DefaultTableCellRenderer {
        private final Font fontPlain = new Font("Courier New", Font.PLAIN, 12);
        private final Font fontBold = new Font("Courier New", Font.BOLD, 12);

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            c.setFont(fontPlain);

            if (row == currentMP) {
                c.setBackground(new Color(25, 89, 101));
                c.setForeground(Color.WHITE);
                c.setFont(fontBold);
            } else if (isSelected) {
                c.setBackground(table.getSelectionBackground());
                c.setForeground(table.getSelectionForeground());
            } else {
                c.setBackground(DisplayStyles.COLOR_BACKGROUND);
                c.setForeground(DisplayStyles.COLOR_TEXT);
            }

            if (column == 0 || column == 1) {
                setHorizontalAlignment(SwingConstants.CENTER);
            } else {
                setHorizontalAlignment(SwingConstants.LEFT);
            }

            return c;
        }
    }
}
