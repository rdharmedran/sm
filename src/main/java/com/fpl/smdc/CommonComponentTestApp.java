
package com.fpl.smdc;

import java.util.Arrays;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * The Class CommonComponentTestApp.
 */
@SpringBootApplication
@EnableAutoConfiguration
@EnableCaching
public class CommonComponentTestApp {

  /**
   * The main method.
   *
   * @param args the arguments
   */
  public static void main(String[] args) {
    SpringApplication.run(CommonComponentTestApp.class, args);

  }

  /**
   * Command line runner.
   *
   * @param ctx the ctx
   * @return the command line runner
   */
  @Bean
  public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
    return args -> {

      String[] beanNames = ctx.getBeanDefinitionNames();
      Arrays.sort(beanNames);

    };
  }
}
