
package com.bot.chat.ws.beans.support;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Metadata implements Serializable
{

    @SerializedName("intentId")
    @Expose
    private String intentId;
    @SerializedName("webhookUsed")
    @Expose
    private String webhookUsed;
    @SerializedName("webhookForSlotFillingUsed")
    @Expose
    private String webhookForSlotFillingUsed;
    @SerializedName("intentName")
    @Expose
    private String intentName;
    private final static long serialVersionUID = 1L;

    public String getIntentId() {
        return intentId;
    }

    public void setIntentId(String intentId) {
        this.intentId = intentId;
    }

    public Metadata withIntentId(String intentId) {
        this.intentId = intentId;
        return this;
    }

    public String getWebhookUsed() {
        return webhookUsed;
    }

    public void setWebhookUsed(String webhookUsed) {
        this.webhookUsed = webhookUsed;
    }

    public Metadata withWebhookUsed(String webhookUsed) {
        this.webhookUsed = webhookUsed;
        return this;
    }

    public String getWebhookForSlotFillingUsed() {
        return webhookForSlotFillingUsed;
    }

    public void setWebhookForSlotFillingUsed(String webhookForSlotFillingUsed) {
        this.webhookForSlotFillingUsed = webhookForSlotFillingUsed;
    }

    public Metadata withWebhookForSlotFillingUsed(String webhookForSlotFillingUsed) {
        this.webhookForSlotFillingUsed = webhookForSlotFillingUsed;
        return this;
    }

    public String getIntentName() {
        return intentName;
    }

    public void setIntentName(String intentName) {
        this.intentName = intentName;
    }

    public Metadata withIntentName(String intentName) {
        this.intentName = intentName;
        return this;
    }

}
