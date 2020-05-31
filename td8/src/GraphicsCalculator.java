import javafx.application.Application;
import javafx.stage.Stage;   
import javafx.scene.*;   
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.input.KeyEvent;

public class GraphicsCalculator extends Application {   
    Tokenizer tok = new Tokenizer();
    Label res = new Label("0.");
    Button b(char c) {
    	Button b = new Button(Character.toString(c));
    	int size = 50;
    	b.setMaxSize(size, size);
    	b.setMinSize(size, size);
    	b.setOnAction(value -> this.update(c));
    	return b;
    }
    
    void update(char c){
    	this.tok.readChar(c);
    	if(c == '=') {
    		this.res.setText(Double.toString(this.tok.calc.getResult()));
    	}
    	else {
    		this.res.setText(Double.toString(this.tok.isNeg * this.tok.num));    		
    	}
    	
    }
    void handlekey(KeyEvent e) {
    	char c = e.getCharacter().charAt(0);
    	this.update(c);
    }
    @Override
    public void start(Stage stage) {
        stage.show();
        stage.setTitle("Calculator");
        stage.setWidth(250.);
        stage.setHeight(400.);

        //principle.getChildren().add(new Label("res"));
        // TODO

        Scene scene = new Scene(new VBox(new HBox(this.res),
        		new HBox(b('7'), b('8'), b('9'), b('+')),
        		new HBox(b('4'), b('5'), b('6'), b('-')),
        		new HBox(b('1'), b('2'), b('3'), b('*')),
        		new HBox(b('0'), b('.'), b('C'), b('/')),
        		new HBox(b('('), b(')'), b('$'), b('='))
        		));
        scene.setOnKeyTyped(e -> handlekey(e));
        stage.setScene(scene);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}