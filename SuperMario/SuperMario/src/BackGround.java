import java.awt.image.*;
import java.util.ArrayList;
import java.util.List;

public class BackGround {
	// 当前场景要显示的图像
	private BufferedImage bgImage = null;
	
	// 记录当前是第几个场景
	private int sort;
	
	// 判断当前是否为最后一个场景
	private boolean flag;
	
	// 用于存放所有的障碍物
	private List<Obstacle> obstacleList = new ArrayList<>();
	
	// 存放敌人
	private List<Enemy> enemyList = new ArrayList<>();
	
	// 显示旗杆
	private BufferedImage gan = null;
	
	// 显示城堡
	private BufferedImage tower = null;
	
	// 判断马里奥是否到旗杆
	private boolean isReach = false;
	
	// 判断旗子是否落地
	private boolean isBase = false;
	
	private void BackGround1() {
		// 绘制第一关的地面，上地面 type = 1，下地面 type = 2
		for(int i = 0; i < 27; i++) obstacleList.add(new Obstacle(i * 30, 420, 1, this));
		
		for(int j = 0; j <= 120; j += 30)
			for(int i = 0; i < 27; i++) 
				obstacleList.add(new Obstacle(i * 30, 570 - j, 2, this));
		
		// 绘制砖块
		for(int i = 120; i <= 150; i += 30) obstacleList.add(new Obstacle(i, 300, 7, this));
		for(int i = 300; i <= 570; i += 30) {
			if(i == 360 || i == 390 || i == 480 || i == 510 || i == 540) obstacleList.add(new Obstacle(i, 300, 7, this));
			else obstacleList.add(new Obstacle(i, 300, 0, this));
		}
		for(int i = 420; i <= 450; i += 30) obstacleList.add(new Obstacle(i, 240, 7, this));
		
		// 绘制水管
		for(int i = 360; i <= 600; i += 25) {
			if(i == 360) {
				obstacleList.add(new Obstacle(620, i, 3, this));
				obstacleList.add(new Obstacle(645, i, 4, this));
			}
			else {
				obstacleList.add(new Obstacle(620, i, 5, this));
				obstacleList.add(new Obstacle(645, i, 6, this));
			}
		}
		enemyList.add(new Mogu(580, 385, true, 1, this));
		enemyList.add(new Shirenhua(635, 420, true, 2, 328, 428));
	}
	
	private void BackGround2() {
		// 绘制第二关的地面，上地面 type = 1，下地面 type = 2
		for(int i = 0; i < 27; i++) obstacleList.add(new Obstacle(i * 30, 420, 1, this));
		for(int j = 0; j <= 120; j += 30)
			for(int i = 0; i < 27; i++) 
				obstacleList.add(new Obstacle(i * 30, 570 - j, 2, this));
		
		// 绘制水管
		for(int i = 360; i <= 600; i += 25) {
			if(i == 360) {
				obstacleList.add(new Obstacle(60, i, 3, this));
				obstacleList.add(new Obstacle(85, i, 4, this));
			}
			else {
				obstacleList.add(new Obstacle(60, i, 5, this));
				obstacleList.add(new Obstacle(85, i, 6, this));
			}
		}
		for(int i = 330; i <= 600; i += 25) {
			if(i == 330) {
				obstacleList.add(new Obstacle(590, i, 3, this));
				obstacleList.add(new Obstacle(615, i, 4, this));
			}
			else {
				obstacleList.add(new Obstacle(590, i, 5, this));
				obstacleList.add(new Obstacle(615, i, 6, this));
			}
		}
		
		// 绘制砖块
		obstacleList.add(new Obstacle(300, 330, 0, this));
		for(int i = 270; i <= 330; i += 30) {
			if(i == 270 || i == 330)obstacleList.add(new Obstacle(i, 360, 0, this));
			else obstacleList.add(new Obstacle(i, 360, 7, this));
		}
		for(int i = 240; i <= 360; i += 30) {
			if(i == 240 || i == 360) obstacleList.add(new Obstacle(i, 390, 0, this));
			else obstacleList.add(new Obstacle(i, 390, 7, this));
		}
		obstacleList.add(new Obstacle(240, 300, 0, this));
		for(int i = 360; i <= 540; i += 60) obstacleList.add(new Obstacle(i, 270, 7, this));
		enemyList.add(new Shirenhua(75, 420, true, 2, 330, 450));
		enemyList.add(new Shirenhua(600, 420, true, 2, 300, 420));
		enemyList.add(new Mogu(200, 385, true, 1, this));
		enemyList.add(new Mogu(500, 385, true, 1, this));
	}
	
	private void BackGround3() {
		// 绘制第三关的地面，上地面 type = 1，下地面 type = 2
		for(int i = 0; i < 27; i++) obstacleList.add(new Obstacle(i * 30, 420, 1, this));
		for(int j = 0; j <= 120; j += 30)
			for(int i = 0; i < 27; i++) 
				obstacleList.add(new Obstacle(i * 30, 570 - j, 2, this));
		
		// 绘制砖块
		int temp = 290;
		for(int i = 390; i >= 270; i -= 30) {
			for(int j = temp; j <= 410; j += 30)
				obstacleList.add(new Obstacle(j, i, 7, this));
			temp += 30;
		}
		
		temp = 60;
		for(int i = 390; i >= 360; i -= 30) {
			for(int j = temp; j <= 90; j += 30)
				obstacleList.add(new Obstacle(j, i, 7, this));
			temp += 30;
		}
		
		// 绘制旗杆
		gan = StaticValue.gan;
		
		// 绘制城堡
		tower = StaticValue.tower;
		
		// 添加旗子
		obstacleList.add(new Obstacle(515, 220, 8, this));
		
		enemyList.add(new Mogu(150, 385, true, 1, this));
	}
	public BackGround() {
		
	}
	public BackGround(int sort, boolean flag) {
		this.sort = sort;
		this.flag = flag;
		if(flag) bgImage = StaticValue.bg2;
		else bgImage = StaticValue.bg;
		
		// 判断是否是第一关
		if(sort == 1) BackGround1();

		// 判断是否是第二关
		if(sort == 2) BackGround2();

		// 判断是否是第三关
		if(sort == 3) BackGround3();
	}
	
	public List<Obstacle> getObstacleList() {
		return obstacleList;
	}

	public BufferedImage getBgImage() {
		return bgImage;
	}
	
	public int getSort() {
		return sort;
	}
	
	public boolean getFlag() {
		return flag;
	}

	public BufferedImage getGan() {
		return gan;
	}

	public BufferedImage getTower() {
		return tower;
	}

	public boolean isReach() {
		return isReach;
	}

	public void setReach(boolean isReach) {
		this.isReach = isReach;
	}

	public boolean isBase() {
		return isBase;
	}

	public void setBase(boolean isBase) {
		this.isBase = isBase;
	}

	public List<Enemy> getEnemyList() {
		return enemyList;
	}

	public void setEnemyList(List<Enemy> enemyList) {
		this.enemyList = enemyList;
	}
	
}
