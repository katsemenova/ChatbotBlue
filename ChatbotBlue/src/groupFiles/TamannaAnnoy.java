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
	
	private char last;
	
	private String[] chatbotAnnoyed = {"Didn't you just ask ", "Can't you think of anything new? You just said ", "Are you bad at making conversation? You should think of something new. You already asked ", "This is disappointing. Ask me something different. You already asked "};
	
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
			Main.print(chatbotAnnoyed[responseSelection] + "'" + responseBefore	+ "'");
		}
	}
	
	public boolean isTriggered(String userInput) {
		
		last = responseBefore.charAt(responseBefore.length() - 1);
		String comparable = ""+last;
		if(comparable.compareTo("?") == 63){
			if(responseBefore.equals(responseAfter)){
				return true;
			}	
		}
		return false;
		//should work
	}
}
