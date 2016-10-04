package groupFiles;

public class AnthonyIdentify implements Chatbot{
	
	private boolean inLoop;
	int topicSwitch = 0;//used to trigger the isTriggered function
	private String sent;//records future sentences to be compared against key 
	private String key;//records 1st sentence for future comparisons
	private String subject;//recorded keyword
	private int reminder = 0;//checks if it has been triggered
	
	private String[] changeOfSubject =
		{"You've been talking a lot about" +
				topic + ". Why don't we change the subject?",
				"I'm bored with "+ topic + 
				". Can we please talk about something else?"+
				" Turtles perhaps?",
				"Could we get off of the topic of "+
						topic + "?"};
	
	@Override
	public void talk() {
		inLoop = true;
		while(inLoop){
			Main.print("(Math.random()*changeOfSubject.length) + 1");
			reminder = false;
			//static call on promptInput method from 
			//Main class 
				Main.promptForever();
			}

		}	

	@Override
	public boolean isTriggered(String userInput) {
			{
			String sent = userinput;
			String[] sentence = sent.split(" ");
			
			if (reminder = 0)//reminder will be used to see if the keyword has been recorded or not,
				String key = sentence;// and whether or not a sentence has been recorded or not to be used in comparisons 
				reminder = 1;
			
			if (remainder = 1)
				for (f = 0; f < sent.length; f++){
					for(d = 0; d < key.length; d++){
						if (key[d] = sentence[f])
							subject = sentence[f];//when a match has been found, reminder wil be upped to 2 and simplify the process.
							reminder = 2;
							topicSwitch = 1;
					}	
				}
			if(remainder = 2)
				for(d = 0; d < key.length; d++){
					if (key[d] = sentence[f])
						subject = sentence[f];
						reminder = 2;
			}
		return true;	
	}

}

