package com.example.JAVA_WEB;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.Route;
import org.hibernate.boot.jaxb.Origin;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.component.Component;
import org.hibernate.boot.jaxb.spi.Binding;



@Route("")
public class MainView extends VerticalLayout {

    private final PersonRepository repository;
    private TextField first_name = new TextField("Fist Name");
    private TextField last_name = new TextField("Last Name");
    private EmailField email = new EmailField("Email");

    private Grid<Person> grid = new Grid<>(Person.class);
    private Binder<Person> binder = new Binder<>(Person.class);


    public MainView(PersonRepository repository){
        this.repository = repository;

        grid.setColumns("first_name","last_name","email");
        add(getForm(), grid);
        refreshGrid();
    }

    private Component getForm(){
        var layout = new HorizontalLayout();

        var addButton = new Button("Add ");
        addButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        layout.setAlignItems(Alignment.BASELINE);
        layout.add(first_name, last_name, email, addButton);

        binder.bindInstanceFields(this);

        addButton.addClickListener(click ->{

            try{
                var person = new Person();
                binder.writeBean(person);
                repository.save(person);
                refreshGrid();
            }
            catch(ValidationException e){

            }
        });

        return layout;
    }

    private void refreshGrid(){
        grid.setItems(repository.findAll());
    }

}
