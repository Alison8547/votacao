package com.br.api.votacao.mapper;

import com.br.api.votacao.dto.request.ResultRequest;
import com.br.api.votacao.dto.response.ResultResponse;
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

}
