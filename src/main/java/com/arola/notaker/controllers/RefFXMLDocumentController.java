package com.arola.notaker.controllers;

import com.arola.notaker.dao.RefsDao;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class RefFXMLDocumentController {

    @FXML
    private TextField titleTextField;

    @FXML
    private TextField urlTextField;

    @FXML
    private TextField authorTextField;

    @FXML
    private void addReference() {
        // Retrieve values from text fields
        String title = titleTextField.getText();
        String url = urlTextField.getText();
        String author = authorTextField.getText();

        // Perform logic to add the reference (e.g., store in a database or display in console)
        
        System.out.println("Added Reference:");
        System.out.println("Title: " + title);
        System.out.println("URL: " + url);
        System.out.println("Author: " + author);
        
        // add to the DB
        
        // create the references service/ DAO
        RefsDao refsDao = new RefsDao();
        
        refsDao.createRef(author,title, url);
        

        // Clear text fields after adding reference
        titleTextField.clear();
        urlTextField.clear();
        authorTextField.clear();
    }
}

