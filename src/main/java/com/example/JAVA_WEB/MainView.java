package com.example.JAVA_WEB;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.awt.*;

@Route("")
public class MainView extends VerticalLayout {

    public MainView(){
        add(new H1("Hello World!"));
        var btn = new Button("Click Me!");
        var textField = new TextField();

        add(textField,btn);
    }

}
