package game;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

import stage.Stage;

public class Main {

	private static int playerHealthPoints;
	private static ArrayList<Stage> stages = new ArrayList<>();

	public static void main(String[] args) {

		if (args.length == 1)
			readArchives(args[0]);
		else
			readArchives("game.txt");

		GameCore gm = new GameCore(playerHealthPoints, stages);
		gm.start();
	}

	private static void readArchives(String fileName) {

		int numberOfStages = 0;
		ArrayList<String> stagesArchives = new ArrayList<>();

		String line;

		try {
			InputStream fis = new FileInputStream(fileName);
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

				fis = new FileInputStream(stagesArchives.get(i));
				isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
				br = new BufferedReader(isr);

				stage = new Stage(i);

				while ((line = br.readLine()) != null) {

					words = line.split(" ");

					if (words[0].equals("INIMIGO")) {
						stage.addEnemy(Integer.parseInt(words[1]), Integer.parseInt(words[2]),
								Integer.parseInt(words[3]), Integer.parseInt(words[4]));

					} else if (words[0].equals("POWERUP")) {
						stage.addPowerup(Integer.parseInt(words[1]), Integer.parseInt(words[2]),
								Integer.parseInt(words[3]), Integer.parseInt(words[4]));

					} else if (words[0].equals("CHEFE")) {
						stage.addBoss(Integer.parseInt(words[1]), Integer.parseInt(words[2]),
								Integer.parseInt(words[3]), Integer.parseInt(words[4]), Integer.parseInt(words[2]));
					}

				}

				stages.add(stage);

				br.close();
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
