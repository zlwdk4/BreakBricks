import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Ball {

	private static final int WIDTH = 20, HEIGHT = 20, BALLSPEED = 3; 
	private GameFrame game; 
	private int x, y, xa = -5, ya = 2 * BALLSPEED;
	
	public Ball(GameFrame gFrame){
		this.game = gFrame; 
		x = game.getWidth() / 2; 
		y = game.getHeight() / 2; 
	}
	
	public int getBallWidth(){
		return WIDTH / 2; 
	}
	
	
	public void update(){
		x += xa;
		y += ya; 
		if(x < 0 || x > game.getWidth() - WIDTH){
			xa = -xa;
		}
		if(y > game.getHeight()){
			game.lose();
			System.out.println("you lose");
		}
		if(y < 0){
			ya = -ya;
		}
		
		checkForPaddleCollision();
		
	}
	
	public void checkForPaddleCollision(){
		if(game.getPanel().getPaddle().getBounds().intersects(getBounds())){
			//75  - 100
			String paddleSectionHit = game.getPanel().getPaddle().getPaddleSectionHit(x);
			//0 - 25
			int paddleSectionHitInt = game.getPanel().getPaddle().getPaddleSectionHitInt(x);
			paddleSectionHitInt += 5; 
			System.out.println( "paddle hit num: " + paddleSectionHitInt);
			System.out.println("pre xa: " + xa);
			ya = -ya;				
			System.out.println(paddleSectionHit);
			if(paddleSectionHit == "right"){
				if(xa <= 0){
					xa *= -1;
					
					System.out.println("post xa: " + xa);
					
				}
			}
			if(paddleSectionHit == "left"){
				paddleSectionHitInt += 15;
				double pdoub = (double) paddleSectionHitInt / 25;
				System.out.println("pdoub" + pdoub);
				if(xa >= 0){
					
					xa *= -1;
					System.out.println("post xa: " + xa);
				}
			}
			
		}
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}



	public void paint(Graphics g){
		//this sets the color of the ball
		//System.out.println("x: " + Integer.toString(x));
		//System.out.println("y: " + Integer.toString(y));
		g.setColor(Color.BLACK);
		g.fillRect(x, y, WIDTH, HEIGHT);
		//this sets the color of the paddle 
		g.setColor(Color.BLUE);
	}

	public int getBallX() {
		return x;
	}

	public int getBallY() {
		return y;
	}

	public int getBallXA() {
		return xa;
	}

	public int getBallYA() {
		return ya;
	}

	public void inverseYA() {
		ya = -ya;
	}
}
