package project4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

/**
 *Game of Thrones program gets character name input from the user,
 *and prints the output of input character's allegiances and battle information, and lineage on the screen
 *or prints out the family tree of every house. 
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
		sLinkedList<House> hList = new sLinkedList<House>();


		String houseFile = "data/characters_lineage.csv";
		File h = new File(houseFile);

		//check whether file can be read
		if (!h.canRead()) {
			System.err.printf("Error : cannot read" + "data from file%s", houseFile);
			System.exit(1);
		}
		Scanner inputFileH = null;
		try {
			inputFileH = new Scanner(h);
		} catch (FileNotFoundException e) {
			System.err.printf("Error : cannot read" + "data from file%s", houseFile);
			System.exit(1);
		}
		inputFileH.nextLine();		//skips the first line because it contains general information
		while (inputFileH.hasNextLine()) {
			String line = inputFileH.nextLine();
			String[] line_split = line.split(",");

			//instantiate the character class and House class
			String name = line_split[0];
			String father = line_split[1];
			String mother = line_split[2];
			String allegiances = line_split[3];
			Character character = new Character(name,father,mother,allegiances,list);
			cList.addLast(character);
			if (!hList.contains(new House(allegiances))) {
				hList.addLast(new House(allegiances));
			}

			for (int i = 0; i < hList.size(); i++) {
				if (hList.get(i).getName().equals(allegiances)) {
					hList.get(i).addMembers(character);
				}
			}
		}
		cList.sort();
		
		pqLinkedList<Prediction> predictionsLL = new pqLinkedList<Prediction>();
		pqMyArrayList<Prediction> predictionsAL = new pqMyArrayList<Prediction>();
		
		String pdFile = "data/predictions.csv";
		File p = new File(pdFile);

		//check whether file can be read
		if (!p.canRead()) {
			System.err.printf("Error : cannot read" + "data from file%s", pdFile);
			System.exit(1);
		}
		Scanner inputFileP = null;
		try {
			inputFileP = new Scanner(p);
		} catch (FileNotFoundException e) {
			System.err.printf("Error : cannot read" + "data from file%s", pdFile);
			System.exit(1);
		}
		inputFileP.nextLine();		//skips the first line because it contains general information
		while (inputFileP.hasNextLine()) {
			String line = inputFileP.nextLine();
			String[] line_split = line.split(",");
			
			String plod = line_split[0];
			String character = line_split[1];
			
			float Fplod = Float.parseFloat(plod);
			Character Ccharacter = new Character(character);	
			
			Prediction prediction = new Prediction(Fplod,Ccharacter);
			
			predictionsLL.insert(prediction);
			predictionsAL.insert(prediction);
			
		}

		int structure = 0;			//structure indicator (0 : default (sLinkedList), 1 : MyArrayList)

		while (true) {
			//gets user information
			System.out.print("Enter a character name (or type \"all\" for all characters,\"family tree\" for a family tree of all houses,\n\"remove all\" to remove all characters, \"remove next\" to remove the next character,\n\"use sLinkedList\" to use the linked list heap, \"use MyArrayList\" to use the MyArrayList heap,\n\"LLTD\" to see which character is the least likely to die,\nor \"exit\" to exit): ");
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
				//if user inputs "family tree" the program outputs family trees for all houses
			} else if (cName.equalsIgnoreCase("family tree")) {
				for (int n = 0; n < hList.size(); n++) {
					System.out.print("\n"+hList.get(n).getName() + ":");
					hList.get(n).makeFamTree();
					hList.get(n).getFamilyTree().printTree();
					System.out.print("\n");
				}
				//if user inputs "use sLinkedList" the program sets the structure to 0 which is default (sLinkedList)
			}else if (cName.equalsIgnoreCase("use sLinkedList")) {
				System.out.println("Now using sLinkedList!");
				structure = 0;
				
				//if user inputs "use MyArrayList" the program sets the structure to 1 which is MyArrayList
			}else if (cName.equalsIgnoreCase("use MyArrayList")) {
				System.out.println("Now using MyArrayList!");
				structure = 1;
				
				//if user inputs "remove next" the program removes the character with lowest prediction from list (0 : sLinkedList, 1: MyArrayList)
				//if there are no more characters to remove prints the error message
			}else if (cName.equalsIgnoreCase("remove next")) {
				if (structure == 0) {
					if (predictionsLL.size == 0) {
						System.err.println("No more characters can be removed!");
					}else {
						System.out.println(" Removed: " + predictionsLL.remove());
					}
				}
				if (structure == 1) {
					if (predictionsAL.getHeap().size == 0) {
						System.err.println("No more characters can be removed!");
					}else {	
						System.out.println(" Removed: " + predictionsAL.remove());
					}
				}
				
				//if user inputs "remove all" the program removes all the characters in the list
			}else if (cName.equalsIgnoreCase("remove all")) {
				if (structure == 0) {
					int pqsLLsize = predictionsLL.size;
					for (int s = 0; s < pqsLLsize; s++) {
						System.out.println(s + " Removed: " + predictionsLL.remove());
					}
				}
				else if (structure == 1) {
					int pqALsize = predictionsAL.getHeap().size;
					for (int ALs = 0; ALs < pqALsize; ALs++) {
						System.out.println(ALs + " Removed: " + predictionsAL.remove());
					}
				}
				
				//if user inputs "LLTD" the program prints character with the lowest prediction without deleting them in the list
				//prints error message when there's no character in the list
			}else if (cName.equalsIgnoreCase("LLTD")) {
				if (structure == 0) {
					if (predictionsLL.peek() == null) {
						System.err.println("There is no character in the list.");
					}else {
						System.out.println("LLTD: "+predictionsLL.peek());
					}
				}
				else if (structure == 1) {
					if (predictionsAL.peek() == null) {
						System.err.println("There is no Character in the list.");
					}else {
						System.out.println("LLTD: "+predictionsAL.peek());
					}
				}
			}
			else {
				Character goal = null;

				//if user inputs a character name, it checks whether the name is valid and returns the character information about that character 

				if (cList.contains(new Character (cName,null,null,null,new sLinkedList<Battle>()))) {
					for (int k = 0; k < cList.size(); k++) {
						if (cList.get(k).name.equalsIgnoreCase(cName)) {
							goal = cList.get(k);
						}
					}
					if (goal != null) {
						System.out.println(goal);
					}

					//for the input character, prints out the lineage
					for (int u = 0; u < hList.size(); u++) {
						if (hList.get(u).members.contains(goal)) {
							FamilyTree houseTree = new FamilyTree(hList.get(u));
							houseTree.LinToString(goal);
						}
					}
				// if the user inputs an invalid character name, then the program notifies the user that the input is invalid.
			}else if (!cList.contains(new Character (cName,null,null,null,new sLinkedList<Battle>()))) {
				System.err.println("Character or command not found!" + '\n');
			}
		}
		inputFile.close();
		inputFileH.close();
		inputFileP.close();
	}
}
}