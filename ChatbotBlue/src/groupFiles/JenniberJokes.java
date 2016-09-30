package groupFiles;

public class JenniberJokes implements Chatbot{
	private String jokeResponse;
	private boolean inJokeMode;
	
	private int jokeCount;
	
	private String[] jokesQuestions = {"What do ghosts eat for supper?","How did the glamorous ghoul earn her living?","How did the ghost patch his sheet?"};
	private String[] jokesAnswers = {"Spooketi","She was a cover ghoul.","With a pumpkin patch."};
	
	public JenniberJokes(){
		jokeCount = 0;
	}
	
	public void talk(){
		inJokeMode = true;
		int responseSelection = (int)(Math.random()*jokesQuestions.length);
		
		while(inJokeMode){
			jokeCount++;
			printResponse(responseSelection); //helper method
			jokeResponse = Main.promptInput();
			//negate use !
			if(!isTriggered(jokeResponse)){
				inJokeMode = false;
				Main.promptForever();
			}
		}
	}

	
	public boolean isTriggered(String userInput) {
		for(int i=0; i>jokesAnswers.length; i++){
			if(Main.findKeyword(userInput, jokesAnswers[i], 0)>=0){
				return true;
			}
		}
			
		return false;
	}
	
	//Decide how jokes will be triggered
	
	private void printResponse(int responseSelection) {
		if(jokeCount>4){
			Main.print("Since you do not seem to know the answer: The answer is "+jokesAnswers[responseSelection]);
		}else if(jokeCount>0 && jokeCount<4){
			Main.print("No. Guess Again. "+jokesQuestions[responseSelection]);
		}else{
			Main.print(jokesQuestions[responseSelection]);
		}
		
	}
}
