package groupFiles;

public class JenniberJokes implements Chatbot{
	private String jokeResponse;
	private String triggerString;
	private boolean inJokeMode;
	private int jokeCount;
	private int triggerNum;

	private String[] userResponse={"yes", "yea","sure","ok", "okay"};
	
	//Created 2D Arrays
	private String[][] jokes = {
			{"What do ghosts eat for supper?","Spooketi"},
			{"How did the glamorous ghoul earn her living?","She was a cover ghoul"},
			{"How did the ghost patch his sheet?","With a pumpkin patch."}
	};
	private String[][] jokeTriggers = {
			{"supper","dinner","meal","food"}, 
			{"living","job","work"},
			{"sheets","sheet","blanket","blankets"}
		};
	
	public JenniberJokes(){
		jokeCount = 0;
	}
	
	public void talk(){
		inJokeMode = true;
		
		while(inJokeMode){
			jokeCount++;
			printResponse(triggerNum); //helper method
			jokeResponse = Main.promptInput();
		}
	}

	public boolean isTriggered(String userInput) {
		for(int x=0; x<jokeTriggers.length; x++){
			for(int y=0; y<jokeTriggers[x].length; y++){
				triggerString = jokeTriggers[x][y];
				if(Main.findKeyword(userInput, triggerString, 0)>=0){
					triggerNum = x;
					return true;
				}
				else{
					for(int z=0; z<userResponse.length; z++){
						if(Main.findKeyword(userInput, userResponse[z], 0)>=0){
							getTriggerNum();
							return true;
						}
					}
				}

			}
		}
			
		return false;
	}
	
	private int getTriggerNum(){	
		double rand = Math.random();
		int trig = (int) (jokes.length *rand);
		return trig;
	}
	
	private void printResponse(int triggerNum) {
		//will exit while loop when user has guessed 3 times or answered the joke correctly
		//else it will ask the joke
		if(jokes[triggerNum][1].equals(jokeResponse)){
			Main.print("That's right! The answer is: "+jokes[triggerNum][1]);
			inJokeMode = false;
			Main.kat.improveMood(1);
			Main.promptForever();
		}
		else if(jokeCount>4){
			Main.print("Since you do not seem to know the answer: The answer is "+jokes[triggerNum][1]);
			inJokeMode = false;
			Main.kat.improveMood(1);
			Main.promptForever();
		}else if(jokeCount>1 && jokeCount<=4){
			Main.kat.improveMood(-1);
			Main.print("No. Guess Again. "+jokes[triggerNum][0]);
		}else{
			Main.print("Speaking of "+triggerString+". "+jokes[triggerNum][0]);
		}
		
	}

}
