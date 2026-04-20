package com.lptstore.controller;

import com.lptstore.service.UserService;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link webcontroller}.
 */
@Generated
public class webcontroller__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'webcontroller'.
   */
  private static BeanInstanceSupplier<webcontroller> getWebcontrollerInstanceSupplier() {
    return BeanInstanceSupplier.<webcontroller>forConstructor(UserService.class)
            .withGenerator((registeredBean, args) -> new webcontroller(args.get(0)));
  }

  /**
   * Get the bean definition for 'webcontroller'.
   */
  public static BeanDefinition getWebcontrollerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(webcontroller.class);
    beanDefinition.setInstanceSupplier(getWebcontrollerInstanceSupplier());
    return beanDefinition;
  }
}
