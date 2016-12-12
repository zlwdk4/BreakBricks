import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GameFrame extends JFrame {
	private final static int WIDTH = 400, HEIGHT = 500;
	private GamePanel panel; 
	
	public GameFrame(){
		setSize(WIDTH, HEIGHT);
		setTitle("Break Them Bricks!!!");
		setBackground(Color.WHITE);
		setResizable(false);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		panel = new GamePanel(this);
		add(panel);
		
		setVisible(true);
	}
	
	public GamePanel getPanel(){
		return panel;
	}
	
	public static void main(String[] args){
		GameFrame theGame = new GameFrame();
		
		
		
		
		theGame.start();
	}

	private void start() {
		panel.gameLoop();
		
	}

	public void lose() {
		JOptionPane.showMessageDialog(null, "You looooossseeee!!!");
		
		
	}
}
