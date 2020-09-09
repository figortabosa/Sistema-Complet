package com.algaworks.cobranca.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.algaworks.cobranca.model.Telefone;
import com.algaworks.cobranca.model.Usuario;
import com.algaworks.cobranca.repository.TelefoneRepository;
import com.algaworks.cobranca.repository.UsuarioRepository;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private TelefoneRepository telefoneRepository;
	
	@RequestMapping(method = RequestMethod.GET, value = "/cadastrousuario")
	public ModelAndView novousuario() {
		ModelAndView modelAndView = new ModelAndView("CadastroUsuario");
		modelAndView.addObject("usuarioEmEdicao", new Usuario());
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/salvarusuarios")
	public ModelAndView salvar(Usuario usuario, Telefone telefone) {
		usuarioRepository.save(usuario);
		telefoneRepository.save(telefone);
		
		ModelAndView modelAndView = new ModelAndView("CadastroUsuario");
		modelAndView.addObject("mensagem", "Usuário salvo com sucesso!");
		
		modelAndView.addObject("usuarioEmEdicao", new Usuario());
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/listausuarios")
	public ModelAndView usuarios() {
		ModelAndView andView = new ModelAndView("/UsuariosCadastrados");
		Iterable<Usuario> UsuarioIt = usuarioRepository.findAll();
		andView.addObject("usuarios", UsuarioIt);
		andView.addObject("usuarioEmEdicao", new Usuario());
		return andView;
	}
	
	
	@GetMapping("/editarusuario/{idusuario}") //É a mesma coisa de fazer com o @RequesteMapping
	public ModelAndView editar(@PathVariable("idusuario") Long idpessoa) {
	
		Optional<Usuario> usuario = usuarioRepository.findById(idpessoa);//Traz os atributos da pessoa que vem do ID
		
		ModelAndView modelAndView = new ModelAndView("CadastroUsuario");
		modelAndView.addObject("usuarioEmEdicao", usuario.get());
		return modelAndView;
	}
	
	@GetMapping("/removerusuario/{idusuario}")
	public ModelAndView excluir(@PathVariable("idusuario") Long idusuario) {
		
		usuarioRepository.deleteById(idusuario);
		
		ModelAndView modelAndView = new ModelAndView("/CadastroUsuario");
		modelAndView.addObject("usuarios", usuarioRepository.findAll());
		modelAndView.addObject("usuarioEmEdicao", new Usuario());
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/pesquisarusuario")
	public ModelAndView pesquisar(@RequestParam("usuariopesquisa") String usuariopesquisa) {
		ModelAndView modelAndView = new ModelAndView("UsuariosCadastrados");
		modelAndView.addObject("usuarios", usuarioRepository.findUsuarioByName(usuariopesquisa));
		modelAndView.addObject("usuarioEmEdicao", new Usuario());
		return modelAndView;
	}

}
