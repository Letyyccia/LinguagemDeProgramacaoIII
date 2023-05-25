import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class TelaDeExclusaoDeUsuario {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplicativo");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Digite o ID do usuário que deseja excluir:");
            Integer id = (int) scanner.nextInt();

            Usuario usuario = em.find(Usuario.class, id);

            if (usuario != null) {

                em.getTransaction().begin();
                em.remove(usuario);
                em.getTransaction().commit();

                System.out.println("Usuário excluído com sucesso!");

            } else {
                System.out.println("Usuário não encontrado.");
            }

        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Erro ao tentar excluir o usuário.");
        } finally {
            em.close();
            emf.close();
        }
    }
}