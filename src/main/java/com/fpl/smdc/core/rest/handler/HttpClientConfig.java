
package com.fpl.smdc.core.rest.handler;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLContext;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import com.fpl.smdc.core.rest.exception.RecordNotFoundException;

/**
 * The Class HttpClientConfig.
 */
@Configuration
@EnableScheduling
public class HttpClientConfig {

  /** The Constant LOGGER. */
  private static final Logger LOGGER = LogManager.getLogger(HttpClientConfig.class);

  /** The Constant CONNECT_TIMEOUT. */
  // Determines the timeout in milliseconds until a connection is established.
  private static final int CONNECT_TIMEOUT = 30000;

  /** The Constant REQUEST_TIMEOUT. */
  // The timeout when requesting a connection from the connection manager.
  private static final int REQUEST_TIMEOUT = 30000;

  /** The Constant SOCKET_TIMEOUT. */
  // The timeout for waiting for data
  private static final int SOCKET_TIMEOUT = 60000;

  /** The Constant MAX_TOTAL_CONNECTIONS. */
  private static final int MAX_TOTAL_CONNECTIONS = 50;

  /** The Constant DEFAULT_KEEP_ALIVE_TIME_MILLIS. */
  private static final int DEFAULT_KEEP_ALIVE_TIME_MILLIS = 20 * 1000;

  /** The Constant CLOSE_IDLE_CONNECTION_WAIT_TIME_SECS. */
  private static final int CLOSE_IDLE_CONNECTION_WAIT_TIME_SECS = 30;

  /**
   * Connection keep alive strategy.
   *
   * @return the connection keep alive strategy
   */
  @Bean
  public ConnectionKeepAliveStrategy connectionKeepAliveStrategy() {
    return new ConnectionKeepAliveStrategy() {
      @Override
      public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
        HeaderElementIterator it =
            new BasicHeaderElementIterator(response.headerIterator(HTTP.CONN_KEEP_ALIVE));
        while (it.hasNext()) {
          HeaderElement he = it.nextElement();
          String param = he.getName();
          String value = he.getValue();

          if (value != null && param.equalsIgnoreCase("timeout")) {
            return Long.parseLong(value) * 1000;
          }
        }
        return DEFAULT_KEEP_ALIVE_TIME_MILLIS;
      }
    };
  }

  /**
   * Http client.
   *
   * @return the closeable http client
   */
  @Bean
  public CloseableHttpClient httpClient() {
    RequestConfig requestConfig =
        RequestConfig.custom().setConnectionRequestTimeout(REQUEST_TIMEOUT)
            .setConnectTimeout(CONNECT_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT).build();

    return HttpClients.custom()// .setSSLHostnameVerifier(new NoopHostnameVerifier())
        .setDefaultRequestConfig(requestConfig).setConnectionManager(poolingConnectionManager())
        .setKeepAliveStrategy(connectionKeepAliveStrategy()).build();
  }

  /**
   * Idle connection monitor.
   *
   * @param connectionManager the connection manager
   * @return the runnable
   */
  @Bean
  public Runnable idleConnectionMonitor(
      final PoolingHttpClientConnectionManager connectionManager) {
    return new Runnable() {
      @Override
      @Scheduled(fixedDelay = 10000)
      public void run() {
        try {
          if (connectionManager != null) {
            LOGGER.trace("run IdleConnectionMonitor - Closing expired and idle connections...");
            connectionManager.closeExpiredConnections();
            connectionManager.closeIdleConnections(CLOSE_IDLE_CONNECTION_WAIT_TIME_SECS,
                TimeUnit.SECONDS);
          } else {
            LOGGER.trace(
                "run IdleConnectionMonitor - Http Client Connection manager is not initialised");
          }
        } catch (Exception e) {
          LOGGER.error("run IdleConnectionMonitor - Exception occurred. msg={}, e={}",
              e.getMessage(), e);
        }
      }
    };
  }

  /**
   * Pooling connection manager.
   *
   * @return the pooling http client connection manager
   */
  @Bean
  public PoolingHttpClientConnectionManager poolingConnectionManager() {
    TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

    FileInputStream inputStream = null;
    try {
      inputStream =
          new FileInputStream(getClass().getClassLoader().getResource("keystore.p12").getFile());
    } catch (IOException e) {
      throw new RecordNotFoundException("SSL certificate is not aailable in path ");
    }

    SSLContext sslContext = null;
    SSLConnectionSocketFactory sslsf = null;
    try {
      KeyStore keystore = KeyStore.getInstance("PKCS12");
      char[] partnerId2charArray = "password".toCharArray();
      keystore.load(inputStream, partnerId2charArray);
      sslContext = org.apache.http.ssl.SSLContexts.custom()
          .loadTrustMaterial(keystore, acceptingTrustStrategy).build();

      new SSLConnectionSocketFactory(sslContext);
      SSLContextBuilder builder = new SSLContextBuilder();
      builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
      sslsf = new SSLConnectionSocketFactory(sslContext);
    } catch (NoSuchAlgorithmException | KeyStoreException | KeyManagementException
        | CertificateException | IOException e) {
      LOGGER.error("Pooling Connection Manager Initialisation failure because of " + e.getMessage(),
          e);
    }

    Registry<ConnectionSocketFactory> socketFactoryRegistry =
        RegistryBuilder.<ConnectionSocketFactory>create().register("https", sslsf)
            .register("http", new PlainConnectionSocketFactory()).build();

    PoolingHttpClientConnectionManager poolingConnectionManager =
        new PoolingHttpClientConnectionManager(socketFactoryRegistry);
    poolingConnectionManager.setMaxTotal(MAX_TOTAL_CONNECTIONS);
    return poolingConnectionManager;
  }
}
