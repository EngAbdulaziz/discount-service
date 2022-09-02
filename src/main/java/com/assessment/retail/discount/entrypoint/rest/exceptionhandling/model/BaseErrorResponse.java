package com.assessment.retail.discount.entrypoint.rest.exceptionhandling.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BaseErrorResponse {

  private String message;

  private String description;
}
