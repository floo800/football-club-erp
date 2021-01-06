package com.mnc.football.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import javax.persistence.Lob;

/**
 * A DTO for the {@link com.mnc.football.domain.Children} entity.
 */
public class ChildrenDTO implements Serializable {
    
    private Long id;

    private String name;

    private String firstName;

    private LocalDate birthDate;

    private String birthCountry;

    private String birthCity;

    @Lob
    private byte[] photo;

    private String photoContentType;
    private String parentKey;


    private Long teamId;
    
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthCountry() {
        return birthCountry;
    }

    public void setBirthCountry(String birthCountry) {
        this.birthCountry = birthCountry;
    }

    public String getBirthCity() {
        return birthCity;
    }

    public void setBirthCity(String birthCity) {
        this.birthCity = birthCity;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getPhotoContentType() {
        return photoContentType;
    }

    public void setPhotoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
    }

    public String getParentKey() {
        return parentKey;
    }

    public void setParentKey(String parentKey) {
        this.parentKey = parentKey;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ChildrenDTO)) {
            return false;
        }

        return id != null && id.equals(((ChildrenDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ChildrenDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", birthDate='" + getBirthDate() + "'" +
            ", birthCountry='" + getBirthCountry() + "'" +
            ", birthCity='" + getBirthCity() + "'" +
            ", photo='" + getPhoto() + "'" +
            ", parentKey='" + getParentKey() + "'" +
            ", teamId=" + getTeamId() +
            "}";
    }
}
