/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp2datetime;

/**
 *
 * @author Donia CHAOUCH
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Serveur {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(3060);
            System.out.println("Serveur en attente de connexion...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connecté : " + clientSocket);

                // Création des flux de lecture et écriture
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);

                // Lecture de la requête du client
                String request = in.readLine();

                // Traitement de la requête
                String response;
                if ("heure".equals(request)) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
                    response = dateFormat.format(new Date());
                } else if ("date".equals(request)) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    response = dateFormat.format(new Date());
                } else {
                    response = "Requête invalide";
                }

                // Envoi de la réponse au client
                out.println(response);

                // Fermeture des flux et de la connexion
                in.close();
                out.close();
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

