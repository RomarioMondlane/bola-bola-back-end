package Mozbola.Mocambola.project;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;

@Controller
public interface JogadoresController extends  JpaRepository<Jogadores, String>{

	
	Jogadores findByNome(String nome);
	Jogadores findByCodigo(int i);
List<Jogadores> findByPosicao(String posicao);
List<Jogadores> findByAltura(Float altura);
List<Jogadores> findByDatanatalicia(String ano);
	


}
