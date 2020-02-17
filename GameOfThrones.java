package project2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

/**
 *Game of Thrones program gets character name input from the user,
 *and prints the output of input character's allegiances and battle information on the screen.
 * @author Chrissy Jeon (jj2174)
 */
public class GameOfThrones {

	/**
	 *This is the main method that is responsible for opening and reading the data files, 
	 *instantiating the Character and Battle objects,
	 *obtaining user input, and printing out responses.
	 * @throws FileNotFoundException on file read errors
	 */
	public static void main(String[] args) {

		sLinkedList<Battle> list = new sLinkedList<Battle>();

		String battleFile = "data/battles.csv";
		File b = new File(battleFile);

		//check whether the file can be read
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
		inputFile.nextLine();			//skips the first line because it contains general information
		while (inputFile.hasNextLine()) {
			String lines = inputFile.nextLine();
			String[] lines_split = lines.split(",");

			//instantiate the battle class
			String bName = lines_split[0];
			String attackerKing = lines_split[1];
			String defenderKing = lines_split[2];
			String attackerOutcome = lines_split[3];
			String battleType = lines_split[4];
			String location = lines_split[5];
			String region = lines_split[6];
			list.addLast(new Battle(bName, attackerKing, defenderKing, attackerOutcome, battleType, location, region));
		
		}

		sLinkedList<Character> cList = new sLinkedList<Character>();

		String characterFile = "data/characters.csv";
		File c = new File(characterFile);

		//check whether file can be read
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
		inputFile2.nextLine();		//skips the first line because it contains general information
		while (inputFile2.hasNextLine()) {
			String line = inputFile2.nextLine();
			String[] line_split = line.split(",");

			//instantiate the character class
			String name = line_split[0];
			String allegiances = line_split[1];
			cList.addLast(new Character(name, allegiances, list));

			
		}
		cList.sort();

		while (true) {
			//gets user information
			System.out.print("Enter a character name (or type \"all\" for all characters, or \"exit\" to exit): ");
			Scanner userInput = new Scanner(System.in);
			String cName = userInput.nextLine();

			//if user inputs "exit" the program breaks
			if (cName.equalsIgnoreCase("exit")) {
				break;
				
			//if user inputs "all" the program outputs all the character informations relating to characters	
			} else if (cName.equalsIgnoreCase("all")) {
				for (int j = 0; j < cList.size(); j++) {
						System.out.println(cList.get(j));

				}
			} else {
				Character goal = null;

				//if user inputs a character name, it checks whether the name is valid and returns the character information about that character 
				if (cList.contains(new Character (cName,null,new sLinkedList<Battle>()))) {
					for (int k = 0; k < cList.size(); k++) {
						if (cList.get(k).name.equalsIgnoreCase(cName)) {
							goal = cList.get(k);
						}
					}
					if (goal != null) {
						System.out.println(goal);
					}
				
				// if the user inputs an invalid character name, then the program notifies the user that the input is invalid.
				} else if (!cList.contains(new Character (cName,null,new sLinkedList<Battle>()))) {
					System.out.println("Character not found!" + '\n');
				}
			}
			inputFile.close();
			inputFile2.close();
		}
	}
}