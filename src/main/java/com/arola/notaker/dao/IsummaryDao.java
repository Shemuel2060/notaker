package com.arola.notaker.dao;

import com.arola.notaker.entities.Summary;

public interface IsummaryDao {
	
	/**
	 * creates a Summary specifying the contents of the Summary 
	 * @param contents
	 * @return Summary
	 */
	public Summary createSummary(String contents);

	
	
	/**
	 * retrieves a Summary object by its id
	 * @return Summary
	 */
	public Summary getSummaryById(int id);
	
	
	
	/**
	 * updates the content of an existing summary.
	 * @param oldContent
	 * @param newContent
	 */
	public void editSummary(String oldContent, String newContent);
	

	
	/**
	 * removes a summary by its specified id.
	 * @param id
	 */
	public void removeSummary(int id);
	
	
	// more methods on user notes, notebooks and Sourcess...

}
