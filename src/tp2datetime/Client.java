/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class Client extends Frame implements ActionListener {
    private TextField tfHeure, tfDate;

    public Client() {
        super("Calendrier");

        // Création des composants
        Button btnHeure = new Button("Heure");
         Button btnDate = new Button("Date");

        tfHeure = new TextField(25);
        tfDate = new TextField(25);
        
       
        // Ajout des composants à la fenêtre
        setLayout(new FlowLayout());
        add(btnHeure);
        add(tfHeure);
        
         add(btnDate);
        add(tfDate);
       

        // Ajout des gestionnaires d'événements pour les boutons
        btnHeure.addActionListener(this);
        btnDate.addActionListener(this);

        // Configuration de la fenêtre
        setSize(300, 150);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Client();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // Création de la socket cliente
            Socket socket = new Socket("localhost", 3060);

            // Création des flux de lecture et écriture
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

            // Envoi de la requête au serveur
            if (e.getActionCommand().equals("Heure")) {
                out.println("heure");
                // Lecture de la réponse du serveur
                tfHeure.setText(in.readLine());
            } else if (e.getActionCommand().equals("Date")) {
                out.println("date");
                // Lecture de la réponse du serveur
                tfDate.setText(in.readLine());
            }

            // Fermeture des flux et de la connexion
            in.close();
            out.close();
            socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}


