package acquire;

import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
        Scene scene = new Scene(startScreen(), 1000, 700);
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


//    List<String> T_tiles = new ArrayList<>();
//    List<String> I_tiles = new ArrayList<>();
//    List<String> C_tiles = new ArrayList<>();
//    List<String> W_tiles = new ArrayList<>();
//    List<String> L_tiles = new ArrayList<>();
//    List<String> F_tiles = new ArrayList<>();
//    List<String> A_tiles = new ArrayList<>();

//    List<List<String>> chains = new ArrayList<>();

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

        //split button outside of chain class. Draw a button per chain that uses the info it needs from each chain (if possible)
//        txt.add("T"); txt.add("I"); txt.add("C"); txt.add("W"); txt.add("L"); txt.add("F"); txt.add("A");
//        c.add(Color.YELLOW); c.add(Color.PINK); c.add(Color.LIGHTBLUE); c.add(Color.BROWN);
//        c.add(Color.RED); c.add(Color.GREEN); c.add(Color.BLUE);
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

//        for (int f = 0; f < 6; f++) {
//            if (!tilesUsed.contains(tiles[f])) {
//                tilesUsed.add(tiles[f]);
//            }
//        }

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

                        loop(tilesUsed);
                    }
                });
            }

//        Button b1; Button b2; Button b3; Button b4; Button b5; Button b6;
//        buttons2.add(b1 = new Button()); buttons2.add(b2 = new Button()); buttons2.add(b3 = new Button());
//        buttons2.add(b4= new Button()); buttons2.add(b5= new Button()); buttons2.add(b6= new Button());
//
//        for (int i = 0; i < buttons2.size(); i++){
//            buttons2.get(i).setText(tiles[i]);
//            buttons2.get(i).setTranslateX(100 + (i * 45));
//            buttons2.get(i).setTranslateY(470);
//
//            int finalI = i;
//            buttons2.get(i).setOnAction(new EventHandler<ActionEvent>() {
//                @Override
//                public void handle(ActionEvent e) {
//                    placeBlackTile(tiles, finalI);
//                    checkNeighboringTiles(tiles, finalI);
//                    tiles[finalI] = replaceTile(tilesUsed).getLocation();
//                    //root.getChildren().removeAll(buttons2);
//                    loop(tilesUsed, tiles);
//                }
//            });
//        }
//        root.getChildren().addAll(buttons2);
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

    List<Color> clist = new ArrayList<>();
    private void checkNeighboringTiles(Tile tile) {
        String s = tile.getLocation();
            char c = s.charAt(0);
            int num = Integer.parseInt(s.substring(1));

            Tile str1 = board.getTile((char)(c + 1) + Integer.toString(num));
            Tile str2 = board.getTile((char)(c - 1) + Integer.toString(num));
            Tile str3 = board.getTile((c) + Integer.toString(num + 1));
            Tile str4 = board.getTile((c) + Integer.toString(num - 1));

            if (checkForChains(tile, str1, str2, str3, str4 )== false ){
                if (!board.canCreateNewStock()){
                    buttons2.forEach(button -> button.setDisable(false));
                    return;
                }
                if (record.contains(str1)) {
                    if(board.mergeChains(tile, str1)){
                        newChain(tile, str1);
                    }
                }
                if (record.contains(str2)) {
                    if(board.mergeChains(tile, str2)){
                        newChain(tile, str2);
                    }
                }
                if (record.contains(str3)) {
                    if(board.mergeChains(tile, str3)){
                        newChain(tile, str3);
                    }
                }
                if (record.contains(str4)) {
                    if(board.mergeChains(tile, str4)){
                        newChain(tile, str4);
                    }
                }
        }
    }

//    static Queue<String> tiles;
//    public Tile replaceTile(LinkedList tilesUsed) {
//        bank(tilesUsed);
//        String tile;
//        if ((tile = getNextTile()) != null) {
//            ;
//        }
//        Tile newTile = new Tile(tile);
//        return newTile;
//    }

