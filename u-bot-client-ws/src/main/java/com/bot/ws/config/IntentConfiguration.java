package com.bot.ws.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IntentConfiguration {

	@ConfigurationProperties(prefix = "intentTypeList")
	public static class MusicIntents {

		private Map<String, String> musicIntents = new HashMap<String, String>();

		public Map<String, String> getMusicIntents() {
			return musicIntents;
		}

		public void setMusicIntents(Map<String, String> musicIntents) {
			this.musicIntents = musicIntents;
		}

	}

	@ConfigurationProperties(prefix = "intentTypeList")
	public static class IgnoreIntents {

//		private List<String> ignoreIntents = new ArrayList<String>();
//
//		public List<String> getIgnoreIntents() {
//			return ignoreIntents;
//		}
//
//		public void setIgnoreIntents(List<String> ignoreIntents) {
//			this.ignoreIntents = ignoreIntents;
//		}
//
//		public HashSet<String> getIgnoreIntentSet() {
//			HashSet<String> ignoreIntentSet = new HashSet<>(ignoreIntents);
//			return ignoreIntentSet;
//		}

		private String ignoreIntents;

		public String getIgnoreIntents() {
			return ignoreIntents;
		}

		public void setIgnoreIntents(String ignoreIntents) {
			this.ignoreIntents = ignoreIntents;
		}

	}

}
