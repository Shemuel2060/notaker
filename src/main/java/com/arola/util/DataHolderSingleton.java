package com.arola.util;

import com.arola.notaker.entities.Note;
import com.arola.notaker.entities.Notebook;

public final class DataHolderSingleton {
	
	private Note note;
	
	private Notebook notebook;
	
	private static final DataHolderSingleton INSTANCE = 
			new DataHolderSingleton();
	
	private DataHolderSingleton() {}
	
	public static DataHolderSingleton getInstance() {
		return INSTANCE;
	}
	
	
	// getters and setters

	public Note getNote() {
		return note;
	}

	public void setNote(Note note) {
		this.note = note;
	}

	public Notebook getNotebook() {
		return notebook;
	}

	public void setNotebook(Notebook notebook) {
		this.notebook = notebook;
	}
	
	
	

}
