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
public abstract class Tetramino {

    Rectangle a;
    Rectangle b;
    Rectangle c;
    Rectangle d;
    Color color;
    public int posicao = 1;
    public char nome;

    public int pontuacao() {
        int pontuacao = (int) (Math.random() * 5) + 5;
        return pontuacao;
    }

    public void mudarPosicao() {
        if (posicao != 4) {
            posicao++;
        } else {
            posicao = 1;
        }
    }

    public char getNome() {
        return nome;
    }

}
