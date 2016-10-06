package groupFiles;

public interface Chatbot {
	public void talk();
	public boolean isTriggered(String userInput);
	public String[] getMood();
}
