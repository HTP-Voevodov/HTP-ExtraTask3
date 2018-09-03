package by.htp.extratask3.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import by.htp.extratask3.criteria.Criteria;
import by.htp.extratask3.criteria.SearchCriteria.NewsCriteria;
import by.htp.extratask3.domain.ApplicationContextFactory;
import by.htp.extratask3.service.EntryService;
import by.htp.extratask3.service.ServiceException;

public class CreationEntryCommand implements Command {
	private ClassPathXmlApplicationContext context;
	private ApplicationContextFactory factory;
	
	public CreationEntryCommand() {
		
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		// set news params from page to newsCriteria
		
		Criteria<NewsCriteria> newsCriteria = new Criteria<NewsCriteria>(NewsCriteria.class);
		newsCriteria.setCategory(request.getParameter("category"));
		newsCriteria.setSubCategory(request.getParameter("subCategory"));
		// set dynamic params
		for (Object obj : newsCriteria.getGroupSearchName().getEnumConstants()) {
			newsCriteria.add(obj.toString(), request.getParameter(obj.toString()));
		}
		// get entry service	
		factory = ApplicationContextFactory.getInstance();
		context = factory.getClassPathXmlApplicationContext();
		EntryService newsService = context.getBean("entryServiceXML", EntryService.class);
		
		try {
			newsService.createEntry(newsCriteria);
		} catch (ServiceException e) {
			// send error info to page
			request.setAttribute("errorMessage", e.getMessage());
			e.printStackTrace();
		}
		return "add.jsp";
	}

}
