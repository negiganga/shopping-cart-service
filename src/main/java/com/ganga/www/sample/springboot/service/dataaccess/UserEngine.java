package com.ganga.www.sample.springboot.service.dataaccess;

import com.ganga.www.sample.springboot.service.common.model.user.User;

public interface UserEngine {
  User getUser(String userId);
}
