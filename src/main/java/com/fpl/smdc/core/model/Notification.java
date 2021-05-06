
package com.fpl.smdc.core.model;

/**
 * The Class Notification.
 */
public class Notification {

  /** The subject. */
  private String subject;
  
  /** The body. */
  private String body;

  /**
   * Instantiates a new notification.
   */
  public Notification() {}

  /**
   * Instantiates a new notification.
   *
   * @param subject the subject
   * @param body the body
   */
  public Notification(String subject, String body) {
    this.setSubject(subject);
    this.setBody(body);
  }

  /**
   * Gets the body.
   *
   * @return the body
   */
  public String getBody() {
    return body;
  }

  /**
   * Gets the subject.
   *
   * @return the subject
   */
  public String getSubject() {
    return subject;
  }

  /**
   * Sets the body.
   *
   * @param body the new body
   */
  public void setBody(String body) {
    this.body = body;
  }

  /**
   * Sets the subject.
   *
   * @param subject the new subject
   */
  public void setSubject(String subject) {
    this.subject = subject;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "Notification{" + "subject='" + getSubject() + '\'' + ", body='" + getBody() + '\''
        + '}';
  }
}
