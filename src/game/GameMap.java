package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameMap extends JPanel implements KeyListener, ActionListener {
	
	JButton backButton;
	
	private static int[] snowmandx_length = new int [550]; //เก็บตำแหน่ง x ของ Snowman 
	private static int[] snowmandy_length = new int [550]; //เก็บตำแหน่ง y ของ Snowman 
	private static int lengthOfSnowman = 1;
	
	//เก็บตำแหน่งที่เป็นไปได้สำหรับการวาง Food โดยมีค่าตำแหน่ง x เริ่มต้นที่ 10
	private static int[] Posx = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150, 160, 170, 180, 190, 200, 210, 220, 230, 240, 250, 260, 270, 280, 290, 300, 310, 320, 330, 340, 350, 360, 370, 380, 390, 400, 410, 420, 430, 440, 450, 460, 470, 480, 490, 500, 510, 520, 530, 540, 550, 560, 570, 580};
	//เก็บตำแหน่งที่เป็นไปได้สำหรับการวาง Food โดยมีค่าตำแหน่ง y เริ่มต้นที่ 30
	private static int[] Posy = {30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150, 160, 170, 180, 190, 200, 210, 220, 230, 240, 250, 260, 270, 280, 290, 300, 310, 320, 330, 340, 350, 360, 370, 380, 390, 400, 410, 420, 430, 440, 450, 460, 470, 480, 490, 500, 510, 520, 530, 540, 550, 560, 570};
	
	private static Random random = new Random(); //สร้างตัวเลขสุ่ม
	private static int foodX; //เก็บตำแหน่ง x ของ Food
	private static int foodY; // เก็บตำแหน่ง y ของ Food
	
	private int speed = 0;
	
	// จับการเคลื่อนไหวของผู้เล่น
	private boolean right = false;
	private boolean left = false;
	private boolean up = false;
	private boolean down = false;
	
	private int move = 0; //เก็บจำนวนการเคลื่อนที่ของ Snowman
	private int score = 0; //เก็บคะแนน
	private boolean gameOver = false;
	
	
	private ImageIcon Background, Leftsnowman, Rightsnowman, Upsnowman, Downsnowman, Snowball, Food;
	
	private Timer timer;
	private int delay = 75;
	
	public GameMap() {
		gameOver = false ;
		backButton = new JButton("Back");
		backButton.setBounds(190,200,200,50);
		setVisible(true);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(true);
		
		timer = new Timer(delay, this);
		timer.start();
		
		newFood();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g); //เรียกใช้เมธอด paint ของคลาสแม่ (JPanel)
		
        if (move == 0) {
            snowmandx_length[2] = 50;
            snowmandx_length[1] = 75;
            snowmandx_length[0] = 100;

            snowmandy_length[2] = 100;
            snowmandy_length[1] = 100;
            snowmandy_length[0] = 100;
        }
        
		Background = new ImageIcon ("Background.png"); //โหลดรูปภาพจากไฟล์ "Background.png"
		Background.paintIcon(this, g, 0, 0); //วาดรูปภาพลงบน JPanel ที่ตำแหน่ง x=0 และ y=0
		
		if(left)
		{
			ImageIcon ls = new ImageIcon ("Leftsnowman.png");
			Image originalImage = ls.getImage();
			Image scaledImage = originalImage.getScaledInstance(295, 295, Image.SCALE_DEFAULT);
			Leftsnowman = new ImageIcon (scaledImage);
			Leftsnowman.paintIcon(this, g, snowmandx_length[0]+30, snowmandy_length[0]+10);
		}
		if(right)
		{
			ImageIcon ls = new ImageIcon("Rightsnowman.png") ;
			Image originalImage = ls.getImage();
			Image scaledImage = originalImage.getScaledInstance(295, 295, Image.SCALE_DEFAULT);
			Rightsnowman = new ImageIcon (scaledImage);
			Rightsnowman.paintIcon(this, g, snowmandx_length[0], snowmandy_length[0]+10);
		}
		if(up)
		{
			ImageIcon ls = new ImageIcon("Upsnowman.png") ;
			Image originalImage = ls.getImage();
			Image scaledImage = originalImage.getScaledInstance(295, 295, Image.SCALE_DEFAULT);
			Upsnowman = new ImageIcon (scaledImage);
			Upsnowman.paintIcon(this, g, snowmandx_length[0]+30, snowmandy_length[0]);
		}
		if(down)
		{
			ImageIcon ls = new ImageIcon("Downsnowman.png") ;
			Image originalImage = ls.getImage();
			Image scaledImage = originalImage.getScaledInstance(295, 295, Image.SCALE_DEFAULT);
			Downsnowman = new ImageIcon (scaledImage);
			Downsnowman.paintIcon(this, g, snowmandx_length[0], snowmandy_length[0]+10);
		}
		
		for(int i = 1; i < lengthOfSnowman; i++)
		{
			ImageIcon SBicon = new ImageIcon("Food.png") ;
			Image SBoriginalImage = SBicon.getImage();
			Image SBscaledImage = SBoriginalImage.getScaledInstance(295, 295, Image.SCALE_DEFAULT);
			Snowball = new ImageIcon (SBscaledImage);
			Snowball.paintIcon(this, g, snowmandx_length[i], snowmandy_length[i]);
		}
		ImageIcon ficon = new ImageIcon("Food.png") ;
		Image foriginalImage = ficon.getImage();
		Image fscaledImage = foriginalImage.getScaledInstance(295, 295, Image.SCALE_DEFAULT);
		Food = new ImageIcon (fscaledImage);
		Food.paintIcon(this, g, foodX, foodY);
		
		if(gameOver) {
			g.setColor(Color.BLACK);
			g.setFont(new Font("Arial", Font.BOLD, 50));
			g.drawString("Game Over", 149, 285);
			add(backButton);
			revalidate();
			repaint();
		}
		
		g.setColor(Color.GRAY);
		g.setFont(new Font("Arial", Font.PLAIN, 14));
		g.drawString("Score : " + score, 460, 40);
		g.dispose();
	}
	//ควบคุมการเคลื่อนที่ของ Snowman
	@Override
	public void actionPerformed(ActionEvent e) {
		
		for(int i = lengthOfSnowman - 1; i > 0; i--)
		{
			snowmandx_length[i] = snowmandx_length[i - 1];
			snowmandy_length[i] = snowmandy_length[i - 1];
		}
		
		if(left)
		{
			snowmandx_length[0] = snowmandx_length[0] - 15 - speed; //เคลื่อนที่ Snowman ไปทางซ้ายโดยลดค่า snowmandx_length[0] ลง 15 เมื่อกดปุ่มลูกศรซ้าย
		}
		if(right)
		{
			snowmandx_length[0] = snowmandx_length[0] + 15 + speed;
		}
		if(up)
		{
			snowmandy_length[0] = snowmandy_length[0] - 15 - speed;
		}
		if(down)
		{
			snowmandy_length[0] = snowmandy_length[0] + 15 + speed;
		}
		
	     if (snowmandx_length[0] < -100 || snowmandx_length[0] >= getWidth() || snowmandy_length[0] < -100 || snowmandy_length[0] >= getHeight()) {
	    	 gameOver = true;
	    	 timer.stop();
	     }
	     
	     collidesWithFood(); //ตรวจสอบการชนของ Snowman กับ Food
	     collidesWithBody(); //ตรวจสอบการชนของ Snowman กับ Snowball
	     
	     repaint();
	     
	}
	
	//การกดปุ่มลูกศรบนคีย์บอร์ด
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			restart(); //เริ่มเกมใหม่เมื่อกด spacebar
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT && (!right))
		{
			left = true;
			right = false;
			up = false;
			down = false;
			move++;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT && (!left))
		{
			left = false;
			right = true;
			up = false;
			down = false;
			move++;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP && (!down))
		{
			left = false;
			right = false;
			up = true;
			down = false;
			move++;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_DOWN && (!up))
		{
			left = false;
			right = false;
			up = false;
			down = true;
			move++;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub เมธอดที่เรียกเมื่อมีการปล่อยปุ่มบนแป้นพิมพ์ (ไม่มีการใช้งาน)
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub เมธอดที่เรียกเมื่อมีการพิมพ์ตัวอักษรหรือสัญลักษณ์บนแป้นพิมพ์ (ไม่มีการใช้งาน)
	}
	
	//สร้างตำแหน่งใหม่เพื่อสุ่มวาง Food และเช็คว่า Food จะไม่ถูกสร้างขึ้นในพื้นที่ใกล้กับ snowman
	public static void newFood() {
		foodX = Posx[random.nextInt(34)];
		foodY = Posy[random.nextInt(26)];
		
		for(int i = lengthOfSnowman - 1; i >= 0; i--) {
			if(snowmandx_length[i] == foodX && snowmandy_length[i] == foodY) {
				newFood();
			}
		}
	}
	
	//ตรวจสอบตำแหน่งของ Snowman และตำแหน่งของ Food ว่าอยู่ใกล้กันหรือไม่ (การตรวจสอบตำแหน่งของ Food ยังไม่ตรงกับตำแหน่งปัจจุบันของ Snowman)
	private void collidesWithFood() {
		//หากตำแหน่งของ Snowman และ Food อยู่ใกล้กันมากกว่าหรือเท่ากับ 15 ทั้งแกน x และ y จะถือว่ามีการชนกัน
		if(Math.abs(snowmandx_length[0] - foodX) <= 15 && Math.abs(snowmandy_length[0] - foodY) <= 15) {
			newFood();
			lengthOfSnowman++;
			score++;
			speed += 1;
		}
	}
	
	//ส่วนขยายในการตรวจสอบว่ามีการชนกันระหว่าง Snowman ในตำแหน่งที่ 0 กับ Snowball
	private void collidesWithBody( ) {
		for(int i = 1; i < lengthOfSnowman; i++) {
			if(snowmandx_length[i] == snowmandx_length[0] && snowmandy_length[i] == snowmandy_length[0]) {
				timer.stop();
				gameOver = true;
				break;
			}
		}
	}
	//การรีเซ็ตสถานะของเกมเพื่อเริ่มเกมใหม่
	private void restart() {
		gameOver = false;
		move = 0;
		score = 0;
		speed = 0;
		lengthOfSnowman = 1;
		left = false;
		right = true;
		up = false;
		down = false;
		timer.start();
		
	    //ตั้งตำแหน่งเริ่มต้นของ Snowman ที่กลางจอ
	    snowmandx_length[0] = getWidth() / 2;
	    snowmandy_length[0] = getHeight() / 2;

		
		repaint();
		
	}

}