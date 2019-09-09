
package server;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/*
    This is just to parse JSON.
    Look no further :|
    Optimized for Jackson/FasterJSONParser
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "timestamp_epoch",
        "message_id"
})
public class ResponseJsonPojo {

    @JsonProperty("timestamp_epoch")
    private Integer timestampEpoch;
    @JsonProperty("message_id")
    private String messageId;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("timestamp_epoch")
    public Integer getTimestampEpoch() {
        return timestampEpoch;
    }

    @JsonProperty("timestamp_epoch")
    public void setTimestampEpoch(Integer timestampEpoch) {
        this.timestampEpoch = timestampEpoch;
    }

    @JsonProperty("message_id")
    public String getMessageId() {
        return messageId;
    }

    @JsonProperty("message_id")
    public void setMessageId(String messageId) {
        this.messageId = messageId;
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