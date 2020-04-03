package acquire;

import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;


public class Driver extends Application {

    private Board board;
    private Stage mainStage;


    @Override
    public void start(Stage stage) throws Exception {

        board = new Board();
        mainStage = stage;
        Scene scene = new Scene(startScreen(), 1000, 700);
        mainStage.setTitle("main page");
        mainStage.setScene(scene);
        mainStage.show();

    }

    //
    protected BorderPane startScreen() {

        Text text = new Text("Welcome to Aquire!");
        text.setFill(Color.BLACK);
        text.setStyle("-fx-font: 54 arial;");

        VBox texts = new VBox(100);

        HBox list = new HBox(20);
        Text t1 = new Text("Start a new game:      ");
        t1.setStyle("-fx-font: 35 arial;");
        Text t2 = new Text("Number of players: ");
        t2.setStyle("-fx-font: 25 arial;");
        TextField tf1 = new TextField ();
        Button b1 = new Button("Start Game");
        list.getChildren().addAll(t1, t2, tf1, b1);

        Text t = new Text("Load game: ");
        t.setStyle("-fx-font: 35 arial;");
        texts.setAlignment(Pos.CENTER_LEFT);

        texts.getChildren().addAll(list, t);


        b1.setOnAction(e -> {
            Stage stage = createContent(mainStage, b1.getText());
            mainStage.hide();
            stage.show();

        });

        BorderPane pane = new BorderPane();
        pane.setTop(text);
        pane.setLeft(texts);
        pane.setAlignment(text, Pos.TOP_CENTER);

        return pane;
    }

    //Desc: This method calls on createBoard and createTable to create the objects.
    //Return: Returns the root, which contains all the JavaFX visual GUI components.
    private Stage createContent(Stage parent, String textLabel) {
        Pane root = new Pane();
        root.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        root.setPrefSize(1000, 700);

        createBoard(root);
        createTable(root);
        Scene scene = new Scene(root, 1000, 700);
        Stage stage = new Stage();

        stage.setTitle(textLabel);
        stage.setScene(scene);

        return stage;
    }


    private Text text = new Text("");

    //Desc: The tile class is created to help create the board with all possible tiles.
    //currently in the flyweight, but not sure if working due to javaFX madness...
    private void createBoard(Pane root) {
        String[] s = new String[108];

        //A String array for all the Tile locations is created.
        int n = 0;
        for (char c = 'A'; c < 'J'; c++) {
            for (int r = 0; r < 12; r++) {
                s[n] = c + Integer.toString(r + 1);
                n += 1;
            }
        }

        n = 0;

        //Each tile is created on the board using a for loop.
        for (char c = 'A'; c < 'J'; c++) {
            for (int r = 1; r < 13; r++) {

                Tile t = board.getTile(c + Integer.toString(r));
                text = new Text(t.getLocation());
                TileDrawing tile = new TileDrawing(t);
                tile.setTranslateX((r - 1) * 50);
                tile.setTranslateY((c - 65) * 50);

                root.getChildren().add(tile);
            }
        }
    }


    private class TileDrawing extends StackPane {
        Tile tile;

        public TileDrawing(Tile t) {
            this.tile = t;
            Rectangle border = new Rectangle(50, 50);
            //Color of Tile is set to white and the outline is set to black.
            border.setFill(Color.WHITE);
            border.setStroke(Color.BLACK);

            //The object is added to the Pane's children list.
            getChildren().addAll(border, text);

            //Temporary hard code: Clicking on the tiles will change the color from white to
            //light blue. This is done just so we can get used ti the javaFX colors.
            setOnMouseClicked(event -> border.setFill(Color.LIGHTBLUE));
        }

        public String getLocation() {
            return tile.getLocation();
        }
    }

    //Desc: This method creates a table on the screen which displays the total Cash and Net value for
    //      each player.
    private void createTable(Pane root) {
        TableView table = new TableView();
        table.setPrefSize(300, 150);
        table.setTranslateX(650);
        table.setTranslateY(50);

        TableColumn firstNameCol = new TableColumn("");
        TableColumn lastNameCol = new TableColumn("Cash");
        TableColumn emailCol = new TableColumn("Net");

        //The columns are added to the table.
        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);
        //The columns are set to sit the size of the table.
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //The table is added to the Children of the Pane.
        root.getChildren().add(table);
    }


    public static void main(String[] args) {
        launch(args);
    }


    private void saveGame() {
        // Used by players to save the game
    }

    private void loadGame() {
        // Used by players to load the game
    }

    public void gameStart() {
        // Used by players to start the game
    }

    public void gameEnd() {
        // Used by players to end the game
    }
}
