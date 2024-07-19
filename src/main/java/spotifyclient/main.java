package spotifyclient;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  // Criar um Scanner para ler a entrada do console

        System.out.println("Informe o ID da Playlist:");
        String playlistId = scanner.nextLine();  // Lê a linha de entrada como ID da playlist

        SpotifyClient spotifyClient = new SpotifyClient();
        spotifyClient.fetchAndPrintPlaylistDetails(playlistId);  // Chama o método com o ID fornecido

        scanner.close();  // Fecha o scanner após o uso
    }
}
