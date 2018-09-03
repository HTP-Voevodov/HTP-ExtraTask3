package by.htp.extratask3.dao;

import java.util.List;

import by.htp.extratask3.criteria.Criteria;
import by.htp.extratask3.domain.TransferDataStrucure;
import by.htp.extratask3.service.ServiceException;


public interface RepositoryDAO{
	public <E> void createEntry(Criteria<E> inputEntry) throws DAOException;
	public <E> List<TransferDataStrucure<E>> searchEntries(Criteria<E> EntryForSearch) throws DAOException; 
}
