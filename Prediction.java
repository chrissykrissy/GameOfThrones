package project4;

/**
 * Prediction class holds all information for a give predictions, as well as information about how to sort Prediction objects. 
 * @author Chrissy Jeon (jj2174)
 */
public class Prediction implements Comparable<Prediction>{
	private float plod;
	private Character character;
	
	/**
	 * Prediction constructor that takes in two parameters
	 * @param plod (float value)
	 * @param character (character object)
	 */
	public Prediction(float plod,Character character) {
		this.character = character;
		this.plod = plod;
	}
	
	/**
	 * Compares two predictions and returns integer and if they have the same prediction compares them by character name.
	 * @param Other (Prediction object you want to compare with)
	 * @return int (if 0 : same, 1 : bigger, -1: smaller)
	 */
	public int compareTo(Prediction Other) {
		int p = ((Float)(this.plod)).compareTo((Float)(Other.plod));
		if(p!=0)
			return p;
		
		return this.character.compareTo(Other.character);
	}

	/**
	 * Returns the information about the prediction as a string
	 * @return String (string of prediction information)
	 */
	public String toString() {
		return character.name +" with PLOD of " +this.plod;
	}
	
}
