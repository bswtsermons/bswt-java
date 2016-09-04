package org.bswt.api.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(uniqueConstraints={
  @UniqueConstraint(columnNames={ "date_preached", "date_series" })
})
public class Service 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="service_id")
	private Long id;
	
	@Column(name="date_preached")
	@Temporal(TemporalType.DATE)
	private Date date;
	
	/** this is an incremental counter in case there are multiple services in 
	 * one day, which we almost never have.
	 */
	@Column(name="date_series")
	private int series;
	
	private String minister;
	
	@Column(name="subseries_title")
	private String title;
	
	@Column(name="subseries_part")
	private Integer part;
	
	@Column(name="series_name")
	private String seriesTitle;
	
	@Column(name="series_part")
	private Integer seriesPart;
	
	private String location;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="service")
	private List<Media> media;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getSeries() {
		return series;
	}

	public void setSeries(int series) {
		this.series = series;
	}

	public String getMinister() {
		return minister;
	}

	public void setMinister(String minister) {
		this.minister = minister;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getPart() {
		return part;
	}

	public void setPart(Integer part) {
		this.part = part;
	}

	public String getSeriesTitle() {
		return seriesTitle;
	}

	public void setSeriesTitle(String seriesTitle) {
		this.seriesTitle = seriesTitle;
	}

	public Integer getSeriesPart() {
		return seriesPart;
	}

	public void setSeriesPart(Integer seriesPart) {
		this.seriesPart = seriesPart;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) { 
		this.location = location;
	}

	public List<Media> getMedia() {
		return media;
	}

	public void setMedia(List<Media> media) {
		this.media = media;
	}
}
