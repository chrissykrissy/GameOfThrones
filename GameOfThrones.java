package project1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

public class GameOfThrones {

	public static void main(String[] args) {

		MyArrayList<Battle> list = new MyArrayList<>();

		//read the battle file
		String battleFile = "data/battles.csv";
		File b = new File(battleFile);

		//catch FileNotFoundException if cannot read file
		if (!b.canRead()) {
			System.err.printf("Error : cannot read" + "data from file%s", battleFile);
			System.exit(1);
		}
		Scanner inputFile = null;
		try {
			inputFile = new Scanner(b);
		} catch (FileNotFoundException e) {
			System.err.printf("Error : cannot read" + "data from file%s", battleFile);
			System.exit(1);
		}

		//put object data into the list
		while (inputFile.hasNextLine()) {
			String lines = inputFile.nextLine();
			String[] lines_split = lines.split(",");

			String bName = lines_split[0];
			String attackerKing = lines_split[1];
			String defenderKing = lines_split[2];
			String attackerOutcome = lines_split[3];
			String battleType = lines_split[4];
			String location = lines_split[5];
			String region = lines_split[6];
			list.add(new Battle(bName, attackerKing, defenderKing, attackerOutcome, battleType, location, region));
		}

		MyArrayList<Character> cList = new MyArrayList<Character>();
		ArrayList<String> validCNames = new ArrayList<String>();

		//read the character file
		String characterFile = "data/characters.csv";
		File c = new File(characterFile);

		//catch FileNotFoundException if cannot read file
		if (!c.canRead()) {
			System.err.printf("Error : cannot read" + "data from file%s", characterFile);
			System.exit(1);
		}
		Scanner inputFile2 = null;
		try {
			inputFile2 = new Scanner(c);
		} catch (FileNotFoundException e) {
			System.err.printf("Error : cannot read" + "data from file%s", characterFile);
			System.exit(1);
		}
		
		//put object data into the list
		while (inputFile2.hasNextLine()) {
			String line = inputFile2.nextLine();
			String[] line_split = line.split(",");

			String name = line_split[0];
			String allegiances = line_split[1];
			cList.add(new Character(name, allegiances, list));
			validCNames.add(name.toLowerCase());
			cList.sort();

		}

		//loop until user inputs "exit"
		while (true) {
			System.out.print("Enter a character name (or type \"all\" for all characters, or \"exit\" to exit): ");
			Scanner userInput = new Scanner(System.in);
			String cName = userInput.nextLine();

			if (cName.equalsIgnoreCase("exit")) {
				break;
				
			//when user inputs "all" give all the list of battles
			} else if (cName.equalsIgnoreCase("all")) {
				for (int j = 0; j < cList.size(); j++) {
					if (cList.get(j).getBattles().size() > 0)
						System.out.println(cList.get(j).toString());

				}
			} else {
				Character goal = null;

				//when the character entered is a valid character name, go through the character list and get the battles the character participated in
				if (validCNames.contains(cName.toLowerCase())) {
					for (int k = 0; k < cList.size(); k++) {
						if (cList.get(k).name.equalsIgnoreCase(cName)) {
							goal = cList.get(k);
						}
					}
					if (goal != null) {
						System.out.println(goal.toString());
					}
					
				//if the character entered is not valid
				} else if (!validCNames.contains(cName.toLowerCase())) {
					System.out.println("Character not found!" + '\n');
				}
			}
			inputFile.close();
			inputFile2.close();
		}
	}
}