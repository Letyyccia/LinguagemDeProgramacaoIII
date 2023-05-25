import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class TelaDePesquisaDeLivro {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o critério de pesquisa:");
        String criterio = scanner.nextLine();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplicativo");
        EntityManager em = emf.createEntityManager();

        try {
            List<Livro> livros = em.createQuery("SELECT l FROM Livro l WHERE l.nome LIKE :nome OR l.editora LIKE :editora", Livro.class)
                    .setParameter("nome", "%" + criterio + "%")
                    .setParameter("editora", "%" + criterio + "%")
                    .getResultList();

            if (!livros.isEmpty()) {
                System.out.println("Livros encontrados:");

                for (Livro l : livros) {
                    System.out.println("ID: " + l.getId() + " | Nome: " + l.getNome() + " | Editora: " + l.getEditora());
                }
            } else {
                System.out.println("Não foram encontrados livros com o critério de pesquisa fornecido.");
            }

        } catch (Exception e) {
            System.out.println("Erro ao tentar pesquisar os livros.");
        } finally {
            em.close();
            emf.close();
        }


    }
}
