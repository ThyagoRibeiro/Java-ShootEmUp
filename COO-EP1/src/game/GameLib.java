package game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import game.util.Draw;

/***********************************************************************/
/*                                                                     */
/* Classe com métodos úteis para implementação de um jogo. Inclui:     */
/*                                                                     */
/* - Método para iniciar modo gráfico.                                 */
/*                                                                     */
/* - Métodos para desenhos de formas geométricas.                      */
/*                                                                     */
/* - Método para atualizar o display.                                  */
/*                                                                     */
/* - Método para verificar o estado (pressionada ou não pressionada)   */
/*   das teclas usadas no jogo:                                        */
/*                                                                     */
/*   	- up, down, left, right: movimentação do player.               */
/*		- control: disparo de projéteis.                               */
/*		- ESC: para sair do jogo.                                      */
/*                                                                     */
/***********************************************************************/

public class GameLib {

	public static final int WIDTH = 480;
	public static final int HEIGHT = 820;

	public static final int KEY_UP = 0;
	public static final int KEY_DOWN = 1;
	public static final int KEY_LEFT = 2;
	public static final int KEY_RIGHT = 3;
	public static final int KEY_CONTROL = 4;
	public static final int KEY_ESCAPE = 5;

	private static MyFrame frame = null;
	private static Graphics2D g = null;
	private static MyKeyAdapter keyboard = null;

	public static void initGraphics() {

		frame = new MyFrame("EP COO - Shoot'em up");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);

		keyboard = new MyKeyAdapter();
		frame.addKeyListener(keyboard);
		frame.requestFocus();

		frame.createBufferStrategy(2);
		g = (Graphics2D) frame.getBufferStrategy().getDrawGraphics();					
		
		Draw.setGraphics(g);
	}

	public static void setColor(Color c) {
		g.setColor(c);
	}

	public static void drawString(String letra, double posX, double posY) {
		g.drawString(letra, (int) posX - 2, (int) posY + 2);
	}

	public static void drawLine(double x1, double y1, double x2, double y2) {

		g.drawLine((int) Math.round(x1), (int) Math.round(y1), (int) Math.round(x2), (int) Math.round(y2));
	}

	public static void drawRec(double x, double y, double radius) {
		g.drawRect((int) x, (int) y, (int) radius, (int) radius);
	}

	public static void drawCircle(double cx, double cy, double radius) {

		int x = (int) Math.round(cx - radius);
		int y = (int) Math.round(cy - radius);
		int width = (int) Math.round(2 * radius);
		int height = (int) Math.round(2 * radius);

		g.drawOval(x, y, width, height);
	}
	
	public static void display() {

		g.dispose();
		frame.getBufferStrategy().show();
		Toolkit.getDefaultToolkit().sync();
		g = (Graphics2D) frame.getBufferStrategy().getDrawGraphics();
		g.setStroke(new BasicStroke(2));
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Draw.setGraphics(g);

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, frame.getWidth() - 1, frame.getHeight() - 1);
		g.setColor(Color.WHITE);
	}

	public static boolean iskeyPressed(int index) {

		return keyboard.isKeyPressed(index);
	}

	public static void debugKeys() {

		keyboard.debug();
	}
}

@SuppressWarnings("serial")
class MyFrame extends JFrame {

	public MyFrame(String title) {

		super(title);
	}

	public void paint(Graphics g) {
	}

	public void update(Graphics g) {
	}

	public void repaint() {
	}
}

class MyKeyAdapter extends KeyAdapter {

	private int[] codes = { KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_CONTROL,
			KeyEvent.VK_ESCAPE, KeyEvent.VK_SPACE };

	private boolean[] keyStates = null;
	private long[] releaseTimeStamps = null;

	public MyKeyAdapter() {

		keyStates = new boolean[codes.length];
		releaseTimeStamps = new long[codes.length];
	}

	public int getIndexFromKeyCode(int keyCode) {

		for (int i = 0; i < codes.length; i++) {

			if (codes[i] == keyCode)
				return i;
		}

		return -1;
	}

	public void keyPressed(KeyEvent e) {

		int index = getIndexFromKeyCode(e.getKeyCode());

		if (index >= 0) {

			keyStates[index] = true;
		}
	}

	public void keyReleased(KeyEvent e) {

		int index = getIndexFromKeyCode(e.getKeyCode());

		if (index >= 0) {

			keyStates[index] = false;
			releaseTimeStamps[index] = System.currentTimeMillis();
		}
	}

	public boolean isKeyPressed(int index) {

		boolean keyState = keyStates[index];
		long keyReleaseTime = releaseTimeStamps[index];

		if (keyState == false) {

			if (System.currentTimeMillis() - keyReleaseTime > 5)
				return false;
		}

		return true;
	}

	public void debug() {

		for (int i = 0; i < codes.length; i++) {
		}
	}
	
}
