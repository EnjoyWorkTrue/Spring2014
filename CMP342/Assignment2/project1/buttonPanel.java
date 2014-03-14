package project1;

import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;

public class buttonPanel extends Panel implements ActionListener{
Button btn3;
Button btn4;
buttonPanel(){
		setLayout(new GridLayout(5,1));
		Button btn = new Button("Clear");
		Button btn1 = new Button("bigger");
		Button btn2 = new Button("smaller");
		btn3 = new Button("Pen");
		btn4	= new Button("Text");
		add(btn);
		add(btn1);
		add(btn2);
		add(btn3);
		add(btn4);
		btn.addActionListener(this);
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);
		btn4.addActionListener(this);
	}

public void changeButtonColor(Color c){
	btn3.setBackground(c);
}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="Clear"){
			WhiteBoard.eraseEverything();
							
		}
		if(e.getActionCommand()=="bigger"){
			WhiteBoard.bigger();
		}
		if(e.getActionCommand()=="smaller")
		{
			WhiteBoard.smaller();
		}
		if(e.getActionCommand()=="Pen"){
			
			WhiteBoard.changeColor( JColorChooser.showDialog(this,"CHOOSE",WhiteBoard.getColor()));
			changeButtonColor(WhiteBoard.getColor());
			
			
		}
		if(e.getActionCommand()=="Text"){
			Color c = JColorChooser.showDialog(this,"CHOOSE",WhiteBoard.getTextColor());
			WhiteBoard.chageTextColor(c);
			changeTextButtonColor(c);
		}
		
	}

	private void changeTextButtonColor(Color c) {
		btn4.setBackground(c);
		
	}
	
	

}
