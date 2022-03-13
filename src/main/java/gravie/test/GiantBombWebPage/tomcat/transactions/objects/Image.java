package gravie.test.GiantBombWebPage.tomcat.transactions.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Image {
	private String icon_url;
	private String medium_url;
	private String screen_url;
	private String screen_large_url;
	private String small_url;
	private String super_url;
	private String thumb_url;
	private String tiny_url;
	private String original_url;
	private String image_tags;
	public String getIcon_url() {
		return icon_url;
	}
	public void setIcon_url(String icon_url) {
		this.icon_url = icon_url;
	}
	public String getMedium_url() {
		return medium_url;
	}
	public void setMedium_url(String medium_url) {
		this.medium_url = medium_url;
	}
	public String getScreen_url() {
		return screen_url;
	}
	public void setScreen_url(String screen_url) {
		this.screen_url = screen_url;
	}
	public String getScreen_large_url() {
		return screen_large_url;
	}
	public void setScreen_large_url(String screen_large_url) {
		this.screen_large_url = screen_large_url;
	}
	public String getSmall_url() {
		return small_url;
	}
	public void setSmall_url(String small_url) {
		this.small_url = small_url;
	}
	public String getSuper_url() {
		return super_url;
	}
	public void setSuper_url(String super_url) {
		this.super_url = super_url;
	}
	public String getThumb_url() {
		return thumb_url;
	}
	public void setThumb_url(String thumb_url) {
		this.thumb_url = thumb_url;
	}
	public String getTiny_url() {
		return tiny_url;
	}
	public void setTiny_url(String tiny_url) {
		this.tiny_url = tiny_url;
	}
	public String getOriginal_url() {
		return original_url;
	}
	public void setOriginal_url(String original_url) {
		this.original_url = original_url;
	}
	public String getImage_tags() {
		return image_tags;
	}
	public void setImage_tags(String image_tags) {
		this.image_tags = image_tags;
	}
	@Override
	public String toString() {
		return "Image [icon_url=" + icon_url + ", medium_url=" + medium_url + ", screen_url=" + screen_url
				+ ", screen_large_url=" + screen_large_url + ", small_url=" + small_url + ", super_url=" + super_url
				+ ", thumb_url=" + thumb_url + ", tiny_url=" + tiny_url + ", original_url=" + original_url
				+ ", image_tags=" + image_tags + "]";
	}
}
