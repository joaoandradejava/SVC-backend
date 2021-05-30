package com.totalmed.svcbackend.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.totalmed.svcbackend.api.model.FeedbackDetalheModel;
import com.totalmed.svcbackend.domain.model.Feedback;

@Component
public class FeedbackDetalheModelAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public FeedbackDetalheModel toModel(Feedback feedback) {
		return modelMapper.map(feedback, FeedbackDetalheModel.class);
	}

	public List<FeedbackDetalheModel> toCollectionModel(List<Feedback> lista) {
		return lista.stream().map(feedback -> toModel(feedback)).collect(Collectors.toList());
	}
}
