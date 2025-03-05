package org.example;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class ModernMouseController extends Application {

    private AtomicBoolean running = new AtomicBoolean(false);
    private Thread mouseThread;
    private Button startButton;
    private Button stopButton;
    private Label statusLabel;

    @Override
    public void start(Stage primaryStage) {
        // Configuration de la fenêtre avec style transparent pour éviter les coins blancs
        primaryStage.setTitle("Alline");
        primaryStage.initStyle(StageStyle.TRANSPARENT);

        // Création du conteneur principal
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(30));
        root.setStyle("-fx-background-color: #1E1E2E; -fx-background-radius: 15;");

        // Effet d'ombre pour ajouter de la profondeur
        DropShadow shadow = new DropShadow();
        shadow.setRadius(10.0);
        shadow.setOffsetX(3.0);
        shadow.setOffsetY(3.0);
        shadow.setColor(Color.color(0.0, 0.0, 0.0, 0.5));
        root.setEffect(shadow);

        // Titre de l'application
        Label titleLabel = new Label("Alline");
        titleLabel.setFont(Font.font("Montserrat", FontWeight.BOLD, 24));
        titleLabel.setTextFill(Color.WHITE);

        // Sous-titre
        Label subtitleLabel = new Label("Automatisation du mouvement");
        subtitleLabel.setFont(Font.font("Montserrat", FontWeight.NORMAL, 14));
        subtitleLabel.setTextFill(Color.web("#CDD6F4"));

        // Label de statut
        statusLabel = new Label("Statut: Inactif");
        statusLabel.setFont(Font.font("Montserrat", 14));
        statusLabel.setTextFill(Color.web("#CDD6F4"));

        // Bouton Start avec style moderne
        startButton = new Button("DEMARRER");
        startButton.setStyle("-fx-background-color: #89B4FA; -fx-text-fill: white; -fx-font-weight: bold; " +
                "-fx-padding: 15 30; -fx-background-radius: 30; -fx-cursor: hand;");
        startButton.setPrefWidth(200);
        startButton.setOnMouseEntered(e -> startButton.setStyle("-fx-background-color: #74c2ff; -fx-text-fill: white; " +
                "-fx-font-weight: bold; -fx-padding: 15 30; -fx-background-radius: 30; -fx-cursor: hand;"));
        startButton.setOnMouseExited(e -> startButton.setStyle("-fx-background-color: #89B4FA; -fx-text-fill: white; " +
                "-fx-font-weight: bold; -fx-padding: 15 30; -fx-background-radius: 30; -fx-cursor: hand;"));
        startButton.setOnAction(e -> startMouseMovement());

        // Bouton Stop avec style moderne
        stopButton = new Button("ARRETER");
        stopButton.setStyle("-fx-background-color: #45475A; -fx-text-fill: #BAC2DE; -fx-font-weight: bold; " +
                "-fx-padding: 15 30; -fx-background-radius: 30; -fx-cursor: hand;");
        stopButton.setPrefWidth(200);
        stopButton.setDisable(true);
        stopButton.setOnMouseEntered(e -> {
            if (!stopButton.isDisabled()) {
                stopButton.setStyle("-fx-background-color: #F38BA8; -fx-text-fill: white; -fx-font-weight: bold; " +
                        "-fx-padding: 15 30; -fx-background-radius: 30; -fx-cursor: hand;");
            }
        });
        stopButton.setOnMouseExited(e -> {
            if (!stopButton.isDisabled()) {
                stopButton.setStyle("-fx-background-color: #F38BA8; -fx-text-fill: white; -fx-font-weight: bold; " +
                        "-fx-padding: 15 30; -fx-background-radius: 30; -fx-cursor: hand;");
            }
        });
        stopButton.setOnAction(e -> stopMouseMovement());

        // Bouton Fermer plus grand
        Button closeButton = new Button("X");
        closeButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #cdd6f4; -fx-font-size: 20; -fx-font-weight: bold; -fx-padding: 5 10;");
        closeButton.setOnMouseEntered(e -> closeButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #F38BA8; -fx-font-size: 20; -fx-font-weight: bold; -fx-padding: 5 10;"));
        closeButton.setOnMouseExited(e -> closeButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #cdd6f4; -fx-font-size: 20; -fx-font-weight: bold; -fx-padding: 5 10;"));
        closeButton.setOnAction(e -> {
            stopMouseMovement();
            Platform.exit();
        });

        // Positionnement du bouton fermer
        VBox topBar = new VBox();
        topBar.setAlignment(Pos.TOP_RIGHT);
        topBar.getChildren().add(closeButton);

        // Ajout des éléments au conteneur principal
        root.getChildren().addAll(topBar, titleLabel, subtitleLabel, statusLabel, startButton, stopButton);

        // Création de la scène avec fond transparent
        Scene scene = new Scene(root, 400, 500);
        scene.setFill(Color.TRANSPARENT);

        // Permet de déplacer la fenêtre sans barre de titre
        final Delta dragDelta = new Delta();
        scene.setOnMousePressed(mouseEvent -> {
            dragDelta.x = primaryStage.getX() - mouseEvent.getScreenX();
            dragDelta.y = primaryStage.getY() - mouseEvent.getScreenY();
        });
        scene.setOnMouseDragged(mouseEvent -> {
            primaryStage.setX(mouseEvent.getScreenX() + dragDelta.x);
            primaryStage.setY(mouseEvent.getScreenY() + dragDelta.y);
        });

        // Configuration finale et affichage
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void startMouseMovement() {
        running.set(true);
        startButton.setDisable(true);
        stopButton.setDisable(false);
        stopButton.setStyle("-fx-background-color: #F38BA8; -fx-text-fill: white; -fx-font-weight: bold; " +
                "-fx-padding: 15 30; -fx-background-radius: 30; -fx-cursor: hand;");
        statusLabel.setText("Statut: Actif");
        statusLabel.setTextFill(Color.web("#A6E3A1"));

        mouseThread = new Thread(() -> {
            try {
                Robot robot = new Robot();
                boolean moveUp = true;

                while (running.get()) {
                    Point mousePosition = MouseInfo.getPointerInfo().getLocation();

                    if (moveUp) {
                        // Déplacer vers le haut
                        robot.mouseMove(mousePosition.x, mousePosition.y - 50);
                    } else {
                        // Déplacer vers le bas
                        robot.mouseMove(mousePosition.x, mousePosition.y + 50);
                    }

                    // Inverser la direction pour le prochain mouvement
                    moveUp = !moveUp;

                    // Attendre 60 secondes
                    for (int i = 0; i < 60; i++) {
                        if (!running.get()) break;
                        Thread.sleep(1000);
                    }
                }
            } catch (AWTException | InterruptedException e) {
                e.printStackTrace();
            }
        });

        mouseThread.setDaemon(true);
        mouseThread.start();
    }

    private void stopMouseMovement() {
        running.set(false);
        if (Platform.isFxApplicationThread()) {
            updateUIAfterStop();
        } else {
            Platform.runLater(this::updateUIAfterStop);
        }
    }

    private void updateUIAfterStop() {
        startButton.setDisable(false);
        stopButton.setDisable(true);
        stopButton.setStyle("-fx-background-color: #45475A; -fx-text-fill: #BAC2DE; -fx-font-weight: bold; " +
                "-fx-padding: 15 30; -fx-background-radius: 30; -fx-cursor: hand;");
        statusLabel.setText("Statut: Inactif");
        statusLabel.setTextFill(Color.web("#CDD6F4"));
    }

    // Classe pour gérer le déplacement de la fenêtre sans barre de titre
    private static class Delta {
        double x, y;
    }

    public static void main(String[] args) {
        launch(args);
    }
}