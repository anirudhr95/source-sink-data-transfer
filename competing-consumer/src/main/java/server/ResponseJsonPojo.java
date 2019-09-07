
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
    Optimized for Jackson/FasterJSONParser (for now?)
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "edited",
        "id",
        "parent_id",
        "author_flair_text",
        "author",
        "distinguished",
        "retrieved_on",
        "gilded",
        "subreddit_id",
        "link_id",
        "stickied",
        "body",
        "controversiality",
        "score",
        "ups",
        "author_flair_css_class",
        "created_utc",
        "subreddit",
        "timestamp_epoch",
        "message_id"
})
public class ResponseJsonPojo {

    @JsonProperty("edited")
    private Boolean edited;
    @JsonProperty("id")
    private String id;
    @JsonProperty("parent_id")
    private String parentId;
    @JsonProperty("author_flair_text")
    private Object authorFlairText;
    @JsonProperty("author")
    private String author;
    @JsonProperty("distinguished")
    private Object distinguished;
    @JsonProperty("retrieved_on")
    private Integer retrievedOn;
    @JsonProperty("gilded")
    private Integer gilded;
    @JsonProperty("subreddit_id")
    private String subredditId;
    @JsonProperty("link_id")
    private String linkId;
    @JsonProperty("stickied")
    private Boolean stickied;
    @JsonProperty("body")
    private String body;
    @JsonProperty("controversiality")
    private Integer controversiality;
    @JsonProperty("score")
    private Integer score;
    @JsonProperty("ups")
    private Integer ups;
    @JsonProperty("author_flair_css_class")
    private Object authorFlairCssClass;
    @JsonProperty("created_utc")
    private Integer createdUtc;
    @JsonProperty("subreddit")
    private String subreddit;
    @JsonProperty("timestamp_epoch")
    private Integer timestampEpoch;
    @JsonProperty("message_id")
    private String messageId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("edited")
    public Boolean getEdited() {
        return edited;
    }

    @JsonProperty("edited")
    public void setEdited(Boolean edited) {
        this.edited = edited;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("parent_id")
    public String getParentId() {
        return parentId;
    }

    @JsonProperty("parent_id")
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @JsonProperty("author_flair_text")
    public Object getAuthorFlairText() {
        return authorFlairText;
    }

    @JsonProperty("author_flair_text")
    public void setAuthorFlairText(Object authorFlairText) {
        this.authorFlairText = authorFlairText;
    }

    @JsonProperty("author")
    public String getAuthor() {
        return author;
    }

    @JsonProperty("author")
    public void setAuthor(String author) {
        this.author = author;
    }

    @JsonProperty("distinguished")
    public Object getDistinguished() {
        return distinguished;
    }

    @JsonProperty("distinguished")
    public void setDistinguished(Object distinguished) {
        this.distinguished = distinguished;
    }

    @JsonProperty("retrieved_on")
    public Integer getRetrievedOn() {
        return retrievedOn;
    }

    @JsonProperty("retrieved_on")
    public void setRetrievedOn(Integer retrievedOn) {
        this.retrievedOn = retrievedOn;
    }

    @JsonProperty("gilded")
    public Integer getGilded() {
        return gilded;
    }

    @JsonProperty("gilded")
    public void setGilded(Integer gilded) {
        this.gilded = gilded;
    }

    @JsonProperty("subreddit_id")
    public String getSubredditId() {
        return subredditId;
    }

    @JsonProperty("subreddit_id")
    public void setSubredditId(String subredditId) {
        this.subredditId = subredditId;
    }

    @JsonProperty("link_id")
    public String getLinkId() {
        return linkId;
    }

    @JsonProperty("link_id")
    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }

    @JsonProperty("stickied")
    public Boolean getStickied() {
        return stickied;
    }

    @JsonProperty("stickied")
    public void setStickied(Boolean stickied) {
        this.stickied = stickied;
    }

    @JsonProperty("body")
    public String getBody() {
        return body;
    }

    @JsonProperty("body")
    public void setBody(String body) {
        this.body = body;
    }

    @JsonProperty("controversiality")
    public Integer getControversiality() {
        return controversiality;
    }

    @JsonProperty("controversiality")
    public void setControversiality(Integer controversiality) {
        this.controversiality = controversiality;
    }

    @JsonProperty("score")
    public Integer getScore() {
        return score;
    }

    @JsonProperty("score")
    public void setScore(Integer score) {
        this.score = score;
    }

    @JsonProperty("ups")
    public Integer getUps() {
        return ups;
    }

    @JsonProperty("ups")
    public void setUps(Integer ups) {
        this.ups = ups;
    }

    @JsonProperty("author_flair_css_class")
    public Object getAuthorFlairCssClass() {
        return authorFlairCssClass;
    }

    @JsonProperty("author_flair_css_class")
    public void setAuthorFlairCssClass(Object authorFlairCssClass) {
        this.authorFlairCssClass = authorFlairCssClass;
    }

    @JsonProperty("created_utc")
    public Integer getCreatedUtc() {
        return createdUtc;
    }

    @JsonProperty("created_utc")
    public void setCreatedUtc(Integer createdUtc) {
        this.createdUtc = createdUtc;
    }

    @JsonProperty("subreddit")
    public String getSubreddit() {
        return subreddit;
    }

    @JsonProperty("subreddit")
    public void setSubreddit(String subreddit) {
        this.subreddit = subreddit;
    }

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