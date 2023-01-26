/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.univali.cc.prog3.tetris.dominio;

import javafx.scene.shape.*;

/**
 *
 * @author bruno
 */
public class Controle {

    public static final int MOVE = Jogo.MOVE;
    public static final int TAMANHO = Jogo.TAMANHO;
    public static int XMAX = Jogo.XMAX;
    public static int YMAX = Jogo.YMAX;
    public static int[][] CAMPO = Jogo.CAMPO;

    public static void moverDireita(Tetramino tetramino) {
        if (tetramino.a.getX() + MOVE <= XMAX - TAMANHO && tetramino.b.getX() + MOVE <= XMAX - TAMANHO && tetramino.c.getX() + MOVE <= XMAX - TAMANHO && tetramino.d.getX() + MOVE <= XMAX - TAMANHO) {
            int movera = CAMPO[((int) tetramino.a.getX() / TAMANHO) + 1][((int) tetramino.a.getY() / TAMANHO)];
            int moverb = CAMPO[((int) tetramino.b.getX() / TAMANHO) + 1][((int) tetramino.b.getY() / TAMANHO)];
            int moverc = CAMPO[((int) tetramino.c.getX() / TAMANHO) + 1][((int) tetramino.c.getY() / TAMANHO)];
            int moverd = CAMPO[((int) tetramino.d.getX() / TAMANHO) + 1][((int) tetramino.d.getY() / TAMANHO)];
            if (movera == 0 && movera == moverb && moverb == moverc && moverc == moverd) {
                tetramino.a.setX(tetramino.a.getX() + MOVE);
                tetramino.b.setX(tetramino.b.getX() + MOVE);
                tetramino.c.setX(tetramino.c.getX() + MOVE);
                tetramino.d.setX(tetramino.d.getX() + MOVE);
            }
        }
    }

    public static void moverEsquerda(Tetramino tetramino) {
        if (tetramino.a.getX() - MOVE >= 0 && tetramino.b.getX() - MOVE >= 0 && tetramino.c.getX() - MOVE >= 0 && tetramino.d.getX() - MOVE >= 0) {
            int movera = CAMPO[((int) tetramino.a.getX() / TAMANHO) - 1][((int) tetramino.a.getY() / TAMANHO)];
            int moverb = CAMPO[((int) tetramino.b.getX() / TAMANHO) - 1][((int) tetramino.b.getY() / TAMANHO)];
            int moverc = CAMPO[((int) tetramino.c.getX() / TAMANHO) - 1][((int) tetramino.c.getY() / TAMANHO)];
            int moverd = CAMPO[((int) tetramino.d.getX() / TAMANHO) - 1][((int) tetramino.d.getY() / TAMANHO)];
            if (movera == 0 && movera == moverb && moverb == moverc && moverc == moverd) {
                tetramino.a.setX(tetramino.a.getX() - MOVE);
                tetramino.b.setX(tetramino.b.getX() - MOVE);
                tetramino.c.setX(tetramino.c.getX() - MOVE);
                tetramino.d.setX(tetramino.d.getX() - MOVE);
            }
        }
    }

    private static boolean verificarMoverBloco(Rectangle bloco, int x, int y) {
        boolean xb = false;
        boolean yb = false;
        if (x >= 0) {
            xb = bloco.getX() + x * MOVE <= XMAX - TAMANHO;
        }

        if (x < 0) {
            xb = bloco.getX() + x * MOVE >= 0;
        }

        if (y >= 0) {
            yb = bloco.getY() - y * MOVE > 0;
        }

        if (y < 0) {
            yb = bloco.getY() + y * MOVE < YMAX;
        }

        return xb && yb && CAMPO[((int) bloco.getX() / TAMANHO) + x][((int) bloco.getY() / TAMANHO) - y] == 0;
    }

