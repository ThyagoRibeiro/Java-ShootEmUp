package game;

import game.screenstate.ScreenContext;
import game.util.Time;

public class GameCore {

	/* Constantes relacionadas aos estados que os elementos */
	/* do jogo (player, projeteis ou inimigos) podem assumir. */

	public final int INACTIVE = 0;
	public final int ACTIVE = 1;
	public final int EXPLODING = 2;

	/* Espera, sem fazer nada, até que o instante de tempo atual seja */
	/* maior ou igual ao instante especificado no parâmetro "time. */

	public void busyWait(long time) {

		while (System.currentTimeMillis() < time)
			Thread.yield();
	}

	/* Método principal */

	public void start() {

		/* Indica que o jogo está em execução */
		boolean running = true;

		GameLib.initGraphics();

		ScreenContext screen = new ScreenContext();
		boolean testing = true;

		while (running) {

			Time.getInstance().UpdateDelta();

			if (testing) {
				screen.Update();
				screen.Draw();
				busyWait(Time.getInstance().CurrentTime() + 5);
				continue;
			}
		}

		System.exit(0);
	}
}
