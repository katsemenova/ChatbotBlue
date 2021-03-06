package groupFiles;

public class KatMood implements Chatbot{

	private String[] happyWords={"smart", "genious","beautiful","cute","pretty","amazing","adorable","funny","lovely","cute"};
	private String[] sadWords={"ugly","dumb","stupid","crazy","bad","disgusting","gross","annoying","evil", "worthless"};
	private String[] youArray={"you","you're","you are","you are very","you are really","your"};
	public String[] moods={"upset","unhappy","sad","glum","miserable","happy","joyous","flattered","delighted","extatic"};
	private String[] responses={"Wow, you make me feel so ","How can you say that? I am ","Because you think that, I am ","I can't believe you think that, now I'm "};
	
	private String triggeredString;
	private String userResponse;
	private String moodWord;
	private String lastInputedMood;
	public String currentMood;
	
	public int moodLevel;//used in other's code
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
	/**
	 * helper method for talk
	 * that checks which word is going to be used (happy/sad)
	 * and then sets chatbots mood accordingly
	 * @param userResponse and the start position that you want to search the string from
	 */
	private String moodSet(String userResponse,int strtPos) {
		for(int i=0; i<happyWords.length; i++){
			
			int happyWordPsn = Main.findKeyword(userResponse, happyWords[i], strtPos);
			int sadWordPsn=Main.findKeyword(userResponse, sadWords[i], strtPos);
			int addressingPsn = findAddressingPosition(userResponse,strtPos);
			
			if(addressingPsn>=0 && happyWordPsn>=0 && addressingPsn<happyWordPsn){
				lastInputedMood="positive";
				return happyWords[i];
				
			}else if(addressingPsn>=0 && sadWordPsn>=0 && addressingPsn<sadWordPsn){
				lastInputedMood="negative";
				
				return sadWords[i];
			}
		}
		return "null";
	}
	/**
	 * helper method for talk
	 * that prints chatbot's response based on mood/compliment count
	 * @param 
	 */
	private void chatbotResponse() {
		if(moodWord.equals("null"))
		{
			Main.print("I don't know how to feel about that");
			inMoodLoop=false;
			Main.promptForever();
		}else if(complimentCntLoop>3){
			Main.print("Enough talking about what you think of me. Let's tell some Halloween Jokes");
			inMoodLoop=false;
			Main.promptForever();
			
		}else if(lastInputedMood.equals("positive")) {
			int responseSelection=(int)(Math.random()*responses.length);
			improveMood(+1);
			Main.print(responses[responseSelection]+currentMood);
			
		}else if(lastInputedMood.equals("negative")) {
			int responseSelection=(int)(Math.random()*responses.length);
			improveMood(-1);
			Main.print(responses[responseSelection]+currentMood);
		}
	}
	/**
	 * its a get method that main.java uses to print out the mood and moodlevel
	 *  
	 */
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
				if(moodLevel<0)
					currentMood = moods[0];
				else if(moodLevel>4)
					currentMood = moods[i-5];
				else 
					currentMood = moods[i];
		}else if(i>0){
			currentMood = moods[i];
			if(moodLevel>moods.length)
				currentMood = moods[moods.length];
			if(moodLevel<5)
				currentMood = moods[moodLevel+5];
			
		}
	}
	/** 
	 * helper method for moodSet();
	 *finds the position of a word from the youArray, and reports its position 
	 *this is used in 
	 */
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
