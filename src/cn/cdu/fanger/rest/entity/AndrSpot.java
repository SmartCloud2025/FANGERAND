package cn.cdu.fanger.rest.entity;

import java.io.Serializable;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@SuppressWarnings("serial")
@Root
public class AndrSpot implements Serializable{
	@Element
	private String imageUrl;
	@Element
	private String category;
	@Element
	private String name;
	@Element
	private String summary;
	@Element
	private String fullAddr;
	@Element
	private String placeId;
	@Element
	private String city;
	@Element
	private Double lat;//经纬度
	@Element
	private Double lng;//经纬度
	@Element
	private int commentsCount;
	@Element
	private int likeCount;
	@Element
	private int markCount;
	@Element
	private int shareCount;
	@Element
	private int downloadCount;
	
	public AndrSpot() {}
	public AndrSpot(String imageUrl, String category, String name,
			String summary, String fullAddr, String placeId, String city,
			Double lat, Double lng, int commentsCount, int likeCount,
			int markCount, int shareCount, int downloadCount) {
		super();
		this.imageUrl = imageUrl;
		this.category = category;
		this.name = name;
		this.summary = summary;
		this.fullAddr = fullAddr;
		this.placeId = placeId;
		this.city = city;
		this.lat = lat;
		this.lng = lng;
		this.commentsCount = commentsCount;
		this.likeCount = likeCount;
		this.markCount = markCount;
		this.shareCount = shareCount;
		this.downloadCount = downloadCount;
	}


	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getFullAddr() {
		return fullAddr;
	}
	public void setFullAddr(String fullAddr) {
		this.fullAddr = fullAddr;
	}
	public String getPlaceId() {
		return placeId;
	}
	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public Double getLng() {
		return lng;
	}
	public void setLng(Double lng) {
		this.lng = lng;
	}
	public int getCommentsCount() {
		return commentsCount;
	}
	public void setCommentsCount(int commentsCount) {
		this.commentsCount = commentsCount;
	}
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	public int getMarkCount() {
		return markCount;
	}
	public void setMarkCount(int markCount) {
		this.markCount = markCount;
	}
	public int getShareCount() {
		return shareCount;
	}
	public void setShareCount(int shareCount) {
		this.shareCount = shareCount;
	}
	public int getDownloadCount() {
		return downloadCount;
	}
	public void setDownloadCount(int downloadCount) {
		this.downloadCount = downloadCount;
	}
}
