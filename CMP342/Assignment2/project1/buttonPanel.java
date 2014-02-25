package project1;

import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;

public class buttonPanel extends Panel implements ActionListener{

	
buttonPanel(){
		setLayout(new GridLayout(5,1));
		Button btn = new Button("Clear");
		Button btn1 = new Button("bigger");
		Button btn2 = new Button("smaller");
		Button btn3 = new Button("Color");
		Button btn4 = new Button("Text");
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
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="Clear"){		
			Applet app = (Applet)WhiteBoard.a.getSource();
			Graphics g = app.getGraphics();
			g.setColor(Color.white);
			g.fillRect(0, 0,app.getSize().width,app.getSize().height);
							
		}
		if(e.getActionCommand()=="bigger"){
			WhiteBoard.bigger();
		}
		if(e.getActionCommand()=="smaller")
		{
			WhiteBoard.smaller();
		}
		if(e.getActionCommand()=="Color"){
			WhiteBoard.changeColor(JColorChooser.showDialog(this,"CHOOSE",WhiteBoard.getColor()));
			//x.changeColor(JColorChooser.showDialog(this,"CHOOSE",Color.red));
		}
		if(e.getActionCommand()=="Text"){
			WhiteBoard.chageTextColor(JColorChooser.showDialog(this,"CHOOSE",WhiteBoard.getTextColor()));
		}
		
	}
	
	

}
