package com.ganga.www.sample.springboot.service.dataaccess.exception;

import com.ganga.www.sample.springboot.service.common.monitoring.Event;

public class RedisAccessException extends Exception {
  static final long serialVersionUID = 1L;
  private Event event;

  public RedisAccessException(Event event) {
    super();
    this.event = event;
  }

  public RedisAccessException(Event event, String errorMessage) {
    super(errorMessage);
    this.event = event;
  }

  public RedisAccessException(Event event, String errorMessage, Throwable ex) {
    super(errorMessage, ex);
    this.event = event;
  }

  public RedisAccessException(Event event, Throwable ex) {
    super(ex);
    this.event = event;
  }

  public Event getEvent() {
    return event;
  }
}
