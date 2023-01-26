/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.univali.cc.prog3.tetris.dominio;

import javafx.scene.shape.Rectangle;

/**
 *
 * @author bruno
 */
public class FabricaTetraminos {

    private static final int MOVE = Jogo.MOVE;
    private static final int TAMANHO = Jogo.TAMANHO;
    private static int XMAX = Jogo.XMAX;
    private static int YMAX = Jogo.YMAX;
    private static int[][] CAMPO = Jogo.CAMPO;
    
    public static Tetramino obterTetramino() {
        int blocoSorteado = (int) (Math.random() * 100);

        Rectangle a = new Rectangle(Jogo.TAMANHO - 1, Jogo.TAMANHO - 1),
                b = new Rectangle(Jogo.TAMANHO - 1, Jogo.TAMANHO - 1),
                c = new Rectangle(Jogo.TAMANHO - 1, Jogo.TAMANHO - 1),
                d = new Rectangle(Jogo.TAMANHO - 1, Jogo.TAMANHO - 1);

        if (blocoSorteado < 15) {
            //Bloco j
            a.setX(XMAX / 2 - TAMANHO);
            b.setX(XMAX / 2 - TAMANHO);
            b.setY(TAMANHO);
            c.setX(XMAX / 2);
            c.setY(TAMANHO);
            d.setX(XMAX / 2 + TAMANHO);
            d.setY(TAMANHO);
            return new TetraminoTipoA(a, b, c, d);
        } else if (blocoSorteado < 30) {
            //Bloco L
            a.setX(XMAX / 2 + TAMANHO);
            b.setX(XMAX / 2 - TAMANHO);
            b.setY(TAMANHO);
            c.setX(XMAX / 2);
            c.setY(TAMANHO);
            d.setX(XMAX / 2 + TAMANHO);
            d.setY(TAMANHO);
            return new TetraminoTipoB(a, b, c, d);
        } else if (blocoSorteado < 45) {
            //Bloco =
            a.setX(XMAX / 2 - TAMANHO);
            b.setX(XMAX / 2);
            c.setX(XMAX / 2 - TAMANHO);
            c.setY(TAMANHO);
            d.setX(XMAX / 2);
            d.setY(TAMANHO);
            return new TetraminoTipoC(a, b, c, d);
        } else if (blocoSorteado < 60) {
            //Bloco _-
            a.setX(XMAX / 2 + TAMANHO);
            b.setX(XMAX / 2);
            c.setX(XMAX / 2);
            c.setY(TAMANHO);
            d.setX(XMAX / 2 - TAMANHO);
            d.setY(TAMANHO);
            return new TetraminoTipoD(a, b, c, d);
        } else if (blocoSorteado < 75) {
            //Bloco W
            a.setX(XMAX / 2 - TAMANHO);
            b.setX(XMAX / 2);
            c.setX(XMAX / 2);
            c.setY(TAMANHO);
            d.setX(XMAX / 2 + TAMANHO);
            return new TetraminoTipoE(a, b, c, d);
        } else if (blocoSorteado < 90) {
            //Bloco Z
            a.setX(XMAX / 2 + TAMANHO);
            b.setX(XMAX / 2);
            c.setX(XMAX / 2 + TAMANHO);
            c.setY(TAMANHO);
            d.setX(XMAX / 2 + TAMANHO + TAMANHO);
            d.setY(TAMANHO);
            return new TetraminoTipoF(a, b, c, d);
        } else {
            //Bloco i
            a.setX(XMAX / 2 - TAMANHO - TAMANHO);
            b.setX(XMAX / 2 - TAMANHO);
            c.setX(XMAX / 2);
            d.setX(XMAX / 2 + TAMANHO);
            return new TetraminoTipoG(a, b, c, d);
        }
    }

}
