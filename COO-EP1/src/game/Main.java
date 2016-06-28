package game;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class Main {

	private static int playerHealthPoints;
	private static ArrayList<Stage> stages = new ArrayList<>();

	public static void main(String[] args) {

		readArchives();

		 GameCore gm = new GameCore();
		 gm.start();
	}

	private static void readArchives() {
		// TODO Auto-generated method stub
		int numberOfStages = 0;
		ArrayList<String> stagesArchives = new ArrayList<>();

		String line;

		try {
			InputStream fis = new FileInputStream("game.txt");
			InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
			BufferedReader br = new BufferedReader(isr);

			playerHealthPoints = Integer.parseInt(br.readLine());
			numberOfStages = Integer.parseInt(br.readLine());

			while ((line = br.readLine()) != null) {
				stagesArchives.add(line);
			}

			String[] words;
			Stage stage = null;

			for (int i = 0; i < numberOfStages; i++) {

				System.out.println(i);

				fis = new FileInputStream(stagesArchives.get(i));
				isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
				br = new BufferedReader(isr);

				while (br.readLine()!= null) {
					words = br.readLine().split(" ");
					stage = new Stage(i);

					System.out.println(words[0]);

					if (words[0].equals("INIMIGO")) {
						stage.addEnemy(Integer.parseInt(words[1]), Integer.parseInt(words[2]),
								Integer.parseInt(words[3]), Integer.parseInt(words[4]));

					} else if (words[0].equals("POWERUP")) {
						stage.addPowerup(Integer.parseInt(words[1]), Integer.parseInt(words[2]),
								Integer.parseInt(words[3]), Integer.parseInt(words[4]));

					} else if (words[0].equals("CHEFE")) {
						stage.addBoss(Integer.parseInt(words[1]), Integer.parseInt(words[2]),
								Integer.parseInt(words[3]), Integer.parseInt(words[4]), Integer.parseInt(words[5]));
					}

				}

				stages.add(stage);

			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
