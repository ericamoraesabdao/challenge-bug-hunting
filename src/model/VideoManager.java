package model;

import repository.FileVideoRepository;
import repository.VideoRepository;
import service.VideoServiceImpl;
import strategy.SearchStrategy;
import strategy.TitleSearchStrategy;

import java.util.List;
import java.util.Scanner;

public class VideoManager extends VideoServiceImpl {

    public VideoManager(VideoRepository repository) {
        super(repository);
    }

    public void buscaPorTitulo(){
        SearchStrategy searchStrategy = new TitleSearchStrategy();
        VideoManager videoService = new VideoManager(new FileVideoRepository("videos.txt"));

        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o título para busca: ");
        String query = scanner.nextLine();

        List<Video> resultados = searchStrategy.search(videoService.listVideos(), query);
        for (Video video : resultados) {
            System.out.println(video);
        }
    }
}
