package project3;

/**
 * Battle class holds all the information for a given battle
 * @author Chrissy Jeon (jj2174)
 */
public class Battle implements Comparable<Battle> {
	String name;
	String attackerKing;
	String defenderKing;
	String attackerOutcome;
	String battleType;
	String location;
	String region;

	/**
	 * A battle constructor that takes in 7 parameters
	 * @param name (name of the battle)
	 * @param attackerKing (attacker king of the battle)
	 * @param defenderKing (defender king of the battle)
	 * @param attackerOutcome (outcome of the battle)
	 * @param battleType (type of the battle)
	 * @param location (location of the battle)
	 * @param region (region of the battle)
	 */
	public Battle(String name, String attackerKing, String defenderKing, String attackerOutcome, String battleType,
			String location, String region) {
		this.name = name;
		this.attackerKing = attackerKing;
		this.defenderKing = defenderKing;
		this.attackerOutcome = attackerOutcome;
		this.battleType = battleType;
		this.location = location;
		this.region = region;
	}

	/**
	 * Returns the name
	 * @return name (name of the battle)
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the attacker king
	 * @return attackerKing (attacker king of the battle)
	 */
	public String getAttackerKing() {
		return attackerKing;
	}

	/**
	 * Returns the defender king
	 * @return defenderKing (defender king of the battle)
	 */
	public String getDefenderKing() {
		return defenderKing;
	}

	/**
	 * Compares two battles and returns integer
	 * @param Other (Battle object you want to compare with)
	 * @return int (if 0 : same, 1 : bigger, -1: smaller)
	 */
	public int compareTo(Battle Other) {
		return this.name.toLowerCase().compareTo(Other.getName().toLowerCase());
	}

	/**
	 * Returns the information about the battle as a string
	 * @return String (string of battle information)
	 */
	public String toString() {
		return (" - " + name + ", when " + attackerKing + " attacked " + defenderKing + ", resulting in a "
				+ attackerOutcome + ", through a " + battleType + ", at " + location + ", in the region of " + region);
	}

}
