module br.univali.cc.prog3.tetris.dominio {
    requires javafx.controls;
    requires javafx.fxml;

    opens br.univali.cc.prog3.tetris.dominio to javafx.fxml;
    exports br.univali.cc.prog3.tetris.dominio;
}
