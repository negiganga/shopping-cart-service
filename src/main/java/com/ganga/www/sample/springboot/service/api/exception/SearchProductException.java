package com.ganga.www.sample.springboot.service.api.exception;

import com.ganga.www.sample.springboot.service.common.monitoring.Event;

public class SearchProductException extends RuntimeException {
  static final long serialVersionUID = 1L;
  private Event event;

  public SearchProductException(Event event) {
    super();
    this.event = event;
  }

  public SearchProductException(Event event, String errorMessage) {
    super(errorMessage);
    this.event = event;
  }

  public SearchProductException(Event event, String errorMessage, Throwable ex) {
    super(errorMessage, ex);
    this.event = event;
  }

  public SearchProductException(Event event, Throwable ex) {
    super(ex);
    this.event = event;
  }

  public Event getEvent() {
    return event;
  }
}
