package groupFiles;

public class AnthonyIdentify implements Chatbot{
	
	private boolean inLoop;
	int topicSwitch = 0;//used to trigger the isTriggered function
	private String[] key;//records 1st sentence for future comparisons
	private String subject;//recorded keyword
	private int reminder = 0;//checks if it has been triggered
	
	private String[] changeOfSubject =
		{"You've been talking a lot about" +
				subject + ". Why don't we change the subject?",
				"I'm bored with "+ subject + 
				". Can we please talk about something else?"+
				" Turtles perhaps?",
				"Could we get off of the topic of "+
						subject + "?"};
	//done
	@Override
	public void talk() {
		inLoop = true;
		while(inLoop){
			Main.print(changeOfSubject[(int) ((Math.random()*3) + 1)]);
			reminder = 0;
			//static call on promptInput method from 
			//Main class 
				Main.promptForever();
			}

		}	

	@Override
	public boolean isTriggered(String userInput) {
			{
			int f;
			int d;
			int s;
			
			String sent = userInput;
			String[] sentence = sent.split(" ");//sentence is an array containing each word of the user's sentence as their own item.
			
			if (reminder == 0)//reminder will be used to see if the keyword has been recorded or not,
				key = sentence;// and whether or not a sentence has been recorded or not to be used in comparisons 
				reminder = 1;
			
			if (reminder == 1)
				for (f = 0; f < sent.length(); f++){
					for(d = 0; d < key.length; d++){
						if (key[d] == sentence[f])
							subject = sentence[f];//when a match has been found, reminder wil be upped to 2 and simplify the process.
							reminder = 2;
							topicSwitch = 1;
					}	
				}
			if(reminder == 2)
				for (s = 0; s < sent.length(); s++){
							subject = sentence[s];//when a match has been found, reminder wil be upped to 2 and simplify the process.
							topicSwitch += 1;
							
			if (topicSwitch == 4)
				talk();
							
			else
				reminder = 0;//if nothing is found in the next sentence,
							 //the function will reset.
				}
		return true;	
			}
		}
}

