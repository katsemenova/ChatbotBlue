package groupFiles;

//Our chatbot will get increasingly "annoyed" if you ask the same question over and over

public class TamannaAnnoy implements Chatbot{

	private boolean inResponseLoop;
	
	private String userResponse;	
	private String responseBefore;
	private String responseAfter;
	
	private String[] chatbotQuestions = {"Are you afraid of ghosts?", 
			"What's your favorite thing about Halloween?", 
			"Do you know what your costume for Halloween is going to be?", 
			"Are you going to go trick or treating?"};
	private String[] chatbotAnnoyed = {"Didn't you just ask ", 
			"Can't you think of anything new? You just said ", 
			"Are you bad at making conversation? You should think of something new. "
			+ "You already asked ", "This is disappointing. Ask me something different. "
					+ "You already asked "};
	
	private int questionCount;
	
	public TamannaAnnoy(){
		//keeps track of how many times the same question is asked
		questionCount = 0;
	}
	
	public void talk(){
		
		inResponseLoop = true;
		/*the first response to get into the loop is saved in responseBefore
		responseAfter saves the following response in userResponse)*/
		responseBefore = Main.prevResponse;
		responseAfter = userResponse;
		
		while(inResponseLoop){
			//based on the questionCount response, the chatbot responds differently
			questionCount++;
			chatbotResponse(); 
			responseBefore = userResponse;
			userResponse = Main.promptInput().toLowerCase();
			responseAfter = userResponse;
			
			if(!isTriggered(userResponse)){
				inResponseLoop = false;
				Main.promptForever();
			}
		}
	}

	private void chatbotResponse(){
		if(questionCount == 1){
			//the chatbot asks its own questions
			int responseSelection = (int)(Math.random()*chatbotQuestions.length);
			Main.print("I don't want to talk about that. " + chatbotQuestions[responseSelection]);
		}else if(questionCount >= 3){
			/*when the user asks a question over 3 times, the chatbot becomes sad and 
			gives an annoyed response*/
			int responseSelection = (int)(Math.random()*chatbotAnnoyed.length);
			Main.kat.improveMood(-1);
			Main.print(chatbotAnnoyed[responseSelection] + "'" + responseBefore	+ "'");
		}else{
			//if these conditions are not satisfied, the chatbot continues to ask the user questions
			int responseSelection = (int)(Math.random()*chatbotQuestions.length);
			Main.print(chatbotQuestions[responseSelection]);
		}
	}
	
	public boolean isTriggered(String userInput) {
		
		userResponse = userInput;
		
		//converts the last character into a String
		String lastChar = ""+ userResponse.charAt(userResponse.length()-1);
		
		/*if the user asks a question and the previous response is the same as 
		the userResponse than it returns true and goes into the talk method*/
		if(lastChar.equals("?") && userResponse.equals(Main.prevResponse)){
			return true;
		}
		return false;
	}
}
