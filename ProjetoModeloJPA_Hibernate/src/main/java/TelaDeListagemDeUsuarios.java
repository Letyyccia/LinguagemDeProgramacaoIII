import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class TelaDeListagemDeUsuarios {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplicativo");
        EntityManager em = emf.createEntityManager();

        try {
            List<Usuario> usuarios = em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();

            if (!usuarios.isEmpty()) {
                System.out.println("Usuários cadastrados:");

                for (Usuario usuario : usuarios) {
                    System.out.println("ID: " + usuario.getId() + " | Nome: " + usuario.getNome() + " | Senha: " + usuario.getSenha());
                }

            } else {
                System.out.println("Não há usuários cadastrados.");
            }

        } catch (Exception e) {
            System.out.println("Erro ao tentar listar os usuários." + e.getMessage());
        } finally {
            em.close();
            emf.close();
        }
    }
}