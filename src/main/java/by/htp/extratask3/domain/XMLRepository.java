package by.htp.extratask3.domain;

import java.util.List;

import by.htp.extratask3.domain.impl.XMLNewsCategory;

public abstract class XMLRepository {
	public abstract XMLCategory searchCategory(String categoryName);
	public abstract List<XMLCategory> getCategories();
	public abstract void addCategory(XMLCategory category);
}
