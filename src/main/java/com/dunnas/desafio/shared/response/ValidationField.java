package com.dunnas.desafio.shared.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidationField {
  private String name;
  private String message;
}
