import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

	private GameFrame game;
	private Ball ball; 
	private Paddle paddle; 
	
	private boolean gameRunning = true;
	private static final int ONE_BILLION = 1000000000;
	private static final int ONE_MILLION = 1000000;
	
	public static final Color LINE_COLOR = Color.GREEN;
	public static final Color SPRITE_COLOR = Color.RED;
	public static final Color CANVAS_BACKGROUND = Color.WHITE;
	
	
	//needs to be interval of 5
	private int brickCount = 10;
	private int currentBrickCount = 0; 
	
	private ArrayList<Brick>  arrBrick = new ArrayList<Brick>();
	
	
	public GamePanel(GameFrame game) {
		setBackground(CANVAS_BACKGROUND);
		this.game = game; 
		ball = new Ball(game);
		this.paddle = new Paddle(game);
		int curX = 5, curY = 5;
		
		for(int i = 0; i < brickCount + 1; i++){
			arrBrick.add(new Brick(game, curX, curY));
			///74 for brick width -- 5 for spacing
			curX += 79;
			if(i != 0  && i % 5 == 0){
				curX = 5;
				curY += 25;
			}
		}
		
		
		addKeyListener(this);
		setFocusable(true);
		
	}
	
	
	@Override
	public void paintComponent(Graphics g){
		//System.out.println("now doing game updates");
		super.paintComponent(g);
		setBackground(CANVAS_BACKGROUND);
		g.setColor(LINE_COLOR);
		ball.paint(g);
		paddle.paint(g);
		for(Brick aBrick: arrBrick){
			if (!aBrick.hasBeenHit())
				aBrick.paint(g);
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//System.out.println("key pressed");
		paddle.pressed(e.getKeyCode());
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		paddle.released(e.getKeyCode());
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	public void gameLoop() {
		long nanosecondAccumulator = 0;
		long fps = 0;
		
		// nano = billion
		long lastLoopTime = System.nanoTime();
		final int TARGET_FPS = 20;
		// optimal nanoseconds each frame
		final long OPTIMAL_NANOSECONDS_EACH_FRAME = ONE_BILLION / TARGET_FPS;

		// If you plan to update gameRunning
		//   on a separate thread consider declaring
		//   gameRunning as volatile.
		while (gameRunning) {
			// work out how long its been since the last update, this
			// will be used to calculate how far the entities should
			// move this loop
			long now = System.nanoTime();
			long updateLength = now - lastLoopTime;
			lastLoopTime = now;
			double delta = updateLength / ((double) OPTIMAL_NANOSECONDS_EACH_FRAME);

			// The following isn't necessary. Can be commented out.
			// Only used to calculate fps
			nanosecondAccumulator = nanosecondAccumulator +  updateLength;
			fps = fps + 1;

			// update our FPS counter if a second has passed since
			// we last recorded
			if (nanosecondAccumulator >= ONE_BILLION) {
				//System.out.println("(FPS: " + fps + ")");
				nanosecondAccumulator = 0;
				fps = 0;
			}
			
			
			// update the game logic
			doGameUpdates(delta);

			// draw frame
			render();

			try {
				long nanosecondsThisFrame = System.nanoTime() - lastLoopTime;  
				long nanosecondsToSleepToGetDesiredFrameRate =
						OPTIMAL_NANOSECONDS_EACH_FRAME - nanosecondsThisFrame;
				// divide by 1M to get milliseconds to sleep
				Thread.sleep(nanosecondsToSleepToGetDesiredFrameRate / ONE_MILLION);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	
}



	private void render() {
		
		repaint();
	}



	private void doGameUpdates(double delta) {
		
		update(getGraphics());
		paddle.update();
		ball.update();
		int indexToBeDeleted = -1;
		for(Brick aBrick : arrBrick){
			aBrick.update();
			if(aBrick.hasBeenHit()){
				indexToBeDeleted = arrBrick.indexOf(aBrick);
				currentBrickCount++;
				if(currentBrickCount == brickCount)
					win();
			}
		}
		if(indexToBeDeleted != -1)
			arrBrick.remove(indexToBeDeleted);
		paintComponent(getGraphics());
	}


	private void win() {
		JOptionPane.showMessageDialog(null, "YOU WIN!!!!!!");
		
	}


	public Paddle getPaddle() {
		return paddle;
	}


	public Ball getBall() {
		return ball;		
	}
}
