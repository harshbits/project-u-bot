package com.bot.chat.ws.config;

import java.util.ArrayList;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app")
public class AppProperties {
	
	private String version;
	
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	@ConfigurationProperties(prefix = "app.properties")
	public class Properties {

		private String twilioUrl;

		private String accountSid;

		private String authToken;

		private String chatExchangeName;

		private ArrayList<String> oAuth2Scopes;

		public String getTwilioUrl() {
			return twilioUrl;
		}

		public void setTwilioUrl(String twilioUrl) {
			this.twilioUrl = twilioUrl;
		}

		public String getAccountSid() {
			return accountSid;
		}

		public void setAccountSid(String accountSid) {
			this.accountSid = accountSid;
		}

		public String getAuthToken() {
			return authToken;
		}

		public void setAuthToken(String authToken) {
			this.authToken = authToken;
		}

		public String getChatExchangeName() {
			return chatExchangeName;
		}

		public void setChatExchangeName(String chatExchangeName) {
			this.chatExchangeName = chatExchangeName;
		}

		public ArrayList<String> getoAuth2Scopes() {
			return oAuth2Scopes;
		}

		public void setoAuth2Scopes(ArrayList<String> oAuth2Scopes) {
			this.oAuth2Scopes = oAuth2Scopes;
		}

	}

}
