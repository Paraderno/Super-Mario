import java.awt.image.BufferedImage;

public class Mogu implements Enemy{
	// 坐标
	private int x;
	private int y;
	
	// 类型
	private int type;
	
	// 移动方向
	private boolean face_to = true;
	
	// 图像
	private BufferedImage show;
	
	// 背景
	private BackGround backGround;

	// 线程
	private Thread thread = new Thread(this);
	
	// 图片装填
	private int image_type ;
	
	public Mogu(int x,int y,boolean face_to, int type, BackGround backGround) {
		this.x = x;
		this.y = y;
		this.face_to = face_to;
		this.type = type;
		this.backGround = backGround;
		show = StaticValue.mogu.get(0);
		thread.start();
	}
	
	// 死亡方法
	public void death() {
		show = StaticValue.mogu.get(2);
		try {
			Thread.sleep(50);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		this.backGround.getEnemyList().remove(this);
	}

	@Override
	public void run() {
		while(true) {
			if(face_to) this.x -= 2;
			else this.x += 2;
			image_type = image_type == 1 ? 0 : 1;
			
			// 能否移动
			boolean canLeft = true;
			boolean canRight = true;
			for(int i = 0; i < backGround.getObstacleList().size(); i++) {
				Obstacle ob = backGround.getObstacleList().get(i);		
				
				// 是否可以往右走
				if(ob.getX() == this.x + 36 
						&& ob.getY() > this.y - 65 && ob.getY() < this.y + 35) 
					canRight = false;
				
				// 是否可以往左走
				if(ob.getX() == this.x - 36 
						&& ob.getY() > this.y - 65 && ob.getY() < this.y + 35) 
					canLeft = false;
				
				if(face_to && (!canLeft || this.x == 0))face_to = false;
				else if((!face_to) && (!canRight || this.x == 764)) face_to = true;
			}	
			show = StaticValue.mogu.get(image_type);		
			try {
				Thread.sleep(50);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public BufferedImage getShow() {
		return show;
	}

	public int getType() {
		return type;
	}
}
