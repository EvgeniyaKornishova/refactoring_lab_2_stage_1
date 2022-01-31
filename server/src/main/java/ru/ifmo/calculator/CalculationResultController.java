package ru.ifmo.calculator;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/results")
public class CalculationResultController {
    private final List<Float> results;

    CalculationResultController(){
        results = new ArrayList<>();
    }

    @GetMapping("/{result_id}")
    public ResponseEntity<Float> getResult(
            @PathVariable("result_id") int resultId
    ){
        if (resultId < 1 || resultId > results.size())
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(results.get(resultId-1), HttpStatus.OK);
    }

    static class ResultInfo{
        public Float result;
    }

    @PostMapping("")
    public ResponseEntity<Integer> appendResult(@RequestBody ResultInfo resultInfo){
        results.add(resultInfo.result);

        return new ResponseEntity<>(results.size(), HttpStatus.CREATED);
    }
}
