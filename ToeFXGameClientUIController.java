/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ToeFX;

//Austin Abro

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class ToeFXGameClientUIController implements Initializable {

    @FXML
    private TextField txtInput;
    @FXML
    private TextArea taReceived;

    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;
    
    private static final int PORT = 5454;
    private static final String IP = "localhost";
    @FXML
    private GridPane grid;
    @FXML
    private TextArea taBox1;
    @FXML
    private TextArea taLabel;
    @FXML
    private TextArea taBox2;
    @FXML
    private TextArea taBox3;
    @FXML
    private TextArea taBox4;
    @FXML
    private TextArea taBox5;
    @FXML
    private TextArea taBox6;
    @FXML
    private TextArea taBox7;
    @FXML
    private TextArea taBox8;
    @FXML
    private TextArea taBox9;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO: Initialize the socket connection here
        try {
            // Replace "localhost" and 12345 with your server's address and port
            socket = new Socket(IP, 5454);
            writer = new PrintWriter(socket.getOutputStream(), true);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Start a separate thread to listen for messages from the server
            new Thread(this::receiveMessages).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void sendMessageClickHandler(ActionEvent event) {
        String message = txtInput.getText();
        String text1 = taBox1.getText();
        String text2 = taBox2.getText();
        String text3 = taBox3.getText();
        String text4 = taBox4.getText();
        String text5 = taBox5.getText();
        String text6 = taBox6.getText();
        String text7 = taBox7.getText();
        String text8 = taBox8.getText();
        String text9 = taBox9.getText();
        if ((!message.isEmpty()) && (message.contains("(1,1)") && (!text1.contains("X"))
                && (!text1.contains("O"))) || (message.contains("1,2")&& (!text2.contains("X"))
                && (!text2.contains("O"))) || (message.contains("1,3")&& (!text3.contains("X"))
                && (!text3.contains("O")))|| (message.contains("2,1")&& (!text4.contains("X"))
                && (!text4.contains("O")))|| (message.contains("2,2")&& (!text5.contains("X"))
                && (!text5.contains("O")))|| (message.contains("2,3")&& (!text6.contains("X"))
                && (!text6.contains("O")))|| (message.contains("3,1")&& (!text7.contains("X"))
                && (!text7.contains("O")))|| (message.contains("3,2")&& (!text8.contains("X"))
                && (!text8.contains("O")))|| (message.contains("3,3")&&(!text9.contains("X"))
                && (!text9.contains("O"))) && (!checkForWinner())){
            // Send the message to the server
                writer.println(message);         

            // Clear the input field after sending the message
                txtInput.clear();
            
        }
    }

    private void receiveMessages() {
        try {
            String message;
            while ((message = reader.readLine()) != null) {
                // Update the UI with the received message
                appendMessageToTextArea(message);
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void appendMessageToTextArea(String message) {
        // Update the UI with the received message
        
        String text1 = taBox1.getText();
        String text2 = taBox2.getText();
        String text3 = taBox3.getText();
        String text4 = taBox4.getText();
        String text5 = taBox5.getText();
        String text6 = taBox6.getText();
        String text7 = taBox7.getText();
        String text8 = taBox8.getText();
        String text9 = taBox9.getText();
        
        
        if((message.contains("(1,1)")) && (!text1.contains("X")) && (!text1.contains("O")) && (message.contains("X")))
        {
            taReceived.appendText(message + "\n");
            taBox1.setText("X");
        }
        else if ((message.contains("(1,1)")) && (!text1.contains("X")) && (!text1.contains("O")) && (message.contains("O")))
        {
            taReceived.appendText(message + "\n");
            taBox1.setText("O");
        }
        else if((message.contains("(1,2)")) && (!text2.contains("X")) && (!text2.contains("O")) && (message.contains("X")))
        {
            taReceived.appendText(message + "\n");
            taBox2.setText("X");
        }
        else if ((message.contains("(1,2)")) && (!text2.contains("X")) && (!text2.contains("O")) && (message.contains("O")))
        {
            taReceived.appendText(message + "\n");
            taBox2.setText("O");
        }
        else if((message.contains("(1,3)")) && (!text3.contains("X")) && (!text3.contains("O")) && (message.contains("X")))
        {
            taReceived.appendText(message + "\n");
            taBox3.setText("X");
        }
        else if ((message.contains("(1,3)")) && (!text3.contains("X")) && (!text3.contains("O")) && (message.contains("O")))
        {
            taReceived.appendText(message + "\n");
            taBox3.setText("O");
        }
        else if((message.contains("(2,1)")) && (!text4.contains("X")) && (!text4.contains("O")) && (message.contains("X")))
        {
            taReceived.appendText(message + "\n");
            taBox4.setText("X");
        }
        else if ((message.contains("(2,1)")) && (!text4.contains("X")) && (!text4.contains("O")) && (message.contains("O")))
        {
            taReceived.appendText(message + "\n");
            taBox4.setText("O");
        }
        else if((message.contains("(2,2)")) && (!text5.contains("X")) && (!text5.contains("O")) && (message.contains("X")))
        {
            taReceived.appendText(message + "\n");
            taBox5.setText("X");
        }
        else if ((message.contains("(2,2)")) && (!text5.contains("X")) && (!text5.contains("O")) && (message.contains("O")))
        {
            taReceived.appendText(message + "\n");
            taBox5.setText("O");
        }
        else if((message.contains("(2,3)")) && (!text6.contains("X")) && (!text6.contains("O")) && (message.contains("X")))
        {
            taReceived.appendText(message + "\n");
            taBox6.setText("X");
        }
        else if ((message.contains("(2,3)")) && (!text6.contains("X")) && (!text6.contains("O")) && (message.contains("O")))
        {
            taReceived.appendText(message + "\n");
            taBox6.setText("O");
        }
        else if((message.contains("(3,1)")) && (!text7.contains("X")) && (!text7.contains("O")) && (message.contains("X")))
        {
            taReceived.appendText(message + "\n");
            taBox7.setText("X");
        }
        else if ((message.contains("(3,1)")) && (!text7.contains("X")) && (!text7.contains("O")) && (message.contains("O")))
        {
            taReceived.appendText(message + "\n");
            taBox7.setText("O");
        }
        else if((message.contains("(3,2)")) && (!text8.contains("X")) && (!text8.contains("O")) && (message.contains("X")))
        {
            taReceived.appendText(message + "\n");
            taBox8.setText("X");
        }
        else if ((message.contains("(3,2)")) && (!text8.contains("X")) && (!text8.contains("O")) && (message.contains("O")))
        {
            taReceived.appendText(message + "\n");
            taBox8.setText("O");
        }
        else if((message.contains("(3,3)")) && (!text9.contains("X")) && (!text9.contains("O")) && (message.contains("X")))
        {
            taReceived.appendText(message + "\n");
            taBox9.setText("X");
        }
        else if ((message.contains("(3,3)")) && (!text9.contains("X")) && (!text9.contains("O")) && (message.contains("O")))
        {
            taReceived.appendText(message + "\n");
            taBox9.setText("O");
        }
        else if((message.contains("You are player"))){
            taReceived.appendText(message + "\n");
        }
        else if(message.contains("has joined the game")){
            taReceived.appendText(message + "\n");
        }
        else if(message.contains("ait")){
            taReceived.appendText(message + "\n");
        }
        
        checkForWinner();
        
    }
    
    private boolean checkForWinner() {
        String text1 = taBox1.getText();
        String text2 = taBox2.getText();
        String text3 = taBox3.getText();
        String text4 = taBox4.getText();
        String text5 = taBox5.getText();
        String text6 = taBox6.getText();
        String text7 = taBox7.getText();
        String text8 = taBox8.getText();
        String text9 = taBox9.getText();

        if ((text1.contains("X")) && (text2.contains("X")) && (text3.contains("X"))) {
            taLabel.appendText("X wins");
            taLabel.setText("X Wins!");
            return true;
        }
        else if((text1.contains("O")) && (text2.contains("O")) && (text3.contains("O"))) {
            taLabel.appendText("O wins");
            taLabel.setText("O Wins!");
            return true;
        }
        else if((text4.contains("X")) && (text5.contains("X")) && (text6.contains("X"))) {
            taLabel.appendText("X wins");
            taLabel.setText("X Wins!");
            return true;
        }
        else if((text4.contains("O")) && (text5.contains("O")) && (text6.contains("O"))) {
            taLabel.appendText("O wins");
            taLabel.setText("O Wins!");
            return true;
        }
        else if((text7.contains("X")) && (text8.contains("X")) && (text9.contains("X"))) {
            taLabel.appendText("X wins");
            taLabel.setText("X Wins!");
            return true;
        }
        else if((text7.contains("O")) && (text8.contains("O")) && (text9.contains("O"))) {
            taLabel.appendText("O wins");
            taLabel.setText("O Wins!");
            return true;
        }
        else if((text1.contains("X")) && (text4.contains("X")) && (text7.contains("X"))) {
            taLabel.appendText("X wins");
            taLabel.setText("X Wins!");
        }
        else if((text1.contains("O")) && (text4.contains("O")) && (text7.contains("O"))) {
            taLabel.appendText("O wins");
            taLabel.setText("O Wins!");
            return true;
        }
        else if((text2.contains("X")) && (text5.contains("X")) && (text8.contains("X"))) {
            taLabel.appendText("X wins");
            taLabel.setText("X Wins!");
            return true;
        }
        else if((text2.contains("O")) && (text5.contains("O")) && (text8.contains("O"))) {
            taLabel.appendText("O wins");
            taLabel.setText("O Wins!");
            return true;
        }
        else if((text3.contains("X")) && (text6.contains("X")) && (text9.contains("X"))) {
            taLabel.appendText("X wins");
            taLabel.setText("X Wins!");
            return true;
        }
        else if((text3.contains("O")) && (text6.contains("O")) && (text9.contains("O"))) {
            taLabel.appendText("O wins");
            taLabel.setText("O Wins!");
            return true;
        }
        else if((text1.contains("X")) && (text5.contains("X")) && (text9.contains("X"))) {
            taLabel.appendText("X wins");
            taLabel.setText("X Wins!");
            return true;
        }
        else if((text1.contains("O")) && (text5.contains("O")) && (text9.contains("O"))) {
            taLabel.appendText("O wins");
            taLabel.setText("O Wins!");
            return true;
        }
        else if((text3.contains("X")) && (text5.contains("X")) && (text7.contains("X"))) {
            taLabel.appendText("X wins");
            taLabel.setText("X Wins!");
            return true;
        }
        else if((text3.contains("O")) && (text5.contains("O")) && (text7.contains("O"))) {
            taLabel.appendText("O wins");
            taLabel.setText("O Wins!");
            return true;
        }
        else if(((text1.contains("X")) || text1.contains("O")) && ((text2.contains("X")) || text2.contains("O"))
                && ((text3.contains("X")) || text3.contains("O")) && ((text4.contains("X")) || text4.contains("O"))
                && ((text5.contains("X")) || text5.contains("O")) && ((text6.contains("X")) || text6.contains("O"))
                && ((text7.contains("X")) || text7.contains("O")) && ((text8.contains("X")) || text8.contains("O"))
                && ((text9.contains("X")) || text9.contains("O")))
        {
            taLabel.appendText("Cat's Game!");
            taLabel.setText("Cat's Game!");
            return true;
        }
        
        return false;
    }
    
    
    
    
}
