package br.unirio.dsw.compartilhador.api.utils;

import br.unirio.dsw.compartilhador.api.model.ItemCompartilhado;
import br.unirio.dsw.compartilhador.api.model.Usuario;
import br.unirio.dsw.compartilhador.api.repository.ItemCompartilhadoRepository;

import java.util.Optional;

public class ValidateItemCompartilhadoService {
    public static Optional<ItemCompartilhado> checkSharedItem(ItemCompartilhadoRepository itemRepositorio,long id,Usuario usuario){
        Optional<ItemCompartilhado> item = itemRepositorio.findById(id);
		
		if (!item.isPresent())
			return null;
		
		if (item.get().getUsuario().getId() != usuario.getId())
			return null;

		if (item.get().isRemovido())
            return null;
            
        return item;
    }   
}
