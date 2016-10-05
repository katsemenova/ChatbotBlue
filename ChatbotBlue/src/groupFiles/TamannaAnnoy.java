package groupFiles;

//Our chatbot will get increasingly "annoyed" if you ask the same question over and over

//conversation begins, responseBefore saves users' response, then asks another question,
//compares the responseBefore to the responseAfter
//if the responseBefore == responseAfter, then it starts to count the responses
//(questionCount)
//if the questionCount > 5, then chatbotAnnoyed isTriggered

public class TamannaAnnoy implements Chatbot{

	private String questionResponse;
	private boolean inResponseLoop;
	
	private String responseBefore;
	private String responseAfter;
	
	private String[] chatbotQuestions = {"Hi, what's your name?", "How are you doing on this fine day of Halloween?", "Are you excited that Halloween is coming up?", "If you could choose a costume, what would you choose?"};
	private String[] chatbotAnnoyed = {"Didn't you just ask that question?", "Can't you think of anything new?", "Are you bad at making conversation?", "This is disappointing. Ask me something different."};
	
	private int questionCount;
	
	public TamannaAnnoy(){
		questionCount = 0;
	}
	
	public void talk(){
		
		inResponseLoop = true;
		responseBefore = " ";
		responseAfter = " ";
		
		while(inResponseLoop){
			questionCount++;
			chatbotResponse(); 
			questionResponse = Main.promptInput();
			
			if(!isTriggered(questionResponse)){
				inResponseLoop = false;
				//Main.promptForever();
			}
		}
	}
	
	private void chatbotResponse(){
		
		if (questionCount >= 5){
			int responseSelection = (int)(Math.random()*chatbotAnnoyed.length);
			Main.print(chatbotAnnoyed[responseSelection]);
		}
		
		else{
				int responseSelection = (int)(Math.random()*chatbotQuestions.length);
				Main.print(chatbotQuestions[responseSelection]);
		}
	}
	
	public boolean isTriggered(String userInput) {
		
		if (responseBefore == responseAfter){
			return true;
		}	
		return false;
	}
}
