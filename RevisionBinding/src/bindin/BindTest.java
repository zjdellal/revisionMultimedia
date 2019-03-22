package bindin;

import java.net.URL;
import java.util.ResourceBundle;import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.scene.BoundsAccessor;

import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.effect.Effect;

public class BindTest implements Initializable {

	IntegerProperty	a	= new SimpleIntegerProperty(-10);
	IntegerProperty	b	= new SimpleIntegerProperty(10);
	IntegerProperty	c	= new SimpleIntegerProperty(1000);

	public BindTest() {
		// this.methodeBind();
		// this.bindBidirectionnel();
		// this.bindingOperation();
//		this.booleanPropretyMethode();
		this.StringPropriete();
	}

	private void methodeBind() {
		b.addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
			{
				System.out.printf("B changé : %d -> %d", oldValue, newValue).println();
			}
		});

		c.addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
			{
				System.out.printf("C changé : %d -> %d", oldValue, newValue).println();
			}
		});
		b.bind(a);
		c.bind(a);
		a.set(69);
		b.unbind();
		c.unbind();

	}

	private void bindBidirectionnel() {
		b.addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
			{
				System.out.printf("B changé : %d -> %d", oldValue, newValue).println();
			}
		});

		a.addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
			{
				System.out.printf("A changé : %d -> %d", oldValue, newValue).println();
			}
		});

		c.addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
			{
				System.out.printf("C changé : %d -> %d", oldValue, newValue).println();
			}
		});

		b.bindBidirectional(c);
		b.bindBidirectional(a);
		a.set(00);
		b.set(02);
		c.set(55);
	}

	private void bindingOperation() {
		IntegerProperty result = new SimpleIntegerProperty();
		result.addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
			{
				System.out.printf("Résult a changé : %d -> %d", oldValue, newValue).println();
			}
		});

		result.bind(a.subtract(b));

		a.set(10);
		b.set(10);

	}

	private void booleanPropretyMethode() {
		/**
		 * bon ici je peux binder un boolean , pour ce faire , nous allons utiliser
		 * l'objet Boolean Proprety<Type> comme suit;
		 */
		ObjectProperty<Button> b = new SimpleObjectProperty<>();
		BooleanProperty a = new SimpleBooleanProperty();
		a.addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				System.out.printf("A changé : %b -> %b", oldValue, newValue).println();
			}
		});
		a.bind(Bindings.selectBoolean(b, "disable"));
		System.out.printf("Valeur de A : %b", a.get()).println();
		Button k = new Button();
		b.setValue(k);
		System.out.println(b.get());

		// StringProperty rep = new SimpleStringProperty();
		// rep.addListener((ObservableValue<? extends String> observable, String
		// oldValue, String newValue) -> {
		// System.out.printf("A changé : %s -> %s", oldValue, newValue).println();
		// });

		// rep.bind(Bindings.selectString();

	}

	private void StringPropriete() {
		ObjectProperty<Button> b = new SimpleObjectProperty<>();
		StringProperty stp = new SimpleStringProperty();
		BooleanProperty bool = new SimpleBooleanProperty();
		
		bool.addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
			System.out.printf("C changé : %b -> %b", oldValue, newValue).println();
		});

		stp.addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
			System.out.printf("C changé : %s -> %s", oldValue, newValue).println();
		});
		stp.bind(Bindings.selectString(b, "text"));
		bool.bind(Bindings.selectBoolean(b, "cache"));
		
		b.set(new Button());
		b.get().setText("Helo zak");
		b.get().setDisable(true);
		
	}
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
}
