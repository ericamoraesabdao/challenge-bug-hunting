package model;

import Validations.Validations;
import repository.FileVideoRepository;
import repository.VideoRepository;
import service.VideoService;
import service.VideoServiceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class FileHandler extends VideoServiceImpl {
    private final VideoService videoService;

    public FileHandler(VideoRepository repository) {
        super(repository);
        this.videoService = new VideoServiceImpl(new FileVideoRepository("videos.txt"));
    }

    public void saveVideo() {
        try {
            Scanner scanner = new Scanner(System.in);
            String titulo = Validations.readNonEmptyString(scanner, "Digite o título do vídeo: ");
            String descricao = Validations.readNonEmptyString(scanner, "Digite a descrição do vídeo: ");
            int duracao = Validations.readPositiveInt(scanner, "Digite a duração do vídeo (em minutos): ");
            String categoria = Validations.selectCategory(scanner);
            Date dataPublicacao = Validations.readValidDate(scanner, "Digite a data de publicação (dd/MM/yyyy): ");

            // Criação do vídeo e adição ao serviço
            Video video = new Video(titulo, descricao, duracao, categoria, dataPublicacao);
            videoService.addVideo(video);
            System.out.println("Vídeo adicionado com sucesso!");
        } catch (Exception e) {
            System.out.println("Não foi possível adicionar o vídeo. Erro: " + e.getMessage());
        }
    }

    public void listingVideos(){
        List<Video> videos = videoService.listVideos();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (Video video : videos) {
            System.out.println("Título: " + video.getTitulo() + ", Descrição: " + video.getDescricao() + ", Duração: " + video.getDuracao() + ", Categoria: " + video.getCategoria() + ", Data de publicação: " + sdf.format(video.getDataPublicacao()));
        }
    }

    public void editVideo(){
        System.out.println("produzindo");
    }

    public void deleteVideo(){
        System.out.println("produzindo");
    }
}
