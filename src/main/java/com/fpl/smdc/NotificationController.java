package com.fpl.smdc;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpl.smdc.core.notification.model.Notification;
import com.fpl.smdc.core.notification.model.SnsMessageData;
import com.fpl.smdc.core.notification.service.SnsPublisherService;

@RestController
@RequestMapping("/sns/notification")
public class NotificationController {

	 @Autowired
	    private SimpMessagingTemplate template;
    @Autowired
   SnsPublisherService snsPublisherService;

    @PostMapping("/publish")
    public boolean publishNotification(@RequestBody Notification notification) {

        try {
        	snsPublisherService.publishSNSMessage(notification);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @PostMapping("/subscirbe")
    public boolean publishNotification1(@RequestBody String notification) throws JsonParseException, JsonMappingException, IOException {

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+notification);
        ObjectMapper mapperObj = new ObjectMapper();
		  mapperObj.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
		  mapperObj.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		  SnsMessageData msg=mapperObj.readValue(notification, SnsMessageData.class);
        template.convertAndSend("/topic/pushNotification", msg.getMessage());
        return true;
    }
    /**
     * GET  /notifications  -> show the notifications page.
     */
    @RequestMapping("/index")
    public String notifications() {
      return "index";
    }

}










