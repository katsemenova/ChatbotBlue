package groupFiles;

public class KatMood implements Chatbot{

	private String[] happyWords={"smart", "genious","beautiful","cute","pretty","amazing","adorable","funny","lovely","cute"};
	private String[] sadWords={"ugly","dumb","stupid","crazy","bad","disgusting","gross","annoying","evil", "worthless"};
	private String[] youArray={"you","you're","you are","you are very","you are really"};
	public String[] moods={"upset","unhappy","sad","glum","miserable","happy","joyous","flattered","delighted","extatic"};
	private String[] responses={"Wow, you make me feel so ","How can you say that? I am ","Because you think that, I am ","I can't believe you think that, now I'm "};
	
	private String triggeredString;
	private String userResponse;
	private String moodWord;
	private String lastInputedMood;
	public String currentMood;
	
	public int moodLevel;
	private int complimentCntLoop; //this is the count for the whole program run
	
	private boolean inMoodLoop;
	
	public void KatHello(){
		moodLevel=0;
		currentMood= "alright";
		
	}
	//combine sadMood, happyMood, moodlevel above 0, get function
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
				moodLevel++;
				if(moodLevel>moods.length)
					moodLevel=moods.length;
				lastInputedMood="positive";
				
				return happyWords[i];
				
			}else if(addressingPsn>=0 && sadWordPsn>=0 && addressingPsn<sadWordPsn){
				moodLevel--;
				if(moodLevel<0)
					moodLevel=0;
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
			inMoodLoop=false;
			
		}else if(complimentCntLoop>3){
			Main.print("Enough talking about what you think of me. Let's tell some Halloween Jokes");
			inMoodLoop=false;
			
		}else if(lastInputedMood.equals("positive")) {
			int responseSelection=(int)(Math.random()*responses.length);
			int moodNum=moodLevel+4;
			if(moodNum>moods.length)
				moodNum=moods.length;
			currentMood=moods[moodNum];
			Main.print(responses[responseSelection]+currentMood);
			
		}else if(lastInputedMood.equals("negative")) {
			int responseSelection=(int)(Math.random()*responses.length);
			int moodNum=moodLevel;
			if(moodNum>5)
				moodNum=moodLevel-5;
			currentMood=moods[moodNum];
			Main.print(responses[responseSelection]+currentMood);
		}
	}
	public String[] getMood(){
		String moodLvl =""+moodLevel+"";
		String[] array ={currentMood,moodLvl};
		return array;
	}
	
	/**
	 * increases/decreases mood
	 * @param i can be positive or negative
	 */
	public void improveMood(int i){
		moodLevel+=i;
		if(i<0){
			currentMood = moods[i];
				if(moodLevel<0)
					currentMood = moods[0];
				if(moodLevel>4)
					currentMood = moods[i-5];
		}else if(i>0){
			currentMood = moods[i];
			if(moodLevel>moods.length)
				currentMood = moods[moods.length];
			if(moodLevel<5)
				currentMood = moods[moodLevel+5];
			
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
