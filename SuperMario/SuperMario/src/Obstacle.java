import java.awt.image.*;

public class Obstacle implements Runnable{
	// 坐标
	private int x;
	private int y;
	
	// 记录障碍物的类型
	private int type;
	
	// 显示图像
	private BufferedImage show = null;
	
	// 定义当前的场景对象
	private BackGround backGround = null;
	
	// 线程
	private Thread thread = new Thread(this);
	
	public Obstacle(int x,int y,int type, BackGround backGround) {
		this.x = x;
		this.y = y;
		this.type = type;
		this.backGround = backGround;
		show = StaticValue.obstacle.get(type);
		if(type == 8) {
			thread.start();
		}
	}
	
	@Override
	public void run() {
		while(true) {
			if(this.backGround.isReach()) {
				if(this.y < 374) {
					this.y += 5;
				}
				else 
					this.backGround.setBase(true);
			}
			
			try {
				Thread.sleep(50);
			}
			catch (Exception e){
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

	public int getType() {
		return type;
	}

	public BufferedImage getShow() {
		return show;
	}

	public BackGround getBg() {
		return backGround;
	}
}
