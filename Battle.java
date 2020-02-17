package project1;

public class Battle implements Comparable<Battle> {
	String name;
	String attackerKing;
	String defenderKing;
	String attackerOutcome;
	String battleType;
	String location;
	String region;

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

	public String getName() {
		return name;
	}

	public String getAttackerKing() {
		return attackerKing;
	}

	public String getDefenderKing() {
		return defenderKing;
	}

	public int compareTo(Battle Other) {
		return this.name.compareTo(Other.getName());
	}

	public String toString() {
		return (" - " + name + ", when " + attackerKing + " attacked " + defenderKing + ", resulting in a "
				+ attackerOutcome + ", through a " + battleType + ", at " + location + ", in the region of " + region);
	}

}
