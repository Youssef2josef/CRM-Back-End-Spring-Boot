package com.example.CRM.Stage.models;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="suivi_rapport")
public class SuiviRapport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="SUIVI_ID") 
	private Long id;
	
	private boolean valid ;
	private LocalDate date;
	private String text;
	
	@OneToOne 
    @JoinColumn(name = "user_id")
    private User user;
	
	@OneToOne  
    @JoinColumn(name = "region_id")
    private RegionModel region;
	
	@OneToOne 
    @JoinColumn(name = "object_id")
    private ObjectModel object;
	
	@OneToOne 
    @JoinColumn(name = "client_id")
    private ClientModel client;
	
	public SuiviRapport() {
	}

	

	public SuiviRapport(boolean valid, LocalDate date, String text, User user, RegionModel region, ObjectModel object,
			ClientModel client) {
		this.valid = valid;
		this.date = date;
		this.text = text;
		this.user = user;
		this.region = region;
		this.object = object;
		this.client = client;
	}



	public String getText() {
		return text;
	}



	public void setText(String text) {
		this.text = text;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public RegionModel getRegion() {
		return region;
	}

	public void setRegion(RegionModel region) {
		this.region = region;
	}

	public ObjectModel getObject() {
		return object;
	}

	public void setObject(ObjectModel object) {
		this.object = object;
	}

	public ClientModel getClient() {
		return client;
	}

	public void setClient(ClientModel client) {
		this.client = client;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public boolean isDateAfterTodayPlus15Days(LocalDate targetDate) {
        LocalDate today = LocalDate.now();
        LocalDate todayPlus15Days = today.plusDays(15);

        return targetDate.isAfter(todayPlus15Days);
       }
	
	@Override
	public String toString() {
		return "SuiviRapport [id=" + id + ", user=" + user + ", region=" + region + ", object=" + object + ", client="
				+ client + ", valid=" + valid + ", date=" + date + "]";
	}	
}
