/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.univali.cc.prog3.tetris.dominio;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author bruno
 */
public class TetraminoTipoG extends Tetramino {

    public TetraminoTipoG(Rectangle a, Rectangle b, Rectangle c, Rectangle d) {
        this.nome = 'i';
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.color = Color.ORANGE;
        this.a.setFill(color);
        this.b.setFill(color);
        this.c.setFill(color);
        this.d.setFill(color);
    }

    @Override
    public void mudarPosicao() {
        super.mudarPosicao(); //To change body of generated methods, choose Tools | Templates.
    }

}