    public static void rotacionar(Tetramino tetramino) {
        int posicaoBloco = tetramino.posicao;
        Rectangle a = tetramino.a;
        Rectangle b = tetramino.b;
        Rectangle c = tetramino.c;
        Rectangle d = tetramino.d;

        switch (tetramino.getNome()) {
            case 'j':
                if (posicaoBloco == 1 && verificarMoverBloco(a, 1, -1) && verificarMoverBloco(c, -1, -1) && verificarMoverBloco(d, -2, -2)) {
                    moverBlocoDireita(tetramino.a);
                    moverBlocoBaixo(tetramino.a);
                    moverBlocoBaixo(tetramino.c);
                    moverBlocoEsquerda(tetramino.c);
                    moverBlocoBaixo(tetramino.d);
                    moverBlocoBaixo(tetramino.d);
                    moverBlocoEsquerda(tetramino.d);
                    moverBlocoEsquerda(tetramino.d);
                    tetramino.mudarPosicao();
                    break;
                }
                if (posicaoBloco == 2 && verificarMoverBloco(a, -1, -1) && verificarMoverBloco(c, -1, 1) && verificarMoverBloco(d, -2, 2)) {
                    moverBlocoBaixo(tetramino.a);
                    moverBlocoEsquerda(tetramino.a);
                    moverBlocoEsquerda(tetramino.c);
                    moverBlocoCima(tetramino.c);
                    moverBlocoEsquerda(tetramino.d);
                    moverBlocoEsquerda(tetramino.d);
                    moverBlocoCima(tetramino.d);
                    moverBlocoCima(tetramino.d);
                    tetramino.mudarPosicao();
                    break;
                }
                if (posicaoBloco == 3 && verificarMoverBloco(a, -1, 1) && verificarMoverBloco(c, 1, 1) && verificarMoverBloco(d, 2, 2)) {
                    moverBlocoEsquerda(tetramino.a);
                    moverBlocoCima(tetramino.a);
                    moverBlocoCima(tetramino.c);
                    moverBlocoDireita(tetramino.c);
                    moverBlocoCima(tetramino.d);
                    moverBlocoCima(tetramino.d);
                    moverBlocoDireita(tetramino.d);
                    moverBlocoDireita(tetramino.d);
                    tetramino.mudarPosicao();
                    break;
                }
                if (posicaoBloco == 4 && verificarMoverBloco(a, 1, 1) && verificarMoverBloco(c, 1, -1) && verificarMoverBloco(d, 2, -2)) {
                    moverBlocoCima(tetramino.a);
                    moverBlocoDireita(tetramino.a);
                    moverBlocoDireita(tetramino.c);
                    moverBlocoBaixo(tetramino.c);
                    moverBlocoDireita(tetramino.d);
                    moverBlocoDireita(tetramino.d);
                    moverBlocoBaixo(tetramino.d);
                    moverBlocoBaixo(tetramino.d);
                    tetramino.mudarPosicao();
                    break;
                }
            case 'l':
                if (posicaoBloco == 1 && verificarMoverBloco(a, 1, -1) && verificarMoverBloco(c, 1, 1) && verificarMoverBloco(b, 2, 2)) {
                    moverBlocoDireita(tetramino.a);
                    moverBlocoBaixo(tetramino.a);
                    moverBlocoCima(tetramino.c);
                    moverBlocoDireita(tetramino.c);
                    moverBlocoCima(tetramino.b);
                    moverBlocoCima(tetramino.b);
                    moverBlocoDireita(tetramino.b);
                    moverBlocoDireita(tetramino.b);
                    tetramino.mudarPosicao();
                    break;
                }
                if (posicaoBloco == 2 && verificarMoverBloco(a, -1, -1) && verificarMoverBloco(b, 2, -2) && verificarMoverBloco(c, 1, -1)) {
                    moverBlocoBaixo(tetramino.a);
                    moverBlocoEsquerda(tetramino.a);
                    moverBlocoDireita(tetramino.b);
                    moverBlocoDireita(tetramino.b);
                    moverBlocoBaixo(tetramino.b);
                    moverBlocoBaixo(tetramino.b);
                    moverBlocoDireita(tetramino.c);
                    moverBlocoBaixo(tetramino.c);
                    tetramino.mudarPosicao();
                    break;
                }
                if (posicaoBloco == 3 && verificarMoverBloco(a, -1, 1) && verificarMoverBloco(c, -1, -1) && verificarMoverBloco(b, -2, -2)) {
                    moverBlocoEsquerda(tetramino.a);
                    moverBlocoCima(tetramino.a);
                    moverBlocoBaixo(tetramino.c);
                    moverBlocoEsquerda(tetramino.c);
                    moverBlocoBaixo(tetramino.b);
                    moverBlocoBaixo(tetramino.b);
                    moverBlocoEsquerda(tetramino.b);
                    moverBlocoEsquerda(tetramino.b);
                    tetramino.mudarPosicao();
                    break;
                }
                if (posicaoBloco == 4 && verificarMoverBloco(a, 1, 1) && verificarMoverBloco(b, -2, 2) && verificarMoverBloco(c, -1, 1)) {
                    moverBlocoCima(tetramino.a);
                    moverBlocoDireita(tetramino.a);
                    moverBlocoEsquerda(tetramino.b);
                    moverBlocoEsquerda(tetramino.b);
                    moverBlocoCima(tetramino.b);
                    moverBlocoCima(tetramino.b);
                    moverBlocoEsquerda(tetramino.c);
                    moverBlocoCima(tetramino.c);
                    tetramino.mudarPosicao();
                    break;
                }
            case 'o':
                break;
            case 's':
                if (posicaoBloco == 1 && verificarMoverBloco(a, -1, -1) && verificarMoverBloco(c, -1, 1) && verificarMoverBloco(d, 0, 2)) {
                    moverBlocoBaixo(tetramino.a);
                    moverBlocoEsquerda(tetramino.a);
                    moverBlocoEsquerda(tetramino.c);
                    moverBlocoCima(tetramino.c);
                    moverBlocoCima(tetramino.d);
                    moverBlocoCima(tetramino.d);
                    tetramino.mudarPosicao();
                    break;
                }
                if (posicaoBloco == 2 && verificarMoverBloco(a, 1, 1) && verificarMoverBloco(c, 1, -1) && verificarMoverBloco(d, 0, -2)) {
                    moverBlocoCima(tetramino.a);
                    moverBlocoDireita(tetramino.a);
                    moverBlocoDireita(tetramino.c);
                    moverBlocoBaixo(tetramino.c);
                    moverBlocoBaixo(tetramino.d);
                    moverBlocoBaixo(tetramino.d);
                    tetramino.mudarPosicao();
                    break;
                }
                if (posicaoBloco == 3 && verificarMoverBloco(a, -1, -1) && verificarMoverBloco(c, -1, 1) && verificarMoverBloco(d, 0, -2)) {
                    moverBlocoBaixo(tetramino.a);
                    moverBlocoEsquerda(tetramino.a);
                    moverBlocoEsquerda(tetramino.c);
                    moverBlocoCima(tetramino.c);
                    moverBlocoCima(tetramino.d);
                    moverBlocoCima(tetramino.d);
                    tetramino.mudarPosicao();
                    break;
                }
                if (posicaoBloco == 4 && verificarMoverBloco(a, 1, 1) && verificarMoverBloco(c, 1, -1) && verificarMoverBloco(d, 0, -2)) {
                    moverBlocoCima(tetramino.a);
                    moverBlocoDireita(tetramino.a);
                    moverBlocoDireita(tetramino.c);
                    moverBlocoBaixo(tetramino.c);
                    moverBlocoBaixo(tetramino.d);
                    moverBlocoBaixo(tetramino.d);
                    tetramino.mudarPosicao();
                    break;
                }
            case 't':
                if (posicaoBloco == 1 && verificarMoverBloco(a, 1, 1) && verificarMoverBloco(d, -1, -1) && verificarMoverBloco(c, -1, 1)) {
                    moverBlocoCima(tetramino.a);
                    moverBlocoDireita(tetramino.a);
                    moverBlocoBaixo(tetramino.d);
                    moverBlocoEsquerda(tetramino.d);
                    moverBlocoEsquerda(tetramino.c);
                    moverBlocoCima(tetramino.c);
                    tetramino.mudarPosicao();
                    break;
                }
                if (posicaoBloco == 2 && verificarMoverBloco(a, 1, -1) && verificarMoverBloco(d, -1, 1) && verificarMoverBloco(c, 1, 1)) {
                    moverBlocoDireita(tetramino.a);
                    moverBlocoBaixo(tetramino.a);
                    moverBlocoEsquerda(tetramino.d);
                    moverBlocoCima(tetramino.d);
                    moverBlocoCima(tetramino.c);
                    moverBlocoDireita(tetramino.c);
                    tetramino.mudarPosicao();
                    break;
                }
                if (posicaoBloco == 3 && verificarMoverBloco(a, -1, -1) && verificarMoverBloco(d, 1, 1) && verificarMoverBloco(d, 1, -1)) {
                    moverBlocoBaixo(tetramino.a);
                    moverBlocoEsquerda(tetramino.a);
                    moverBlocoCima(tetramino.d);
                    moverBlocoDireita(tetramino.d);
                    moverBlocoDireita(tetramino.c);
                    moverBlocoBaixo(tetramino.c);
                    tetramino.mudarPosicao();
                    break;
                }
                if (posicaoBloco == 4 && verificarMoverBloco(a, -1, 1) && verificarMoverBloco(d, 1, -1) && verificarMoverBloco(c, -1, -1)) {
                    moverBlocoEsquerda(tetramino.a);
                    moverBlocoCima(tetramino.a);
                    moverBlocoDireita(tetramino.d);
                    moverBlocoBaixo(tetramino.d);
                    moverBlocoBaixo(tetramino.c);
                    moverBlocoEsquerda(tetramino.c);
                    tetramino.mudarPosicao();
                    break;
                }
            case 'z':
                if (posicaoBloco == 1 && verificarMoverBloco(b, 1, 1) && verificarMoverBloco(c, -1, 1) && verificarMoverBloco(d, -2, 0)) {
                    moverBlocoCima(tetramino.b);
                    moverBlocoDireita(tetramino.b);
                    moverBlocoEsquerda(tetramino.c);
                    moverBlocoCima(tetramino.c);
                    moverBlocoEsquerda(tetramino.d);
                    moverBlocoEsquerda(tetramino.d);
                    tetramino.mudarPosicao();
                    break;
                }
                if (posicaoBloco == 2 && verificarMoverBloco(b, -1, -1) && verificarMoverBloco(c, 1, -1) && verificarMoverBloco(d, 2, 0)) {
                    moverBlocoBaixo(tetramino.b);
                    moverBlocoEsquerda(tetramino.b);
                    moverBlocoDireita(tetramino.c);
                    moverBlocoBaixo(tetramino.c);
                    moverBlocoDireita(tetramino.d);
                    moverBlocoDireita(tetramino.d);
                    tetramino.mudarPosicao();
                    break;
                }
                if (posicaoBloco == 3 && verificarMoverBloco(b, 1, 1) && verificarMoverBloco(c, -1, 1) && verificarMoverBloco(d, -2, 0)) {
                    moverBlocoCima(tetramino.b);
                    moverBlocoDireita(tetramino.b);
                    moverBlocoEsquerda(tetramino.c);
                    moverBlocoCima(tetramino.c);
                    moverBlocoEsquerda(tetramino.d);
                    moverBlocoEsquerda(tetramino.d);
                    tetramino.mudarPosicao();
                    break;
                }
                if (posicaoBloco == 4 && verificarMoverBloco(b, -1, -1) && verificarMoverBloco(c, 1, -1) && verificarMoverBloco(d, 2, 0)) {
                    moverBlocoBaixo(tetramino.b);
                    moverBlocoEsquerda(tetramino.b);
                    moverBlocoDireita(tetramino.c);
                    moverBlocoBaixo(tetramino.c);
                    moverBlocoDireita(tetramino.d);
                    moverBlocoDireita(tetramino.d);
                    tetramino.mudarPosicao();
                    break;
                }
            case 'i':
                if (posicaoBloco == 1 && verificarMoverBloco(a, 2, 2) && verificarMoverBloco(b, 1, 1) && verificarMoverBloco(d, -1, -1)) {
                    moverBlocoCima(tetramino.a);
                    moverBlocoCima(tetramino.a);
                    moverBlocoDireita(tetramino.a);
                    moverBlocoDireita(tetramino.a);
                    moverBlocoCima(tetramino.b);
                    moverBlocoDireita(tetramino.b);
                    moverBlocoBaixo(tetramino.d);
                    moverBlocoEsquerda(tetramino.d);
                    tetramino.mudarPosicao();
                    break;
                }
                if (posicaoBloco == 2 && verificarMoverBloco(a, -2, -2) && verificarMoverBloco(b, -1, -1) && verificarMoverBloco(d, 1, 1)) {
                    moverBlocoBaixo(tetramino.a);
                    moverBlocoBaixo(tetramino.a);
                    moverBlocoEsquerda(tetramino.a);
                    moverBlocoEsquerda(tetramino.a);
                    moverBlocoBaixo(tetramino.b);
                    moverBlocoEsquerda(tetramino.b);
                    moverBlocoCima(tetramino.d);
                    moverBlocoDireita(tetramino.d);
                    tetramino.mudarPosicao();
                    break;
                }
                if (posicaoBloco == 3 && verificarMoverBloco(a, 2, 2) && verificarMoverBloco(b, 1, 1) && verificarMoverBloco(d, -1, -1)) {
                    moverBlocoCima(tetramino.a);
                    moverBlocoCima(tetramino.a);
                    moverBlocoDireita(tetramino.a);
                    moverBlocoDireita(tetramino.a);
                    moverBlocoCima(tetramino.b);
                    moverBlocoDireita(tetramino.b);
                    moverBlocoBaixo(tetramino.d);
                    moverBlocoEsquerda(tetramino.d);
                    tetramino.mudarPosicao();
                    break;
                }
                if (posicaoBloco == 4 && verificarMoverBloco(a, -2, -2) && verificarMoverBloco(b, -1, -1) && verificarMoverBloco(d, 1, 1)) {
                    moverBlocoBaixo(tetramino.a);
                    moverBlocoBaixo(tetramino.a);
                    moverBlocoEsquerda(tetramino.a);
                    moverBlocoEsquerda(tetramino.a);
                    moverBlocoBaixo(tetramino.b);
                    moverBlocoEsquerda(tetramino.b);
                    moverBlocoCima(tetramino.d);
                    moverBlocoDireita(tetramino.d);
                    tetramino.mudarPosicao();
                    break;
                }

        }

    }

