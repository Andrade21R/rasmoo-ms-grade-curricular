package com.rasmoo.cliente.escola.gradecurricular.service;

import java.util.List;

import com.rasmoo.cliente.escola.gradecurricular.dto.MateriaDto;

public interface IMateriaService {
	
	public List<MateriaDto> listar();
	
	public MateriaDto consultar(final Long id);
	
	public Boolean cadastrar(MateriaDto materia);
	
	public Boolean atualizar(final MateriaDto materia);
	
	public Boolean excluir(final Long id);
}
