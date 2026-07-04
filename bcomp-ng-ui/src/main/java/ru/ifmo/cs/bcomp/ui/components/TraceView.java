package ru.ifmo.cs.bcomp.ui.components;

import ru.ifmo.cs.bcomp.CPU;
import ru.ifmo.cs.bcomp.ControlSignal;
import ru.ifmo.cs.bcomp.Reg;
import ru.ifmo.cs.bcomp.ui.GUI;
import ru.ifmo.cs.components.DataDestination;
import ru.ifmo.cs.components.Utils;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static ru.ifmo.cs.bcomp.ui.components.DisplayStyles.*;

//боже, не смотри сюда, тут какой-то трэш

public class TraceView extends BCompPanel implements ActionListener {

    private static boolean printRegsTitle = true;
    private GUI gui;
    private static CPU cpu;
    private final ComponentManager cmanager;
    private static JTextPane text;
    private StringBuilder stringRegsCsv = new StringBuilder();

    private final ArrayList<Long> writelist = new ArrayList<Long>();
    private volatile long savedPointer;
    private volatile boolean printOnStop = true;

    private boolean isRun = false;
    private boolean isContinue = false;

    private String getReg(Reg reg) {
        return Utils.toHex(cpu.getRegValue(reg), cpu.getRegWidth(reg));
    }

    private static Reg[] printRegs = new Reg[] { Reg.IP, Reg.CR, Reg.AR, Reg.DR, Reg.SP, Reg.BR, Reg.AC, Reg.PS };

    public String printRegsTitle() {
        if (!printRegsTitle)
            return "";

        StringBuilder stringBuilderCsv = new StringBuilder();
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Адр\t" + (cpu.getClockState() ? "Знчн" : "МК"));
        stringBuilderCsv.append("Адр," + (cpu.getClockState() ? "Знчн" : "МК"));
        for (Reg reg : printRegs) {
            int width = (int) Math.ceil(cpu.getRegWidth(reg) / 4.0);
            int l = (int) Math.ceil((width - reg.name().length()) / 2.0);
            stringBuilder.append(String.format("\t%s", reg.name()));
            stringBuilderCsv.append(String.format(",%s", reg.name()));
        }
        stringBuilder.append("\tNZVC" + (cpu.getClockState() ? "\tАдр" + "\tЗнчн" : "\tСчМК") + "\n");
        stringBuilderCsv.append(",NZVC" + (cpu.getClockState() ? ",Адр,Знчн" : "СчМК") + "\n");
        printRegsTitle = false;

        setTrace(stringBuilder.toString());
        return stringBuilderCsv.toString();
    }

    private String printRegs(String add, String addCsv) {

        StringBuilder stringBuilderRegsCsv = new StringBuilder();
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append((cpu.getClockState() ? getMemory(savedPointer) + "\t"
                : Utils.toHex(savedPointer, 8) +
                        Utils.toHex(cpu.getMicroCode().getValue(savedPointer), 40) + "\t"));

        stringBuilderRegsCsv.append((cpu.getClockState() ? getMemoryCsv(savedPointer) + ","
                : Utils.toHex(savedPointer, 8) + "," +
                        Utils.toHex(cpu.getMicroCode().getValue(savedPointer), 40) + ","));

        for (Reg reg : printRegs) {
            stringBuilder.append(getReg(reg) + "\t");
            stringBuilderRegsCsv.append(getReg(reg) + ",");
        }

        stringBuilder.append(Utils.toBinary(cpu.getRegValue(Reg.PS) & 0xF, 4)
                + (cpu.getClockState() ? add : getReg(Reg.MP)) + "\n");

        stringBuilderRegsCsv.append(Utils.toBinary(cpu.getRegValue(Reg.PS) & 0xF, 4)
                + (cpu.getClockState() ? addCsv : getReg(Reg.MP)) + "\n");

        setTrace(stringBuilder.toString());
        return stringBuilderRegsCsv.toString();
    }

    private String getMemory(long addr) {
        return Utils.toHex(addr, 11) + "\t" + Utils.toHex(cpu.getMemory().getValue(addr), 16);
    }

