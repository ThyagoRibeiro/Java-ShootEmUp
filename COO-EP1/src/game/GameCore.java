package game;

import game.screenstate.ScreenContext;
import game.stages.Stage;
import game.utils.Time;

import java.util.ArrayList;

// Classe criado para tratar as principais mecânicas do jogo.

public class GameCore {

	/* Constantes relacionadas aos estados que os elementos */
	/* do jogo (player, projeteis ou inimigos) podem assumir. */

	public final int ACTIVE = 1;
	public final int EXPLODING = 2;
	public final int INACTIVE = 0;

	private int playerHealthPoints;
	private ArrayList<Stage> stages;

	// Método público.

	public GameCore(int playerHealthPoints, ArrayList<Stage> stages) {
		this.playerHealthPoints = playerHealthPoints;
		this.stages = stages;
	}

	/* Espera, sem fazer nada, até que o instante de tempo atual seja */
	/* maior ou igual ao instante especificado no parâmetro "time. */

	public void busyWait(long time) {
		while (System.currentTimeMillis() < time)
			Thread.yield();
	}

	// Método que inicializa o jogo

	public void init() {
		/* Indica que o jogo está em execução */
		boolean running = true;
		GameLib.initGraphics();
		ScreenContext screen = new ScreenContext(playerHealthPoints, stages);
		while (running) {
			Time.getInstance().updateDelta();
			screen.update();
			screen.draw();
			busyWait(Time.getInstance().currentTime() + 5);
		}
		System.exit(0);
	}
}
