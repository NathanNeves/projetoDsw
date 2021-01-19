package br.unirio.dsw.compartilhador.api.utils;

import br.unirio.dsw.compartilhador.api.model.ItemCompartilhado;
import br.unirio.dsw.compartilhador.api.model.Usuario;
import br.unirio.dsw.compartilhador.api.repository.ItemCompartilhadoRepository;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.slf4j.Logger;

import java.util.Optional;

public class ValidateItemCompartilhadoService {

    public static Optional<ItemCompartilhado> checkSharedItem(ItemCompartilhadoRepository itemRepositorio,long id,Usuario usuario){
        Optional<ItemCompartilhado> item = itemRepositorio.findById(id);
		
		if (!item.isPresent()){
            System.out.println("Entrei1");
            return null;
        }
		
        if(item.get().getUsuario().getId() != usuario.getId()){
            System.out.println("Entrei2");
            return null;
        }

		if (item.get().isRemovido()){
            System.out.println("Entrei3");
            return null;
        }
            
        return item;
    }   
}
