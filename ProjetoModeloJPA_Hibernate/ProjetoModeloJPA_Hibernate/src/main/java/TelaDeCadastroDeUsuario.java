import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class TelaDeCadastroDeUsuario {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o nome do usuário:");
        String nome = scanner.next();

        System.out.println("Digite a senha do usuário:");
        String senha = scanner.next();

        Usuario usuario = new Usuario(null, nome, senha);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplicativo");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();

            System.out.println("Usuário cadastrado com sucesso!");

        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Erro ao tentar cadastrar o usuário.");
        }
        finally {
            em.close();
            emf.close();
        }
    }
}