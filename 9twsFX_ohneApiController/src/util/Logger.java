package util;



//    UNUSED      Not USED     DOES NOT WORK!!!


import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class Logger {
    @FXML
	public TextArea b_logWindow;

	public Logger() {
		b_logWindow.appendText("In Logger das Window erzeugt" + "\n");
	}

	public void log(String s) {

		b_logWindow.appendText(s + "\n");
	}
}