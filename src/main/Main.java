package main;

import model.Video;
import repository.FileVideoRepository;
//import service.VideoService;
import service.VideoServiceImpl;
import strategy.SearchStrategy;
import strategy.TitleSearchStrategy;

//import java.text.SimpleDateFormat;
//import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VideoServiceImpl videoService = new VideoServiceImpl(new FileVideoRepository("videos.txt"));
        SearchStrategy searchStrategy = new TitleSearchStrategy();

        while (true) {
            System.out.println("\n=== Sistema de Gerenciamento de Vídeos ===");
            System.out.println("1. Adicionar vídeo");
            System.out.println("2. Listar vídeos");
            System.out.println("3. Pesquisar vídeo por título");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            if (opcao == 1) {
                videoService.saveVideo();
            } else if (opcao == 2) {
                videoService.listingVideos();
            } else if (opcao == 3) {
                System.out.print("Digite o título para busca: ");
                String query = scanner.nextLine();
                List<Video> resultados = searchStrategy.search(videoService.listVideos(), query);
                for (Video video : resultados) {
                    System.out.println(video);
                }
            } else if (opcao == 4) {
                System.out.println("Saindo do sistema...");
                break;
            } else {
                System.out.println("Opção inválida.");
            }
        }

        scanner.close();
    }
}