package com.example.apigateway.controllers;

import com.example.apigateway.models.MathModel;
import com.example.apigateway.exceptions.UnsuportedMathOperationException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MathController {

    @RequestMapping(value = "{operationType}/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double getOperationValue(@PathVariable(value = "operationType") String operationType,
                                    @PathVariable(value = "numberOne") String numberOne,
                                    @PathVariable(value = "numberTwo") String numberTwo) {

        if (!MathModel.isNumeric(numberOne) || !MathModel.isNumeric(numberTwo)) {
            throw new UnsuportedMathOperationException("Please set a numeric value");
        }

        return MathModel.getResult(MathModel.Operation.valueOf(operationType), MathModel.convertToDouble(numberOne), MathModel.convertToDouble(numberTwo));
    }

    @RequestMapping(value = "sqrt/{numberOne}", method = RequestMethod.GET)
    public Double getOperationValueFromSqrt(@PathVariable(value = "numberOne") String numberOne) {

        if (!MathModel.isNumeric(numberOne)) {
            throw new UnsuportedMathOperationException("Please set a numeric value");
        }

        return MathModel.getResultSqrt(MathModel.convertToDouble(numberOne));
    }

}
