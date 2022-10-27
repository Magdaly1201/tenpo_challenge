package com.magdy.challenge.tenpo.infrastructure.delivery;

import com.magdy.challenge.tenpo.adapter.delivery.SumEndpoints;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tenpo/sum")
public class SumRestController {

    private final SumEndpoints sumEndpoints;

    public SumRestController(SumEndpoints sumEndpoints) {
        this.sumEndpoints = sumEndpoints;
    }

    @GetMapping
    public float sum(int value1 , int value2){
        return sumEndpoints.sum(value1,value2);
    }
}
