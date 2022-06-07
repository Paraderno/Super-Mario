import java.awt.image.*;

public class Mario implements Runnable{
	// 坐标
	private int x;
	private int y;
	
	// 当前的状态
	private String status;
	
	// 当前状态对应的图像
	private BufferedImage show = null;
	
	// 障碍物
	private BackGround backGround = new BackGround();
	
	// 实现马里奥的动作
	private Thread thread = null;
	
	// 马里奥的移动速度
	private int xSpeed;
	
	// 马里奥的跳跃速度
	private int ySpeed;
	
	// 索引
	private int index;
	
	// 上升
	private int upTime = 0;
	
	// 门口
	private boolean isOK = false;
	
	// 是否死亡
	private boolean isDeath = false;
	
	// 分数
	private int score = 0;
	
	public Mario() {
		
	}
	
	public Mario(int x,int y) {
		this.x = x;
		this.y = y;
		show = StaticValue.stand_R;
		this.status = "stand--right";
		thread = new Thread(this);
		thread.start();
	}

	// 死亡
	public void death() {
		isDeath = true;
	}
	
	// 向左移动
	public void leftMove() {
		xSpeed = -5;
		if(backGround.isReach()) xSpeed = 0;
		if(status.indexOf("jump") != -1) status = "jump--left";
		else status = "move--left";
	}
	
	// 向右移动
	public void rightMove() {
		xSpeed = 5;
		if(backGround.isReach()) xSpeed = 0;
		if(status.indexOf("jump") != -1) status = "jump--right";
		else status = "move--right";
	}
	
	// 马里奥向左停止
	public void leftStop() {
		xSpeed = 0;
		if(status.indexOf("jump") != -1) status = "jump--left";
		else status = "stop--left";
		
	}
	
	// 马里奥右左停止
	public void rightStop() {
		xSpeed = 0;
		if(status.indexOf("jump") != -1) status = "jump--right";
		else status = "stop--right";
	}
	
	// 跳跃
	public void jump() {
		if(status.indexOf("jump") == -1) {
			if(status.indexOf("left") != -1) status = "jump--left";
			else status = "jump--right";
			ySpeed = -10;
			upTime = 8;
		}
		
		if(backGround.isReach()) ySpeed = 0;
	}
	
	// 下落
	public void fall() {
		if(status.indexOf("left") != -1) status = "jump--left";
		else status = "jump--right";
		ySpeed = 10;
	}
	
	public void run() {
		while(true) {
			boolean onObstacle = false;
			boolean canRight = true;
			boolean canLeft = true;
			if(backGround.getFlag() && this.x >= 500) {
				this.backGround.setReach(true);
				
				// 判断旗子
				if(this.backGround.isBase()) {
					status = "move--right";
					if(x < 690) x += 5;
					else isOK = true;
				}
				else {
					if(y < 395) {
						xSpeed = 0;
						this.y += 5;
						status = "jump--right";
					}
					else {
						this.y = 395;
						status = "stop--right";
					}
				}
			}
			else {
				// 障碍物
				for(int i = 0; i < backGround.getObstacleList().size(); i++) {
					Obstacle ob = backGround.getObstacleList().get(i);
					// 是否在障碍物上
					if(ob.getY() == this.y + 25 && ob.getX() > this.x - 30 && ob.getX() < this.x + 25) 
						onObstacle = true;
					
					// 是否顶到砖块 
					if(ob.getY() >= this.y - 30 && ob.getY() <= this.y - 20
						&& ob.getX() > this.x - 30 && ob.getX() < this.x + 25) {
						if(ob.getType() == 0) {
							backGround.getObstacleList().remove(ob);
							score += 1;	
						}
						upTime = 0;
					}
					
					// 是否可以往右走
					if(ob.getX() == this.x + 25 
							&& ob.getY() > this.y - 35 && ob.getY() < this.y + 25) 
						canRight = false;
					
					// 是否可以往左走
					if(ob.getX() == this.x - 30 
							&& ob.getY() > this.y - 35 && ob.getY() < this.y + 25) 
						canLeft = false;
				}
				
				// 与敌人
				for(int i = 0; i < backGround.getEnemyList().size(); i++) {
					Enemy e = backGround.getEnemyList().get(i);
					if(e.getY() == this.y + 20 
							&& e.getX() - 25 < this.x && e.getX() + 35 >= this.x) {
						if(e.getType() == 1) {
							e.death();
							score += 2;
							upTime = 3;
							ySpeed = -10;
						}
						else death();
					}
					if(e.getX() + 35 > this.x && e.getX() - 25 < this.x
							&& e.getY() + 35 > this.y && e.getY() - 20 < this.y) 
						death();
				}
				
				// 跳跃
				if(onObstacle && upTime == 0) {
					if(status.indexOf("left") != -1) {
						if(xSpeed !=0) status = "move--left";
						else status = "stop--left";
					}
					else {
						if(xSpeed !=0) status = "move--right";
						else status = "stop--right";
					}
				}
				else {
					if(upTime != 0) upTime--;
					else fall();
					y += ySpeed;
				}
			}
			
			if((canLeft && xSpeed < 0) || (canRight && xSpeed > 0)) {
				x += xSpeed;
				
				// 边界判断
				if(x < 0) x = 0;
			}
			// 是否在移动
			if(status.contains("move")) index = index == 0 ? 1 : 0;
			
			// 移动
			if("move--left".equals(status)) show = StaticValue.run_L.get(index);
			if("move--left".equals(status)) show = StaticValue.run_R.get(index);
			
			// 停止
			if("stop--left".equals(status)) show = StaticValue.stand_L;
			if("stop--right".equals(status)) show = StaticValue.stand_R;
			
			// 跳跃
			if("jump--left".equals(status)) show = StaticValue.jump_L;
			if("jump--right".equals(status)) show = StaticValue.jump_R;
				
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
	
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public void setBackGround(BackGround backGround) {
		this.backGround = backGround;
	}

	public boolean isOK() {
		return isOK;
	}

	public void setOK(boolean isOK) {
		this.isOK = isOK;
	}

	public boolean isDeath() {
		return isDeath;
	}

	public int getScore() {
		return score;
	}
}
