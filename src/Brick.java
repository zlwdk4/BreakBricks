import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Brick {

	public static final int WIDTH = 65, HEIGHT = 20;
	private GameFrame game;
	private boolean hasBeenHit = false, hasColor = false;
	private int x, y;
	private Color color; 
	
	
	
	public Brick(GameFrame game, int givenX, int givenY){
		this.game = game; 
		this.x = givenX;
		this.y = givenY;
		
		
	}
	
	
	public void update(){
		
		checkForCollision();
		
	}


	public void checkForCollision() {
		if(game.getPanel().getBall().getBounds().intersects(getBounds())){
			hasBeenHit = true;
			int ballX = game.getPanel().getBall().getBallX();
			int ballY = game.getPanel().getBall().getBallY();
			int ballXA = game.getPanel().getBall().getBallXA();
			int ballYA = game.getPanel().getBall().getBallYA();
			/*
			System.out.println("ballX: " + ballX);
			System.out.println("ballXA: " + ballXA);
			System.out.println("ballY: " + ballY);
			System.out.println("ballXY: " + ballYA);
			
			System.out.println("Brick x: " + x);
			System.out.println("Brick y: " + y);
			*/
			game.getPanel().getBall().inverseYA();
			
			
			
		
			
		}
		
	}
	
	private Rectangle getBounds(){
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}


	public void paint(Graphics g) {
		
		if(!hasColor){
			System.out.println("changing color");
			hasColor = true;
			
			Random rand = new Random();
			int max = 5, min = 1;
			int randNum = rand.nextInt(max - min + 1 ) + min;
			Color color = Color.red;
			switch(randNum){
			case 1: color = Color.red;
					g.setColor(color);
					g.fillRect(x, y, WIDTH, HEIGHT);
					break;
			case 2: color = Color.yellow;
					break;
			case 3: color = Color.cyan;
					break;
			case 4: color = Color.pink;
					break;
			case 5: color = Color.green;
					break;
			}
		}
		else{
			g.setColor(Color.pink);
			g.fillRect(x, y, WIDTH, HEIGHT);
		}
		
		
				
	}


	public boolean hasBeenHit() {
		// TODO Auto-generated method stub
		return hasBeenHit;
	}
	
}
