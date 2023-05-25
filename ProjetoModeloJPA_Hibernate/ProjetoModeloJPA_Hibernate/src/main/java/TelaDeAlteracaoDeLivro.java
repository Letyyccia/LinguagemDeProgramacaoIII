import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class TelaDeAlteracaoDeLivro {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplicativo");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Digite o ID do livro que deseja alterar:");
            Integer id = (int) scanner.nextInt();

            Livro livro = em.find(Livro.class, id);

            if (livro != null) {

                System.out.println("Digite o novo nome do livro:");
                String nome = scanner.next();
                livro.setNome(nome);

                System.out.println("Digite a nova editora do livro:");
                String editora = scanner.next();
                livro.setEditora(editora);

                System.out.println("Digite o novo numero de paginas do livro:");
                Integer paginas = Integer.valueOf(scanner.next());
                livro.setPaginas(paginas);

                System.out.println("Digite o novo ano do livro:");
                Integer ano = Integer.valueOf(scanner.next());
                livro.setAno(ano);

                System.out.println("Digite a nova edicao do livro:");
                Integer edicao = Integer.valueOf(scanner.next());
                livro.setEdicao(edicao);

                em.getTransaction().begin();
                em.merge(livro);
                em.getTransaction().commit();

                System.out.println("Livro alterado com sucesso!");

            } else {
                System.out.println("Livro não encontrado.");
            }

        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Erro ao tentar alterar o livro.");
        } finally {
            em.close();
            emf.close();
        }

    }
}
