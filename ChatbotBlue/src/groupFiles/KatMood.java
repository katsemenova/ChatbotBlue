package groupFiles;

public class KatMood implements Chatbot{

	private String[] happyWords={"smart", "genious","beautiful","cute","pretty","amazing","adorable","funny","lovely","cute"};
	private String[] sadWords={"ugly","dumb","stupid","crazy","bad","disgusting","gross","annoying","evil", "worthless"};
	private String[] youArray={"you","you're","you are","you are very","you are really"};
	private String[] sadMoods={"upset","sad","unhappy","glum","miserable"};
	private String[] happyMoods={"happy","joyous","flattered","delighted","extatic"};
	private String[] responses={"Wow, you make me feel so ","How can you say that? I am ","Because you think that, I am ","I can't believe you think that, now I'm "};
	
	private String triggeredString;
	private String userResponse;
	private String moodWord;
	private String lastInputedMood;
	
	private int sadLevel;
	private int happyLevel;
	private int complimentCntLoop; //this is the count for the whole program run
	
	private boolean inMoodLoop;
	
	public void KatHello(){
		sadLevel=0;
		happyLevel=0;
		
	}
	@Override
	public void talk() {
		complimentCntLoop=0;
		inMoodLoop=true;
		moodWord=moodSet(triggeredString,0);
		chatbotResponse();
		while(inMoodLoop){
			complimentCntLoop++;
			userResponse=Main.promptInput();
			moodWord=moodSet(userResponse,0);
			chatbotResponse();
			
			if(!isTriggered(userResponse)){
				inMoodLoop=false;
				Main.promptForever();
			}
		}
		
	}
	private String moodSet(String userResponse,int strtPos) {
		for(int i=0; i<happyWords.length; i++){
			
			int happyWordPsn = Main.findKeyword(userResponse, happyWords[i], strtPos);
			int sadWordPsn=Main.findKeyword(userResponse, sadWords[i], strtPos);
			int addressingPsn = findAddressingPosition(userResponse,strtPos);
			
			if(addressingPsn>=0 && happyWordPsn>=0 && addressingPsn<happyWordPsn){
				happyLevel++;
				sadLevel--;
				if(sadLevel<0)
					sadLevel=0;
				lastInputedMood="positive";
				return happyWords[i];
				
			}else if(addressingPsn>=0 && sadWordPsn>=0 && addressingPsn<sadWordPsn){
				sadLevel++;
				happyLevel--;
				if(happyLevel<0)
					happyLevel=0;
				lastInputedMood="negative";
				return sadWords[i];
			}
		}
		return "null";
	}
	private void chatbotResponse() {
		if(moodWord.equals("null"))
		{
			Main.print("I don't know how to feel about that");
			
		}else if(complimentCntLoop>3){
			Main.print("Enough talking about what you think of me. Let's tell some Halloween Jokes");
			inMoodLoop=false;
			
		}else if(lastInputedMood.equals("positive")){
			int responseSelection=(int)(Math.random()*responses.length);
			int moodNum=happyLevel;
			if(happyLevel>happyMoods.length)
				moodNum=5;
			Main.print(responses[responseSelection]+happyMoods[moodNum]);
			
		}else if(lastInputedMood.equals("negative")){
			int responseSelection=(int)(Math.random()*responses.length);
			int moodNum=sadLevel;
			if(happyLevel>sadMoods.length)
				moodNum=5;
			Main.print(responses[responseSelection]+sadMoods[moodNum]);
		}
		
	}
	private int findAddressingPosition(String userResponse, int strtPos){
		for(int i=0;i<youArray.length;i++){
			int youPosition=Main.findKeyword(userResponse, youArray[i], strtPos);
			if(youPosition>=0)
				return youPosition;		
		}
		return -1;
	}
	
	@Override
	public boolean isTriggered(String userInput) {
		for(int i=0;i<youArray.length;i++){
			if(Main.findKeyword(userInput,youArray[i],0)>=0){
				triggeredString=userInput;
				return true;
			}
		}
		return false;
	}

}
