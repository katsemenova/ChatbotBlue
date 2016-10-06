package groupFiles;

//Our chatbot will get increasingly "annoyed" if you ask the same question over and over

//conversation begins, responseBefore saves users' response, then asks another question,
//compares the responseBefore to the responseAfter
//if the responseBefore == responseAfter, then it starts to count the responses
//(questionCount)
//if the questionCount > 5, then chatbotAnnoyed isTriggered

public class TamannaAnnoy implements Chatbot{

	private boolean inResponseLoop;
	
	private String userResponse;	
	private String responseBefore;
	private String responseAfter;
	
	private String[] chatbotQuestions = {"Are you afraid of ghosts?", "What's your favorite thing about Halloween?", "Do you know what your costume for Halloween is going to be?", "Are you going to go trick or treating?"};
	private String[] chatbotAnnoyed = {"Didn't you just ask ", "Can't you think of anything new? You just said ", "Are you bad at making conversation? You should think of something new. You already asked ", "This is disappointing. Ask me something different. You already asked "};
	
	private int questionCount;
	
	public TamannaAnnoy(){
		questionCount = 0;
	}
	
	public void talk(){
		
		inResponseLoop = true;
		responseBefore = Main.prevResponse;
		responseAfter = userResponse;
		
		while(inResponseLoop){
			questionCount++;
			chatbotResponse(); 
			responseBefore=userResponse;
			userResponse = Main.promptInput();
			responseAfter=userResponse;
			if(!isTriggered(userResponse)){
				inResponseLoop = false;
				//Main.promptForever();
			}
		}
	}
	
	private void chatbotResponse(){
		
		if (questionCount >= 3){
			int responseSelection = (int)(Math.random()*chatbotAnnoyed.length);
			Main.print(chatbotAnnoyed[responseSelection] + "'" + responseBefore	+ "'");
		}else{
			int responseSelection = (int)(Math.random()*chatbotQuestions.length);
			Main.print(chatbotQuestions[responseSelection]);
		}
	}
	
	public boolean isTriggered(String userInput) {
		
		//last = responseBefore.charAt(responseBefore.length() - 1);
		//String comparable = ""+last;
		userResponse = userInput;
		String pr=Main.prevResponse;
		
		//System.out.print("equal?"+pr.equals(userResponse));
		
		String lastChar = ""+ userResponse.charAt(userResponse.length()-1);
		//new
		
		if((lastChar.compareTo("?") == 0) && userResponse.equals(pr)){
			return true;
		}
		return false;
	}

}
