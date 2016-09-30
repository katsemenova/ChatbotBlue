package groupFiles;

public class JenniberJokes implements Chatbot{
	private String jokeResponse;
	private boolean inJokeMode;
	
	private int jokeCount;
	
	private String[] jokesQuestions = {"What do ghosts eat for supper?","How did the glamorous ghoul earn her living?","How did the ghost patch his sheet?"};
	private String[] jokesAnswers = {"Spooketi","She was a cover ghoul.","With a pumpkin patch."};
	
	public JenniberJokes(){
		
	}
	
	public void talk(){
		inJokeMode = true;
		while(inJokeMode){
			jokeCount++;
			printResponse(); //helper method
			jokeResponse = Main.promptInput();
			//negate use !
			if(!isTriggered(jokeResponse)){
				inJokeMode = false;
				Main.promptForever();
			}
		}
	}

	
	public boolean isTriggered(String userInput) {
		if(Main.findKeyword(userInput, "ghosts", 0)>=0){
			return true;
		}
		if(Main.findKeyword(userInput, "ghoul", 0)>=0){
			return true;
		}
		if(Main.findKeyword(userInput, "ghost", 0)>=0){
			return true;
		}
			
		return false;
	}
	
	private void printResponse() {
		if(jokeCount>4){
			int responseSelection = (int)(Math.random()*jokesQuestions.length);
			
			Main.print(jokesQuestions[responseSelection]);
		}else{
			int responseSelection = (int)(Math.random()*jokesQuestions.length);
			Main.print(jokesAnswers[responseSelection]);
		}
		
	}
}
