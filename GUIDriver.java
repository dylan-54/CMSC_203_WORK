import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GUIDriver extends Application{
	public final double WINDOW_HEIGHT = 400;
	public final double CANVAS_WIDTH = 400;
	public final double CANVAS_HEIGHT = 300;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage stg) throws Exception{
		MainPaneFX mainPane = new MainPaneFX (CANVAS_WIDTH, CANVAS_HEIGHT);
		BorderPane root = mainPane.getMainPane();
		Scene scn = new Scene (root, CANVAS_WIDTH, WINDOW_HEIGHT);
		stg.setScene(scn);
		stg.setTitle("Function Grapher");
		stg.show();
	}
}
