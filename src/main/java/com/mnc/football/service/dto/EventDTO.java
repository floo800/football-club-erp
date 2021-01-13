package com.mnc.football.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import javax.persistence.Lob;

/**
 * A DTO for the {@link com.mnc.football.domain.Event} entity.
 */
public class EventDTO implements Serializable {
    
    private Long id;

    private String name;

    private LocalDate startDate;

    private LocalDate endDate;

    private String description;

    private String streetAndNumber;

    private String cityAndZipCode;

    private String country;

    @Lob
    private byte[] image;

    private String imageContentType;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStreetAndNumber() {
        return streetAndNumber;
    }

    public void setStreetAndNumber(String streetAndNumber) {
        this.streetAndNumber = streetAndNumber;
    }

    public String getCityAndZipCode() {
        return cityAndZipCode;
    }

    public void setCityAndZipCode(String cityAndZipCode) {
        this.cityAndZipCode = cityAndZipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EventDTO)) {
            return false;
        }

        return id != null && id.equals(((EventDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EventDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", description='" + getDescription() + "'" +
            ", streetAndNumber='" + getStreetAndNumber() + "'" +
            ", cityAndZipCode='" + getCityAndZipCode() + "'" +
            ", country='" + getCountry() + "'" +
            ", image='" + getImage() + "'" +
            "}";
    }
}
