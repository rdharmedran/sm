package com.fpl.smdc.core.notification.config;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClient;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClientBuilder;
import com.amazonaws.services.securitytoken.model.Credentials;
import com.amazonaws.services.securitytoken.model.GetSessionTokenRequest;
import com.amazonaws.services.securitytoken.model.GetSessionTokenResult;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;

import io.github.gilbertojrequena.bonsai_sns.server.BonsaiSnsEnvironment;
import io.github.gilbertojrequena.bonsai_sns.server.BonsaiSnsServer;
import io.github.gilbertojrequena.bonsai_sns.server.Subscription;
import io.github.gilbertojrequena.bonsai_sns.server.Topic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;
import org.springframework.cloud.aws.messaging.endpoint.NotificationMessageHandlerMethodArgumentResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
@PropertySource("classpath:application.properties")
//@EnableWebMvc
public class AWSAppConfig extends WebMvcConfigurerAdapter{

    private static final Integer TEMPORARY_CREDENTIALS_DURATION_DEFAULT = 7200;

    @Value("${aws.temporary.credentials.validity.duration}")
    String credentialsValidityDuration;

    @Value("${aws.sns.topic.demo.ARN}")
    String snsTopicDemoARN;

    @Bean(name = "snsTopicDemoARN")
    public String snsTopcARN() {
        return this.snsTopicDemoARN;
    }

    @Value("${sns.topic.arn}")
    private String snsTopicARN;

    @Value("${aws.accessKey}")
    private String awsAccessKey;

    @Value("${aws.secretKey}")
    private String awsSecretKey;

    @Value("${aws.region}")
    private String awsRegion;

    @SuppressWarnings("deprecation")
	@Bean(name = "sessionCredentials")
    public BasicAWSCredentials sessionCredentials() {

        AWSSecurityTokenServiceClient sts_client = (AWSSecurityTokenServiceClient) AWSSecurityTokenServiceClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
       // sts_client.withRegion(Regions.US_EAST_1);
        GetSessionTokenRequest session_token_request = new GetSessionTokenRequest();
      
        if (this.credentialsValidityDuration == null || this.credentialsValidityDuration.trim().equals("")) {
            session_token_request.setDurationSeconds(TEMPORARY_CREDENTIALS_DURATION_DEFAULT);
        } else {
            session_token_request.setDurationSeconds(Integer.parseInt(this.credentialsValidityDuration));
        }

        GetSessionTokenResult session_token_result = sts_client.getSessionToken(session_token_request);

        Credentials session_creds = session_token_result.getCredentials();

        BasicAWSCredentials  sessionCredentials = new BasicAWSCredentials (
        		"AKIAZTLYPHZE2X72YYCK", "usTm5zefs3bQAnoqkGYGAoj9Ggsc/orPR28RAAVf");
        return sessionCredentials;
    }
    @Autowired
    private NotificationMessageHandlerMethodArgumentResolver notificationResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(notificationMessageHandlerMethodArgumentResolver());
    }

    @Bean
    public NotificationMessageHandlerMethodArgumentResolver notificationMessageHandlerMethodArgumentResolver () {

        return new NotificationMessageHandlerMethodArgumentResolver();
    };
    
    @Bean
    public  NotificationMessagingTemplate notificationMessagingTemplate() {
    	return new NotificationMessagingTemplate(amazonSNS());
    }
    
    @Bean
    public BonsaiSnsServer bonsaiSnsServer() {
    	return new BonsaiSnsServer.Builder().withAccountId("660067008073").withRegion("us-east-1")
        .withBonsaiSnsEnvironmentDefinition(
            BonsaiSnsEnvironment.Companion.definition()
                .withTopic(
                    Topic.Companion.definition()
                        .withName("LayerUpdate")
                        .withSubscription(
                            Subscription.Companion.definition()
                                .withEndpoint("http://localhost:8080/sns/notification/subscirbe")
                                .withProtocol("http")
                    )
                )
        ).start();
    }
    
    @Bean
    public AmazonSNS amazonSNS () {
    	

          AWSCredentialsProvider awsCredentialsProvider = new AWSStaticCredentialsProvider(
                  new BasicAWSCredentials(awsAccessKey, awsSecretKey)
          );

          return AmazonSNSClientBuilder.standard()
                  .withCredentials(awsCredentialsProvider)
                 // .withRegion(awsRegion)
                  .withEndpointConfiguration(
                	        new AwsClientBuilder.EndpointConfiguration("http://localhost:7979", "us-east-1")
                		    )
                		.build();
                  
       
    };
}


