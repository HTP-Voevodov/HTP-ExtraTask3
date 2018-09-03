package by.htp.extratask3.service;

import java.util.List;

import by.htp.extratask3.domain.TransferDataStrucure;
import by.htp.extratask3.criteria.Criteria;
import by.htp.extratask3.criteria.SearchCriteria.NewsCriteria;

public interface EntryService {
	public <E> void createEntry(Criteria<E> inputEntry) throws ServiceException;
	public <E> List<TransferDataStrucure<E>> searchEntry(Criteria<E> entryForSearch) throws ServiceException;
}
