package br.univali.cc.prog3.tetris.dominio;

import java.io.InputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
        Pane test = new Pane();
        test.setPrefSize(800, 600);
        
        InputStream input = Files.newInputStream(Paths.get("TETRIS_INICIAL.png"));
        
        ImageView imagem = new ImageView();
        imagem.setFitWidth(800);
        imagem.setFitHeight(600);
        
        test.getChildren().addAll(imagem);
    }
}
