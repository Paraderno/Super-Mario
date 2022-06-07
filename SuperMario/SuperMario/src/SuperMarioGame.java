import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;
import java.util.*;

public class SuperMarioGame extends JFrame implements KeyListener,Runnable {
	// 用于存储所有的背景
	private List<BackGround> allBg = new ArrayList<>();
	
	// 用于存储当前的背景
	private BackGround nowBg = new BackGround();
	
	// 双缓存
	private Image offScreenImage = null;
	
	// 马里奥对象
	private Mario mario = new Mario();
	
	// 线程对象，实现马里奥的运动
	private Thread thread = new Thread(this);
	
	
	public SuperMarioGame() {
		// 设置窗口x y 坐标
		this.setLocation(200,50);

		// 设置窗口大小
		this.setSize(800,600);
		
		// 设置窗口是否可见
		this.setVisible(true);
		
		// 设置窗口关闭程序就停止
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 设置窗口可移动
		this.setResizable(true);
		
		// 添加键盘监听器
		this.addKeyListener(this);
		
		//设置窗口名称
		this.setTitle("超级马里奥");
		
		// 初始化图片
		StaticValue.init();
		
		// 初始化马里奥
		mario = new Mario(10,355);
		
		// 创建全部的场景
		for(int i = 1; i <= 3; i++) {
			allBg.add(new BackGround(i, i == 3 ? true : false));
		}
		
		// 将第一个场景设置为当前场景
		nowBg = allBg.get(0);
		mario.setBackGround(nowBg);
		
		// 绘制图像
		repaint();
		thread.start();
	}
	
	// 重写
	public void paint(Graphics g) {
		if(offScreenImage == null) {
			offScreenImage = createImage(800,600);
		}
		
		Graphics graphics = offScreenImage.getGraphics();
		graphics.fillRect(0,0, 800, 600);
		
		// 绘制背景
		graphics.drawImage(nowBg.getBgImage(),0,0,this);
		
		// 绘制敌人
		for(Enemy e : nowBg.getEnemyList()) {
			graphics.drawImage(e.getShow(), e.getX(), e.getY(), this);
		}
		
		// 绘制障碍物
		for(Obstacle ob : nowBg.getObstacleList()) {
			graphics.drawImage(ob.getShow(), ob.getX(), ob.getY(), this);
		}
		
		// 绘制城堡
		graphics.drawImage(nowBg.getTower(), 620, 270, this);
		
		// 绘制旗杆
		graphics.drawImage(nowBg.getGan(), 500, 220, this);
		
		// 绘制马里奥
		graphics.drawImage(mario.getShow(), mario.getX(), mario.getY(), this);
		
		// 绘制分数
		Color c = graphics.getColor();
		graphics.setColor(Color.BLACK);
		graphics.setFont(new Font("黑体", Font.BOLD, 25));
		graphics.drawString("当前的分数为：" + mario.getScore(), 300, 100);
		graphics.setColor(c);

		// 将图片绘制到窗口
		g.drawImage(offScreenImage,0,0,this);
		
	}
	public static void main(String args[]) {
		SuperMarioGame superMarioGame = new SuperMarioGame();
	}

	public void keyTyped(KeyEvent e) {	
	}

	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == 39) {
			mario.rightMove();
		}
		if(e.getKeyCode() == 37) {
			mario.leftMove();
		}
		if(e.getKeyCode() == 38) {
			mario.jump();
		}
	}
	
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == 39) {
			mario.rightStop();
		}
		if(e.getKeyCode() == 37) {
			mario.leftStop();
		}
	}

	@Override
	public void run() {
		while(true) {
			repaint();
			try {
				Thread.sleep(50);		
				if(mario.getX() >= 775) {
					nowBg = allBg.get(nowBg.getSort());
					mario.setBackGround(nowBg);
					mario.setX(10);
					mario.setY(355);
				}
				
				// 是否死亡
				if(mario.isDeath()) {
					JOptionPane.showMessageDialog(this, "马里奥死亡！！！！");
					System.exit(0);
				}
				
				// 是否通关
				if(mario.isOK()) {
					JOptionPane.showMessageDialog(this, "通关");
					System.exit(0);
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
