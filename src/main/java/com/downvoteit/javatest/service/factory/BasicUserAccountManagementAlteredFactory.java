package com.downvoteit.javatest.service.factory;

import com.downvoteit.javatest.service.basic.BasicAlteredUserService;

public class BasicUserAccountManagementAlteredFactory extends UserAccountManagementFactory {
  public BasicUserAccountManagementAlteredFactory create() {
    super.create();
    userService = new BasicAlteredUserService();

    return this;
  }
}
