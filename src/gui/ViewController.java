package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import model.entities.Person;

public class ViewController implements Initializable {
	
	@FXML
	private ComboBox<Person> comboBoxPerson;
	@FXML
	private Button btAll;
	
	private ObservableList<Person> obsList; //simplifica a gestão de listas dinâmicas e a atualização da UI em resposta a mudanças nos dados.
	
	@FXML
	public void onComboBoxAction() {
		Person person = comboBoxPerson.getSelectionModel().getSelectedItem(); //getSelectionModel -  é essencial para interagir com a seleção de itens em componentes JavaFX. Ele permite controlar e responder à seleção do usuário de forma dinâmica e eficiente. 
		System.out.println(person);
	}
	
	@FXML
	public void onBtAllAction() {
		for(Person x : comboBoxPerson.getItems()) {
			System.out.println(x);
		}
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) { //essas ações serão executadas na hora da instanciação da classe 
		List<Person> list = new ArrayList<>();
		list.add(new Person(1, "Darrow o'Lykos","thereaperofmars@gmail.com"));
		list.add(new Person(2, "Cassius Bellona","themanwhokilledfear@gmail.com"));
		list.add(new Person(3, "Virginia Augustus","mustangthesovereign@gmail.com"));
		
		obsList = FXCollections.observableArrayList(list);
		comboBoxPerson.setItems(obsList); //vai carregar esses elementos para o comboboc no Scane Builder
	
		Callback<ListView<Person>, ListCell<Person>> factory = lv -> new ListCell<Person>() {
			@Override
			protected void updateItem(Person item, boolean empty) {
				super.updateItem(item, empty);
				setText(empty ? "" : item.getName());
			}
		};
		
		comboBoxPerson.setCellFactory(factory);
		comboBoxPerson.setButtonCell(factory.call(null)); 
	}
	
}
