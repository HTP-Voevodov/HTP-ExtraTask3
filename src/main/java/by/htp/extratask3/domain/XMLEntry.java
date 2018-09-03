package by.htp.extratask3.domain;

import by.htp.extratask3.criteria.Criteria;

public abstract class XMLEntry {
	public abstract String getName(); 
	public abstract <E> void setParams(Criteria<E> inputEntry);
	public abstract <E> boolean processEntry(Criteria<E> EntryForSearch);
	public abstract <T> void createStructureForOutput(XMLCategory category, XMLSubCategory subCategory, TransferDataStrucure<T> criteriaForInput);
}
