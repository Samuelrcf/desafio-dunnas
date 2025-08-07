package com.dunnas.desafio.components.client.application.usecases;

import com.dunnas.desafio.components.client.application.usecases.inputs.AddCreditUseCaseInput;
import com.dunnas.desafio.components.client.application.usecases.outputs.AddCreditUseCaseOutput;

public interface AddCreditUseCase {
	AddCreditUseCaseOutput execute(AddCreditUseCaseInput input) throws Exception;
}
