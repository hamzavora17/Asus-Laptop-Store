package com.lptstore;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link lptstore}.
 */
@Generated
public class lptstore__BeanDefinitions {
  /**
   * Get the bean definition for 'lptstore'.
   */
  public static BeanDefinition getLptstoreBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(lptstore.class);
    beanDefinition.setInstanceSupplier(lptstore::new);
    return beanDefinition;
  }
}
