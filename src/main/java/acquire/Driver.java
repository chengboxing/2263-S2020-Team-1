package acquire;

import javafx.application.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


public class Driver extends Application {

    private Board board;
    private Dealer d;
    private int displacement = 0;
    private Stage mainStage;

    @Override
    public void start(Stage stage) throws Exception {
        this.board = new Board();
        this.d = Dealer.getDealerInstance();
        mainStage = stage;
        Scene scene = new Scene(startScreen(), 1200, 700);
        mainStage.setTitle("main page");
        mainStage.setScene(scene);
        mainStage.show();

    }

    //Desc: This method creates the objects for the start screen including texts, fields and buttons.
    //Return: A BorderPane is returned.
    protected BorderPane startScreen() {

        //Heading
        Text text = new Text("Welcome to Aquire!");
        text.setFill(Color.BLACK);
        text.setStyle("-fx-font: 54 arial;");

        //The vertical box is created to add objects to the Border Pane
        //Consists of a Horizontal Box and a text
        VBox texts = new VBox(100);

        //The horizontal box consists of texts, fields and a start game button.
        HBox list = new HBox(20);
        Text t1 = new Text("Start a new game:      ");
        t1.setStyle("-fx-font: 35 arial;");
        Text t2 = new Text("Number of players: ");
        t2.setStyle("-fx-font: 25 arial;");
        TextField tf1 = new TextField();
        Button b1 = new Button("Start Game");
        list.getChildren().addAll(t1, t2, tf1, b1);

        //the text indicates this section of the BorderPane is for loading games
        //The options for peviously played games are to be added soon.
        Text t = new Text("Load game: ");
        t.setStyle("-fx-font: 35 arial;");
        texts.setAlignment(Pos.CENTER_LEFT);

        texts.getChildren().addAll(list, t);

        //When the start game button is clicked, the start screen is hidden and the Acquire
        //board is shown
        b1.setOnAction(e -> {
            Stage stage = createContent(mainStage, b1.getText());
            mainStage.hide();
            stage.show();

        });

        //The positioning of heading and the vertical box is set on the BorderPane
        BorderPane pane = new BorderPane();
        pane.setTop(text);
        pane.setLeft(texts);
        pane.setAlignment(text, Pos.TOP_CENTER);
        return pane;
    }

    //Desc: This method calls on createBoard and createTable to create the objects.
    //Return: Returns the root, which contains all the JavaFX visual GUI components.
    Pane root = new Pane();
    private Stage createContent(Stage parent, String textLabel) {
        //Pane root = new Pane();
        root.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        root.setPrefSize(1000, 700);

        createBoard(root, "abc");

        createChainLists();
        createPlayers(root);
        createTables(root);
        Scene scene = new Scene(root, 1200, 700);

        Stage stage = new Stage();

        stage.setTitle(textLabel);
        stage.setScene(scene);

        return stage;
    }

    private Text text = new Text("");

