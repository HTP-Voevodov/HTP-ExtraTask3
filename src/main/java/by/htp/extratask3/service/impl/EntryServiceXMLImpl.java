package by.htp.extratask3.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import by.htp.extratask3.dao.DAOFactory;
import by.htp.extratask3.criteria.Criteria;
import by.htp.extratask3.dao.DAOException;
import by.htp.extratask3.dao.RepositoryDAO;
import by.htp.extratask3.domain.ApplicationContextFactory;
import by.htp.extratask3.domain.TransferDataStrucure;
import by.htp.extratask3.service.EntryService;
import by.htp.extratask3.service.ServiceException;


public class EntryServiceXMLImpl implements EntryService{
		
	public EntryServiceXMLImpl() {}
	
	@Override
	public <E> void createEntry(Criteria<E> inputCriteria) throws ServiceException{			
		
		ApplicationContextFactory factory = ApplicationContextFactory.getInstance();
		ClassPathXmlApplicationContext context = factory.getClassPathXmlApplicationContext();

		RepositoryDAO repository = context.getBean("xmlRepositoryDAO", RepositoryDAO.class);
		
		try {
			repository.createEntry(inputCriteria);
		} catch (DAOException e) {
			throw new ServiceException("RepositoryDAO entry creation exeption ", e);
		}
	}
	
	@Override
	public <E> List<TransferDataStrucure<E>> searchEntry(Criteria<E> inputCriteria) throws ServiceException {
		
		List<TransferDataStrucure<E>> entriesForPage = new ArrayList<>();
		
		ApplicationContextFactory factory = ApplicationContextFactory.getInstance();
		ClassPathXmlApplicationContext context = factory.getClassPathXmlApplicationContext();

		RepositoryDAO repository = context.getBean("xmlRepositoryDAO", RepositoryDAO.class);
		
		try {
			entriesForPage = repository.searchEntries(inputCriteria);
		} catch (DAOException e) {
			throw new ServiceException("RepositoryDAO searching exeption ", e);
		}
		
		return entriesForPage;
	}
	
}
