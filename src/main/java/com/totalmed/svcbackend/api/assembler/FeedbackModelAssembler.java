package com.totalmed.svcbackend.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.totalmed.svcbackend.api.model.FeedbackModel;
import com.totalmed.svcbackend.domain.model.Feedback;

@Component
public class FeedbackModelAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public FeedbackModel toModel(Feedback feedback) {
		return modelMapper.map(feedback, FeedbackModel.class);
	}

	public List<FeedbackModel> toCollectionModel(List<Feedback> lista) {
		return lista.stream().map(feedback -> toModel(feedback)).collect(Collectors.toList());
	}
}
