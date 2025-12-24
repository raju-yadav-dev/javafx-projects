package tictactoe;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TicTacToe extends Application {

    int r=1;
    boolean reset=false;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        int i,j;
        int[][] arr = new int[3][3];
        Button[][] btn = new Button[3][3];
        GridPane gridPane = new GridPane();
        for(i=0;i<3;i++) {
            for(j=0;j<3;j++) {
                btn[i][j]=new Button(" ");
                btn[i][j].setMinSize(300.0,300.0);
                btn[i][j].setFont(new Font(50));
                gridPane.add(btn[i][j], j, i);
            }
        }
        for(i=0;i<3;i++)
        {
            for(j=0;j<3;j++)
            {
                final int row=i,col=j;
                btn[i][j].setOnAction(e-> {
                    if(arr[row][col]==0)
                    {
                        arr[row][col]=(r%2==0)?2:1;
                        btn[row][col].setText((r%2==0)?"O":"X");
                        r++;
                        if (OddChecker(arr)) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Game Over");
                            alert.setHeaderText(null);
                            alert.setContentText("X wins!");
                            alert.showAndWait();
                            reset=true;
                        } else if (EvenChecker(arr)) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Game Over");
                            alert.setHeaderText(null);
                            alert.setContentText("O wins!");
                            alert.showAndWait();
                            reset=true;
                        }else if(Filled(arr)) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Game Over");
                            alert.setHeaderText(null);
                            alert.setContentText("Match Draw!");
                            alert.showAndWait();
                            reset=true;
                        }
                        if(reset){
                            for(int n=0;n<3;n++)
                            {
                                for(int m=0;m<3;m++)
                                {
                                    btn[n][m].setText(" ");
                                    arr[n][m]=0;
                                    r=1;
                                }
                            }
                            reset=false;
                        }
                    }
                });
            }
        }
        Scene scene = new Scene(gridPane, 900,900);
        stage.setResizable(false);
        stage.setTitle("Tic Tac Toe");
        stage.setScene(scene);
        stage.show();
    }
    boolean EvenChecker(int[][] arr)
    {
        int i,j;
        for(i=0;i<3;i++)
        {
            if(arr[i][0]==2 && arr[i][1]==2 && arr[i][2]==2)
                return true;
        }
        for(i=0;i<3;i++)
        {
            if(arr[0][i]==2 && arr[1][i]==2 && arr[2][i]==2)
                return true;
        }
        if(arr[0][0]==2 && arr[1][1]==2 && arr[2][2]==2)
            return true;
        if(arr[0][2]==2 && arr[1][1]==2 && arr[2][0]==2)
            return true;
        return false;
    }
    boolean OddChecker(int[][] arr)
    {
        int i,j;
        for(i=0;i<3;i++)
        {
            if(arr[i][0]==1 && arr[i][1]==1 && arr[i][2]==1)
                return true;
        }
        for(i=0;i<3;i++)
        {
            if(arr[0][i]==1 && arr[1][i]==1 && arr[2][i]==1)
                return true;
        }
        if(arr[0][0]==1 && arr[1][1]==1 && arr[2][2]==1)
            return true;
        if(arr[0][2]==1 && arr[1][1]==1 && arr[2][0]==1)
            return true;
        return false;
    }
    boolean Filled(int[][] arr)
    {
        int i,j;
        for(i=0;i<3;i++)
        {
            for(j=0;j<3;j++)
            {
                if(arr[i][j]==0)
                    return false;
            }
        }
        return true;
    }
}