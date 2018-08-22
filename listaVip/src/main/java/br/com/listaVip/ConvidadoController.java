package br.com.listaVip;

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
	public String salvar(@RequestParam("nome") String nome, @RequestParam("email") String email, 
			@RequestParam("telefone") String telefone, Model model){
		Convidado novoConvidado = new Convidado(nome, email, telefone);
		this.repository.save(novoConvidado);
		//EmailService emailService = new EmailService();
		//emailService.enviar(nome, email);
		Iterable<Convidado> listaConvidado = repository.findAll();
		model.addAttribute("convidados", listaConvidado);
		return "listaconvidados";
	}
	
	
	
}
