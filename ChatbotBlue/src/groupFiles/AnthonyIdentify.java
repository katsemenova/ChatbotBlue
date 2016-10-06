package groupFiles;

public class AnthonyIdentify implements Chatbot{
	
	private boolean inLoop;
	private String[] checker;//records 1st sentence for future comparisons
	private String keyword = "test";//recorded keyword
	private int triggerCount = 0;//checks if it has been triggered
	
	private String[] changeOfSubject =
		{"You've been talking a lot about" +
				keyword + ". Why don't we change the subject?",
				"I'm bored with "+ keyword + 
				". Can we please talk about something else?"+
				" Turtles perhaps?",
				"Could we get off of the topic of "+
						keyword + "?"};
	//done
	@Override
	public void talk() 
	{
		Main.print(changeOfSubject[(int) ((Math.random()*3) + 1)]);
		inLoop = true;
		while(inLoop)
		{
			Main.print(changeOfSubject[(int) ((Math.random()*3) + 1)]);
			//static call on promptInput method from 
			//Main class 
				Main.promptForever();
		}
	}	

	@Override
	public boolean isTriggered(String userInput) 
	{
			
			String sent = userInput;
			String[] sentence = sent.split(" ");//sentence is an array containing each word of the user's sentence as their own item.
			
			if (triggerCount == 0)
			{	Main.print("stage1");					//triggerCount will be used to see if the keyword has been recorded or not,
				checker = sentence; // and whether or not a sentence has been recorded or not to be used in comparisons 
				triggerCount += 1;
				return false;
			}
			//wooooork
			if (triggerCount == 1){
				Main.print("stage 2");
			System.out.println(triggerCount);
			System.out.println(keyword);
			for (int f = 0; f < sentence.length; f++)
				{
					for(int d = 0; d < checker.length;d++)
					{
						if (checker[d] == sentence[f])
						{
							keyword = sentence[f];//when a match has been found, the keyword is recorded to simplify the rest.
							triggerCount += 1;
						}	
					}	
				}
			return false;
			}
			
			if(triggerCount == 2)
			{Main.print("stage 3");
				for (int s = 0; s < sent.length(); s++)
				{
					keyword = sentence[s];//The keyword is then used to simplify the function
					triggerCount += 1;
				}
			return false;
			}
							
			if (triggerCount == 4)
			{Main.print("stage 4");
				triggerCount = 0;
				return true;
			}
							
			else
			{
				Main.print(changeOfSubject[(int) ((Math.random()*3) + 1)]);
				triggerCount = 0;//if nothing matches in the next sentence,the function will reset.
				return false;
			}	
	}

}


