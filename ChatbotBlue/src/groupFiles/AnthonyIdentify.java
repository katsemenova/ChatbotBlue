package groupFiles;

import java.util.Scanner;

public class AnthonyIdentify {
	static Scanner input;
	static String user;

	public static void main(String[] args) {
		//demonstrateStringMethods();
		createFields();
		promptName();
		promptForever();
		}
	
	public static void promptName() {
		print("enter your name");
		user = input.nextLine();
		print("Glad to meet you, "+user + "."
				+"\nFor the rest of the time," 
				+"\nI will call you "+user
				+".\nYou may call me Computer."
				+"\nWe should become friends." );
	}
	
	public static void promptForever(){
		while(true){
			promptInput();
		}
	}
	
	public static void promptInput(){
		print("Please type something "+user+".");
		String userInput = input.nextLine();
		print("Congratulations!You typed: "+userInput);
	}
	
	public static void createFields(){
		input = new Scanner(System.in);
		user = "";
	}
	
	public static void print(String s){
		System.out.println(s);
		
	}
	
}
