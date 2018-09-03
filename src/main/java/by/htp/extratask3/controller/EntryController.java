package by.htp.extratask3.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import by.htp.extratask3.controller.command.Command;
import by.htp.extratask3.controller.command.CommandDirector;
import by.htp.extratask3.criteria.Criteria;
import by.htp.extratask3.criteria.SearchCriteria.NewsCriteria;
import by.htp.extratask3.domain.ApplicationContextFactory;
import by.htp.extratask3.service.EntryService;
import by.htp.extratask3.service.ServiceException;

public class EntryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ApplicationContextFactory factory;
	CommandDirector commandDirector;
	ClassPathXmlApplicationContext context;
	
	
    public EntryController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String commandName, responsePage;
		Command command;
		
		factory = ApplicationContextFactory.getInstance();
		context = factory.getClassPathXmlApplicationContext();
		commandDirector = context.getBean("commandDirector", CommandDirector.class);
	
		commandName = request.getParameter("command");
		command = commandDirector.getCommand(commandName);
	
		responsePage = command.execute(request, response);

		request.getRequestDispatcher(responsePage).forward(request, response);		
	}

}
