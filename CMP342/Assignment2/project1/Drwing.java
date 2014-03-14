package project1;
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

public class Drwing extends Applet{

buttonPanel btn = new buttonPanel();
WhiteBoard wb = new WhiteBoard();
public static Dimension d;
public void init() {
	
	setLayout(new BorderLayout());
	setSize(800, 600);
	add(btn,BorderLayout.EAST);
    add(wb,BorderLayout.CENTER);
    System.out.println("hell");
    

   
}

}






