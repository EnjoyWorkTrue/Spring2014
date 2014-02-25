package project1;
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Frame;

public class Drwing extends Applet{

buttonPanel btn = new buttonPanel();
WhiteBoard wb = new WhiteBoard();

public void init() {
	
	
	

	setLayout(new BorderLayout());
    add(btn,BorderLayout.EAST);
    add(wb,BorderLayout.CENTER);
    
    System.out.println(getX());
    System.out.println(getY());
   
   
}





}