import java.awt.image.*;
public interface Enemy extends Runnable{
	int getX();
	int getY();
	int getType();
	BufferedImage getShow();
	void death();
}