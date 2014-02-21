package project1;

import java.awt.Button;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;

public class buttonPanel extends Panel implements ActionListener{

	
buttonPanel(){
		setLayout(new GridLayout(4,1));
		Button btn = new Button("Clear");
		Button btn1 = new Button("bigger");
		Button btn2 = new Button("smaller");
		Button btn3 = new Button("Color");
		add(btn);
		add(btn1);
		add(btn2);
		add(btn3);
		btn.addActionListener(this);
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="Clear"){		
		
				//Color originalColor = Drawing.backg.getColor();
				//backbuffer = createImage( 500, 500 );
				//backg = backbuffer.getGraphics();
				//backg.setColor(originalColor);			
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
		
	}
	
	

}
