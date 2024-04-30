package game;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Menu extends JPanel implements ActionListener {
	
    JButton startButton, backButton, exitButton;
    
    private ImageIcon backgroundImage;
    
    public Menu() {
    	
        startButton = new JButton("Start");
        startButton.setBounds(190,200,200,50);
        
        backButton = new JButton("Exit");
        backButton.setBounds(190,270,200,50);

        exitButton = new JButton("Exit");
        exitButton.setBounds(190,270,200,50);   
        setVisible(true);
        add(exitButton);
        add(startButton);
        
    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g); // วาดรูปภาพเป็นพื้นหลัง
	    backgroundImage = new ImageIcon ("Hungrysnowman.png"); //โหลดรูปภาพจากไฟล์ "Background.png"
		backgroundImage.paintIcon(this, g, 0, 0);
		}
	}