
package com.fpl.smdc.core.log;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.session.SessionDestroyedEvent;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import com.fpl.smdc.core.model.AuditLogRequest;

/**
 * The listener interface for receiving logout events. The class that is interested in processing a
 * logout event implements this interface, and the object created with that class is registered with
 * a component using the component's <code>addLogoutListener<code> method. When the logout event
 * occurs, that object's appropriate method is invoked.
 *
 * @see LogoutEvent
 */
@Component
public class LogoutListener implements ApplicationListener<SessionDestroyedEvent> {

  /** The Constant LOG. */
  private static final Logger LOG = LogManager.getLogger(LogoutListener.class);

  /** The dashboard id. */
  @Value("${common.logservice.application.id}")
  private String dashboardId;

  /** The log service. */
  @Autowired
  LoggingService logService;

  /*
   * (non-Javadoc)
   *
   * @see org.springframework.context.ApplicationListener#onApplicationEvent(org.
   * springframework.context.ApplicationEvent)
   */
  @Override
  public void onApplicationEvent(SessionDestroyedEvent event) {
    List<SecurityContext> lstSecurityContext = event.getSecurityContexts();
    @SuppressWarnings("unused")
    AuditLogRequest logRequest = null;

    for (SecurityContext securityContext : lstSecurityContext) {

      if (securityContext.getAuthentication()
          .getPrincipal() instanceof org.springframework.security.core.userdetails.User) {
        User ud = (User) securityContext.getAuthentication().getPrincipal();
        Authentication auth = securityContext.getAuthentication();
        WebAuthenticationDetails details = (WebAuthenticationDetails) auth.getDetails();
        String ipAddress = details.getRemoteAddress();

        logRequest = new AuditLogRequest(dashboardId, ud.getUsername(), ipAddress);
      }

      logService.logout(logRequest);
    }
  }

}
