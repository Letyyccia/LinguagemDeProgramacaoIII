import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class TelaDePesquisaDeUsuario {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o critério de pesquisa:");
        String criterio = scanner.nextLine();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplicativo");
        EntityManager em = emf.createEntityManager();

        try {
            List<Usuario> usuarios = em.createQuery("SELECT u FROM Usuario u WHERE u.nome LIKE :nome OR u.senha LIKE :senha", Usuario.class)
                    .setParameter("nome", "%" + criterio + "%")
                    .setParameter("senha", "%" + criterio + "%")
                    .getResultList();

            if (!usuarios.isEmpty()) {
                System.out.println("Usuários encontrados:");

                for (Usuario u : usuarios) {
                    System.out.println("ID: " + u.getId() + " | Nome: " + u.getNome() + " | Senha: " + u.getSenha());
                }
            } else {
                System.out.println("Não foram encontrados usuários com o critério de pesquisa fornecido.");
            }

        } catch (Exception e) {
            System.out.println("Erro ao tentar pesquisar os usuários.");
        } finally {
            em.close();
            emf.close();
        }
    }
}
