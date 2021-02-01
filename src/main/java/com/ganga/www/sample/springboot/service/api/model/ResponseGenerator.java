package com.ganga.www.sample.springboot.service.api.model;

import com.ganga.www.sample.springboot.service.common.monitoring.Event;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Generates response object.
 */
public class ResponseGenerator {

  /**
   * Generates error response object.
   *
   * @param message    : Error message
   * @param event    : System event
   * @param httpStatus : HTTP Status code
   * @return ResponseEntity object
   */
  public ResponseEntity error(final String message, final Event event,
                              final HttpStatus httpStatus) {
    final BaseResponse baseResponse = new BaseResponse();
    baseResponse.setStatusType(BaseResponse.StatusType.FAILED);
    baseResponse.setStatusName(event.getName());
    baseResponse.setStatusId(event.getId());
    baseResponse.setStatusDescription(message);
    return new ResponseEntity(baseResponse, httpStatus);
  }

  /**
   * Generates warning response object.
   *
   * @param response   :  response object
   * @param message    : Warning message
   * @param event    : System event
   * @param httpStatus : HTTP Status
   * @return : ResponseEntity object
   */
  public ResponseEntity warn(final BaseResponse response, final Event event,
                             final String message, final HttpStatus httpStatus) {
    response.setStatusType(BaseResponse.StatusType.WARNING);
    response.setStatusDescription(message);
    response.setStatusName(event.getName());
    response.setStatusId(event.getId());
    return new ResponseEntity(response, httpStatus);
  }

  /**
   * Generates warning response object.
   *
   * @param response   :  response object
   * @param httpStatus : HTTP Status
   * @return : ResponseEntity object
   */
  public ResponseEntity warn(final BaseResponse response,
                             final HttpStatus httpStatus) {
    response.setStatusType(BaseResponse.StatusType.WARNING);
    return new ResponseEntity(response, httpStatus);
  }

  /**
   * Generates success response object.
   *
   * @param response   :  response object
   * @param httpStatus : HTTP Status
   * @return : ResponseEntity object
   */
  public ResponseEntity success(final HttpStatus httpStatus,
                                final BaseResponse response) {
    response.setStatusType(BaseResponse.StatusType.SUCCESS);
    return new ResponseEntity(response, httpStatus);
  }
}
