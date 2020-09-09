package com.algaworks.cobranca.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.jsf.FacesContextUtils;
import org.springframework.web.servlet.ModelAndView;

import com.algaworks.cobranca.model.StatusTitulo;
import com.algaworks.cobranca.model.Titulo;
import com.algaworks.cobranca.model.Usuario;
import com.algaworks.cobranca.repository.Titulos;

@Controller
public class TituloController {
	
	@Autowired
	private Titulos titulos;

	@RequestMapping(method = RequestMethod.GET, value = "/salvartitulo")
	public ModelAndView novo() {
		ModelAndView modeView = new ModelAndView("CadastroTitulo");
		return modeView;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/titulos")
	public ModelAndView salvar(Titulo titulo, Usuario usuario) {
		
		titulos.save(titulo);
		
		ModelAndView modelView = new ModelAndView("CadastroTitulo");
		modelView.addObject("mensagem", "Titulo salvo com sucesso!");
		return modelView;
	}
	@ModelAttribute("todosStatusTitulo")
	public List<StatusTitulo> todosStatusTitulo() {
		return Arrays.asList(StatusTitulo.values());
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/listarTitulos")
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView("CadastroTitulo");
		Iterable<Titulo> tituloIt = titulos.findAll();
		modelAndView.addObject("titulos", tituloIt);
		return modelAndView;
	}
}
