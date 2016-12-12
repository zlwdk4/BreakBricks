import java.awt.Graphics;
import java.awt.Rectangle;

public class Paddle {

	public static final int WIDTH = 100, HEIGHT = 2, YDIST = 400, PADDLESPEED = 15;
	private static final int LEFTONPADDLE = (int) ((int) WIDTH * 0.25) - 10;
	private static final int RIGHTONPADDLE = (int) ((int) WIDTH * 0.75);
	private GameFrame game;
	private int LEFTKEY = 37, RIGHTKEY = 39;
	private int x, xa;
	private int y;
	
	
	
	public Paddle(GameFrame game){
		this.game = game;
		//this.y = y;
		x = game.getWidth() / 2; 
		y = game.getHeight() - 50;
		//this.left = left;
		//this.right = right;
		System.out.println("left on paddle: " + LEFTONPADDLE);
		System.out.println("right on paddle: " + RIGHTONPADDLE);
	}
	
	
	public void update(){
		//System.out.println(Integer.toString(x));
		if(x > 0 && x < game.getWidth() - WIDTH){
			x += xa;
			if(x < 0){
				x = 0;
			}
			else if(x > game.getWidth() - WIDTH){
				x = game.getWidth() - WIDTH;
			}
		}
		else if(x == 0){
			x++;
		}
		else if(x == game.getWidth() - WIDTH){
			x--; 
		}
		
	}
	
	public void paint(Graphics g){
		g.fillRect(x, y, WIDTH, HEIGHT);
	}


	public void pressed(int keyCode) {
		//System.out.println(Integer.toString(keyCode));
		//System.out.println(Integer.toString());
		if(keyCode == LEFTKEY){
			//System.out.println("should go left");
			xa = -1 * PADDLESPEED; 
		}
		else if(keyCode == RIGHTKEY){
			//System.out.println("should go right");
			xa = 1 * PADDLESPEED; 
		}
		
	}
	
	
	
	
	public void released(int keyCode){
		if(keyCode == LEFTKEY || keyCode == RIGHTKEY){
			xa = 0;
		}			
	}


	public Rectangle getBounds() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}


	public String getPaddleSectionHit(int ballX) {
		/*
		System.out.println("paddle x: " + x);
		System.out.println("paddle y: " + y);
		System.out.println("ball x: " + ballX);
		System.out.println("ball y: " + ballY);
		*/
		//TODO make the 10 half the ball width 
		int dif = ballX - x;
	
		/*
		 * if (dif < 0)
			return 0;
		else if(dif > 100)
			return 100;
		return dif;
		 * 
		 */
		
		
		
		System.out.println(dif);
		if(dif < LEFTONPADDLE){
			return "left";
		}
		else if(dif > RIGHTONPADDLE){
			return "right";
		}
		else{
			return "middle"; 
		}
		
	}


	public int getPaddleSectionHitInt(int ballX) {
	
		return ballX - x;
	}
}
