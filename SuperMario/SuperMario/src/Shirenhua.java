import java.awt.image.BufferedImage;

public class Shirenhua implements Enemy{
	// 坐标
	private int x;
	private int y;
	
	// 类型
	private int type;
	
	// 移动方向
	private boolean face_to = true;
	
	// 图像
	private BufferedImage show;
	
	// 线程
	private Thread thread = new Thread(this);

	// 图片装填
	private int image_type ;
	
	// 食人花的运动范围
	private int max_up = 0;
	private int max_down = 0;
	
	// 食人花
	public Shirenhua(int x,int y,boolean face_to, int type, int max_up, int max_down) {
		this.x = x;
		this.y = y;
		this.face_to = face_to;
		this.type = type;
		this.max_up = max_up;
		this.max_down = max_down;
		show = StaticValue.shirenhua.get(0);
		thread.start();
	}

	@Override
	public void run() {
		while(true) {
			if(face_to) this.y -= 2;
			else this.y += 2;
			image_type = image_type == 1 ? 0 : 1;
			if(face_to && (this.y == max_up)) face_to = false;
			else if((!face_to) && (this.y == max_down)) face_to = true;
			show = StaticValue.shirenhua.get(image_type);
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

	public void death() {
	}

}
