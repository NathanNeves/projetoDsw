package br.unirio.dsw.compartilhador.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle.Control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.unirio.dsw.compartilhador.api.model.Compartilhamento;
import br.unirio.dsw.compartilhador.api.model.ItemCompartilhado;
import br.unirio.dsw.compartilhador.api.model.Usuario;
import br.unirio.dsw.compartilhador.api.repository.CompartilhamentoRepository;
import br.unirio.dsw.compartilhador.api.repository.ItemCompartilhadoRepository;
import br.unirio.dsw.compartilhador.api.repository.UsuarioRepository;
import br.unirio.dsw.compartilhador.api.service.security.JwtUser;
import br.unirio.dsw.compartilhador.api.utils.ValidationUtils;
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

    @Autowired
    private ItemCompartilhadoRepository itemCompartilhado;

    @PostMapping("/novo")
    public ResponseEntity<ResponseData> novo(@RequestBody NovoCompartilhamentoForm form){
        Usuario usuario = JwtUser.isAuthenticated(usuarioRepositorio);
		if(usuario == null)
            return ControllerResponse.fail("nome", "Não há um usuário logado no sistema.");
        
        if(form.getEmail().length() == 0 && ValidationUtils.validEmail(form.getEmail())){
            return ControllerResponse.fail("email","Email Inválido");
        }

        if(form.getDtInicio().isBefore(LocalDate.now())){
            return ControllerResponse.fail("Data Inicio", "Data de Inicio não pode ser menor que ");
        }

        if(form.getIdItem() == 0){
            return ControllerResponse.fail("IdItem", "id do item não funcionando");
        }

        ItemCompartilhado item = itemCompartilhado.findByUserIdAndByItemId(usuario.getId(),form.getIdItem());    
        if(item == null){
            return ControllerResponse.fail("Compartilhamento","Erro ao buscar item compartilhado"); 
        }
        Compartilhamento compartilhamento = new Compartilhamento();
        compartilhamento.setItem(item);
        compartilhamento.setDataInicio(form.getDtInicio());
        compartilhamento.setDataTermino(form.getDtFim());
        compartilhamentoRepository.save(compartilhamento);
        return ControllerResponse.success();
        
    }

    @DeleteMapping(value = "/{id}")
	public ResponseEntity<ResponseData> remove(@PathVariable("id") long id){
        Usuario usuario = JwtUser.isAuthenticated(usuarioRepositorio);
        if(usuario == null)
			return ControllerResponse.fail("nome", "Não há um usuário logado no sistema.");

        Compartilhamento compartilhamento = compartilhamentoRepository.findByUsuarioIdAndId(usuario.getId(), id);
        if(!compartilhamento.isCanceladoDono())
            return ControllerResponse.fail("Cancelado","Item já cancelado pelo dono");
        compartilhamento.setCanceladoDono(true);
        compartilhamentoRepository.save(compartilhamento);
        return ControllerResponse.success();
    }
    
}

@Data class NovoCompartilhamentoForm{
    private String email;
    private long idItem;
    private LocalDate dtInicio;
    private LocalDate dtFim;
    private ItemCompartilhado itemCompartilhado; 


    public String getEmail(){
        return this.email;
    }

    public LocalDate getDtInicio(){
        return this.dtInicio;
    }

    public LocalDate getDtFim(){
        return this.dtFim;
    }

    public  long getIdItem(){
        return this.idItem;
    }

    public  void setIdItem(long idItem){
        this.idItem = idItem;
    }


    public void setEmail(String email){
        this.email = email;
    }


    public void setDtInicio(LocalDate dtInicio){
        this.dtInicio = dtInicio;
    }

    public void setDtFim(LocalDate dtFim){
        this.dtFim = dtFim;
    }

    public void setItemCompartilhado(ItemCompartilhado itemCompartilhado){
        this.itemCompartilhado = itemCompartilhado;
    }

}