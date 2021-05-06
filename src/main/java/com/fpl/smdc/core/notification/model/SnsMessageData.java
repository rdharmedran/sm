package com.fpl.smdc.core.notification.model;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"Type",
"MessageId",
"TopicArn",
"Message",
"SignatureVersion",
"Signature",
"SigningCertURL",
"UnsubscribeURL"
})
public class SnsMessageData {

@JsonProperty("Type")
private String type;
@JsonProperty("MessageId")
private String messageId;
@JsonProperty("TopicArn")
private String topicArn;
@JsonProperty("Message")
private String message;
@JsonProperty("SignatureVersion")
private String signatureVersion;
@JsonProperty("Signature")
private String signature;
@JsonProperty("SigningCertURL")
private String signingCertURL;
@JsonProperty("UnsubscribeURL")
private String unsubscribeURL;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

/**
* No args constructor for use in serialization
*
*/
public SnsMessageData() {
}

/**
*
* @param signingCertURL
* @param signature
* @param messageId
* @param type
* @param message
* @param signatureVersion
* @param unsubscribeURL
* @param topicArn
*/
public SnsMessageData(String type, String messageId, String topicArn, String message, String signatureVersion, String signature, String signingCertURL, String unsubscribeURL) {
super();
this.type = type;
this.messageId = messageId;
this.topicArn = topicArn;
this.message = message;
this.signatureVersion = signatureVersion;
this.signature = signature;
this.signingCertURL = signingCertURL;
this.unsubscribeURL = unsubscribeURL;
}

@JsonProperty("Type")
public String getType() {
return type;
}

@JsonProperty("Type")
public void setType(String type) {
this.type = type;
}

@JsonProperty("MessageId")
public String getMessageId() {
return messageId;
}

@JsonProperty("MessageId")
public void setMessageId(String messageId) {
this.messageId = messageId;
}

@JsonProperty("TopicArn")
public String getTopicArn() {
return topicArn;
}

@JsonProperty("TopicArn")
public void setTopicArn(String topicArn) {
this.topicArn = topicArn;
}

@JsonProperty("Message")
public String getMessage() {
return message;
}

@JsonProperty("Message")
public void setMessage(String message) {
this.message = message;
}

@JsonProperty("SignatureVersion")
public String getSignatureVersion() {
return signatureVersion;
}

@JsonProperty("SignatureVersion")
public void setSignatureVersion(String signatureVersion) {
this.signatureVersion = signatureVersion;
}

@JsonProperty("Signature")
public String getSignature() {
return signature;
}

@JsonProperty("Signature")
public void setSignature(String signature) {
this.signature = signature;
}

@JsonProperty("SigningCertURL")
public String getSigningCertURL() {
return signingCertURL;
}

@JsonProperty("SigningCertURL")
public void setSigningCertURL(String signingCertURL) {
this.signingCertURL = signingCertURL;
}

@JsonProperty("UnsubscribeURL")
public String getUnsubscribeURL() {
return unsubscribeURL;
}

@JsonProperty("UnsubscribeURL")
public void setUnsubscribeURL(String unsubscribeURL) {
this.unsubscribeURL = unsubscribeURL;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}


}