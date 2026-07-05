package ru.ifmo.cs.bcomp.ui.components;

import ru.ifmo.cs.bcomp.ui.GUI;
import javax.swing.*;
import java.awt.*;

public class HelpView extends ActivateblePanel {
    private final ComponentManager cmanager;

    public HelpView(GUI gui) {
        this.cmanager = gui.getComponentManager();
        setLayout(new BorderLayout());
        setBackground(DisplayStyles.COLOR_BACKGROUND);

        JTabbedPane helpTabs = new JTabbedPane() {
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(DisplayStyles.COLOR_BACKGROUND_STYLE);
                g.fillRect(0, 0, this.getWidth(), this.getHeight());
                super.paintComponent(g);
            }
        };
        helpTabs.setForeground(DisplayStyles.COLOR_TEXT);
        helpTabs.setBackground(DisplayStyles.COLOR_BACKGROUND);

        // Tab 1: Commands Table
        helpTabs.addTab("Команды", createScrollableHtmlPane(getCommandsHtml()));

        // Tab 2: Directives
        helpTabs.addTab("Директивы", createScrollableHtmlPane(getDirectivesHtml()));

        // Tab 3: Labels
        helpTabs.addTab("Метки", createScrollableHtmlPane(getLabelsHtml()));

        // Tab 4: Addressing Modes
        helpTabs.addTab("Адресация", createScrollableHtmlPane(getAddressingHtml()));

