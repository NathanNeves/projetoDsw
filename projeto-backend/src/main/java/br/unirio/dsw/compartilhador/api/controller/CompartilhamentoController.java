/*package br.unirio.dsw.compartilhador.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.unirio.dsw.compartilhador.api.model.Usuario;
import br.unirio.dsw.compartilhador.api.repository.CompartilhamentoRepository;
import br.unirio.dsw.compartilhador.api.repository.ItemCompartilhadoRepository;
import br.unirio.dsw.compartilhador.api.repository.UsuarioRepository;
import br.unirio.dsw.compartilhador.api.service.security.JwtUser;
import br.unirio.dsw.compartilhador.api.utils.spring.ControllerResponse;
import br.unirio.dsw.compartilhador.api.utils.spring.ResponseData;
import lombok.Data;

@RestController
@RequestMapping("/api/compartilhamento")
public class CompartilhamentoController {
    private static final Logger log = LoggerFactory.getLogger(ItemCompartilhadoController.class);

	@Autowired
	private UsuarioRepository usuarioRepositorio;

	@Autowired
    private CompartilhamentoRepository compartilhamentoRepository;

    @PostMapping("/compartilhamento/novo")
    public ResponseEntity<ResponseData> novo(@RequestBody NovoCompartilhamentoForm){
        Usuario usuario = JwtUser.isAuthenticated(usuarioRepositorio);
		if(usuario == null)
            return ControllerResponse.fail("nome", "Não há um usuário logado no sistema.");
        
    }
    
}

@Data class NovoCompartilhamentoForm{
    private String 
}*/