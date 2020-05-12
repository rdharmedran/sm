
package com.fpl.smdc.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.fpl.smdc.core.rest.exception.InvalidUri;

/**
 * The Class Util.
 */
public class Util {

  /** The Constant logger. */
  static final Logger logger = LogManager.getLogger(Util.class);

  /**
   * Gets the framework version.
   *
   * @return the framework version
   */
  public static String getFrameworkVersion() {

    Class clazz = Util.class;
    URL location = clazz.getResource('/' + clazz.getName().replace('.', '/') + ".class");
    // location =
    return location.toString();
  }

  /**
   * Gets the inet address.
   *
   * @return the inet address
   */
  public static InetAddress getInetAddress() {
    InetAddress inetAddress = null;
    try {
      inetAddress = InetAddress.getLocalHost();
    } catch (IOException ioe) {
      logger.error("Error in getting InetAddress", ioe);
    }
    return inetAddress;
  }

  /**
   * Gets the jar version.
   *
   * @return the jar version
   */
  public static String getJarVersion() {
    String path = Util.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    // String path =

    logger.debug("path = " + path);
    String ver = null;
    if (path.endsWith(".jar")) {
      int endIndex = path.indexOf(".jar");
      int startIndex = path.lastIndexOf('/');
      String jarName = path.substring(startIndex + 1, endIndex);
      ver = jarName.substring(jarName.lastIndexOf('-') + 1);
    }
    return ver;
  }

  /**
   * Gets the uuid.
   *
   * @return the uuid
   */
  public static String getUUID() {
    UUID id = UUID.randomUUID();
    ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
    bb.putLong(id.getMostSignificantBits());
    bb.putLong(id.getLeastSignificantBits());
    return Base64.encodeBase64URLSafeString(bb.array());
  }

  /**
   * Parses the integer.
   *
   * @param intStr the int str
   * @return the int
   */
  public static int parseInteger(String intStr) {
    if (intStr == null) {
      return Constants.DEFAULT_INT_VALUE;
    }
    try {
      return Integer.parseInt(intStr);
    } catch (NumberFormatException e) {
      return Constants.DEFAULT_INT_VALUE;
    }
  }

  /**
   * Quote.
   *
   * @param value the value
   * @return the string
   */
  public static String quote(final String value) {
    if (value == null) {
      return null;
    }
    String result = value;
    if (!result.startsWith("\"")) {
      result = "\"" + result;
    }
    if (!result.endsWith("\"")) {
      result = result + "\"";
    }
    return result;
  }

  /**
   * Substitute variables.
   *
   * @param template the template
   * @param variables the variables
   * @return the string
   */
  public static String substituteVariables(String template, Map<String, String> variables) {
    Pattern pattern = Pattern.compile("\\$\\{(.+?)\\}");
    Matcher matcher = pattern.matcher(template);
    // StringBuilder cannot be used here because Matcher expects StringBuffer
    StringBuffer buffer = new StringBuffer();
    while (matcher.find()) {
      if (variables.containsKey(matcher.group(1))) {
        String replacement = variables.get(matcher.group(1));
        // quote to work properly with $ and {,} signs
        matcher.appendReplacement(buffer,
            replacement != null ? Matcher.quoteReplacement(replacement) : "null");
      }
    }
    matcher.appendTail(buffer);
    return buffer.toString();
  }

  /**
   * Url decode.
   *
   * @param value the value
   * @return the string
   */
  public static String urlDecode(String value) {
    if (value == null || value.length() == 0) {
      return "";
    }
    try {
      return URLDecoder.decode(value, Constants.DEFAULT_CHARACTER);
    } catch (UnsupportedEncodingException e) {
      throw new InvalidUri(e.getMessage());
    }
  }

  /**
   * Url encode.
   *
   * @param value the value
   * @return the string
   */
  public static String urlEncode(String value) {
    if (value == null || value.length() == 0) {
      return "";
    }
    try {
      return URLEncoder.encode(value, Constants.DEFAULT_CHARACTER);
    } catch (UnsupportedEncodingException e) {
      throw new InvalidUri(e.getMessage());
    }
  }

  /**
   * Instantiates a new util.
   */
  private Util() {

  }

}
