package Mozbola.Mocambola.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

@Controller
public interface ClubeController extends JpaRepository<Clube, String>{

	Clube findByNome(String nome);
	Clube findByCodigo(int nome);
}
