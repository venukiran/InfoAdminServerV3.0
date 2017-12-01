package com.slt.infobus.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name="ib_screen_position_dtl")
public class IBScreenPositionDtl {
	private int scrnPosId;
	private String area;
	private String displayType;
	private int screenId;
	private int platformId;
	//scrn_id
	/*private IBScreenDtl screen;
	@OneToOne(optional=false, mappedBy="positionDtl")	
	public IBScreenDtl getScreen(){return screen;}
	
	public void setScreen(IBScreenDtl screen) {
		this.screen = screen;
	}*/

	/*private IBPlatformDtl platform;
	//pltm_id
	@OneToOne(optional=false)
    @JoinColumn(name="PLTFM_ID", unique=true, nullable=false, updatable=false)	
	public IBPlatformDtl getPlatform(){return platform;}
	
	public void setPlatform(IBPlatformDtl platform) {
		this.platform = platform;
	}
*/	
	@Id	
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="SCRN_POS_ID", unique = true, nullable = false)
	public int getScrnPosId() {
		return scrnPosId;
	}
	public void setScrnPosId(int scrnPosId) {
		this.scrnPosId = scrnPosId;
	}
	@Column(name="AREA")
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	@Column(name="DISPLAY_TYPE")
	public String getDisplayType() {
		return displayType;
	}
	public void setDisplayType(String displayType) {
		this.displayType = displayType;
	}
	
	@Column(name="SCRN_ID")
	public int getScreenId() {
		return screenId;
	}
	public void setScreenId(int screenId) {
		this.screenId = screenId;
	}
	
	@Column(name="PLTFM_ID")
	public int getPlatformId() {
		return platformId;
	}
	public void setPlatformId(int platformId) {
		this.platformId = platformId;
	}	
	
}
