package Mozbola.Mocambola.project;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class OperacoesController {
	@Autowired
	private ControladorUser us;
	@Autowired
	private ClubeController clube;
	@Autowired
	private JogadoresController jogador;
	
	
	
	
	@GetMapping("/")
	@Transactional
	
	public boolean login(@AuthenticationPrincipal UserDetails detalhes) {
	
		return SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
		

	}
	
	//Postagens
	@RequestMapping(value="/criarUser", method=RequestMethod.POST)	
	@Transactional
	//@PreAuthorize("hasAuthority('Admin')")
	public ResponseEntity<String> CriarUser(@Valid @ModelAttribute User e) throws IllegalStateException, IOException {
		BCryptPasswordEncoder pass=new BCryptPasswordEncoder();
		System.out.println(e.toString());
		User u=new User(e.getUsername(),pass.encode(e.getPassword()),e.isadmin);
		
		if(us.findByUsername(u.getUsername())!=null ) {
			
			return new ResponseEntity<>("Usuario exsitente!",HttpStatus.OK);
			
		}else {
			us.save(u);}
			return new ResponseEntity<>("Usuario criado com sucesso!",HttpStatus.OK);
		
	
	}
	
	@RequestMapping(value="/criarClube", method=RequestMethod.POST)	
	@Transactional
	@PreAuthorize("hasAuthority('Admin')")
	public ResponseEntity<String> CriarClube(@Valid @ModelAttribute Clube e,@RequestParam("file") MultipartFile file) throws IllegalStateException, IOException {
		
		System.out.println(e.toString());
		Clube c=new Clube(e.getNome(),e.getLocalizacao(),e.getTreinador(),e.getFundacao(),e.getDono(),compressBytes(file.getBytes()));
				
		if(clube.findByNome(e.getNome())!=null ) {
			
			return new ResponseEntity<>("Clube registado!",HttpStatus.OK);
			
		}else {
			clube.save(c);}
			return new ResponseEntity<>("Clube criado com sucesso!",HttpStatus.OK);
		
	
	}
	
	@RequestMapping(value="/criarJogador", method=RequestMethod.POST)	
	@Transactional
	@PreAuthorize("hasAuthority('Admin')")
	public ResponseEntity<String> CriarJogador(@Valid @ModelAttribute Jogadores e,@RequestParam("file") MultipartFile file) throws IllegalStateException, IOException {
		
		System.out.println(e.toString());
		Jogadores j=new Jogadores(e.getNome(),e.getPosicao(),e.getDatanatalicia(),e.getAltura(),e.getAlcunha(),compressBytes(file.getBytes()));
				
		if(jogador.findByNome(e.getNome())!=null ) {
			
			return new ResponseEntity<>("Jogador  registado!",HttpStatus.OK);
			
		}else {
			jogador.save(j);}
			return new ResponseEntity<>("Jogador criado com sucesso!",HttpStatus.OK);
		
	
	}
	
	//Listagens
	@RequestMapping(value="/listarClubes", method=RequestMethod.GET)

	public List<Clube> listaClubes() {
		List<Clube>  lista=clube.findAll();
		
		for(int i=0;i<lista.size();i++) {
			;
			lista.get(i).setPicByte(decompressBytes(lista.get(i).getPicByte()));
		}
		
	return  lista;
	
	}
	
	
	@RequestMapping(value="/listarJogadores", method=RequestMethod.GET)

	public List<Jogadores> listaJogadores() {
		List<Jogadores>  lista=jogador.findAll();
		
		for(int i=0;i<lista.size();i++) {
			;
			lista.get(i).setPicByte(decompressBytes(lista.get(i).getPicByte()));
		}
		
	return  lista;
	
	}
	
	//Pesquisas
	@RequestMapping(value="/pesquisaclube", method=RequestMethod.GET)

	public Clube PesquisarClube(@RequestParam("nome") String nome ) {
		Clube cl=clube.findByNome(nome);
		if(cl!=null) {
	  		cl.setPicByte(decompressBytes(cl.getPicByte()))		;
	  		return cl;
		}
		
		return null;
	
	}
	
	@RequestMapping(value="/pesquisaclubeid", method=RequestMethod.GET)

	public Clube PesquisarClubeID(@RequestParam("codigo") int codigo ) {
		Clube cl=clube.findByCodigo(codigo);
		
	  		cl.setPicByte(decompressBytes(cl.getPicByte()))		;
	  		return cl;
		
	}

	
	
	@RequestMapping(value="/pesquisarjogador", method=RequestMethod.GET)

	public Jogadores PesquisarJogador(@RequestParam("nome") String nome) {
		Jogadores jj=jogador.findByNome(nome);
			if(jj!=null) {

			  	jj.setPicByte(decompressBytes(jj.getPicByte()))		;

			  	return jj;
			 	
			}
			return null;
	}

	
	@RequestMapping(value="/pesquisarConter", method=RequestMethod.GET)

	public List<Jogadores> PesquisarJogadorConter(@RequestParam("nome") String nome) {
		List<Jogadores> jj=jogador.findAll();
		List<Jogadores> retorno=new ArrayList<Jogadores>();
		for(int i=0;i<jj.size();i++) {
			System.out.print(jj.get(i).getNome().contains(nome));
			if(jj.get(i).getNome().contains(nome)) {
			 jj.get(i).setPicByte(decompressBytes(jj.get(i).getPicByte()))		;
			 retorno.add(i,jj.get(i));
			 
		 }	
			
		}
		System.out.println(retorno.size());
		if(retorno.size()==0) {
			return null;
		}
		return retorno;
			}
	@RequestMapping(value="/pesquisarCriterio", method=RequestMethod.GET)

	public List<Jogadores> PesquisarJogadorCriterio(@RequestParam("criterio") String criterio,@RequestParam("dado") String dado) {
		List<Jogadores> retorno=new ArrayList<Jogadores>();
		
		if(criterio.equalsIgnoreCase("nome")) {
			List<Jogadores> jj=jogador.findAll();
			
			if(dado.length()>=3) {
			for(int i=0;i<jj.size();i++) {
				
				if(jj.get(i).getNome().contains(dado)) {
				 jj.get(i).setPicByte(decompressBytes(jj.get(i).getPicByte()))		;
				 retorno.add(i,jj.get(i));
				 
			 }	
				
			}
			}
		}
		if(criterio.equalsIgnoreCase("posicao")) {
			
			if(dado.length()>=3) {
				List<Jogadores> jj=jogador.findByPosicao(dado);
				
				for(int i=0;i<jj.size();i++) {
				
				 jj.get(i).setPicByte(decompressBytes(jj.get(i).getPicByte()))		;
				 retorno.add(i,jj.get(i));
				 
			
			}
			}
		}
if(criterio.equalsIgnoreCase("altura")) {
			float altura=Float.parseFloat(dado);
			if(dado.length()>=3) {
				List<Jogadores> jj=jogador.findByAltura(altura);
				
				for(int i=0;i<jj.size();i++) {
				
				 jj.get(i).setPicByte(decompressBytes(jj.get(i).getPicByte()))		;
				 retorno.add(i,jj.get(i));
				 
			
			}
			}
		}
if(criterio.equalsIgnoreCase("idade")) {
 int idade=Integer.parseInt(dado);
String anonasc="";
 if(dado.length()==2) {
		 int nasc	=LocalDate.now().getYear(); 
		 int ano=(nasc-idade);
		 anonasc +=ano;
		 List<Jogadores> jj=jogador.findAll();
		for(int i=0;i<jj.size();i++) {
			
			if(jj.get(i).getDatanatalicia().contains(anonasc)) {
			 jj.get(i).setPicByte(decompressBytes(jj.get(i).getPicByte()))		;
			 retorno.add(i,jj.get(i));
			 
		 }	
			
		}
		
	}
}
		
		
		if(retorno.size()==0) {
			return null;
		}
		return retorno;
			}

	//Apagar

	@RequestMapping(value="/apagarclube", method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('Admin')")
	public String ApagarClube(@RequestParam("id") int i ) {
		Clube cl=clube.findByCodigo(i);
			clube.delete(cl);
			  	
		
		return "Clube apgado com sucesso!";
	
	}
	

	@RequestMapping(value="/apagarjogador", method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('Admin')")
	public String ApagarJogador(@RequestParam("id") int i) {
		Jogadores jj=jogador.findByCodigo(i);
		return "Jogador apagdo com sucesso!";	
	}


	///Fotos
	public static byte[] compressBytes(byte[] data) {
		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[40000000];
		while (!deflater.finished()) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		try {
			outputStream.close();
		} catch (IOException e) {
		}
		//System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
		return outputStream.toByteArray();
	}
	// uncompress the image bytes before returning it to the angular application
	public static byte[] decompressBytes(byte[] data) {
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[40000000];
		try {
			while (!inflater.finished()) {
				int count = inflater.inflate(buffer);
				outputStream.write(buffer, 0, count);
			}
			outputStream.close();
		} catch (IOException ioe) {
		} catch (DataFormatException e) {
		}
		return outputStream.toByteArray();
	}


}
