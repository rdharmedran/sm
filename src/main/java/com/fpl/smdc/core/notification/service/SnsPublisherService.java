package com.fpl.smdc.core.notification.service;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.fpl.smdc.core.notification.model.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class SnsPublisherService {

    @Value("${sns.topic.arn}")
    private String snsTopicARN;

    @Value("${aws.accessKey}")
    private String awsAccessKey;

    @Value("${aws.secretKey}")
    private String awsSecretKey;

    @Value("${aws.region}")
    private String awsRegion;
@Autowired
    private AmazonSNS amazonSNS;
@Autowired
private  NotificationMessagingTemplate notificationMessagingTemplate;

    private static final Logger logger = LoggerFactory.getLogger(SnsPublisherService.class);

    @PostConstruct
    private void postConstructor() {

      
    }

    public void publishSNSMessage(Notification message) {

        logger.info("Publishing SNS message: " + message);
        PublishRequest publishRequest = new PublishRequest(snsTopicARN,message.getBody(),message.getSubject());
        PublishResult publishResult = this.amazonSNS.publish(publishRequest);


    }
}