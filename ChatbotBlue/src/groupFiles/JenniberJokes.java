package groupFiles;

public class JenniberJokes implements Chatbot{
	private String jokeResponse;
	private String triggerString;
	private boolean inJokeMode;
	private int jokeCount;
	private int triggerNum;
	
	//user responses to check over when a triggerWord is not used
	private String[] userResponse={"yes", "yea","sure","ok","alright","yep","why not"};
	
	//Created 2D Arrays
	//Jokes with its respective answer
	private String[][] jokes = {
			{"What do ghosts eat for supper?","Spooketi"},
			{"How did the glamorous ghoul earn her living?","She was a cover ghoul"},
			{"How did the ghost patch his sheet?","With a pumpkin patch"},
			{"Why didn’t the skeleton want to go to school?","His heart wasn’t in it"},
			{"Why didn’t the skeleton go to the ball?","Because he had no BODY to go with"},
			{"What do you get when you cross a witch with sand?","A sandwich!"},
			{"What kind of dessert does a ghost like?","I scream!"},
			{"What do mummies like listening to on Halloween?","Wrap music!"}
	};
	//Possible triggerWords
	private String[][] jokeTriggers = {
			{"supper","dinner","meal","food"}, 
			{"living","job","work"},
			{"sheets","sheet","blanket","blankets"},
			{"school","class","subject","education"},
			{"ball","dance","party"},
			{"food","bread","lunch"},
			{"dessert","sweets","winter"},
			{"songs","song","music","beats","rythm"}
		};
	
	public JenniberJokes(){
		jokeCount = 0;
	}
	
	//asks a joke to user and prompts a response from the user
	public void talk(){
		inJokeMode = true;
		
		while(inJokeMode){
			jokeCount++;
			printResponse(triggerNum); //helper method
			jokeResponse = Main.promptInput().toLowerCase();
		}
	}

	public boolean isTriggered(String userInput) {
		//Checks user's input to see if a trigger word was used to do a joke
		//triggerNum is used for printResponse, to ask a joke in relation to the user's input
		for(int x=0; x<jokeTriggers.length; x++){
			for(int y=0; y<jokeTriggers[x].length; y++){
				triggerString = jokeTriggers[x][y];
				if(Main.findKeyword(userInput, triggerString, 0)>=0){
					triggerNum = x;
					return true;
				}
			}	
		}
		//if user wants to hear a joke without having a  trigger word
		//it will select a random number as the triggerNum
		//empty triggerString will be used to check in printResponse and print the appropriate string
		for(int z=0; z<userResponse.length; z++){
			if(Main.findKeyword(userInput, userResponse[z], 0)>=0){
				triggerNum = getTriggerNum();
				triggerString = " ";
				return true;
			}
		}
			
		return false;
	}
	
	//generate a random triggerNum to be used when a trigger word was not used
	private int getTriggerNum(){	
		double rand = Math.random();
		int trig = (int) (jokes.length*rand);
		return trig;
	}
	
	private void printResponse(int triggerNum) {
		//converts all letters in the answer to the joke to lower case  
		String jokeAnswer = jokes[triggerNum][1].toLowerCase();
		
		//will exit while loop, reset jokeCount, and change mood when user has guessed 3 times or answered the joke correctly
		//else if there is no triggerWord and the user wants a joke, it will ask user a joke
		//else it will ask the joke while also stating the rigger word that was used by the user
		if(jokeAnswer.equals(jokeResponse)){
			Main.print("That's right! The answer is: "+jokes[triggerNum][1]);
			inJokeMode = false;
			Main.kat.improveMood(1);
			jokeCount = 0;
			Main.promptForever();
		}
		else if(jokeCount>4){
			Main.print("Since you do not seem to know the answer. The answer is: "+jokes[triggerNum][1]);
			inJokeMode = false;
			Main.kat.improveMood(1);
			jokeCount = 0;
			Main.promptForever();
		}else if(jokeCount>1 && jokeCount<=4){
			Main.kat.improveMood(-1);
			Main.print("No. Guess Again. "+jokes[triggerNum][0]);
		}else if(triggerString.equals(" ")){
			Main.print(jokes[triggerNum][0]);
		}
		else{
			Main.print("Speaking of "+triggerString+". "+jokes[triggerNum][0]);
		}	
	}
}
