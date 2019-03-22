package binding;

import java.net.URL;
import java.text.NumberFormat;
import java.util.ResourceBundle;

import javax.print.StreamPrintService;

import bindin.BindTest;
import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.effect.Reflection;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

public class Controller implements Initializable {
	@FXML
	Slider											slider;
	@FXML
	TextField										text;

	@FXML
	TextField										txtSomme1;
	@FXML
	TextField										txtSomme2;
	@FXML
	Label												lblResult;
	@FXML
	Label												lblBas;

	private static final double	INIT_VALUE	= 50;

	SimpleDoubleProperty				somme;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("hey !");
		slider.setValue(INIT_VALUE);
		text.setText(new Double(INIT_VALUE).toString());
		text.textProperty().bindBidirectional(slider.valueProperty(), NumberFormat.getNumberInstance());
		somme = new SimpleDoubleProperty();
		// txtSomme1.textProperty().bind(new StringBinding() {
		// {
		// bind(somme);
		// }
		//
		// @Override
		// protected String computeValue() {
		// return Double.toString(somme.get());
		// }
		// });
		//

		// lblResult.textProperty().bindBidirectional();
		DoubleProperty dp = new SimpleDoubleProperty();

		// lblResult.textProperty().bind(dp.asString());
		BindTest bi = new BindTest();
		this.calculBinding();
		this.setEffet();

	}

	private void calculBinding() {
		/**
		 * calcul de deux valeur et affficher sa dans le label result
		 */
		DoubleProperty dp = new SimpleDoubleProperty();
		DoubleProperty dp1 = new SimpleDoubleProperty();
		DoubleProperty somme = new SimpleDoubleProperty();

		Bindings.bindBidirectional(this.txtSomme1.textProperty(), dp1, NumberFormat.getInstance());
		Bindings.bindBidirectional(this.txtSomme2.textProperty(), dp, NumberFormat.getInstance());
		somme.bind(dp.add(dp1.add(slider.valueProperty())));
		this.lblResult.textProperty().bind(somme.asString());

	}

	private void setEffet() {
		// shadow
		DropShadow ds = new DropShadow(BlurType.GAUSSIAN, Color.RED, 15, 2, -10, -10);

		BoxBlur bb = new BoxBlur(5, 10, 1);
		// bind iteration (3e param de bb (1))

		bb.iterationsProperty().bind(this.slider.valueProperty());
		// this.lblBas.setEffect(bb);
		
		
		/**
		 * Reflection
		 */
		Reflection reflect =  new Reflection(1, 5, 5,5);
this.lblBas.setFont(Font.font(null, FontWeight.EXTRA_BOLD, 15));

		this.lblBas.setEffect(reflect);
	}

}
