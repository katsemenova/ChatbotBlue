package groupFiles;

public class AnthonyIdentify implements Chatbot{
	
	private boolean inLoop;
	int topicSwitch = 0;
	private String topic;
	private String subject;
	
	private String[] changeOfSubject =
		{"You've been talking a lot about" +
				topic + ". Why don't we change the subject?",
				"I'm bored with "+ topic + 
				". Can we please talk about something else?"+
				" Turtles perhaps?",
				"Could we get off of the topic of "+
						topic + "?"};
	//in progress
	@Override
	public void talk() {
		inLoop = true;
		while(inLoop){
			Main.print("(Type 'quit' to go back.)");
			//static call on promptInput method from 
			//AnthonyMain class
			topic = Main.promptInput(); 
			if(Main.findKeyword(topic, subject, 0) >= 0){
				inLoop = false;
				Main.promptForever();
			}
			Main.print("that's my favorite part "
					+ "about school");
		}	
	}

	@Override
	public boolean isTriggered(String userInput) {
		if((int) topicSwitch >= 4){
			System.out.println((Math.random()*changeOfSubject.length) + 1);
		}
		return false;
	}
}
