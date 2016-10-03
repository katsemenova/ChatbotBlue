package groupFiles;

public class AnthonyIdentify implements Chatbot{
	
	private boolean inLoop;
	int topicSwitch = 0;//trigger for changeOfSubject
	private String topic;//records sentence
	private String subject;//records keyword
	
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
		inLoopA = true;
		while(inLoopA){
			Main.print("(Math.random()*changeOfSubject.length) + 1");
			//static call on promptInput method from 
			//Main class 
				Main.promptForever();
			}

		}	

	@Override
	public boolean isTriggered(String userInput) {
			{
			String topic = userinput;
			String[] array2 = topic.split(" ");
		}
		return false;	
	}
	
	public void Checking(String)
}

