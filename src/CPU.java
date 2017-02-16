public class CPU{
	//For now, this is going to be the same as the $PC, later, when pipelined
	//This will be different.
	private int clock;
	
	public boolean CPU(){
		clock = 0;
		return true;
	}
	/* @brief Returns current state of PC
	*
	*@params None.
	*@return 16 b
	*/
	public static int readPC(){
		return Registers.read16(Global.$PC);
	}
	/* @brief Writes a 16-bit value to the current $PC
	*
	*@params value 16-bit integer.
	*@return Void.
	*/
	public static void writePC(int value){

	}
	/* @brief Increment the current $PC by 4. (In this case, 1 based on our arrays)
	*
	*@params None.
	*@return Void.
	*/
	public static void incrPC(){
		
	}
	//TODO for CPU: Currently, will just run entire program. Increment 3 will introduce "Stepping"

	/* @brief Run the CPU based on user input actions. Called for every CPU cycle.
	*
	*@params None.
	*@return Void.
	*/
	public static void CPURun(){

	}

	/* @brief Will read the current $PC, use the $PC to find the correct instruction in memory
	* then increment the current $PC by 4 (in case it does get overwritten) and then find the correct
	* corresponding instruction to execute.
	*
	*@params None.
	*@return Void.
	*/
	private static void decode(){

	}


}