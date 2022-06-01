package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Controller {

    private static final double WIDTH = 700;
    private static final double HEIGHT = 700;
    private static final double PADDING = 10;
    private static final int SIZE = 16;

    private ImageView curImageView;

    @FXML
    protected void play(ActionEvent event){

        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();

        Stage stage = new Stage();

        double side = (WIDTH - 2 * PADDING) / (SIZE * 1.0);
        GridPane gridPane = new GridPane();
        for (int i = 0; i < SIZE; i++) {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setPercentWidth(100 / (SIZE * 1.0));
            gridPane.getColumnConstraints().add(columnConstraints);
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setPercentHeight(100 / (SIZE * 1.0));
            gridPane.getRowConstraints().add(rowConstraints);
        }
        gridPane.setPadding(new Insets(PADDING));

        addRobot(gridPane, new Image(getClass().getResourceAsStream("/gui/p1.jpg"), side, side, true, true), 1, 1);
        addRobot(gridPane, new Image(getClass().getResourceAsStream("/gui/p2.jpg"), side, side, true, true), 8, 9);
        addRobot(gridPane, new Image(getClass().getResourceAsStream("/gui/p3.jpg"), side, side, true, true), 13, 5);
        addRobot(gridPane, new Image(getClass().getResourceAsStream("/gui/p4.jpg"), side, side, true, true), 4, 13);

        gridPane.setOnDragOver(value -> {
            value.acceptTransferModes(TransferMode.MOVE);
        });
        gridPane.setOnDragDropped(value -> {
            double sceneX = value.getSceneX() - PADDING;
            double sceneY = value.getSceneY() - PADDING;
            int columnIndex = (int) (sceneX / side);
            int rowIndex = (int) (sceneY / side);
            curImageView.setUserData(rowIndex + "-" + columnIndex);
            System.out.println("to positon " + curImageView.getUserData());
            GridPane.setConstraints(curImageView, columnIndex, rowIndex);
            value.setDropCompleted(true);
            value.consume();

        });
        gridPane.setPrefSize(WIDTH, HEIGHT);

        Image image = new Image(getClass().getResourceAsStream("/gui/Plateau.jpg"));
        BackgroundSize size = new BackgroundSize(WIDTH, WIDTH, true, false, false, true);
        gridPane.setBackground(new Background(new BackgroundImage(image, null, null, null, size)));
        Scene scene = new Scene(new FlowPane(gridPane), 760, 760);
        stage.setScene(scene);
        stage.show();
    }

    private void addRobot(GridPane gridPane, Image image, int row, int col) {
        ImageView imageView = new ImageView(image);
        imageView.setUserData(row + "-" + col);
        imageView.setOnDragDetected(value -> {
            curImageView = imageView;
            Dragboard dragboard = imageView.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putImage(imageView.getImage());
            dragboard.setContent(content);
            System.out.println("from positon " + curImageView.getUserData());
        });
        gridPane.add(imageView, col, row);
    }


}
