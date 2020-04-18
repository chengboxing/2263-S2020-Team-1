package acquire;

import javafx.application.*;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class TileType{

    //get the color of the chain it is contained in.
    private Color c;
    Rectangle border = new Rectangle(50, 50);

    public TileType(int c){
        //get the chain, and the color of the chain.
    }

    public Color getColor(){
        return this.c;
    }
}
