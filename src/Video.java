import java.text.SimpleDateFormat;
import java.util.Date;

public class Video {
    private String titulo;
    private String descricao;
    private int duracao; // em minutos
    private String categoria;
    private Date dataPublicacao;

    public Video(String titulo, String descricao, int duracao, String categoria, Date dataPublicacao) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.duracao = duracao;
        this.categoria = categoria;
        this.dataPublicacao = dataPublicacao;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return "Título: " + titulo + ";Descrição: " + descricao + ";Duração: " + duracao + ";Categoria: " + categoria + ";Data: " + sdf.format(dataPublicacao);
    }

    public static Video fromString(String linha) {
        try {
            String[] partes = linha.split(";");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            return new Video(partes[0], partes[1], Integer.parseInt(partes[2]), partes[3], sdf.parse(partes[4]));
        } catch (Exception e) {
            return null; // Ignora erros de parsing
        }
    }
}