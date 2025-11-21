package com.bugsfounder.notebud;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import java.io.*;
import java.nio.file.Files;

public class NoteController {

    @FXML private TextArea noteText;
    @FXML private Label fileNameLabel;
    @FXML private Label filePathLabel;

    private File currentFile = new File(System.getProperty("user.home") + "/untitled.txt");

    @FXML
    public void initialize() {
        loadNote();
        updateHeader();
    }

    private void updateHeader() {
        fileNameLabel.setText(currentFile.getName());
        filePathLabel.setText(currentFile.getAbsolutePath());
    }

    private void loadNote() {
        if (currentFile.exists()) {
            try {
                noteText.setText(new String(Files.readAllBytes(currentFile.toPath())));
            } catch (IOException ignored) {}
        }
    }

    @FXML
    public void saveNote() {
        try (FileWriter writer = new FileWriter(currentFile)) {
            writer.write(noteText.getText());
            updateHeader();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void newNote() {
        noteText.clear();
        currentFile = new File(System.getProperty("user.home") + "/untitled.txt");
        updateHeader();
    }

    @FXML
    public void deleteNote() {
        if (currentFile.exists()) currentFile.delete();
        newNote();
    }

    @FXML
    public void pinNote() {
        boolean newValue = !stage.isAlwaysOnTop();
        stage.setAlwaysOnTop(newValue);
    }


    @FXML
    public void onFileNameClicked(MouseEvent event) {
        if (event.getClickCount() != 2) return;

        TextInputDialog dialog = new TextInputDialog(currentFile.getName());
        dialog.setTitle("Rename Note");
        dialog.setHeaderText("Enter new file name:");
        dialog.setContentText("New name:");
        var result = dialog.showAndWait();
        if (result.isEmpty()) return;

        File newFile = new File(currentFile.getParent(), result.get());
        if (currentFile.exists()) currentFile.renameTo(newFile);

        currentFile = newFile;
        updateHeader();
    }

    @FXML
    public void copyFilePath() {
        ClipboardContent content = new ClipboardContent();
        content.putString(currentFile.getAbsolutePath());
        Clipboard.getSystemClipboard().setContent(content);
    }

    private Stage stage;
    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
