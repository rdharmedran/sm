
package com.fpl.smdc.core.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import com.fpl.smdc.core.model.AuditLogRequest;

/**
 * The listener interface for receiving login events. The class that is interested in processing a
 * login event implements this interface, and the object created with that class is registered with
 * a component using the component's <code>addLoginListener<code> method. When the login event
 * occurs, that object's appropriate method is invoked.
 *
 * @see LoginEvent
 */
@Component
public class LoginListener implements ApplicationListener<AbstractAuthenticationEvent> {

  /** The Constant LOG. */
  private static final Logger LOG = LogManager.getLogger(LoginListener.class);

  /** The log service. */
  @Autowired
  LoggingService logService;

  /** The dashboard id. */
  @Value("${common.logservice.application.id}")
  private String dashboardId;

  /*
   * (non-Javadoc)
   *
   * @see org.springframework.context.ApplicationListener#onApplicationEvent(org.
   * springframework.context.ApplicationEvent)
   */
  @Override
  public void onApplicationEvent(AbstractAuthenticationEvent event) {
    AuditLogRequest logRequest = null;
    if (event.getAuthentication()
        .getPrincipal() instanceof org.springframework.security.core.userdetails.User) {
      Authentication auth = event.getAuthentication();
      WebAuthenticationDetails details = (WebAuthenticationDetails) auth.getDetails();
      String ipAddress = details.getRemoteAddress();
      User ud = (User) event.getAuthentication().getPrincipal();
      logRequest = new AuditLogRequest(dashboardId, ud.getUsername(), ipAddress);
    }
    // }else {
    // DefaultOidcUser ud = (DefaultOidcUser)
    // event.getAuthentication().getPrincipal();
    // logRequest = new AuditLogRequest(dashboardId, ud.getEmail(),
    // ud.getAuthenticatedAt().toString(), null, null);
    // }
    logService.login(logRequest);
  }

}
