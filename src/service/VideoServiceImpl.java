package service;

import model.Video;
import repository.FileVideoRepository;
import repository.VideoRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class VideoServiceImpl implements VideoService {
    private final VideoRepository repository;

    public VideoServiceImpl(VideoRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addVideo(Video video) {
        repository.save(video);
    }

    @Override
    public List<Video> listVideos() {
        return repository.findAll();
    }

    public void saveVideo() {
        Scanner scanner = new Scanner(System.in);
        VideoService videoService = new VideoServiceImpl(new FileVideoRepository("videos.txt"));

        System.out.print("Digite o título do vídeo: ");
        String titulo = scanner.nextLine();
        System.out.print("Digite a descrição do vídeo: ");
        String descricao = scanner.nextLine();
        System.out.print("Digite a duração do vídeo (em minutos): ");
        int duracao = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha
        System.out.print("Digite a categoria do vídeo: ");
        String categoria = scanner.nextLine();
        System.out.print("Digite a data de publicação (dd/MM/yyyy): ");
        String dataStr = scanner.nextLine();

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