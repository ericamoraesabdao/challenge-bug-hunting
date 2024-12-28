package model;

import repository.FileVideoRepository;
import repository.VideoRepository;
import service.VideoService;
import service.VideoServiceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class FileHandler extends VideoServiceImpl {
    public FileHandler(VideoRepository repository) {
        super(repository);
    }

    public void saveVideo() {
        Scanner scanner = new Scanner(System.in);
        VideoService videoService = new VideoServiceImpl(new FileVideoRepository("videos.txt"));
        
        String titulo = "";
        while (titulo.isEmpty()){
            System.out.print("Digite o título do vídeo: ");
            titulo = scanner.nextLine();
        }
        String descricao = "";
        while (descricao.isEmpty()){
            System.out.print("Digite a descrição do vídeo: ");
            descricao = scanner.nextLine();
        }
        int duracao = 0;
        while ((duracao <= 0)){
            System.out.print("Digite a duração do vídeo (em minutos): ");
            duracao = scanner.nextInt();
            scanner.nextLine();  // Consumir a quebra de linha
        }
        String categoria;
        int choice = 0;

            for (CategoryType category : CategoryType.values()) {
            System.out.println(category.ordinal() + 1 + " - " + category.getCategory());
            }
        while (1 > choice || choice > CategoryType.values().length) {
            System.out.print("Digite o número da categoria do vídeo: ");
            choice = scanner.nextInt();
        }
            CategoryType selectCategory = CategoryType.values()[choice - 1];
            categoria = String.valueOf(selectCategory);
            scanner.nextLine();

            String dataStr = "";
            while (dataStr.isEmpty()){
                System.out.print("Digite a data de publicação (dd/MM/yyyy): ");
                dataStr = scanner.nextLine();
            }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dataPublicacao = sdf.parse(dataStr);
            Video video = new Video(titulo, descricao, duracao, categoria, dataPublicacao);
            videoService.addVideo(video);
            System.out.println("Vídeo adicionado com sucesso!");
        } catch (Exception e) {
            System.out.println("Não foi possível adicionar o vídeo.");
        }
    }

    public void listingVideos(){
        VideoService videoService = new VideoServiceImpl(new FileVideoRepository("videos.txt"));
        List<Video> videos = videoService.listVideos();

        for (Video video : videos) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            System.out.println("Título: " + video.getTitulo() + ", Descrição: " + video.getDescricao() + ", Duração: " + video.getDuracao() + ", Categoria: " + video.getCategoria() + ", Data de publicação: " + sdf.format(video.getDataPublicacao()));
        }
    }


}
