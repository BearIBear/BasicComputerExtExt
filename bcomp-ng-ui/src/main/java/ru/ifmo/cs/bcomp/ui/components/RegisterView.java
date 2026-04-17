/*
 * $Id$
 */

package ru.ifmo.cs.bcomp.ui.components;

import ru.ifmo.cs.components.Utils;
import ru.ifmo.cs.components.DataDestination;
import ru.ifmo.cs.components.Register;

import javax.swing.*;
import java.awt.*;

//import ru.ifmo.cs.components.DataWidth;
import static ru.ifmo.cs.bcomp.ui.components.DisplayStyles.*;

/**
 *
 * @author Dmitry Afanasiev <KOT@MATPOCKuH.Ru>
 */
public class RegisterView extends BCompComponent implements DataDestination {
	private int formatWidth;
	private int valuemask;
	private boolean hex;
	private boolean isLeft;

	private final Register reg;
	protected final JLabel value = addValueLabel();
	
	// Optional decoded instruction label (for CR)
	private JLabel decodedLabel = null;
	private InstructionDecoder decoder = null;

	public JLabel getDecodedLabel() {
		return decodedLabel;
	}

	public RegisterView(Register reg, Color colorTitleBG) {
		super("", 0,colorTitleBG);

		this.reg = reg;
	}

	public RegisterView(Register reg) {
		this(reg, COLOR_TITLE);
	}
	protected void setBounds(int x, int y, int wight){

		setBounds(x,y,this.width=wight,height);

	}

	protected void setProperties(int x, int y, boolean hex, int regWidth,boolean isLeft) {
		this.hex = hex;
		this.formatWidth = regWidth;
		this.valuemask = (1 << regWidth) - 1;
		this.isLeft=isLeft;
		setBounds(x, y, getValueWidth(regWidth, false)+REG_TITLE_WIDTH - 10);
		setValue();

		title.setForeground(COLOR_TEXT);
		value.setForeground(COLOR_TEXT);
		if (!isLeft) {
			title.setBounds(1, 1, REG_TITLE_WIDTH, CELL_HEIGHT + 1);
			value.setBounds(REG_TITLE_WIDTH, 1, width - REG_TITLE_WIDTH - 1, CELL_HEIGHT + 1);
		} else {
			title.setBounds(width - 1 - REG_TITLE_WIDTH, 1, REG_TITLE_WIDTH, CELL_HEIGHT + 1);
			value.setBounds(1, 1, width - REG_TITLE_WIDTH - 3, CELL_HEIGHT + 1);
		}
	}

	public void setInstructionDecoder(InstructionDecoder decoder) {
		this.decoder = decoder;
		if (decodedLabel == null) {
			decodedLabel = new JLabel("", JLabel.LEFT);
			decodedLabel.setFont(DisplayStyles.FONT_COURIER_BOLD_18);
			decodedLabel.setForeground(DisplayStyles.COLOR_TEXT);
			// We DO NOT add it to the RegisterView itself. We provide it via getDecodedLabel()
			// so the parent layout can add it appropriately.
		}
	}

	public void setProperties(int x, int y, boolean hex,boolean isLeft) {
		setProperties(x, y, hex, (int)reg.width, isLeft);
	}

	protected long getRegWidth(){
		return reg.width;
	}

	protected void setValue(String val) {
		value.setText(val);
	}

	public void setValue() {
		setValue(hex ?
			Utils.toHex(reg.getValue() & valuemask, formatWidth) :
			Utils.toBinary((int)reg.getValue() & valuemask, formatWidth));
			
		if (decoder != null && decodedLabel != null) {
			decodedLabel.setText(decoder.decode((int) reg.getValue()));
		}
	}

	@Override
	public void setValue(long value) {
		setValue();
	}

	public Register getReg() {
		return reg;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);

	}
}