    private String getMemoryCsv(long addr) {
        return Utils.toHex(addr, 11) + "," + Utils.toHex(cpu.getMemory().getValue(addr), 16);
    }

    public TraceView(final GUI gui) {
        super(gui.getComponentManager(), null, null);
        this.gui = gui;
        this.cpu = gui.getCPU();
        this.cmanager = gui.getComponentManager();

        cpu.addDestination(ControlSignal.STOR, new DataDestination() {
            @Override
            public void setValue(long value) {
                if (!isRun)
                    return;
                long addr = cpu.getRegValue(Reg.AR);

                if (!writelist.contains(addr))
                    writelist.add(addr);
            }
        });

        cpu.setCPUStartListener(new Runnable() {
            @Override
            public void run() {
                if (!isRun)
                    return;
                if (!printOnStop)
                    return;
                writelist.clear();
                savedPointer = cpu.getRegValue(cpu.getClockState() ? Reg.IP : Reg.MP);
                synchronized (stringRegsCsv) {
                    stringRegsCsv.append(printRegsTitle());
                }
            }
        });

        cpu.setCPUStopListener(new Runnable() {
            @Override
            public void run() {
                if (!isRun)
                    return;
                if (!printOnStop)
                    return;

                Long _addr = 0L;
                if (!writelist.isEmpty())
                    _addr = writelist.remove(0);

                synchronized (stringRegsCsv) {
                    stringRegsCsv.append(printRegs((_addr == 0L ? "" : "\t" + getMemory(_addr)),
                            (_addr == 0L ? "" : "," + getMemoryCsv(_addr))));
                    for (Long wraddr : writelist) {
                        System.out.println(wraddr);
                        setTrace(String.format(",%1$34s", "\t") + getMemory(wraddr) + "\n");
                        stringRegsCsv.append(String.format(",%1$34s", ",") + getMemoryCsv(wraddr) + "\n");
                    }
                }
            }
        });

        JPanel mainPanel = new JPanel();
        JPanel leftPanel = new JPanel();
        JPanel bottomButtons = new JPanel();

        add(mainPanel);
        mainPanel.setLayout(new BorderLayout());
        bottomButtons.setLayout(new FlowLayout(FlowLayout.LEFT));

        GridBagLayout gbl = new GridBagLayout();
        leftPanel.setLayout(gbl);

        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.NORTH;
        c.fill = GridBagConstraints.BOTH;
        c.gridheight = 1;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.gridx = GridBagConstraints.RELATIVE;
        c.gridy = GridBagConstraints.RELATIVE;
        c.insets = new Insets(0, 0, 0, 0);
        c.ipadx = 0;
        c.ipady = 0;
        c.weightx = 0.0;
        c.weighty = 0.0;

        mainPanel.add(bottomButtons, BorderLayout.SOUTH);
        mainPanel.add(leftPanel, BorderLayout.EAST);

        text = new JTextPane();
        text.setFont(FONT_COURIER_BOLD_21);
        text.setBackground(COLOR_BACKGROUND);
        text.setForeground(COLOR_TEXT);
        text.setCaretColor(COLOR_TEXT);

        JScrollPane scroll = new JScrollPane(text);
        scroll.setBounds(TEXTAREA_X, TEXTAREA_Y, TEXTAREA_WIDTH, TEXTAREA_HEIGHT);
        mainPanel.add(scroll, BorderLayout.CENTER);

        // left
        JTextField sleepTb = new JTextField(String.valueOf(cmanager.getDelay()));
        c.insets = new Insets(15, 0, 0, 0);
        gbl.setConstraints(sleepTb, c);
        leftPanel.add(sleepTb);

        cmanager.addDelayListener(new Runnable() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        sleepTb.setText(String.valueOf(cmanager.getDelay()));
                    }
                });
            }
        });

        JButton sleepBtn = new JButton("Задать задержку (мс)");
        sleepBtn.setForeground(COLOR_TEXT);
        sleepBtn.setBackground(COLOR_VALUE);
        setCustomButtonStyle(sleepBtn);
        sleepBtn.setFont(FONT_COURIER_PLAIN_12);
        sleepBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    long newDelay = Long.parseLong(sleepTb.getText());
                    cmanager.setDelay(newDelay);
                    setTrace("Задержка установлена на " + newDelay + " мс\n");
                } catch (NumberFormatException ex) {
                    setTrace("Неверный формат задержки\n");
                }
            }
        });
        c.insets = new Insets(5, 0, 20, 0);
        gbl.setConstraints(sleepBtn, c);
        leftPanel.add(sleepBtn);

        JButton button = new JButton("Выполнить трассировку");
        button.setForeground(COLOR_TEXT);
        button.setBackground(COLOR_VALUE);
        setCustomButtonStyle(button);
        button.setFont(FONT_COURIER_PLAIN_12);
        button.setBounds(625, 1, 200, BUTTONS_HEIGHT);
        button.setFocusable(false);

        // Thread cpuRun = new Thread(() -> {
        // cpu.startStart();
        // cpu.startContinue();
        //
        // cpu.executeContinue();
        // while (true) {
        // if (!Long.toHexString(cpu.getRegValue(Reg.CR)).equals("100") && isRun)
        // cpu.executeContinue();
        // else {
        // isRun = false;
        // button.setText("Выполнить трассировку");
        // System.out.println(currentThread().getName() + " is stopping Server thread");
        // break;
        // }
        // }
        // });

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isRun) {
                    isRun = true;
                    button.setText("Приостановить");

                    if (!isContinue) {
                        printRegsTitle = true;
                        text.setText("");
                        synchronized (stringRegsCsv) {
                            stringRegsCsv.setLength(0);
                        }
                    } else
                        isContinue = false;

                    // Delay is managed by ComponentManager; no need to override tickFinishListener

                    // sleep = sleeptime;
                    new Thread(() -> {
                        cpu.startStart();
                        cpu.startContinue();

                        cpu.executeContinue();
                        while (true) {
                            if (!Long.toHexString(cpu.getRegValue(Reg.CR)).equals("100") && isRun)
                                cpu.executeContinue();
                            else {
                                isRun = false;
                                SwingUtilities.invokeLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        button.setText("Выполнить трассировку");
                                    }
                                });
                                break;
                            }
                        }
                    }).start();

                } else {
                    button.setText("Выполнить трассировку");
                    isRun = false;
                    isContinue = true;
                }
            }
        });
        c.weighty = 1.0;
        gbl.setConstraints(button, c);
        leftPanel.add(button);

        JTextField textField = new JTextField("", 10);
        bottomButtons.add(textField);

        JButton btn1 = new JButton("Задать адрес начала программы");
        btn1.setForeground(COLOR_TEXT);
        btn1.setBackground(COLOR_VALUE);
        setCustomButtonStyle(btn1);
        btn1.setFont(FONT_COURIER_PLAIN_12);
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textVal = textField.getText().trim();
                if (textVal.isEmpty()) {
                    JOptionPane.showMessageDialog(gui, "Введите адрес в шестнадцатеричном формате!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String parsedVal = textVal;
                if (parsedVal.toLowerCase().startsWith("0x")) {
                    parsedVal = parsedVal.substring(2);
                }
                try {
                    int value = Integer.parseInt(parsedVal, 16);
                    if (value < 0 || value > 0x7ff) {
                        JOptionPane.showMessageDialog(gui, "Адрес должен быть в диапазоне от 000 до 7FF!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    cpu.getRegister(Reg.IR).setValue(value);
                    cpu.executeSetAddr();
                    setTrace("IP установлен на 0x" + Integer.toHexString(value) + "\n");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(gui, "Неверный формат шестнадцатеричного числа: " + textVal, "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        bottomButtons.add(btn1);

        JButton btn2 = new JButton("Экспорт в CSV-файл");
        btn2.setForeground(COLOR_TEXT);
        btn2.setBackground(COLOR_VALUE);
        setCustomButtonStyle(btn2);
        btn2.setFont(FONT_COURIER_PLAIN_12);
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setSelectedFile(new File("Трассировка.csv"));
                int retval = fileChooser.showSaveDialog(gui);
                if (retval == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    if (file == null) {
                        return;
                    }
                    if (!file.getName().toLowerCase().endsWith(".csv")) {
                        file = new File(file.getParentFile(), file.getName() + ".csv");
                    }
                    try {
                        BufferedWriter writer = null;
                        try {
                            writer = new BufferedWriter(new FileWriter(file));
                            writer.write('\ufeff');
                            String csvData;
                            synchronized (stringRegsCsv) {
                                csvData = stringRegsCsv.toString();
                            }
                            writer.write(csvData);
                        } finally {
                            if (writer != null) {
                                writer.close();
                            }
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        bottomButtons.add(btn2);

        JButton btnXlsx = new JButton("Экспорт в XLSX-файл");
        btnXlsx.setForeground(COLOR_TEXT);
        btnXlsx.setBackground(COLOR_VALUE);
        setCustomButtonStyle(btnXlsx);
        btnXlsx.setFont(FONT_COURIER_PLAIN_12);
        btnXlsx.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setSelectedFile(new File("Трассировка.xlsx"));
                int retval = fileChooser.showSaveDialog(gui);
                if (retval == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    if (file == null) {
                        return;
                    }
                    if (!file.getName().toLowerCase().endsWith(".xlsx")) {
                        file = new File(file.getParentFile(), file.getName() + ".xlsx");
                    }
                    try {
                        String csvData;
                        synchronized (stringRegsCsv) {
                            csvData = stringRegsCsv.toString();
                        }
                        exportToXlsx(csvData, file);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(gui, "Ошибка экспорта в XLSX: " + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        bottomButtons.add(btnXlsx);

        JButton btn3 = new JButton("Нажми на меня...");
        btn3.setForeground(COLOR_TEXT);
        btn3.setBackground(COLOR_VALUE);
        setCustomButtonStyle(btn3);
        btn3.setFont(FONT_COURIER_PLAIN_12);
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "1) Всегда указывайте адрес начала программы\n2) В программе обязательно должен быть HLT, чтобы БЭВМ тупа не завис (мне лень просто делать какие-то проверки)\n3) Вроде все :D");
            }
        });

        bottomButtons.add(btn3);
    }

    public static void setTrace(final String str) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    StyleContext sc = StyleContext.getDefaultStyleContext();
                    AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.WHITE);

                    int len = text.getDocument().getLength();
                    text.setCaretPosition(len);
                    text.setCharacterAttributes(aset, false);
                    text.replaceSelection(str);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public String getPanelName() {
        return "Трассировка";
    }

    @Override
    public void redrawArrows() {
        // no arrows no draw
    }

    @Override
    public void panelActivate() {
        text.requestFocus();

    }

    @Override
    public void panelDeactivate() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    private void exportToXlsx(String csvData, File file) throws Exception {
        List<String[]> data = new ArrayList<>();
        String[] lines = csvData.split("\n");
        for (String line : lines) {
            if (line.trim().isEmpty()) {
                continue;
            }
            data.add(line.split(",", -1));
        }

        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(file))) {
            // 1. Write [Content_Types].xml
            zos.putNextEntry(new ZipEntry("[Content_Types].xml"));
            byte[] contentTypes = (
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<Types xmlns=\"http://schemas.openxmlformats.org/package/2006/content-types\">\n" +
                "  <Default Extension=\"rels\" ContentType=\"application/vnd.openxmlformats-package.relationships+xml\"/>\n" +
                "  <Default Extension=\"xml\" ContentType=\"application/xml\"/>\n" +
                "  <Override PartName=\"/xl/workbook.xml\" ContentType=\"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet.main+xml\"/>\n" +
                "  <Override PartName=\"/xl/worksheets/sheet1.xml\" ContentType=\"application/vnd.openxmlformats-officedocument.spreadsheetml.worksheet+xml\"/>\n" +
                "</Types>"
            ).getBytes(StandardCharsets.UTF_8);
            zos.write(contentTypes);
            zos.closeEntry();

            // 2. Write _rels/.rels
            zos.putNextEntry(new ZipEntry("_rels/.rels"));
            byte[] rels = (
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<Relationships xmlns=\"http://schemas.openxmlformats.org/package/2006/relationships\">\n" +
                "  <Relationship Id=\"rId1\" Type=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships/officeDocument\" Target=\"xl/workbook.xml\"/>\n" +
                "</Relationships>"
            ).getBytes(StandardCharsets.UTF_8);
            zos.write(rels);
            zos.closeEntry();

            // 3. Write xl/workbook.xml
            zos.putNextEntry(new ZipEntry("xl/workbook.xml"));
            byte[] workbook = (
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<workbook xmlns=\"http://schemas.openxmlformats.org/spreadsheetml/2006/main\" xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\">\n" +
                "  <sheets>\n" +
                "    <sheet name=\"Трассировка\" sheetId=\"1\" r:id=\"rId1\"/>\n" +
                "  </sheets>\n" +
                "</workbook>"
            ).getBytes(StandardCharsets.UTF_8);
            zos.write(workbook);
            zos.closeEntry();

            // 4. Write xl/_rels/workbook.xml.rels
            zos.putNextEntry(new ZipEntry("xl/_rels/workbook.xml.rels"));
            byte[] workbookRels = (
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<Relationships xmlns=\"http://schemas.openxmlformats.org/package/2006/relationships\">\n" +
                "  <Relationship Id=\"rId1\" Type=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships/worksheet\" Target=\"worksheets/sheet1.xml\"/>\n" +
                "</Relationships>"
            ).getBytes(StandardCharsets.UTF_8);
            zos.write(workbookRels);
            zos.closeEntry();

            // 5. Calculate column widths and write xl/worksheets/sheet1.xml
            zos.putNextEntry(new ZipEntry("xl/worksheets/sheet1.xml"));
            
            int maxCols = 0;
            for (String[] row : data) {
                if (row.length > maxCols) maxCols = row.length;
            }
            int[] colWidths = new int[maxCols];
            for (String[] row : data) {
                for (int i = 0; i < row.length; i++) {
                    int len = row[i] != null ? row[i].length() : 0;
                    if (len > colWidths[i]) colWidths[i] = len;
                }
            }

            StringBuilder sheetXml = new StringBuilder();
            sheetXml.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n");
            sheetXml.append("<worksheet xmlns=\"http://schemas.openxmlformats.org/spreadsheetml/2006/main\">\n");
            
            if (maxCols > 0) {
                sheetXml.append("  <cols>\n");
                for (int i = 0; i < maxCols; i++) {
                    int width = Math.max(colWidths[i] + 4, 10);
                    sheetXml.append("    <col min=\"").append(i + 1).append("\" max=\"").append(i + 1)
                            .append("\" width=\"").append(width).append("\" customWidth=\"1\"/>\n");
                }
                sheetXml.append("  </cols>\n");
            }

            sheetXml.append("  <sheetData>\n");
            int rowNum = 1;
            for (String[] rowData : data) {
                sheetXml.append("    <row r=\"").append(rowNum).append("\">\n");
                for (int colNum = 0; colNum < rowData.length; colNum++) {
                    String val = rowData[colNum];
                    if (val == null) continue;
                    String ref = getColName(colNum) + rowNum;
                    sheetXml.append("      <c r=\"").append(ref).append("\" t=\"inline\">\n");
                    sheetXml.append("        <is><t>").append(escapeXml(val)).append("</t></is>\n");
                    sheetXml.append("      </c>\n");
                }
                sheetXml.append("    </row>\n");
                rowNum++;
            }
            sheetXml.append("  </sheetData>\n");
            sheetXml.append("</worksheet>");

            zos.write(sheetXml.toString().getBytes(StandardCharsets.UTF_8));
            zos.closeEntry();
        }
    }

    private String getColName(int col) {
        StringBuilder sb = new StringBuilder();
        while (col >= 0) {
            sb.insert(0, (char) ('A' + (col % 26)));
            col = (col / 26) - 1;
        }
        return sb.toString();
    }

    private String escapeXml(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&apos;");
    }
}
