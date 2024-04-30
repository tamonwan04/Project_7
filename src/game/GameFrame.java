package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameFrame extends JPanel implements ActionListener {
	
	static Menu home;

	public static void main(String[] args) {
		
		JFrame f = new JFrame(); //สร้าง JFrame เพื่อใช้เป็นหน้าต่างหลักของเกม
		f.setTitle("Hungry Snowman"); //กำหนดหัวเรื่องของหน้าต่าง
		f.setBounds(0, 0, 589, 590); //กำหนดขนาดและตำแหน่งของหน้าต่าง
		f.setResizable(false); //กำหนดให้ไม่สามารถปรับขนาดหน้าต่างได้
		f.setVisible(true); //ทำให้หน้าต่างของ JFrame ปรากฏบนหน้าจอ
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //กำหนดการปิดโปรแกรมเมื่อปิดหน้าต่าง
		f.setLocationRelativeTo(null); //กำหนดให้หน้าต่างปรากฏตรงกลางของหน้าจอ
		
		home = new Menu();
		f.add(home);
		
		home.startButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GameMap gamemap = new GameMap(); //สร้าง object จากคลาส MapGame เพื่อเรียกใช้งานหน้าต่างเกม
				f.getContentPane().removeAll();
				f.add(gamemap); //เพิ่ม MapGame ลงใน JFrame เพื่อแสดงผลบนหน้าต่างเกม	
				gamemap.requestFocusInWindow();
				gamemap.backButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						f.getContentPane().removeAll();
						f.add(home) ;
						f.setVisible(true); //ทำให้หน้าต่างของ JFrame ปรากฏบนหน้าจอ
						f.revalidate();
						f.repaint();
						}
					});
				gamemap.backButton.setVisible(true);
				f.setVisible(true); //ทำให้หน้าต่างของ JFrame ปรากฏบนหน้าจอ
				f.revalidate();
				f.repaint();
			}
		});
		
		home.exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				}
			});
		}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		}
	}