    private static void moverBlocoBaixo(Rectangle bloco) {
        if (bloco.getY() + MOVE < YMAX) {
            bloco.setY(bloco.getY() + MOVE);
        }

    }

    private static void moverBlocoDireita(Rectangle bloco) {
        if (bloco.getX() + MOVE <= XMAX - TAMANHO) {
            bloco.setX(bloco.getX() + MOVE);
        }
    }

    private static void moverBlocoEsquerda(Rectangle bloco) {
        if (bloco.getX() - MOVE >= 0) {
            bloco.setX(bloco.getX() - MOVE);
        }
    }

    private static void moverBlocoCima(Rectangle bloco) {
        if (bloco.getY() - MOVE > 0) {
            bloco.setY(bloco.getY() - MOVE);
        }
    }

    public static void moverBaixo(Tetramino tetramino) {
        if (tetramino.a.getY() == YMAX - TAMANHO || tetramino.b.getY() == YMAX - TAMANHO || tetramino.c.getY() == YMAX - TAMANHO || tetramino.d.getY() == YMAX - TAMANHO || moveA(tetramino) || moveB(tetramino) || moveC(tetramino) || moveD(tetramino)) {

            CAMPO[(int) tetramino.a.getX() / TAMANHO][(int) tetramino.a.getY() / TAMANHO] = 1;
            CAMPO[(int) tetramino.b.getX() / TAMANHO][(int) tetramino.b.getY() / TAMANHO] = 1;
            CAMPO[(int) tetramino.c.getX() / TAMANHO][(int) tetramino.c.getY() / TAMANHO] = 1;
            CAMPO[(int) tetramino.d.getX() / TAMANHO][(int) tetramino.d.getY() / TAMANHO] = 1;
            Jogo.removerLinha(Jogo.groupe); //Verificar

            //Criando um novo bloco e adicionando a cena
            Tetramino a = FabricaTetraminos.obterTetramino();
            Jogo.proximoTetramino = FabricaTetraminos.obterTetramino();
            Jogo.objeto = a;
            Jogo.groupe.getChildren().addAll(a.a, a.b, a.c, a.d);
            Jogo.moveOnKeyPress(a);
            Jogo.pontuacao += a.pontuacao();

        }

        //Movendo um bloco para baixo até que chegue no chão
        if (tetramino.a.getY() + MOVE < YMAX && tetramino.b.getY() + MOVE < YMAX && tetramino.c.getY() + MOVE < YMAX && tetramino.d.getY() + MOVE < YMAX) {
            int movea = CAMPO[(int) tetramino.a.getX() / TAMANHO][((int) tetramino.a.getY() / TAMANHO) + 1];
            int moveb = CAMPO[(int) tetramino.b.getX() / TAMANHO][((int) tetramino.b.getY() / TAMANHO) + 1];
            int movec = CAMPO[(int) tetramino.c.getX() / TAMANHO][((int) tetramino.c.getY() / TAMANHO) + 1];
            int moved = CAMPO[(int) tetramino.d.getX() / TAMANHO][((int) tetramino.d.getY() / TAMANHO) + 1];

            if (movea == 0 && movea == moveb && moveb == movec && movec == moved) {
                tetramino.a.setY(tetramino.a.getY() + MOVE);
                tetramino.b.setY(tetramino.b.getY() + MOVE);
                tetramino.c.setY(tetramino.c.getY() + MOVE);
                tetramino.d.setY(tetramino.d.getY() + MOVE);
            }
        }
    }

    private static boolean moveA(Tetramino tetramino) {
        return (CAMPO[(int) tetramino.a.getX() / TAMANHO][((int) tetramino.a.getY() / TAMANHO) + 1] == 1);
    }

    private static boolean moveB(Tetramino tetramino) {
        return (CAMPO[(int) tetramino.b.getX() / TAMANHO][((int) tetramino.b.getY() / TAMANHO) + 1] == 1);
    }

    private static boolean moveC(Tetramino tetramino) {
        return (CAMPO[(int) tetramino.c.getX() / TAMANHO][((int) tetramino.c.getY() / TAMANHO) + 1] == 1);
    }

    private static boolean moveD(Tetramino tetramino) {
        return (CAMPO[(int) tetramino.d.getX() / TAMANHO][((int) tetramino.d.getY() / TAMANHO) + 1] == 1);
    }

}
