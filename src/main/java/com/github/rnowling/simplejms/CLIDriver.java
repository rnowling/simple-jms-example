package com.github.rnowling.simplejms;

import java.util.Scanner;

public class CLIDriver
{
	
	public static void main(String[] args) throws Exception
	{
		if(args.length != 3)
		{
			System.out.println("Usage: factoryName topicName username");
			System.exit(1);
		}
		
		Receiver receiver = new Receiver(args[0], args[1]);
		Sender sender = new Sender(args[0], args[1], args[2]);
		
		Scanner scanner = new Scanner(System.in);
		
		while(true)
		{
			String line = scanner.nextLine();
			
			if(line.equalsIgnoreCase("exit"))
			{
				scanner.close();
				sender.close();
				receiver.close();
				System.exit(0);
			}
			
			sender.sendMessage(line);
		}
	}
}
