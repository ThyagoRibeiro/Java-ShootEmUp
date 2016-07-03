package game.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Draw {

	private static Graphics2D _g;

	public static void drawCircle(double cx, double cy, double radius) {

		int x = (int) Math.round(cx - radius);
		int y = (int) Math.round(cy - radius);
		int width = (int) Math.round(2 * radius);
		int height = (int) Math.round(2 * radius);
		
		_g.drawOval(x, y, width, height);
	}
	
	public static void drawDiamond(double x, double y, double radius) {

		int x1 = (int) Math.round(x);
		int y1 = (int) Math.round(y - radius);

		int x2 = (int) Math.round(x + radius);
		int y2 = (int) Math.round(y);

		int x3 = (int) Math.round(x);
		int y3 = (int) Math.round(y + radius);

		int x4 = (int) Math.round(x - radius);
		int y4 = (int) Math.round(y);

		drawLine(x1, y1, x2, y2);
		drawLine(x2, y2, x3, y3);
		drawLine(x3, y3, x4, y4);
		drawLine(x4, y4, x1, y1);
	}

	public static void drawExplosion(double x, double y, double alpha) {

		int p = 5;
		int r = (int) (255 - Math.pow(alpha, p) * 255);
		int g = (int) (128 - Math.pow(alpha, p) * 128);
		int b = 0;

		Draw.setColor(new Color(r, g, b));
		Draw.drawCircle(x, y, alpha * alpha * 40);
		Draw.drawCircle(x, y, alpha * alpha * 40 + 1);
	}

	public static void drawHourglass(double x, double y, double radius) {

		int x1 = (int) Math.round(x);
		int y1 = (int) Math.round(y);

		int x2 = (int) Math.round(x + radius);
		int y2 = (int) Math.round(y);

		int x3 = (int) Math.round(x);
		int y3 = (int) Math.round(y + radius);

		int x4 = (int) Math.round(x + radius);
		int y4 = (int) Math.round(y + radius);

		drawLine(x1, y1, x2, y2);
		drawLine(x2, y2, x3, y3);
		drawLine(x3, y3, x4, y4);
		drawLine(x4, y4, x1, y1);
	}

	public static void drawLifeBar(int x, int y, int width, int height, double lifePointsPercent, String character) {

		_g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
		_g.drawString(character, x, y - 10);
		_g.drawRect(x, y, width, height);
		_g.fillRect(x + 5, y + 5, (int) ((width - 10) * lifePointsPercent), height - 10);

	}
	
	public static void drawLine(double x1, double y1, double x2, double y2) {

		_g.drawLine((int) Math.round(x1), (int) Math.round(y1), (int) Math.round(x2), (int) Math.round(y2));
	}

	public static void drawPlayer(double player_X, double player_Y, double radius) {

		double player_size = radius * 0.85;

		Draw.drawLine(player_X - player_size, player_Y + player_size, player_X, player_Y - player_size);
		Draw.drawLine(player_X + player_size, player_Y + player_size, player_X, player_Y - player_size);
		Draw.drawLine(player_X - player_size, player_Y + player_size, player_X, player_Y + player_size * 0.5);
		Draw.drawLine(player_X + player_size, player_Y + player_size, player_X, player_Y + player_size * 0.5);
	}

	public static void drawPowerUp(String text, float x, float y, Font f) {
		_g.drawString(text, (int) x, (int) y);
	}

	public static void drawPowerUpActive(String text, float x, float y, Font f) {
		_g.drawString(text, (int) x, (int) y);
	}

	public static void drawSquare(double x, double y, double radius) {

		int x1 = (int) Math.round(x);
		int y1 = (int) Math.round(y);

		int x2 = (int) Math.round(x + radius);
		int y2 = (int) Math.round(y);

		int x3 = (int) Math.round(x + radius);
		int y3 = (int) Math.round(y + radius);

		int x4 = (int) Math.round(x);
		int y4 = (int) Math.round(y + radius);

		drawLine(x1, y1, x2, y2);
		drawLine(x2, y2, x3, y3);
		drawLine(x3, y3, x4, y4);
		drawLine(x4, y4, x1, y1);
	}

	public static void drawText(String text, float x, float y) {
		_g.drawString(text, (int) x, (int) y);
	}

	public static void drawTime(int x, int y, int secs) {

		_g.setFont(new Font("Arial", Font.PLAIN, 30));
		_g.drawString("Tempo", x, y - 10);
		_g.drawString(Integer.toString(secs), x, y + 20);
	}

	public static void drawTwoStrings(String text1, String text2, int x, int y) {
		
		_g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
	    int x2 = (x + _g.getFontMetrics().stringWidth(text1)/2) - 2;
		
		setColor(Color.WHITE);
		_g.drawString(text1, x, y - 10);
		_g.drawString(text2, x2, y + 15);
	}

	public static void fillCircle(double cx, double cy, double radius) {

		int x = (int) Math.round(cx - radius);
		int y = (int) Math.round(cy - radius);
		int width = (int) Math.round(2 * radius);
		int height = (int) Math.round(2 * radius);
		
		_g.fillOval(x, y, width, height);
	}

	public static void fillRect(double cx, double cy, double width, double height) {

		int x = (int) Math.round(cx);
		int y = (int) Math.round(cy);

		_g.fillRect(x, y, (int) Math.round(width), (int) Math.round(height));
	}

	public static FontMetrics getFontMetrics(Font f) {
		return _g.getFontMetrics(f);
	}

	public static Graphics getGraphics() {
		return _g;
	}

	public static void setColor(Color c) {
		_g.setColor(c);
	}

	public static void setFont(Font f) {
		_g.setFont(f);
	}

	public static void setGraphics(Graphics2D g) {
		_g = g;
	}

	public static void writeInCircle(float x, float y, String text, double radius) {
		// Find the size of string s in font f in the current Graphics context g.
	
		FontMetrics fm = _g.getFontMetrics();
		java.awt.geom.Rectangle2D rect = fm.getStringBounds(text, _g);

		int textHeight = (int) (rect.getHeight());
		int textWidth = (int) (rect.getWidth());

		// Find the top left and right corner
		int cornerX = (int) (x - (textWidth / 2));
		int cornerY = (int) (y - (textHeight / 2) + fm.getAscent());

		_g.drawString(text, cornerX, cornerY);  // Draw the string.
		}

	protected Draw() {

	}
}
