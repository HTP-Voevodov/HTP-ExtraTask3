package by.htp.extratask3.domain;

import java.util.List;

import by.htp.extratask3.criteria.Criteria;

public interface XMLSubCategory {
	public String getName();
	public void setName(String name);
	public XMLEntry searchEntry(String entryName);
	public void addEntry(final XMLEntry entry);
	public <E> void processSubCategory(XMLCategory category, Criteria<E> EntryForSearch, List<TransferDataStrucure<E>> entriesForPage);
	
}
