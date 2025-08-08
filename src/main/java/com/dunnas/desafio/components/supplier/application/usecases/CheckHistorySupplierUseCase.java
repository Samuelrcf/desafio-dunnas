package com.dunnas.desafio.components.supplier.application.usecases;

import com.dunnas.desafio.components.supplier.application.usecases.outputs.CheckHistoryUseCaseOutput;
import com.dunnas.desafio.shared.response.PaginationResult;

public interface CheckHistorySupplierUseCase {
	PaginationResult<CheckHistoryUseCaseOutput> execute(int page, int size) throws Exception;
}
