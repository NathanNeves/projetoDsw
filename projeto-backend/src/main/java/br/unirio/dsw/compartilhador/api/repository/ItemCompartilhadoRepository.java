package br.unirio.dsw.compartilhador.api.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.unirio.dsw.compartilhador.api.model.ItemCompartilhado;

/**
 * Classe que representa um repositório para acesso a itens compartilhados
 * 
 * @author User
 */
@Transactional(readOnly = true)
public interface ItemCompartilhadoRepository extends JpaRepository<ItemCompartilhado, Long>
{
	ItemCompartilhado findByNome(String nome);

	ItemCompartilhado findByNomeAndUsuarioId(String nome, Long id);
	final String selectQuery = "SELECT ic FROM ItemCompartilhado ic WHERE ";

	@Query(selectQuery+"ic.usuario.id = :ownerId AND ic.removido = 0") 
	List<ItemCompartilhado> findByUsuarioId(@Param("ownerId") Long ownerId);

	@Query(selectQuery+"ic.usuario.id = :ownerId AND ic.removido = 0") 
	Page<ItemCompartilhado> findByUsuarioId(@Param("ownerId") Long ownerId, Pageable pageable);
	
	@Query(selectQuery+"ic.usuario.id = :ownerId AND ic.removido = 0 AND (ic.descricao LIKE '%:text%' OR ic.nome LIKE '%:text%')")
	Page<ItemCompartilhado> findByUserIdAndBySearchTerm(@Param("ownerId") Long ownerId,@Param("text") String term, Pageable pageable);

	@Query(selectQuery+"ic.usuario.id = :ownerId AND ic.removido = 0 AND ic.descricao LIKE '%:text%'")
	Page<ItemCompartilhado> findByUserIdAndByFileDescription(@Param("ownerId") Long ownerId,@Param("text") String term, Pageable pageable);

	@Query(selectQuery+"ic.usuario.id = :ownerId AND ic.removido = 0 AND ic.nome LIKE '%:text%'")
	Page<ItemCompartilhado> findByUserIdAndByFileName(@Param("ownerId") Long ownerId,@Param("text") String term, Pageable pageable);

	@Query(selectQuery+"ic.usuario.id = :ownerId AND ic.removido = 0 AND ic.id = :idItemCompartilhado")
	ItemCompartilhado findItemByUserIdAndByFileId(@Param("ownerId") Long ownerId,@Param("idItemCompartilhado") long idItemCompartilhado);
}