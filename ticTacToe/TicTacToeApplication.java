package ticTacToe;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;




public class TicTacToeApplication extends Application{
    private String currentPlayer="X";
    private ArrayList<Button> lista=new ArrayList<>();

    public static void main(String[] args) {
        launch(TicTacToeApplication.class);
    }

    // metoda za generisanje prozora za igru
    
    @Override
    public void start(Stage arg0) throws Exception {
        Label l1=new Label("Turn: " + this.currentPlayer);
        l1.setFont(Font.font("Monospaced", 25));
        BorderPane bp=new BorderPane();
        
        bp.setTop(l1);
        
        GridPane gp=new GridPane();
        gp.setAlignment(Pos.CENTER);
        gp.setPadding(new Insets(10,10,10,10));
        gp.setVgap(10);
        gp.setHgap(10);
        
        // generisanje 9 tastera, koji imitiraju tablu za igru
        
        for (int i=0; i<9; i++){
            Button btn=new Button();
            btn.setFont(Font.font("Monospaced", 25));
            btn.setMinSize(70, 70);
            btn.setMaxSize(70, 70);
            
            /* zadavanje akcije buttonima, ukoliko je igra zavrsena buttoni se deaktiviraju,
                ukoliko je button slobodan, njegov text property se postavlja na vrednost trenutnog igraca
                zatim se redom proverava da li je trenutni igrac pobednik ili je mozda svako polje popunjeno, ukoliko 
                to nije slucaj, igra se nastavlja uz promenu trenutnog igraca
            */

            btn.setOnAction((event)->{
                if(l1.getText().startsWith("The") || l1.getText().equals("It's draw!")){
                    btn.disarm();
                } else if(btn.getText().isEmpty()){
                    btn.setText(currentPlayer);
                    if(checkIfWinner()){
                        l1.setText("The end!");
                    } else if(allBoxesFilled()){
                        l1.setText("It's a draw!");
                    } else {
                        takeTurn();
                        l1.setText("Turn: " + currentPlayer);
                    }
                }
                
            });
            
            lista.add(btn);
        }
        
        gp.add(lista.get(0), 0, 0);
	gp.add(lista.get(1), 0, 1);
	gp.add(lista.get(2), 0, 2);
	gp.add(lista.get(3), 1, 0);
	gp.add(lista.get(4), 1, 1);
        gp.add(lista.get(5), 1, 2);
	gp.add(lista.get(6), 2, 0);
        gp.add(lista.get(7), 2, 1);
	gp.add(lista.get(8), 2, 2);
        
        bp.setCenter(gp);
        Scene scena=new Scene(bp);
        arg0.setScene(scena);
        arg0.show();
    }

    // metoda za promenu trenutnog igraca
    
    private void takeTurn() {
        if(currentPlayer.equals("X")){
            this.currentPlayer="O";
        } else if(currentPlayer.equals("O")){
            this.currentPlayer="X";
        }
    }

    // metoda za proveru da li je cela tabla /svi buttoni/ popunjena
    
    private boolean allBoxesFilled() {
        for(Button b:lista){
            if(b.getText().isEmpty()){
                return false;
            }
        }
        return true;
    }

    private boolean checkIfWinner() {
        return checkVertical()||checkHorizontal()||checkDiagonal();
    }

    
    /* metode za proveru pobednika, provera se vrsi po orijentacijama: vertikalno, horizontalno i dijagonalno, 
       trazi se bilo koji red/kolona/dijagonala u kojem prvi taster nije prazan i u kojem su druga dva tastera iste vrednosti kao i prvi
    */
    
    private boolean checkVertical() {
        if(!lista.get(0).getText().isEmpty() && ((lista.get(0).getText().equals(lista.get(1).getText())) && (lista.get(0).getText().equals(lista.get(2).getText())))){
            return true;
        }
        
        if(!lista.get(3).getText().isEmpty() && ((lista.get(3).getText().equals(lista.get(4).getText())) && (lista.get(1).getText().equals(lista.get(5).getText())))){
            return true;
        }
        
        if (!lista.get(6).getText().isEmpty() && ((lista.get(6).getText().equals(lista.get(7).getText())) && (lista.get(6).getText().equals(lista.get(8).getText())))) {
            return true;
	}
        
        return false;
    }

    private boolean checkHorizontal() {
        if(!lista.get(0).getText().isEmpty() && ((lista.get(0).getText().equals(lista.get(3).getText())) && (lista.get(0).getText().equals(lista.get(6).getText())))){
            return true;
        }
         if(!lista.get(1).getText().isEmpty() && ((lista.get(1).getText().equals(lista.get(4).getText())) && (lista.get(1).getText().equals(lista.get(7).getText())))){
            return true;
        }
        if(!lista.get(2).getText().isEmpty() && ((lista.get(2).getText().equals(lista.get(5).getText())) && (lista.get(2).getText().equals(lista.get(8).getText())))){
            return true;
        }
        return false;
    }

    private boolean checkDiagonal() {
        if(!lista.get(0).getText().isEmpty() && ((lista.get(0).getText().equals(lista.get(4).getText())) && (lista.get(0).getText().equals(lista.get(8).getText())))){
            return true;
        }
        if(!lista.get(2).getText().isEmpty() && ((lista.get(2).getText().equals(lista.get(4).getText())) && (lista.get(2).getText().equals(lista.get(6).getText())))){
            return true;
        }
        return false;
    }

}
