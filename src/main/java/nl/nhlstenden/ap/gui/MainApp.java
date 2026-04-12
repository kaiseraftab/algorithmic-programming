package nl.nhlstenden.ap.gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import nl.nhlstenden.ap.datastructures.*;
import nl.nhlstenden.ap.model.DataRecord;
import nl.nhlstenden.ap.util.DatasetLoader;

import java.io.File;

public class MainApp extends Application {

    private CustomLinkedList<DataRecord> linkedList = new CustomLinkedList<>();
    private Label statusLabel;
    private Label datasetInfoLabel;
    private TextField subsetField;
    private TableView<DataRecord> tableView;

    private String loadedFilePath = "None";
    private int totalRecords = 0;

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        root.setTop(createTopPanel());
        root.setCenter(createTablePanel());

        statusLabel = new Label("Ready. Import a dataset to begin.");
        statusLabel.setStyle("-fx-font-size: 13px; -fx-padding: 8px;");
        root.setBottom(statusLabel);

        Scene scene = new Scene(root, 1100, 750);
        primaryStage.setTitle("Algorithmic Programming - Data Structures & Algorithms");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createTopPanel() {
        VBox topPanel = new VBox(8);
        topPanel.setPadding(new Insets(0, 0, 10, 0));

        Label titleLabel = new Label("Algorithmic Programming - Final Assignment");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        HBox importRow = new HBox(10);
        importRow.setAlignment(Pos.CENTER_LEFT);

        Button importButton = new Button("Import CSV Dataset");
        importButton.setStyle("-fx-font-size: 13px;");
        importButton.setOnAction(e -> importDataset());

        subsetField = new TextField();
        subsetField.setPromptText("Max records (empty = all)");
        subsetField.setPrefWidth(180);

        datasetInfoLabel = new Label("No dataset loaded");
        datasetInfoLabel.setStyle("-fx-font-size: 13px;");

        importRow.getChildren().addAll(importButton, subsetField, datasetInfoLabel);
        topPanel.getChildren().addAll(titleLabel, importRow);

        return topPanel;
    }

    private VBox createTablePanel() {
        VBox panel = new VBox(10);

        tableView = new TableView<>();
        tableView.setPrefHeight(500);

        TableColumn<DataRecord, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCol.setPrefWidth(50);

        TableColumn<DataRecord, String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleCol.setPrefWidth(250);

        TableColumn<DataRecord, Integer> yearCol = new TableColumn<>("Year");
        yearCol.setCellValueFactory(new PropertyValueFactory<>("year"));
        yearCol.setPrefWidth(80);

        TableColumn<DataRecord, Double> ratingCol = new TableColumn<>("Rating");
        ratingCol.setCellValueFactory(new PropertyValueFactory<>("rating"));
        ratingCol.setPrefWidth(80);

        TableColumn<DataRecord, String> genreCol = new TableColumn<>("Genre");
        genreCol.setCellValueFactory(new PropertyValueFactory<>("genre"));
        genreCol.setPrefWidth(120);

        tableView.getColumns().addAll(idCol, titleCol, yearCol, ratingCol, genreCol);

        panel.getChildren().add(tableView);
        return panel;
    }

    private void importDataset() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select CSV Dataset");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("CSV Files", "*.csv")
        );

        File file = fileChooser.showOpenDialog(null);
        if (file == null) return;

        try {
            linkedList.clear();

            int maxRecords = Integer.MAX_VALUE;
            String subsetText = subsetField.getText().trim();
            if (!subsetText.isEmpty()) {
                maxRecords = Integer.parseInt(subsetText);
            }

            DatasetLoader.load(file.getAbsolutePath(), linkedList, maxRecords);
            totalRecords = linkedList.size();
            loadedFilePath = file.getName();

            datasetInfoLabel.setText("Loaded: " + loadedFilePath + " | Records: " + totalRecords);
            statusLabel.setText("Dataset imported successfully.");

            refreshTable(linkedList);
        } catch (Exception e) {
            statusLabel.setText("Error: " + e.getMessage());
        }
    }

    private void refreshTable(CustomList<DataRecord> list) {
        tableView.getItems().clear();
        for (int i = 0; i < list.size(); i++) {
            tableView.getItems().add(list.get(i));
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}