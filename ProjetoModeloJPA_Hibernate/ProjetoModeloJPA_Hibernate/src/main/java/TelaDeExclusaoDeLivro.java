import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class TelaDeExclusaoDeLivro {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplicativo");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Digite o ID do livro que deseja excluir:");
            Integer id = (int) scanner.nextInt();

            Livro livro = em.find(Livro.class, id);

            if (livro != null) {

                em.getTransaction().begin();
                em.remove(livro);
                em.getTransaction().commit();

                System.out.println("Livro excluído com sucesso!");

            } else {
                System.out.println("Livro não encontrado.");
            }

        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Erro ao tentar excluir o livro.");
        } finally {
            em.close();
            emf.close();
        }



    }
}
