package com.magdy.challenge.tenpo.infrastructure.delivery;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.magdy.challenge.tenpo.adapter.delivery.SumEndpoints;
import com.magdy.challenge.tenpo.infrastructure.delivery.dto.HistoryDTO;
import com.magdy.challenge.tenpo.infrastructure.delivery.dto.SumResponseDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tenpo/sum")
public class SumRestController {

    private final SumEndpoints sumEndpoints;
    private final ModelMapper modelMapper;

    public SumRestController(SumEndpoints sumEndpoints, ModelMapper modelMapper) {
        this.sumEndpoints = sumEndpoints;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    public SumResponseDTO sum(@RequestParam(value = "value1", required = true) int value1, @RequestParam(value = "value2", required = true) int value2) throws JsonProcessingException {
        return modelMapper.map(sumEndpoints.operationSumValuesAndPercentage(value1, value2), SumResponseDTO.class);
    }

    @GetMapping("/history")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    public Page<HistoryDTO> getAllByPage(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                         @RequestParam(value = "size", defaultValue = "10", required = false) int size) {
        return sumEndpoints.getAllByPage(page, size).map(history -> modelMapper.map(history, HistoryDTO.class));

    }
}
