package groupFiles;

public class JenniberJokes implements Chatbot{
	private String jokeResponse;
	private String triggerString;
	private boolean inJokeMode;
	
	private int jokeCount;
	private int triggerNum;
	
	private String[] jokesQuestions = {"What do ghosts eat for supper?","How did the glamorous ghoul earn her living?","How did the ghost patch his sheet?"};
	private String[] jokesAnswers = {"Spooketi","She was a cover ghoul.","With a pumpkin patch."};
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
			//negate use !
//			if(!isTriggered(jokeResponse)){
//				inJokeMode = false;
//				Main.promptForever();
//			}
		}
	}

	
	public boolean isTriggered(String userInput) {
		for(int x=0; x<jokeTriggers.length; x++){
			for(int y=0; y<jokeTriggers[x].length; y++){
				if(Main.findKeyword(userInput, jokeTriggers[x][y], 0)>=0){
					triggerNum = x;
					triggerString = jokeTriggers[x][y];
					return true;
				}
			}
		}
			
		return false;
	}
	
	//Decide how jokes will be triggered
	
	private void printResponse(int triggerNum) {
		if(jokesAnswers[triggerNum].equals(jokeResponse)){
			Main.print("That's right! The answer is: "+jokesAnswers[triggerNum]);
			inJokeMode = false;
		}
		else if(jokeCount>4){
			Main.print("Since you do not seem to know the answer: The answer is "+jokesAnswers[triggerNum]);
			inJokeMode = false;
		}else if(jokeCount>1 && jokeCount<=4){
			Main.print("No. Guess Again. "+jokesQuestions[triggerNum]);
		}else{
			Main.print("Speaking of "+triggerString+". "+jokesQuestions[triggerNum]);
		}
		
	}
}
