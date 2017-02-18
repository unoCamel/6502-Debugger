import java.util.HashMap;

public class Assembly{

	private String[] instructions;
	private HashMap<String, Integer> labels;

    /*@brief Initializes Assembly class, purpose is to hold instruction and 
    * their corresponding labels.
	* 
	* @param String[] ins all instructions, HashMap<String, Integer> ls labels 
	* as keys and indexes of labels as values.
	* @return None.
	*/
	public Assembly(String[] ins, HashMap<String, Integer> ls){
		instructions = ins;
		labels = ls;
	}

    /*@brief Gets the array of instructions when called. Array is size 
    * Global.MAX_SIZE/8
	* 
	* @param None.
	* @return String[] instructions
	*/
	public String[] getAllInstructions(){
		return instructions;
	}

    /*@brief Gets the HashMap of labels and their indexes.
	* 
	* @param None.
	* @return HashMap<String, Integer> labels
	*/
	public HashMap<String, Integer> getAllLabels(){
		return labels;
	}

    /*@brief Gets a single instruction from array
	* 
	* @param int i index of instruction you would like
	* @return String instructions[i]
	*/
	public String getInstruction(int i){
		return instructions[i];
	}

    /*@brief Gets a single corresponding index to label passed.
	* 
	* @param String key label
	* @return int index of label
	*/
	public int getLabelIndex(String key){
		return labels.get(key);
	}

}