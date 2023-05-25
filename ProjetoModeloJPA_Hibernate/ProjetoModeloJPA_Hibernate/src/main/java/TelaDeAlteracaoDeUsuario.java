import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class TelaDeAlteracaoDeUsuario {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplicativo");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Digite o ID do usuário que deseja alterar:");
            Integer id = (int) scanner.nextInt();

            Usuario usuario = em.find(Usuario.class, id);

            if (usuario != null) {

                System.out.println("Digite o novo nome do usuário:");
                String nome = scanner.next();
                usuario.setNome(nome);

                System.out.println("Digite a nova senha do usuário:");
                String senha = scanner.next();
                usuario.setSenha(senha);

                em.getTransaction().begin();
                em.merge(usuario);
                em.getTransaction().commit();

                System.out.println("Usuário alterado com sucesso!");

            } else {
                System.out.println("Usuário não encontrado.");
            }

        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Erro ao tentar alterar o usuário.");
        } finally {
            em.close();
            emf.close();
        }
    }
}
