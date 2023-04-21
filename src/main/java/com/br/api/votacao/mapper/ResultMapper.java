package com.br.api.votacao.mapper;

import com.br.api.votacao.dto.response.ResultResponse;
import com.br.api.votacao.dto.request.ResultRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ResultMapper {
    private final ModelMapper modelMapper;

    public ResultRequest toRequestResult(ResultResponse resultResponse) {
        return modelMapper.map(resultResponse, ResultRequest.class);
    }

    public ResultResponse toResponseResult(ResultRequest resultRequest) {
        return modelMapper.map(resultRequest, ResultResponse.class);
    }

}
