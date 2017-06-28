
package com.bot.chat.ws.beans.support;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Message implements Serializable
{
	private final static long serialVersionUID = 1L;

    @SerializedName("type")
    @Expose
    private long type;
    
    @SerializedName("speech")
    @Expose
    private String speech;
    
    public Message withType(long type) {
        this.type = type;
        return this;
    }

    public Message withSpeech(String speech) {
        this.speech = speech;
        return this;
    }

}
