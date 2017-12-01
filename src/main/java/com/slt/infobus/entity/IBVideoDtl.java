package com.slt.infobus.entity;

import java.sql.Date;
import java.sql.Time;
import java.sql.Date;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="ib_video_dtl")
public class IBVideoDtl {
	private int videoId;
	private String videoName;
	private String playTime;
	private String videoFormat;
	private Date uploadedDate; //= new Date(Calendar.getInstance().getTimeInMillis());
	private String fileSize;
	private String status;
	private Date validFrom;
	private Date validTo;
	private String modifyName;
	private String orientation;
	private String videoDescr;
	private String videoCategory;
	/*private Time videoDuration;*/
	private int sponserId;
	//spnrr_id
	/*private IBSponserDtl sponser;
	@ManyToOne
    @JoinColumn(name="SPNSR_ID", nullable=false)
	public IBSponserDtl getSponser(){ return sponser;}
	
	public void setSponser(IBSponserDtl sponser) {
		this.sponser = sponser;
	}
*/
/*	private Set<IBVideoSlotDtl> slotDtl = new HashSet<IBVideoSlotDtl>(0);
	@OneToMany(cascade=CascadeType.ALL, mappedBy="videoDtl")    
	public Set<IBVideoSlotDtl> getSlotDtl() {
		return slotDtl;
	}
	public void setSlotDtl(Set<IBVideoSlotDtl> slotDtl) {
		this.slotDtl = slotDtl;
	}
*/	
	@Id	
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="VID_ID", unique = true, nullable = false)
	public int getVideoId() {
		return videoId;
	}
	
	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}
	@Column(name="VIDEO_NAME")
	public String getVideoName() {
		return videoName;
	}
	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}
	@Column(name="PLAY_TIME")
	public String getPlayTime() {
		return playTime;
	}
	public void setPlayTime(String playTime) {
		this.playTime = playTime;
	}
	@Column(name="VIDEO_FORMAT")
	public String getVideoFormat() {
		return videoFormat;
	}
	public void setVideoFormat(String videoFormat) {
		this.videoFormat = videoFormat;
	}
	@Column(name="UPLOADED_DATE")
	public Date getUploadedDate() {
		return uploadedDate;
	}
	public void setUploadedDate(Date uploadedDate) {
		this.uploadedDate = uploadedDate;
	}
	@Column(name="FILE_SIZE")
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	@Column(name="STATUS")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name="VALID_FROM")
	public Date getValidFrom() {
		return validFrom;
	}
	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}
	@Column(name="VALID_TO")
	public Date getValidTo() {
		return validTo;
	}
	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}
	@Column(name="MODIFIED_NAME")
	public String getModifyName() {
		return modifyName;
	}
	public void setModifyName(String modifyName) {
		this.modifyName = modifyName;
	}	
	@Column(name="ORIENTATION")
	public String getOrientation() {
		return orientation;
	}
	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}
	
	@Column(name="SPNSR_ID", nullable=false)
	public int getSponserId() {
		return sponserId;
	}

	public void setSponserId(int sponserId) {
		this.sponserId = sponserId;
	}

	@Column(name="VIDEO_DESCR")
	public String getVideoDescr() {
		return videoDescr;
	}

	public void setVideoDescr(String videoDescr) {
		this.videoDescr = videoDescr;
	}

	@Column(name="VIDEO_CATEGORY")
	public String getVideoCategory() {
		return videoCategory;
	}

	public void setVideoCategory(String videoCategory) {
		this.videoCategory = videoCategory;
	}
	
	/*@Column(name="VIDEO_DURATION")
	public Time getVideoDuration() {
		return videoDuration;
	}

	public void setVideoDuration(Time videoDuration) {
		this.videoDuration = videoDuration;
	}
*/
	@Override
	public String toString() {
		return "IBVideoDtl [videoId=" + videoId + ", videoName=" + videoName + ", playTime=" + playTime
				+ ", videoFormat=" + videoFormat + ", uploadedDate=" + uploadedDate + ", fileSize=" + fileSize
				+ ", status=" + status + ", validFrom=" + validFrom + ", validTo=" + validTo + ", modifyName="
				+ modifyName + ", orientation=" + orientation + ", videoDescr=" + videoDescr + ", videoCategory="
				+ videoCategory + ", sponserId=" + sponserId + "]";
	}
	
}