//    private void bank(List tilesUsed) {
//        ArrayList s = new ArrayList();
//        for (char c = 'A'; c < 'J'; c++) {
//            for (int r = 0; r < 12; r++) {
//                if (!tilesUsed.contains(c + Integer.toString(r + 1))) {
//                    s.add(c + Integer.toString(r + 1));
//                } } }
//        Collections.shuffle(s, new Random());
//        tiles = new ArrayDeque<>(s);
//
//    }
//
//    public String getNextTile() {
//        if (tiles.isEmpty()) {
//            return null;
//        }
//        return tiles.remove();
//    }



    private boolean checkForChains(Tile s, Tile s1, Tile s2, Tile s3, Tile s4){
        Chain[] chains = board.getActiveChains();
        String str = s.getLocation();
        for (int i = 0; i < chains.length; i++) {
            LinkedList<Tile> chainTiles = chains[i].getTilesInChain();
            if(chainTiles != null && (chainTiles.contains(s1) || chainTiles.contains(s2) || chainTiles.contains(s3) || chainTiles.contains(s4))){
                chains[i].addTile(s);
                text = new Text(s.getLocation());
                TileDrawing tile = new TileDrawing(s);
                blackTiles.remove(s.getLocation());
                int num1 = Integer.parseInt(str.substring(1));
                tile.setTranslateX((num1 - 1) * 50);
                tile.setTranslateY((str.charAt(0) - 65) * 50);
                root.getChildren().add(tile);
                //Tile[] blacks = checkBlackTiles(chains[i], s.getLocation());
                board.getActiveChains()[i].addTile(s);
                return true;
            }
        }

//        chains.add(T_tiles); chains.add(I_tiles); chains.add(C_tiles); chains.add(W_tiles); chains.add(L_tiles);
//        chains.add(F_tiles); chains.add(A_tiles);

//        clist.add(Color.YELLOW); clist.add(Color.PINK); clist.add(Color.LIGHTBLUE); clist.add(Color.BROWN);
//        clist.add(Color.RED); clist.add(Color.GREEN); clist.add(Color.BLUE);
//        for (int i = 0; i< chains.size(); i++) {
//            if (chains.get(i).contains(s1) || chains.get(i).contains(s2) || chains.get(i).contains(s3) || chains.get(i).contains(s4)) {
//                chains.get(i).add(s);
//                t = board.getTile(s);
//                text = new Text(t.getLocation());
//                TileDrawing tile = new TileDrawing(t);
//                blackTiles.remove(t.getLocation());
//                int num1 = Integer.parseInt(s.substring(1));
//                tile.setTranslateX((num1 - 1) * 50);
//                tile.setTranslateY((s.charAt(0) - 65) * 50);
//                root.getChildren().add(tile);
//                checkBlackTiles(chains.get(i), clist.get(i), t.getLocation());
//                return true;
//            }
//        }
        return false;
    }



    private void newChain(Tile tile, Tile s1) {
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
                        createChain(tile, s1, chains[finalI]);
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

    private void createChain(Tile one, Tile two, Chain chain) {
        chain.addTile(one);
        chain.addTile(two);

        text = new Text(one.getLocation());
        TileDrawing tile1 = new TileDrawing(one);
        blackTiles.remove(t.getLocation());
        int num1 = Integer.parseInt(one.getLocation().substring(1));
        tile1.setTranslateX((num1 - 1) * 50);
        tile1.setTranslateY((one.getLocation().charAt(0) - 65) * 50);
        root.getChildren().add(tile1);

        text = new Text(t.getLocation());
        TileDrawing tile2 = new TileDrawing(two);
        blackTiles.remove(t.getLocation());
        int num2 = Integer.parseInt(two.getLocation().substring(1));
        tile2.setTranslateX((num2 - 1) * 50);
        tile2.setTranslateY((two.getLocation().charAt(0) - 65) * 50);
        root.getChildren().add(tile2);

    }

//    private void recordChainList(int i, String s1, String s2){
//        String s = txt.get(i);
//        if (s.equals("T")){
//            T_tiles.add(s1); T_tiles.add(s2);
//            checkBlackTiles(T_tiles, clist.get(0), s1);
//            checkBlackTiles(T_tiles, clist.get(0), s2);
//        }
//        if (s.equals("I")){
//            I_tiles.add(s1); I_tiles.add(s2);
//            checkBlackTiles(I_tiles, clist.get(1), s1);
//            checkBlackTiles(I_tiles, clist.get(1), s2);
//        }
//        if (s.equals("C")){
//            C_tiles.add(s1); C_tiles.add(s2);
//            checkBlackTiles(C_tiles, clist.get(2), s1);
//            checkBlackTiles(C_tiles, clist.get(2), s2);
//        }
//        if (s.equals("W")){
//            W_tiles.add(s1); W_tiles.add(s2);
//            checkBlackTiles(W_tiles, clist.get(3), s1);
//            checkBlackTiles(W_tiles, clist.get(3), s2);
//        }
//        if (s.equals("L")){
//            L_tiles.add(s1); L_tiles.add(s2);
//            checkBlackTiles(L_tiles, clist.get(4), s1);
//            checkBlackTiles(L_tiles, clist.get(4), s2);
//        }
//        if (s.equals("F")){
//            F_tiles.add(s1); F_tiles.add(s2);
//            checkBlackTiles(F_tiles, clist.get(5), s1);
//            checkBlackTiles(F_tiles, clist.get(5), s2);
//        }
//        if (s.equals("A")){
//            A_tiles.add(s1); A_tiles.add(s2);
//            checkBlackTiles(A_tiles, clist.get(6), s1);
//            checkBlackTiles(A_tiles, clist.get(6), s2);
//        }
//
//    }

//    private void removeChainOption(int finalI){
//        txt.remove(finalI);
//        c.remove(finalI);
//        buttons1.remove(finalI);
//    }


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

        //modify color to come from flyweight pattern.
        //modify str to come from tile.getLocation

        public TileDrawing(Tile t) {
            this.tile = t;
            Rectangle border = new Rectangle(50, 50);
            //Color of Tile is set to white and the outline is set to black.
            border.setFill(t.getColor());
            border.setStroke(Color.BLACK);

            //The object is added to the Pane's children list.
            getChildren().addAll(border, text);

        }
    }

    private void buttonCheck(){

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
