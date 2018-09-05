package br.com.listaVip;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.enviadorEmail.EmailService;
import br.com.listaVip.model.Convidado;
import br.com.listaVip.repository.ConvidadoRepository;


@Controller
public class ConvidadoController {
	
	@Autowired
	private ConvidadoRepository repository;
	
	private EntityManager entityManager;
	
	@RequestMapping("/")
	public String index(){
		return "index";
	}
	
	@RequestMapping("listaconvidados")
	public String listaConvidados(Model model){
		Iterable<Convidado> listaConvidado = repository.findAll();
		model.addAttribute("convidados", listaConvidado);
		return "listaconvidados";
	}
	
	@RequestMapping(value = "salvar", method = RequestMethod.POST)
	public String salvar(Convidado convidado, Model model){
		this.repository.save(convidado);
		EmailService emailService = new EmailService();
		emailService.enviar(convidado.getNome(), convidado.getEmail());
		Iterable<Convidado> listaConvidado = repository.findAll();
		model.addAttribute("convidados", listaConvidado);
		return "listaconvidados";
	}
	
	@RequestMapping(value = "excluir", method = RequestMethod.POST)
	public String excluir(Convidado convidado){
		this.repository.deleteById(convidado.getId());
		return "listaconvidados";
	}
	
	@RequestMapping(value = "atualizar", method = RequestMethod.POST)
	public String atualizar(Convidado convidado){
		this.repository.save(convidado);
		return "listaconvidados";
	}
	
	@RequestMapping(value = "atualizarconvidado", method = RequestMethod.POST)
	public String pageAtualizar(Convidado convidado, Model model){
		model.addAttribute("convidado", convidado);
		return "atualizarconvidado";
	}
	
}
