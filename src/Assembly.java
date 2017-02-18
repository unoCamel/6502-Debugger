import java.util.HashMap;

public class Assembly{

	private String[] instructions;
	private HashMap<String, Integer> labels;

	public Assembly(String[] ins, HashMap<String, Integer> ls){
		instructions = ins;
		labels = ls;
	}

	public String[] getAllInstructions(){
		return instructions;
	}

	public HashMap<String, Integer> getAllLabels(){
		return labels;
	}

	public String getInstruction(int i){
		return instructions[i];
	}
	
	public int getLabelIndex(String key){
		return labels.get(key);
	}

}