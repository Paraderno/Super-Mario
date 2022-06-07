import java.awt.image.*;
import java.io.*;
import java.util.*;

import javax.imageio.*;

public class StaticValue {
	// 背景
	public static BufferedImage bg = null;
	public static BufferedImage bg2 = null;
	
	// 马里奥向左跳跃
	public static BufferedImage jump_L = null;
	
	// 马里奥向右跳跃
	public static BufferedImage jump_R = null;

	// 马里奥向左站立
	public static BufferedImage stand_L = null;
	
	// 马里奥向右站立
	public static BufferedImage stand_R = null;

	// 城堡
	public static BufferedImage tower = null;
	
	// 旗杆
	public static BufferedImage gan = null;
	
	// 障碍物
	public static List<BufferedImage> obstacle = new ArrayList<>();
	
	// 马里奥向左跑
	public static List<BufferedImage> run_L = new ArrayList<>();

	// 马里奥向右跑
	public static List<BufferedImage> run_R = new ArrayList<>();

	// 蘑菇仔
	public static List<BufferedImage> mogu = new ArrayList<>();

	// 食人花
	public static List<BufferedImage> shirenhua = new ArrayList<>();

	// 绝对路径的前缀
	public static String path = System.getProperty("user.dir") + "/src/images/";
	
	// 初始化方法
	public static void init() {
		try {
			// 加载背景图片
			bg = ImageIO.read(new File(path + "bg.png"));
			bg2 = ImageIO.read(new File(path + "bg2.png"));
			
			// 加载马里奥向左站立
			stand_L = ImageIO.read(new File(path + "s_mario_stand_L.png"));

			// 加载马里奥向右站立
			stand_R = ImageIO.read(new File(path + "s_mario_stand_R.png"));

			// 加载城堡
			tower = ImageIO.read(new File(path + "tower.png"));

			// 加载旗杆
			gan = ImageIO.read(new File(path + "gan.png"));

			// 加载马里奥向左跳跃
			jump_L = ImageIO.read(new File(path + "s_mario_jump1_L.png"));

			// 加载马里奥向右跳跃
			jump_R = ImageIO.read(new File(path + "s_mario_jump1_R.png"));
			
			// 加载马里奥向左跑
			for(int i = 1; i <= 2; i++) {
				run_L.add(ImageIO.read(new File(path + "s_mario_run" + i + "_L.png")));
			}
			
			// 加载马里奥向右跑
			for(int i = 1; i <= 2; i++) {
				run_R.add(ImageIO.read(new File(path + "s_mario_run" + i + "_R.png")));
			}
			
			// 加载障碍物图片
			obstacle.add(ImageIO.read(new File(path + "brick.png")));
			obstacle.add(ImageIO.read(new File(path + "soil_up.png")));
			obstacle.add(ImageIO.read(new File(path + "soil_base.png")));
			
			// 加载水管
			for(int i = 1; i <= 4; i++) {
				obstacle.add(ImageIO.read(new File(path + "pipe" + i + ".png")));
			}
			
			// 加载不可破坏的砖块和旗子
			obstacle.add(ImageIO.read(new File(path + "brick2.png")));
			
			// 加载旗子
			obstacle.add(ImageIO.read(new File(path + "flag.png")));
			
			// 加载蘑菇仔
			for(int i = 1; i <= 3; i++) {
				mogu.add(ImageIO.read(new File(path + "mogu" + i + ".png")));
			}
			
			// 加载食人花
			for(int i = 1; i <= 2; i++) {
				shirenhua.add(ImageIO.read(new File(path + "shirenhua" + i + ".png")));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
