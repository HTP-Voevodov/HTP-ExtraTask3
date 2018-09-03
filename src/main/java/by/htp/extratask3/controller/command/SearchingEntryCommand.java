package by.htp.extratask3.controller.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import by.htp.extratask3.criteria.Criteria;
import by.htp.extratask3.criteria.SearchCriteria.NewsCriteria;
import by.htp.extratask3.domain.ApplicationContextFactory;
import by.htp.extratask3.domain.NewsForDisplay;
import by.htp.extratask3.domain.TransferDataStrucure;
import by.htp.extratask3.service.EntryService;
import by.htp.extratask3.service.ServiceException;

public class SearchingEntryCommand implements Command {
	ClassPathXmlApplicationContext context;
	ApplicationContextFactory factory;
	
	
	public SearchingEntryCommand() {}
	
	
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
		// get news service
		factory = ApplicationContextFactory.getInstance();
		context = factory.getClassPathXmlApplicationContext();
		EntryService newsService = context.getBean("entryServiceXML", EntryService.class);
		
		
		List<TransferDataStrucure<NewsCriteria>> structureForPage = new ArrayList<>();

		try {
			// get news by params
			structureForPage = newsService.searchEntry(newsCriteria);
		} catch (ServiceException e) {
			// send error info to page
			request.setAttribute("errorMessage", e.getMessage());
			e.printStackTrace();
		}

		if (structureForPage != null) {
			// send news info to page, if it's exists
			request.setAttribute("newsForPage", mappingDataForDisplay(structureForPage));
		}
		return "search.jsp";
	}

	private List<NewsForDisplay> mappingDataForDisplay(List<TransferDataStrucure<NewsCriteria>> newsForPage) {
		List<NewsForDisplay> news = new ArrayList<NewsForDisplay>();
		for (TransferDataStrucure<NewsCriteria> structure : newsForPage) {
			NewsForDisplay pieceOfNews = new NewsForDisplay();
			pieceOfNews.setCategory(structure.getCategory());
			pieceOfNews.setSubCategory(structure.getSubCategory());
			pieceOfNews.setNewsName(structure.getDynamicCriterias().get("newsName"));
			pieceOfNews.setProvider(structure.getDynamicCriterias().get("provider"));
			pieceOfNews.setDateOfIssue(structure.getDynamicCriterias().get("dateOfIssue"));
			pieceOfNews.setNewsBody(structure.getDynamicCriterias().get("newsBody"));
			news.add(pieceOfNews);
		}
		return news;
	}

}
