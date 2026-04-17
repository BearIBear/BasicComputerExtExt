/*
 * $Id$
 */

package ru.ifmo.cs.bcomp.ui.components;

import org.fife.ui.rsyntaxtextarea.AbstractTokenMakerFactory;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.Theme;
import org.fife.ui.rsyntaxtextarea.TokenMakerFactory;
import org.fife.ui.rtextarea.RTextScrollPane;
import ru.ifmo.cs.bcomp.CPU;
import ru.ifmo.cs.bcomp.ProgramBinary;
import ru.ifmo.cs.bcomp.assembler.AsmNg;
import ru.ifmo.cs.bcomp.assembler.Program;
import ru.ifmo.cs.bcomp.ui.GUI;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;

import static ru.ifmo.cs.bcomp.ui.components.DisplayStyles.*;

/**
 *
 * @author Dmitry Afanasiev <KOT@MATPOCKuH.Ru>
 */
public class AssemblerView extends BCompPanel implements ActionListener {
	private final GUI gui;
	private final CPU cpu;
	private final ComponentManager cmanager;
	private final RSyntaxTextArea  text;
	private final JTextArea errorarea;



	public AssemblerView(final GUI gui) {
		super (gui.getComponentManager(),null,null);
		this.gui = gui;
		this.cpu = gui.getCPU();
		this.cmanager = gui.getComponentManager();

		JPanel pane = new JPanel(new BorderLayout());
		pane.setBackground(COLOR_BACKGROUND);

		text = new RSyntaxTextArea();
		AbstractTokenMakerFactory atmf = (AbstractTokenMakerFactory) TokenMakerFactory.getDefaultInstance();
		atmf.putMapping(BCompTokenMaker.SYNTAX_STYLE_BCOMP_ASM,
				"ru.ifmo.cs.bcomp.ui.components.BCompTokenMaker");
		text.setSyntaxEditingStyle(BCompTokenMaker.SYNTAX_STYLE_BCOMP_ASM);
		text.setCodeFoldingEnabled(true);
		text.setBackground(COLOR_BACKGROUND);
		text.setForeground(COLOR_TEXT);
		RTextScrollPane  scroll = new RTextScrollPane(text);
		pane.add(scroll,BorderLayout.CENTER);
		Theme theme = null;
		try {
			theme = Theme.load(DisplayStyles.class.getClassLoader().getResourceAsStream("dark.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		theme.apply(text);

		// Keep the label registry updated so label references are highlighted
		text.getDocument().addDocumentListener(new DocumentListener() {
			private void update() {
				BCompTokenMaker.updateKnownLabels(text.getText());
				// Force re-tokenize so highlighting refreshes
				text.forceReparsing(0);
			}
			@Override public void insertUpdate(DocumentEvent e) { update(); }
			@Override public void removeUpdate(DocumentEvent e) { update(); }
			@Override public void changedUpdate(DocumentEvent e) { update(); }
		});

		JButton button = new JButton(cmanager.getRes().getString("compile"));
		button.setForeground(COLOR_TEXT);
		button.setBackground(COLOR_VALUE);
		setCustomButtonStyle(button);
		button.setFont(FONT_COURIER_PLAIN_12);
		button.setFocusable(false);
		button.addActionListener(this);
		JPanel buttonpane = new JPanel();
		//buttonpane.applyComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		buttonpane.add(button);
		//button.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		pane.add(buttonpane,BorderLayout.PAGE_START);
		errorarea = new JTextArea();
		//errorarea.setRows(3);
		errorarea.setEditable(false);
		errorarea.setBackground(COLOR_BACKGROUND);
		errorarea.setForeground(COLOR_TEXT);
		JScrollPane errscroll = new JScrollPane(errorarea);
		errscroll.setBackground(COLOR_BACKGROUND);
		pane.add(errscroll,BorderLayout.SOUTH);

		JSplitPane splitpane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, pane, errscroll);
		splitpane.setDividerSize(4);
		splitpane.setDividerLocation((int)(PANE_HEIGHT*0.8)); // TODO FIX ALL Layouts
		add(splitpane);

	}

	@Override
	public void panelActivate() {
		text.requestFocus();
	}

	@Override
	public void panelDeactivate() { }

	@Override
	public String getPanelName() {
		return cmanager.getRes().getString("assembler");
	}

	private void showError(String msg) {
		JOptionPane.showMessageDialog(gui, msg, cmanager.getRes().getString("error"), JOptionPane.ERROR_MESSAGE);
	}

	@Override
	public void redrawArrows() {
		//no arrows no draw
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (cpu.isLocked()) {
			showError(cmanager.getRes().getString("stopRunning"));
			return;
		}

		cmanager.saveDelay();
		boolean clock = cpu.getClockState();
		cpu.setClockState(true);
		long starttime = System.currentTimeMillis();
		AsmNg asm = new AsmNg(text.getText());
		Program pobj = null;
		try {
			pobj = asm.compile();
		} catch (Exception ex) {
			asm.getErrors().add("Internal compiler error: " + ex.getClass().getSimpleName() + ": " + ex.getMessage());
		}
		long finishtime = System.currentTimeMillis();
		String errors = new String();
		String st = "Start compilation at "+new Date(starttime)+"\n";
		String ft = "Finish compilation at "+new Date(finishtime)+"\n";
		errors = st;
		for (String err: asm.getErrors()) {
			errors = errors + err + '\n';
		}

		errors = errors + ft;
		if(asm.getErrors().isEmpty() && pobj != null) errors = errors + "\n" + pobj.toCompiledWords();

		errorarea.setText(errors);
		if (pobj != null && asm.getErrors().isEmpty()) {
			gui.getBasicComp().loadProgram(new ProgramBinary(pobj.getBinaryFormat()));
			gui.getInstructionDecoder().setProgram(pobj);
		} else {
			gui.getInstructionDecoder().clearProgram();
		}

		cpu.setClockState(clock);
		cmanager.clearActiveSignals();
		cmanager.restoreDelay();
	}

	@Override
	public void paintComponent(Graphics g) {

	}
}

