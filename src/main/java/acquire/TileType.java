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

    private Color c = new Color(0,0,0,0);

    Rectangle border = new Rectangle(50, 50);

    public void setColor(Color color){
        this.c = color;
    }

    public Color getColor(){
        return this.c;
    }
}

