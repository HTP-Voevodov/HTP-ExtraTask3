package by.htp.extratask3.domain;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import by.htp.extratask3.dao.DAOFactory;
import by.htp.extratask3.dao.RepositoryDAO;
import by.htp.extratask3.dao.impl.XMLRepositoryDAO;

public class ApplicationContextFactory {
	private static final ApplicationContextFactory instance = new ApplicationContextFactory();
	
	//TODO add dependencies of type from parameter (XML, JDBC,..)
	
	private final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	private ApplicationContextFactory() {}
	
	public ClassPathXmlApplicationContext getClassPathXmlApplicationContext() {
		return context;
	}

	public static ApplicationContextFactory getInstance() {
		return instance;
	}
}
