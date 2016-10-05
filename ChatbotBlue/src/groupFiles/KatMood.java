package groupFiles;

public class KatMood implements Chatbot{

	private String[] happyWords={"smart", "genious","beautiful","cute","pretty","amazing","adorable","funny","lovely","cute"};
	private String[] sadWords={"ugly","dumb","stupid","crazy","bad","disgusting","gross","annoying","evil", "worthless"};
	private String[] youArray={"you","you're","you are","you are very","you are really"};
	private int sadLevel;
	private int happyLevel;
	private int complimentCount;
	private boolean inMoodLoop;
	private String userResponse;
	
	public void KatHello(){
		sadLevel=0;
		happyLevel=0;
		complimentCount=0;
	}
	public void talk() {
		inMoodLoop=true;
		while(inMoodLoop){
			
			userResponse=Main.promptInput();
			System.out.println(moodSet(userResponse,0));
		}
		
	}
	private String moodSet(String userResponse,int strtPos) {
		for(int i=0; i<happyWords.length; i++){
			int happyWordPsn = Main.findKeyword(userResponse, happyWords[i], strtPos);
			int sadWordPsn=Main.findKeyword(userResponse, sadWords[i], strtPos);
			int addressingPsn = findAddressingPosition(userResponse,strtPos);
			//fix whole thing abv because dealing with arrays
			System.out.println(""+addressingPsn+"hap"+happyWordPsn+"sad"+sadWordPsn);
			if(addressingPsn>=0&&happyWordPsn>=0&&addressingPsn>happyWordPsn){
				sadLevel--;
				happyLevel++;
				chatbotResponse();
				return happyWords[i];
			}else if(addressingPsn>=0&&sadWordPsn>=0&&addressingPsn>sadWordPsn){
				sadLevel++;
				happyLevel--;
				chatbotResponse();
				return sadWords[i];
			}
			System.out.println("happyLevel:"+happyLevel+" sadLevel:"+sadLevel);
		}
		return "I'm okay";
	}
	private void chatbotResponse() {
		System.out.println("notbroken");
		
	}
	private int findAddressingPosition(String userResponse, int strtPos){
		for(int i=0;i<youArray.length;i++){
			int youPosition=Main.findKeyword(userResponse, youArray[i], strtPos);
			if(youPosition>=0)
				return youPosition;		
		}
		return -1;
	}
//	private static boolean addressingChatbot(String searchString, int psn) {
//		String[] youArray={"you ","you're ","you are ","you are very ","you are really "};
//		for(int i=0;i<youArray.length;i++){
//			String testWord=youArray[i];
//			if(psn-testWord.length()>=0&&searchString.substring(psn-testWord.length(),psn).equals(testWord)){
//				return true;
//			}
//		}
//		return false;	
//	}
	

	public boolean isTriggered(String userInput) {
		for(int i=0;i<youArray.length;i++){
			if(Main.findKeyword(userInput,youArray[i],0)>=0)
				return true;
		}
		return false;
	}

}
