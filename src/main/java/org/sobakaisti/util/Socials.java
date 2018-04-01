/**
 * 
 */
package org.sobakaisti.util;

import static org.sobakaisti.util.PropertiesUtil.*;

/**
 * @author jelli0t
 *
 */
public enum Socials {
	FACEBOOK("Facebook", FACEBOOK_ICON_PATH),
	TWITTER("Twitter", TWITTER_ICON_PATH),
	INSTAGRAM("Instagram", INSTAGRAM_ICON_PATH),
	YOUTUBE("YouTube", YOUTUBE_ICON_PATH),
	SOUNDCLOUD("Soundcloud", SOUNDCLOUD_ICON_PATH),
	FLICKR("Flickr", FLICKR_ICON_PATH),
	BANDCAMP("Bandcamp", BANDCAMP_ICON_PATH);
	
	
	private String name;
	private String username;
	private String url;
	private String iconPath;

	private Socials(String name, String iconPath) {
		this.name = name;
		this.iconPath = iconPath;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}	
	
	public String getIconPath() {
		return iconPath;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Social: {");
		sb.append(TextUtil.notEmpty(name) ? this.name : "");
		sb.append(TextUtil.notEmpty(username) ? "; " + this.username : "");
		sb.append(TextUtil.notEmpty(url) ? "; " + this.url : "");
		return sb.append("}").toString();
	}
}
