package by.htp.extratask3.controller.command;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import by.htp.extratask3.domain.ApplicationContextFactory;

public class CommandDirector {
	private Map<String, Command> commands = new HashMap<>();
	//private ClassPathXmlApplicationContext context;
	//private ApplicationContextFactory factory;
	
	
	public CommandDirector() {
		//factory = ApplicationContextFactory.getInstance();
		//context = factory.getClassPathXmlApplicationContext();
		
		//commands.put("createEntry", context.getBean("creationEntryCommand", CreationEntryCommand.class));
		//commands.put("searchEntry", context.getBean("searchingEntryCommand", SearchingEntryCommand.class));
		
		commands.put("createEntry", new CreationEntryCommand());
		commands.put("searchEntry", new SearchingEntryCommand());
		
	}
	
	public Command getCommand(String commandName) {
		return commands.get(commandName);
	}
}
