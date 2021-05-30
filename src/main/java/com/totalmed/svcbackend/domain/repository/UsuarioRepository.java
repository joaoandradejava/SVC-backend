package com.totalmed.svcbackend.domain.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.totalmed.svcbackend.domain.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	@Query("select u from Usuario u where lower(u.nome) like lower(concat('%', ?1, '%'))")
	Page<Usuario> buscarTodosPorPaginacaoENome(String nome, Pageable pageable);

	Optional<Usuario> findByCpf(String cpf);

	Optional<Usuario> findByEmail(String email);

}
