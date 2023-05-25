import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class TelaDeListagemDeLivros {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplicativo");
        EntityManager em = emf.createEntityManager();

        try {
            List<Livro> livros = em.createQuery("SELECT l FROM Livro l", Livro.class).getResultList();

            if (!livros.isEmpty()) {
                System.out.println("Livros cadastrados:");

                for (Livro livro : livros) {
                    System.out.println("ID: " + livro.getId() + " | Nome: " + livro.getNome() + " | Editora: " + livro.getEditora() + " | Paginas: " + livro.getPaginas() + " | Ano: " + livro.getAno() + " | Edicao: " + livro.getEdicao());
                }

            } else {
                System.out.println("Não há livros cadastrados.");
            }

        } catch (Exception e) {
            System.out.println("Erro ao tentar listar os livros." + e.getMessage());
        } finally {
            em.close();
            emf.close();
        }



    }
}
