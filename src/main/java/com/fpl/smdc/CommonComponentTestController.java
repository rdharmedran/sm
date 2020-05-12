
package com.fpl.smdc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.fpl.smdc.core.rest.exception.AuthenticationFailed;
import com.fpl.smdc.core.rest.exception.GeoServerUnAvailable;

/**
 * The Class CommonComponentTestController.
 */
@RestController
public class CommonComponentTestController {

  /** The Constant logger. */
  private static final Logger logger = LogManager.getLogger(CommonComponentTestController.class);

  /** The rest templae. */
  @Autowired
  RestTemplate restTemplae;

  /**
   * Home.
   *
   * @param exceptionType the exception type
   * @return the string
   */
  @GetMapping(value = "/home")
  @ResponseBody
  public String home(@RequestParam Integer exceptionType) {
    logger.info("Info log");
    logger.warn("Hey, This is a warning!");
    logger.error("Oops! We have an Error. OK");
    logger.fatal("Damn! Fatal error. Please fix me.");
    if (exceptionType == 1) {
      throw new AuthenticationFailed("Null");
    } else if (exceptionType == 2) {
      throw new GeoServerUnAvailable("Server Not Available");
    } else if (exceptionType == 3) {
      String abs = null;
      boolean result = abs.equals("qw");
      logger.debug(result);
    }
    return "welcome";
  }

  /**
   * Show welcome page.
   *
   * @param exceptionType the exception type
   * @return the string
   */
  @GetMapping(value = "/")
  @ResponseBody
  public String showWelcomePage(@RequestParam Integer exceptionType) {

    // final OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails)
    // auth.
    // token
    // String accessToken = details.getTokenValue();
    // reference
    for (int i = 0; i < 20; i++) {
      logger.debug("Info log" + i);
    }
    logger.info("Info log");
    logger.warn("Hey, This is a warning!");
    logger.error("Oops! We have an Error. OK");
    logger.fatal("Damn! Fatal error. Please fix me.");
    if (exceptionType == 1) {
      throw new AuthenticationFailed("Null");
    } else if (exceptionType == 2) {
      throw new GeoServerUnAvailable("Server Not Available");
    } else if (exceptionType == 3) {
      String abs = null;
      abs.equals("qw");
    }
    return "welcome";
  }
}