        add(helpTabs, BorderLayout.CENTER);
    }

    private JScrollPane createScrollableHtmlPane(String htmlContent) {
        JEditorPane pane = new JEditorPane();
        pane.setContentType("text/html");
        pane.setEditable(false);
        pane.setBackground(DisplayStyles.COLOR_BACKGROUND);
        pane.setForeground(DisplayStyles.COLOR_TEXT);
        pane.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
        pane.setText(htmlContent);
        pane.setCaretPosition(0);

        JScrollPane scroll = new JScrollPane(pane);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(DisplayStyles.COLOR_BACKGROUND);
        return scroll;
    }

    private String getHtmlHeader() {
        return "<html><head><style>" +
                "body { background-color: #1e272e; color: #ffffff; font-family: sans-serif; font-size: 12px; margin: 15px; }" +
                "h2 { color: #55efc4; margin-top: 5px; margin-bottom: 10px; border-bottom: 1px solid #455a64; padding-bottom: 4px; }" +
                "h3 { color: #81ecec; margin-top: 15px; margin-bottom: 5px; }" +
                "table { border-collapse: collapse; width: 100%; margin-top: 10px; margin-bottom: 15px; font-size: 11px; }" +
                "th { background-color: #054750; color: #ffffff; border: 1px solid #455a64; padding: 5px; font-weight: bold; text-align: left; }" +
                "td { border: 1px solid #455a64; padding: 5px; }" +
                "tr.even { background-color: #2d3436; }" +
                "code { font-family: monospace; font-weight: bold; color: #ffeaa7; font-size: 12px; }" +
                "ul { margin-top: 5px; margin-bottom: 10px; padding-left: 20px; }" +
                "li { margin-bottom: 5px; }" +
                ".mnemonic { color: #55efc4; font-weight: bold; font-family: monospace; font-size: 12px; }" +
                ".alias { color: #a29bfe; font-family: monospace; font-size: 11px; }" +
                ".flag-header { text-align: center; width: 25px; }" +
                ".flag-val { text-align: center; font-weight: bold; }" +
                ".note { font-style: italic; color: #b2bec3; margin-top: 10px; }" +
                "</style></head><body>";
    }

    private String getCommandsHtml() {
        return getHtmlHeader() +
                "<h2>Система команд базовой ЭВМ</h2>" +
                "<p class='note'>Примечания по признакам результатов: <b>-</b> — не влияет на признак, <b>0</b> — признак сбрасывается (0), <b>*</b> — значение устанавливается по результату операции.</p>" +

                "<h3>Безадресные команды (0XXX)</h3>" +
                "<table>" +
                "<tr><th>Код</th><th>Команда</th><th class='flag-header'>N</th><th class='flag-header'>Z</th><th class='flag-header'>V</th><th class='flag-header'>C</th><th>Описание</th><th>Русская альтернатива</th></tr>" +
                "<tr><td><code>0000</code></td><td class='mnemonic'>NOP</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td>Нет операции</td><td class='alias'>ПРОП</td></tr>" +
                "<tr class='even'><td><code>0100</code></td><td class='mnemonic'>HLT</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td>Останов</td><td class='alias'>СТОП</td></tr>" +
                "<tr><td><code>0200</code></td><td class='mnemonic'>CLA</td><td class='flag-val'>*</td><td class='flag-val'>*</td><td class='flag-val'>0</td><td class='flag-val'>-</td><td>0 → AC</td><td class='alias'>ЧИСТЬ</td></tr>" +
                "<tr class='even'><td><code>0280</code></td><td class='mnemonic'>NOT</td><td class='flag-val'>*</td><td class='flag-val'>*</td><td class='flag-val'>0</td><td class='flag-val'>-</td><td>(!AC) → AC</td><td class='alias'>CMA, COM, НЕТЬ</td></tr>" +
                "<tr><td><code>0300</code></td><td class='mnemonic'>CLC</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td class='flag-val'>0</td><td>0 → C</td><td class='alias'>ЧИСТЦ</td></tr>" +
                "<tr class='even'><td><code>0380</code></td><td class='mnemonic'>CMC</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td class='flag-val'>*</td><td>(!C) → C</td><td class='alias'>ИНВЦ</td></tr>" +
                "<tr><td><code>0400</code></td><td class='mnemonic'>ROL</td><td class='flag-val'>*</td><td class='flag-val'>*</td><td class='flag-val'>*</td><td class='flag-val'>*</td><td>AC и C сдвигается влево (AC15 → C, C → AC0)</td><td class='alias'>ЦЛЕВ</td></tr>" +
                "<tr class='even'><td><code>0480</code></td><td class='mnemonic'>ROR</td><td class='flag-val'>*</td><td class='flag-val'>*</td><td class='flag-val'>*</td><td class='flag-val'>*</td><td>AC и C сдвигается вправо (AC0 → C, C → AC15)</td><td class='alias'>ЦПРАВ</td></tr>" +
                "<tr><td><code>0500</code></td><td class='mnemonic'>ASL</td><td class='flag-val'>*</td><td class='flag-val'>*</td><td class='flag-val'>*</td><td class='flag-val'>*</td><td>AC сдвигается влево (AC15 → C, 0 → AC0)</td><td class='alias'>АЛЕВ</td></tr>" +
                "<tr class='even'><td><code>0580</code></td><td class='mnemonic'>ASR</td><td class='flag-val'>*</td><td class='flag-val'>*</td><td class='flag-val'>*</td><td class='flag-val'>*</td><td>AC сдвигается вправо (AC0 → C, AC15 → AC14)</td><td class='alias'>АПРАВ</td></tr>" +
                "<tr><td><code>0600</code></td><td class='mnemonic'>SXTB</td><td class='flag-val'>*</td><td class='flag-val'>*</td><td class='flag-val'>0</td><td class='flag-val'>-</td><td>Расширение знака мл. байта (AC7 → AC15..AC8)</td><td class='alias'>РАСШ</td></tr>" +
                "<tr class='even'><td><code>0680</code></td><td class='mnemonic'>SWAB</td><td class='flag-val'>*</td><td class='flag-val'>*</td><td class='flag-val'>0</td><td class='flag-val'>-</td><td>Обмен ст. и мл. байта (AC7..AC0 ↔ AC15..AC8)</td><td class='alias'>НАОБОРОТ</td></tr>" +
                "<tr><td><code>0700</code></td><td class='mnemonic'>INC</td><td class='flag-val'>*</td><td class='flag-val'>*</td><td class='flag-val'>*</td><td class='flag-val'>*</td><td>AC + 1 → AC</td><td class='alias'>УВЕЛ</td></tr>" +
                "<tr class='even'><td><code>0740</code></td><td class='mnemonic'>DEC</td><td class='flag-val'>*</td><td class='flag-val'>*</td><td class='flag-val'>*</td><td class='flag-val'>*</td><td>AC - 1 → AC</td><td class='alias'>УМЕН</td></tr>" +
                "<tr><td><code>0780</code></td><td class='mnemonic'>NEG</td><td class='flag-val'>*</td><td class='flag-val'>*</td><td class='flag-val'>*</td><td class='flag-val'>*</td><td>(!AC) + 1 → AC</td><td class='alias'>ОТРИЦ</td></tr>" +
                "<tr class='even'><td><code>0800</code></td><td class='mnemonic'>POP</td><td class='flag-val'>*</td><td class='flag-val'>*</td><td class='flag-val'>0</td><td class='flag-val'>-</td><td>(SP)+ → AC (извлечение из стека)</td><td class='alias'>ВЫНЬ</td></tr>" +
                "<tr><td><code>0900</code></td><td class='mnemonic'>POPF</td><td class='flag-val'>*</td><td class='flag-val'>*</td><td class='flag-val'>*</td><td class='flag-val'>*</td><td>(SP)+ → PS (извлечение флагов)</td><td class='alias'>ВЫНЬФ</td></tr>" +
                "<tr class='even'><td><code>0A00</code></td><td class='mnemonic'>RET</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td>(SP)+ → IP (возврат из подпрограммы)</td><td class='alias'>ВОЗВР</td></tr>" +
                "<tr><td><code>0B00</code></td><td class='mnemonic'>IRET</td><td class='flag-val'>*</td><td class='flag-val'>*</td><td class='flag-val'>*</td><td class='flag-val'>*</td><td>(SP)+ → PS, (SP)+ → IP (возврат из прерывания)</td><td class='alias'>ВОЗВРП</td></tr>" +
                "<tr class='even'><td><code>0C00</code></td><td class='mnemonic'>PUSH</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td>AC → -(SP) (помещение в стек)</td><td class='alias'>СУНЬ</td></tr>" +
                "<tr><td><code>0D00</code></td><td class='mnemonic'>PUSHF</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td>PS → -(SP) (помещение флагов в стек)</td><td class='alias'>СУНЬФ</td></tr>" +
                "<tr class='even'><td><code>0E00</code></td><td class='mnemonic'>SWAP</td><td class='flag-val'>*</td><td class='flag-val'>*</td><td class='flag-val'>0</td><td class='flag-val'>-</td><td>Обмен содержимым AC и вершины стека</td><td class='alias'>МЕНЬ</td></tr>" +
                "</table>" +

                "<h3>Команды ввода-вывода (1XXX)</h3>" +
                "<table>" +
                "<tr><th>Код</th><th>Команда</th><th class='flag-header'>N</th><th class='flag-header'>Z</th><th class='flag-header'>V</th><th class='flag-header'>C</th><th>Описание</th><th>Русская альтернатива</th></tr>" +
                "<tr><td><code>10XX</code></td><td class='mnemonic'>DI</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td>Запрет прерывания</td><td class='alias'>НИЗЯ</td></tr>" +
                "<tr class='even'><td><code>11XX</code></td><td class='mnemonic'>EI</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td>Разрешение прерываний</td><td class='alias'>ЛЬЗЯ</td></tr>" +
                "<tr><td><code>12XX</code></td><td class='mnemonic'>IN REG</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td>Чтение из регистра ВУ REG</td><td class='alias'>ВВОД</td></tr>" +
                "<tr class='even'><td><code>13XX</code></td><td class='mnemonic'>OUT REG</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td>Запись в регистр ВУ REG</td><td class='alias'>ВЫВОД</td></tr>" +
                "<tr><td><code>18XX</code></td><td class='mnemonic'>INT NUM</td><td class='flag-val'>*</td><td class='flag-val'>*</td><td class='flag-val'>*</td><td class='flag-val'>*</td><td>Программное прерывание с вектором NUM</td><td class='alias'>ПРЕР</td></tr>" +
                "</table>" +

                "<h3>Адресные команды (XXXX)</h3>" +
                "<table>" +
                "<tr><th>Код</th><th>Команда</th><th class='flag-header'>N</th><th class='flag-header'>Z</th><th class='flag-header'>V</th><th class='flag-header'>C</th><th>Описание</th><th>Русская альтернатива</th></tr>" +
                "<tr><td><code>2XXX</code></td><td class='mnemonic'>AND M</td><td class='flag-val'>*</td><td class='flag-val'>*</td><td class='flag-val'>0</td><td class='flag-val'>-</td><td>M & AC → AC (побитовое И)</td><td class='alias'>И</td></tr>" +
                "<tr class='even'><td><code>3XXX</code></td><td class='mnemonic'>OR M</td><td class='flag-val'>*</td><td class='flag-val'>*</td><td class='flag-val'>0</td><td class='flag-val'>-</td><td>M | AC → AC (побитовое ИЛИ)</td><td class='alias'>ИЛИ</td></tr>" +
                "<tr><td><code>4XXX</code></td><td class='mnemonic'>ADD M</td><td class='flag-val'>*</td><td class='flag-val'>*</td><td class='flag-val'>*</td><td class='flag-val'>*</td><td>M + AC → AC</td><td class='alias'>ПЛЮС</td></tr>" +
                "<tr class='even'><td><code>5XXX</code></td><td class='mnemonic'>ADC M</td><td class='flag-val'>*</td><td class='flag-val'>*</td><td class='flag-val'>*</td><td class='flag-val'>*</td><td>M + AC + C → AC</td><td class='alias'>ПЛЮСC</td></tr>" +
                "<tr><td><code>6XXX</code></td><td class='mnemonic'>SUB M</td><td class='flag-val'>*</td><td class='flag-val'>*</td><td class='flag-val'>*</td><td class='flag-val'>*</td><td>AC - M → AC</td><td class='alias'>МИНУС</td></tr>" +
                "<tr class='even'><td><code>7XXX</code></td><td class='mnemonic'>CMP M</td><td class='flag-val'>*</td><td class='flag-val'>*</td><td class='flag-val'>*</td><td class='flag-val'>*</td><td>Установить флаги по результату AC - M</td><td class='alias'>СРАВ</td></tr>" +
                "<tr><td><code>8XXX</code></td><td class='mnemonic'>LOOP M</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td>M - 1 → M; Если M ≤ 0, то IP + 1 → IP</td><td class='alias'>КРУГ</td></tr>" +
                "<tr class='even'><td><code>AXXX</code></td><td class='mnemonic'>LD M</td><td class='flag-val'>*</td><td class='flag-val'>*</td><td class='flag-val'>0</td><td class='flag-val'>-</td><td>M → AC (загрузка в аккумулятор)</td><td class='alias'>НЯМ</td></tr>" +
                "<tr><td><code>BXXX</code></td><td class='mnemonic'>SWAM M</td><td class='flag-val'>*</td><td class='flag-val'>*</td><td class='flag-val'>0</td><td class='flag-val'>-</td><td>M ↔ AC (обмен памяти и аккумулятора)</td><td class='alias'>ОБМЕН</td></tr>" +
                "<tr class='even'><td><code>CXXX</code></td><td class='mnemonic'>JUMP M</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td>M → IP (переход по адресу)</td><td class='alias'>ПРЫГ</td></tr>" +
                "<tr><td><code>DXXX</code></td><td class='mnemonic'>CALL M</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td>SP - 1 → SP, IP → (SP), M → IP (вызов подпрограммы)</td><td class='alias'>ВЫЗВ</td></tr>" +
                "<tr class='even'><td><code>EXXX</code></td><td class='mnemonic'>ST M</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td>AC → M (запись из аккумулятора в память)</td><td class='alias'>ТУДА</td></tr>" +
                "</table>" +

                "<h3>Команды ветвления (FXXX)</h3>" +
                "<table>" +
                "<tr><th>Код</th><th>Команда</th><th class='flag-header'>N</th><th class='flag-header'>Z</th><th class='flag-header'>V</th><th class='flag-header'>C</th><th>Условие перехода</th><th>Русская альтернатива</th></tr>" +
                "<tr><td><code>F0XX</code></td><td class='mnemonic'>BEQ (BZS)</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td>Переход если равенство / нуль (Z == 1)</td><td class='alias'>БЯКА</td></tr>" +
                "<tr class='even'><td><code>F1XX</code></td><td class='mnemonic'>BNE (BZC)</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td>Переход если неравенство / не нуль (Z == 0)</td><td class='alias'>—</td></tr>" +
                "<tr><td><code>F2XX</code></td><td class='mnemonic'>BMI (BNS)</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td>Переход если минус (N == 1)</td><td class='alias'>—</td></tr>" +
                "<tr class='even'><td><code>F3XX</code></td><td class='mnemonic'>BPL (BNC)</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td>Переход если плюс (N == 0)</td><td class='alias'>—</td></tr>" +
                "<tr><td><code>F4XX</code></td><td class='mnemonic'>BHIS (BCS)</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td>Переход если выше или равно / перенос (C == 1)</td><td class='alias'>—</td></tr>" +
                "<tr class='even'><td><code>F5XX</code></td><td class='mnemonic'>BLO (BCC)</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td>Переход если ниже / нет переноса (C == 0)</td><td class='alias'>—</td></tr>" +
                "<tr><td><code>F6XX</code></td><td class='mnemonic'>BVS</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td>Переход если переполнение (V == 1)</td><td class='alias'>—</td></tr>" +
                "<tr class='even'><td><code>F7XX</code></td><td class='mnemonic'>BVC</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td>Переход если нет переполнения (V == 0)</td><td class='alias'>—</td></tr>" +
                "<tr><td><code>F8XX</code></td><td class='mnemonic'>BLT</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td>Переход если меньше (N ⊕ V == 1)</td><td class='alias'>—</td></tr>" +
                "<tr class='even'><td><code>F9XX</code></td><td class='mnemonic'>BGE</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td>Переход если больше или равно (N ⊕ V == 0)</td><td class='alias'>—</td></tr>" +
                "<tr><td><code>CE00</code></td><td class='mnemonic'>BR M</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td class='flag-val'>-</td><td>Безусловный относительный переход (синтетическая)</td><td class='alias'>—</td></tr>" +
                "</table>" +
                "</body></html>";
    }

    private String getDirectivesHtml() {
        return getHtmlHeader() +
                "<h2>Директивы ассемблера</h2>" +
                "<p>Директивы управляют процессом компиляции и резервированием памяти. Они не транслируются напрямую в процессорные инструкции, но влияют на размещение кода и данных в оперативной памяти.</p>" +

                "<h3><code>ORG адрес</code></h3>" +
                "<ul>" +
                "<li><b>Описание:</b> Указывает компилятору начальный адрес памяти для размещения последующего кода или данных. Похожа на пультовую команду «Ввод адреса» в базовой ЭВМ.</li>" +
                "<li><b>Пример:</b> <code>ORG 0x10</code> — последующий код начнется с адреса 16 (0x10).</li>" +
                "</ul>" +

                "<h3><code>[метка:] WORD значение [, значение ...]</code></h3>" +
                "<ul>" +
                "<li><b>Описание:</b> Используется для инициализации одной или нескольких ячеек памяти константами, переменными или адресами.</li>" +
                "<li>Если указан символ <code>?</code>, ячейка резервируется, но не инициализируется определенным значением (по умолчанию заполняется 0).</li>" +
                "<li>Если в качестве аргумента указано имя метки, в ячейку памяти будет записан абсолютный адрес этой метки.</li>" +
                "<li><b>Пример:</b> <code>VAR: WORD 5, 0x10, ?, $OTHER_LABEL</code></li>" +
                "</ul>" +

                "<h3><code>[метка:] WORD количество DUP (значение)</code></h3>" +
                "<ul>" +
                "<li><b>Описание:</b> Позволяет быстро создавать массивы и резервировать блоки памяти, дублируя указанное значение заданное количество раз.</li>" +
                "<li><b>Пример:</b> <code>ARRAY: WORD 10 DUP (0)</code> — объявляет массив из 10 ячеек памяти, заполненных нулями.</li>" +
                "<li><b>Пример:</b> <code>BUF: WORD 64 DUP (?)</code> — резервирует буфер размером 64 ячейки без инициализации конкретным значением.</li>" +
                "</ul>" +

                "<h3><code>END</code></h3>" +
                "<ul>" +
                "<li><b>Описание:</b> Обозначает конец исходного кода ассемблера. Компилятор прекращает обработку текста после обнаружения этой директивы. Она не производит машинного кода во время выполнения программы.</li>" +
                "</ul>" +
                "</body></html>";
    }

    private String getLabelsHtml() {
        return getHtmlHeader() +
                "<h2>Использование меток</h2>" +
                "<p>Метки — это символические имена, привязанные к определенным ячейкам памяти (инструкциям или переменным). Метки избавляют от необходимости использовать жестко закодированные абсолютные адреса.</p>" +

                "<h3>Синтаксис определения меток</h3>" +
                "<p>Метка определяется в начале строки и завершается двоеточием:</p>" +
                "<pre>    MY_LABEL: CLA\n" +
                "    VAR_X:    WORD 0xDEAD</pre>" +
                "<p>В дальнейшем имя метки можно использовать в качестве операнда для адресных команд (например, <code>ADD VAR_X</code>) или при объявлении указателей (например, <code>PTR: WORD $VAR_X</code>).</p>" +

                "<h3>Специальные метки</h3>" +
                "<ul>" +
                "<li><code><b>START</b></code> (регистр не важен): указывает компилятору на первую выполняемую команду программы (точку входа). " +
                "При загрузке скомпилированной программы в эмулятор, значение регистра IP (счетчика команд) будет автоматически установлено на адрес этой метки. " +
                "Если метка <code>START</code> отсутствует, то начальным адресом выполнения будет первый сгенерированный адрес памяти (заданный первой директивой <code>ORG</code>).</li>" +
                "<li><code><b>R</b></code>: традиционно используется для обозначения ячейки памяти, в которую записывается финальный результат вычислений программы.</li>" +
                "</ul>" +
                "</body></html>";
    }

    private String getAddressingHtml() {
        return getHtmlHeader() +
                "<h2>Режимы адресации</h2>" +
                "<p>Адресные команды поддерживают 7 различных способов указания операндов:</p>" +

                "<table>" +
                "<tr><th>Режим адресации</th><th>Синтаксис</th><th>Пример</th><th>Описание</th></tr>" +
                "<tr>" +
                "  <td><b>Прямая абсолютная</b></td>" +
                "  <td><code>адрес</code><br><code>$метка</code></td>" +
                "  <td><code>ADD 0x40</code><br><code>ADD $L</code></td>" +
                "  <td>Операнд находится в ячейке памяти по указанному абсолютному адресу.</td>" +
                "</tr>" +
                "<tr class='even'>" +
                "  <td><b>Косвенная относительная</b></td>" +
                "  <td><code>(метка)</code></td>" +
                "  <td><code>ADD (L)</code></td>" +
                "  <td>Операнд находится в памяти по адресу, хранящемуся в указанной ячейке.</td>" +
                "</tr>" +
                "<tr>" +
                "  <td><b>Косвенная автоинкрементная (постинкремент)</b></td>" +
                "  <td><code>(метка)+</code></td>" +
                "  <td><code>ADD (L)+</code></td>" +
                "  <td>Значение читается косвенно, после чего адрес в ячейке-указателе увеличивается на 1.</td>" +
                "</tr>" +
                "<tr class='even'>" +
                "  <td><b>Косвенная автодекрементная (предекремент)</b></td>" +
                "  <td><code>-(метка)</code></td>" +
                "  <td><code>ADD -(L)</code></td>" +
                "  <td>Адрес в ячейке-указателе сначала уменьшается на 1, а затем происходит косвенное чтение.</td>" +
                "</tr>" +
                "<tr>" +
                "  <td><b>Косвенная относительная, со смещением (SP)</b></td>" +
                "  <td><code>&смещение</code><br><code>(SP + смещение)</code></td>" +
                "  <td><code>ADD &N</code><br><code>ADD (SP+N)</code></td>" +
                "  <td>Операнд находится в стеке со смещением относительно текущего указателя стека SP.</td>" +
                "</tr>" +
                "<tr class='even'>" +
                "  <td><b>Прямая относительная</b></td>" +
                "  <td><code>метка</code><br><code>(IP + смещение)</code></td>" +
                "  <td><code>ADD L</code><br><code>ADD (IP+N)</code></td>" +
                "  <td>Операнд находится в памяти со смещением относительно счетчика команд IP.</td>" +
                "</tr>" +
                "<tr>" +
                "  <td><b>Прямая загрузка</b></td>" +
                "  <td><code>#число</code></td>" +
                "  <td><code>ADD #N</code></td>" +
                "  <td>Операндом является константное значение, встроенное непосредственно в команду.</td>" +
                "</tr>" +
                "</table>" +
                "</body></html>";
    }

    @Override
    public void panelActivate() {
    }

    @Override
    public void panelDeactivate() {
    }

    @Override
    public String getPanelName() {
        return cmanager.getRes().getString("help");
    }

    @Override
    public void redrawArrows() {
    }
}
