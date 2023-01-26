/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.univali.cc.prog3.tetris.dominio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 *
 * @author bruno
 */
public class Jogo {

    public static final int MOVE = 25; // Tamanho do quadrado no campo
    public static final int TAMANHO = 25; // Tamanho do quadrado que ocupa o campo
    public static int XMAX = TAMANHO * 12; // Tamanho do campo em X
    public static int YMAX = TAMANHO * 24; // Tamanho do campo em Y
    public static int[][] CAMPO = new int[XMAX / TAMANHO][YMAX / TAMANHO]; // Matriz do campo do jogo
    public static Pane groupe = new Pane(); // layout "tela"
    public static Tetramino objeto;
    private static Scene scene = new Scene(groupe, XMAX, YMAX + 40); // Background "Onde da vida ao nossa tela do Campo" + pontuação ao lado
    public static int pontuacao = 0; //Pontuação do jogador
    public static int tempo = 0; //Tempo
    private static boolean jogo = true;
    private static FabricaTetraminos fabrica;
    public static Tetramino proximoTetramino = fabrica.obterTetramino();
    private static int qtdLinhas = 0; //Quantidade de linhas que o jogador concluiu

    public void iniciar(Stage stage) throws IOException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        for (int[] a : CAMPO) {
            Arrays.fill(a, 0); //Setando que nosso campo não há blocos
        }
        //Interface com a ponutação
        Line linha = new Line(0, YMAX, XMAX, YMAX);
        Line linhaMeio = new Line(XMAX/2+25, YMAX, XMAX/2+25, YMAX + 40);
        scene.setFill(Color.GRAY);
        Text textoPontuacao = new Text("Pontuação: ");
        textoPontuacao.setStyle("-fx-font: 20 arial;-fx-font-weight: bold;");
        textoPontuacao.setY(626);
        textoPontuacao.setX(10);
        textoPontuacao.setFill(Color.BLACK);
        Text textoLinhasJogador = new Text("Linhas: ");
        textoLinhasJogador.setStyle("-fx-font: 20 arial;-fx-font-weight: bold;");
        textoLinhasJogador.setY(626);
        textoLinhasJogador.setX(185);
        textoLinhasJogador.setFill(Color.BLACK);
        Text textoDesenvolvedores = new Text("Developed by: L&Bsoft");
        textoDesenvolvedores.setStyle("-fx-font: 15 arial;-fx-font-weight: bold;");
        textoDesenvolvedores.setY(25);
        textoDesenvolvedores.setX(60);
        textoLinhasJogador.setFill(Color.BLACK);
        groupe.getChildren().addAll(textoPontuacao, linha, textoLinhasJogador,linhaMeio,textoDesenvolvedores);

        //Criando primeiro bloco
        Tetramino a = proximoTetramino;
        groupe.getChildren().addAll(a.a, a.b, a.c, a.d);
        moveOnKeyPress(a);
        objeto = a;
        proximoTetramino = fabrica.obterTetramino();
        pontuacao += proximoTetramino.pontuacao();

        //Nome do executável no canto superior esquerdo
        stage.setScene(scene);
        stage.setTitle("BUGRIS - The buggest tetris version!");
        stage.show();

        //Timer
        Timer fall = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        if (objeto.a.getY() == 0 || objeto.b.getY() == 0 || objeto.c.getY() == 0 || objeto.d.getY() == 0) {
                            tempo++;
                        } else {
                            tempo = 0;
                        }
                        //perdeu o jogo
                        if (tempo == 2) {
                            Text textoPerder = new Text("GAME OVER");
                            textoPerder.setFill(Color.BLACK);
                            textoPerder.setStyle("-fx-font: 45 arial;-fx-font-weight: bold;");
                            textoPerder.setX(10);
                            textoPerder.setY(250);
                            groupe.getChildren().add(textoPerder);
                            jogo = false;
                        }

                        //Sair
                        if (tempo == 15) {
                            System.exit(0);
                        }

                        if (jogo) {
                            Controle.moverBaixo(objeto);
                            textoPontuacao.setText("Pontuação: " + Integer.toString(pontuacao));
                            textoLinhasJogador.setText("Linhas: " + Integer.toString(qtdLinhas));

                        }
                    }
                });
            }
        };
        fall.schedule(task, 0, 300);
    }

    public static void moveOnKeyPress(Tetramino tetramino) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent evento) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                switch (evento.getCode()) {
                    case RIGHT: case D:
                        Controle.moverDireita(tetramino);
                        break;
                    case DOWN: case S:
                        Controle.moverBaixo(tetramino);
                        pontuacao++;
                        break;
                    case LEFT: case A:
                        Controle.moverEsquerda(tetramino);
                        break;
                    case UP: case SPACE: case ENTER:
                        Controle.rotacionar(tetramino);
                        break;
                    case ESCAPE:
                        System.exit(0);
                        break;
                }
            }
        });
    }

    public static void removerLinha(Pane pane) {
        List<Node> reta = new ArrayList<>(); // Salvamento da linha
        List<Integer> linhas = new ArrayList<>();
        List<Node> novaReta = new ArrayList<>();
        int cheio = 0;
        //verificando se a linha está completa
        for (int i = 0; i < CAMPO[0].length; i++) {
            for (int j = 0; j < CAMPO.length; j++) {
                if (CAMPO[j][i] == 1) {
                    cheio++;
                }
            }
            if (cheio == CAMPO.length) {
                linhas.add(i);
                //linhas.add(i + linhas.size());
            }
            cheio = 0;
        }

        //Apagando a linha
        if (linhas.size() > 0) {
            do {
                for (Node node : pane.getChildren()) {
                    if (node instanceof Rectangle) {
                        reta.add(node);
                    }
                }
                pontuacao += 10;
                qtdLinhas++;

                for (Node node : reta) {
                    Rectangle a = (Rectangle) node;
                    if (a.getY() == linhas.get(0) * TAMANHO) {
                        CAMPO[(int) a.getX() / TAMANHO][(int) a.getY() / TAMANHO] = 0;
                        pane.getChildren().remove(node);
                    } else {
                        novaReta.add(node);
                    }
                }

                //apagando os blocos da linha
                for (Node node : novaReta) {
                    Rectangle a = (Rectangle) node;
                    if (a.getY() < linhas.get(0) * TAMANHO) {
                        CAMPO[(int) a.getX() / TAMANHO][(int) a.getY() / TAMANHO] = 0;
                        a.setY(a.getY() + TAMANHO);
                    }
                }
                linhas.remove(0);
                reta.clear();
                novaReta.clear();

                for (Node node : pane.getChildren()) {
                    if (node instanceof Rectangle) {
                        reta.add(node);
                    }
                }

                for (Node node : reta) {
                    Rectangle a = (Rectangle) node;
                    try {
                        CAMPO[(int) a.getX() / TAMANHO][(int) a.getY() / TAMANHO] = 1;
                    } catch (ArrayIndexOutOfBoundsException e) {

                    }
                }
                reta.clear();
            } while (linhas.size() > 0);
        }
    }

}
