package main;

import model.FileHandler;
import model.VideoManager;
import repository.FileVideoRepository;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FileHandler videoService = new FileHandler(new FileVideoRepository("videos.txt"));
        VideoManager videoManager = new VideoManager(new FileVideoRepository("videos.txt"));

        while (true) {
            System.out.println("\n=== Sistema de Gerenciamento de Vídeos ===");
            System.out.println("1. Adicionar vídeo");
            System.out.println("2. Listar vídeos");
            System.out.println("3. Pesquisar vídeo por título");
            System.out.println("4. Editar vídeo.");
            System.out.println("5. Excluir vídeo.");
            System.out.println("6. Filtrar vídeos por categoria.");
            System.out.println("7. Ordenar vídeos por data de publicação.");
            System.out.println("8. Exibir relatório de estatísticas.");
            System.out.println("9. Sair.");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 1) {
                videoService.saveVideo();
            } else if (opcao == 2) {
                videoService.listingVideos();
            } else if (opcao == 3) {
                videoManager.buscaPorTitulo();
            } else if (opcao == 4) {
                videoService.editVideo();
            } else if (opcao == 5) {
                videoService.deleteVideo();
            } else if (opcao == 6) {
                System.out.println("Produzindo");
            } else if (opcao == 7) {
                System.out.println("Produzindo");
            } else if (opcao == 8) {
                System.out.println("Produzindo");
            } else if (opcao == 9) {
                System.out.println("Saindo do sistema...");
                break;
            } else {
                System.out.println("Opção inválida. Digite uma opção do menu!");
            }
        }
        scanner.close();
    }
}