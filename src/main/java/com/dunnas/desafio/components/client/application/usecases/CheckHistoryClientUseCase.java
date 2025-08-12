package com.dunnas.desafio.components.client.application.usecases;

import com.dunnas.desafio.components.client.application.usecases.outputs.CheckHistoryUseCaseOutput;
import com.dunnas.desafio.shared.response.PaginationResult;

public interface CheckHistoryClientUseCase {
	PaginationResult<CheckHistoryUseCaseOutput> execute(int page, int size) throws Exception;
}
