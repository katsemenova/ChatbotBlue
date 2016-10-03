package groupFiles;

public class KatMood implements Chatbot{

	public String[] happyWords={"smart", "genious","beautiful","cute","pretty","amazing","adorable","funny","lovely","cute"};
	public String[] sadWords={"ugly","dumb","stupid","crazy","bad","disgusting","gross","annoying"};
	public int sadLevel;
	public int happyLevel;
	public int complimentCount;
	
	public void KatHello(){
		sadLevel=0;
		happyLevel=0;
		complimentCount=0;
	}
	public void talk() {
		
	}
//	private static boolean addressingChatbot(String searchString, int psn) {
//		String[] youArray={"you ","you're ","you are ","you are very ","you are really "};
//		for(int i=0;i<youArray.length;i++){
	//		testWord=youArray[i];
	//		if(psn-testWord.length>=0&&searchString.substring(psn-testWord.length,psn).equals(testWord)){
	//			return true;
	//		}
//		}
//		return false;	
//	}
	

	@Override
	public boolean isTriggered(String userInput) {
		// TODO Auto-generated method stub
		return false;
	}

}
