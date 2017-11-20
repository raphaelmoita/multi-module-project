package org.moita.sbe.config;

public class Config {
	
	private final String key;
	
	public Config(Builder builder) {
		this.key = builder.key;
	}
	
	public String getKey() {
		return key;
	}
	
	public static class Builder {
		private String key;
		
		public Builder key(String key) {
			this.key = key;
			return this;
		}
		
		public Config build() {
			return new Config(this);
		}
	}
}