    //Desc: The tile class is created to help create the board with all possible tiles.
    //currently in the flyweight, but not sure if working due to javaFX madness...
    private void createBoard(Pane root, String str) {
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

                t = board.getTile(c + Integer.toString(r));
                text = new Text(t.getLocation());
                TileDrawing tile = new TileDrawing(t);
                tile.setTranslateX((r - 1) * 50);
                tile.setTranslateY((c - 65) * 50);

                root.getChildren().add(tile);
            }
        }
    }

    //Desc: This method creates a table on the screen which displays the total Cash and Net value for
    //      each player.

    private void createTables(Pane root) {
        TableView<Player> table1 = new TableView();
        table1.setPrefSize(300, 150);
        table1.setTranslateX(650);
        table1.setTranslateY(50);
        TableColumn<Player, String> firstNameCol = new TableColumn<>("");
        TableColumn<Player, String> lastNameCol = new TableColumn<>("Cash");
        TableColumn<Player, String> emailCol = new TableColumn<>("Net");

        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("money"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("money"));

        table1.setItems(getPlayer());

        //The columns are added to the table.
        table1.getColumns().addAll(firstNameCol, lastNameCol, emailCol);
        //The columns are set to sit the size of the table.
        table1.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //The table is added to the Children of the Pane.
        root.getChildren().add(table1);




        TableView<Table2> table2 = new TableView();
        table2.setPrefSize(420, 250);
        table2.setTranslateX(650);
        table2.setTranslateY(250);

        TableColumn<Table2, String> first= new TableColumn<>("");
        first.setCellValueFactory(new PropertyValueFactory<>("chainName"));
        table2.getColumns().add(first);
        PlayerList l = d.getPlayerList();
        LinkedList<Player> temp = l.getList();
        for (int i = 0; i<temp.size(); i++){
            TableColumn<Table2, String> player = new TableColumn<>(temp.get(i).getID());
            player.setCellValueFactory(new PropertyValueFactory<>("playerStocks"));
            table2.getColumns().addAll(player);
        }

        TableColumn<Table2, String> available = new TableColumn<>("Available");
        TableColumn<Table2, String> chainSize = new TableColumn<>("Chain Size");
        TableColumn<Table2, String> price = new TableColumn<>("Price");

        available.setCellValueFactory(new PropertyValueFactory<>("availableStocks"));
        chainSize.setCellValueFactory(new PropertyValueFactory<>("chainSize"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        Chain[] chains = board.getActiveChains();
        table2.setItems(getTable2Items(chains));

        table2.getColumns().addAll(available, chainSize, price);
        table2.setColumnResizePolicy( TableView.CONSTRAINED_RESIZE_POLICY );

        root.getChildren().add(table2);

    }

    private ObservableList<Table2> getTable2Items(Chain[] chains) {
        ObservableList<Table2> items = FXCollections.observableArrayList();
        for (int i = 1; i<chains.length; i++){
            Table2 t = new Table2(chains[i]);
            items.add(t);
        }

        return items;
    }

    public class Table2{
        private Chain chain;
        private String chainName;
        private int availableStocks;
        private int playerStocks;
        private int chainSize;
        private int price;

        public Table2(Chain c){
            chain = c;
            chainName = chain.getName();
            chainSize = chain.chainSize();
            PlayerList l = d.getPlayerList();
            LinkedList<Player> temp = l.getList();
            Player p = temp.get(0);
            availableStocks = 25 - chain.chainSize();
            playerStocks = p.getOwnedStocks(c);
            price = chainSize * 100;
        }

        public Chain getChain(){ return chain; }
        public String getChainName(){ return chainName; }
        public int getPlayerStocks(){ return playerStocks; }
        public int getChainSize(){ return chainSize; }
        public int getPrice(){ return price; }
        public int getAvailableStocks() { return availableStocks; }

    }


    public ObservableList<Player> getPlayer(){
        PlayerList l = d.getPlayerList();
        LinkedList<Player> temp = l.getList();
        ObservableList<Player> players = FXCollections.observableArrayList();
        for (int i = 0; i<temp.size(); i++) {
            players.add((Player) temp.get(i));
        }
        return players;
    }

    List<String> txt = new ArrayList<>();
    List<Color> c = new ArrayList<>();
    List<Button> buttons1 = new ArrayList<>();
    List<Button> buttons2 = new ArrayList<>();

    private void createChainLists(){
        if(this.board == null){
            this.board = new Board();
        }
        this.board.addChain("T", Color.YELLOW,  0);
        this.board.addChain("I", Color.PINK,  1);
        this.board.addChain("C", Color.LIGHTBLUE, 2);
        this.board.addChain("W", Color.BROWN, 3);
        this.board.addChain("L", Color.RED, 4);
        this.board.addChain("F", Color.GREEN,  5);
        this.board.addChain("A", Color.BLUE,   6);

        Button b1; Button b2; Button b3; Button b4; Button b5; Button b6; Button b7;
        buttons1.add(b1 = new Button()); buttons1.add(b2 = new Button()); buttons1.add(b3 = new Button());
        buttons1.add(b4= new Button()); buttons1.add(b5= new Button()); buttons1.add(b6= new Button());
        buttons1.add(b7= new Button());
        //should have all the colors parsed into the flyweight.


    }

    LinkedList record = new LinkedList();
    LinkedList blackTiles = new LinkedList();

    private void createPlayers(Pane root) {
        d.addPlayer(new Player("Player 1"));
        LinkedList tilesUsed = new LinkedList();
        loop(tilesUsed);

    }

    public void loop(LinkedList tilesUsed) {
        Player c = d.getPlayer();
        List<Tile> playerTiles = c.getHand();
        Map<Tile, Button>buttonDisplay = new HashMap<Tile, Button>();
            playerTiles.forEach(Tile -> buttonDisplay.put(Tile, new Button(Tile.getLocation())));
            this.displacement = 0;
            for(Map.Entry<Tile, Button> entry : buttonDisplay.entrySet()){
                Button b = entry.getValue();
                Tile t = entry.getKey();
                b.setTranslateX(100 + (displacement * 45));
                b.setTranslateY(470);
                displacement++;
                buttons2.add(b);
                root.getChildren().addAll(b);
                b.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        placeBlackTile(c, t);
                        checkNeighboringTiles(t);
                        //tiles[finalI] = replaceTile(tilesUsed).getLocation();
                            root.getChildren().removeAll(buttons2);
                            createTables(root);
                        loop(tilesUsed);
                    }
                });
            }

    }
    Tile t;
    //all tiles start as black, when we check for neighboring tiles we can adjust their color.
    private void placeBlackTile(Player p, Tile t) {
        p.playTile(t);
        text = new Text(t.getLocation());
        TileDrawing tile = new TileDrawing(t);
        int num = Integer.parseInt(t.getLocation().substring(1));
        tile.setTranslateX((num - 1) * 50);
        tile.setTranslateY((t.getLocation().charAt(0) - 65) * 50);
        root.getChildren().add(tile);
        record.add(t);
        blackTiles.add(t);

    }

    private void checkNeighboringTiles(Tile tile) {
        String s = tile.getLocation();
            char c = s.charAt(0);
            int num = Integer.parseInt(s.substring(1));
            LinkedList<Tile> tiles = new LinkedList<Tile>();
            tiles.add(tile);

            Tile str1 = board.getTile((char)(c + 1) + Integer.toString(num));
            Tile str2 = board.getTile((char)(c - 1) + Integer.toString(num));
            Tile str3 = board.getTile((c) + Integer.toString(num + 1));
            Tile str4 = board.getTile((c) + Integer.toString(num - 1));

            if (checkForChains(tile, str1, str2, str3, str4 )== false ){
                if (!board.canCreateNewStock()){
                    buttons2.forEach(button -> button.setDisable(false));
                    return;
                }

                //possibly change it so it finds all possible tiles, then makes the new chain.
                if (str1 != null && (str1).isPlayed()) {
                    if(board.mergeChains(tile, str1)){
                        tiles.add(str1);
                    }
                }
                if (str2 != null && (str2).isPlayed()) {
                    if(board.mergeChains(tile, str2)){
                        tiles.add(str2);
                    }
                }
                if (str3 != null && (str3).isPlayed()) {
                    if(board.mergeChains(tile, str3)){
                        tiles.add(str3);
                    }
                }
                if (str4 != null && str4.isPlayed()) {
                    if(board.mergeChains(tile, str4)){
                        tiles.add(str4);
                    }
                }

                if(tiles.size() > 1){
                    newChain(tiles);
                }
        }
    }



    private boolean checkForChains(Tile s, Tile s1, Tile s2, Tile s3, Tile s4){
        Chain[] chains = board.getActiveChains();
        LinkedList<Chain> nearby= new LinkedList<Chain>();
        String str = s.getLocation();
        for (int i = 0; i < chains.length; i++) {
            LinkedList<Tile> chainTiles = chains[i].getTilesInChain();
            if (chainTiles != null && (chainTiles.contains(s1) || chainTiles.contains(s2) || chainTiles.contains(s3) || chainTiles.contains(s4))) {
                nearby.add(chains[i]);
            }
        }
            //if there is only one nearby chain.
            if(nearby.size() == 1){
                nearby.getFirst().addTile(s);
                text = new Text(s.getLocation());
                TileDrawing tile = new TileDrawing(s);
                blackTiles.remove(s.getLocation());
                int num1 = Integer.parseInt(str.substring(1));
                tile.setTranslateX((num1 - 1) * 50);
                tile.setTranslateY((str.charAt(0) - 65) * 50);
                root.getChildren().add(tile);
                //Tile[] blacks = checkBlackTiles(chains[i], s.getLocation());
                return true;
                //if there is more than one chain
            }else if(nearby.size() > 1){
                Chain largest = nearby.getFirst();
                for (Chain chain : nearby) {
                    if(chain.chainSize() > largest.chainSize()){
                        largest.merge(chain);
                        largest = chain;
                        largest.addTile(s);
                        text = new Text(s.getLocation());
                        TileDrawing tile = new TileDrawing(s);
                        blackTiles.remove(s.getLocation());
                        int num1 = Integer.parseInt(str.substring(1));
                        tile.setTranslateX((num1 - 1) * 50);
                        tile.setTranslateY((str.charAt(0) - 65) * 50);
                        root.getChildren().add(tile);
                        //Tile[] blacks = checkBlackTiles(chains[i], s.getLocation());
                        return true;
                    }else{
                        chain.merge(largest);
                    }
                }
            }
        return false;
    }



    private void newChain(LinkedList<Tile> tiles) {
        buttons2.forEach(button -> button.setDisable(true));
        Text t = new Text();
        t.setText("New Chain");
        t.setTranslateY(520);
        t.setTranslateX(100);

        root.getChildren().add(t);

        Chain[] chains = board.getActiveChains();

        for (int i = 0; i < chains.length; i++) {
            if(chains[i].chainSize() == 0){
                buttons1.get(i).setText(chains[i].getName());
                buttons1.get(i).setTranslateX(100 + (i * 45));
                buttons1.get(i).setTranslateY(540);
                buttons1.get(i).setBackground(new Background(new BackgroundFill(chains[i].getColor(), CornerRadii.EMPTY, Insets.EMPTY)));
                int finalI = i;
                buttons1.get(i).setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        root.getChildren().removeAll(buttons1);
                        createChain(tiles, chains[finalI]);
                        buttons2.forEach(button -> button.setDisable(false));
                        t.setText(null);
                    }
                });
            }else{
                buttons1.get(i).setDisable(true);
            }

        }
        root.getChildren().addAll(buttons1);

    }

    private void createChain(LinkedList<Tile> tiles, Chain chain) {
        for (Tile tile : tiles) {
            chain.addTile(tile);

            text = new Text(tile.getLocation());
            TileDrawing tile1 = new TileDrawing(tile);
            blackTiles.remove(t.getLocation());
            int num1 = Integer.parseInt(tile.getLocation().substring(1));
            tile1.setTranslateX((num1 - 1) * 50);
            tile1.setTranslateY((tile.getLocation().charAt(0) - 65) * 50);
            root.getChildren().add(tile1);
        }

    }


    private Tile[] checkBlackTiles(Chain c, String s){

        Tile[] ret = new Tile[4];
        char chr = s.charAt(0);
        int num = Integer.parseInt(s.substring(1));

        String str1 = (char)(chr + 1) + Integer.toString(num);
        String str2 = (char)(chr - 1) + Integer.toString(num);
        String str3 = (chr) + Integer.toString(num + 1);
        String str4 = (chr) + Integer.toString(num - 1);

        LinkedList<String> strings = new LinkedList();
        strings.add(str1); strings.add(str2); strings.add(str3); strings.add(str4);

        for (int i = 0; i< strings.size(); i++) {
            if (blackTiles.contains(strings.get(i))) {
                t = board.getTile(strings.get(i));
                text = new Text(t.getLocation());
                TileDrawing tile = new TileDrawing(t);
                blackTiles.remove(t.getLocation());
                int num1 = Integer.parseInt(strings.get(i).substring(1));
                tile.setTranslateX((num1 - 1) * 50);
                tile.setTranslateY((strings.get(i).charAt(0) - 65) * 50);
                root.getChildren().add(tile);
                ret[i] = t;
            }
        }
        return ret;
    }


    private class TileDrawing extends StackPane {
        Tile tile;

        public TileDrawing(Tile t) {
            this.tile = t;
            Rectangle border = new Rectangle(50, 50);
            border.setFill(t.getColor());
            border.setStroke(Color.BLACK);
            getChildren().addAll(border, text);

        }
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
