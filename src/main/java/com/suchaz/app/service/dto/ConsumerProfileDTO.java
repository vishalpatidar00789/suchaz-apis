package com.suchaz.app.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import com.suchaz.app.domain.enumeration.Gender;
import com.suchaz.app.domain.enumeration.AgeGroup;

/**
 * A DTO for the ConsumerProfile entity.
 */
public class ConsumerProfileDTO implements Serializable {

    private Long id;

    private String name;

    @NotNull
    private Gender gender;

    @NotNull
    private AgeGroup age;

    private String inputTraits;

    private String traitStructure;

    private String activityStructure;

    private String hobbyStructure;

    private String inputReletionship;

    private String inputHobbies;

    private String reccomendedProductTypes;

    private Long suchAzUserId;

    private String suchAzUserEmail;

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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public AgeGroup getAge() {
        return age;
    }

    public void setAge(AgeGroup age) {
        this.age = age;
    }

    public String getInputTraits() {
        return inputTraits;
    }

    public void setInputTraits(String inputTraits) {
        this.inputTraits = inputTraits;
    }

    public String getTraitStructure() {
        return traitStructure;
    }

    public void setTraitStructure(String traitStructure) {
        this.traitStructure = traitStructure;
    }

    public String getActivityStructure() {
        return activityStructure;
    }

    public void setActivityStructure(String activityStructure) {
        this.activityStructure = activityStructure;
    }

    public String getHobbyStructure() {
        return hobbyStructure;
    }

    public void setHobbyStructure(String hobbyStructure) {
        this.hobbyStructure = hobbyStructure;
    }

    public String getInputReletionship() {
        return inputReletionship;
    }

    public void setInputReletionship(String inputReletionship) {
        this.inputReletionship = inputReletionship;
    }

    public String getInputHobbies() {
        return inputHobbies;
    }

    public void setInputHobbies(String inputHobbies) {
        this.inputHobbies = inputHobbies;
    }

    public String getReccomendedProductTypes() {
        return reccomendedProductTypes;
    }

    public void setReccomendedProductTypes(String reccomendedProductTypes) {
        this.reccomendedProductTypes = reccomendedProductTypes;
    }

    public Long getSuchAzUserId() {
        return suchAzUserId;
    }

    public void setSuchAzUserId(Long suchAzUserId) {
        this.suchAzUserId = suchAzUserId;
    }

    public String getSuchAzUserEmail() {
        return suchAzUserEmail;
    }

    public void setSuchAzUserEmail(String suchAzUserEmail) {
        this.suchAzUserEmail = suchAzUserEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ConsumerProfileDTO consumerProfileDTO = (ConsumerProfileDTO) o;
        if(consumerProfileDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), consumerProfileDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ConsumerProfileDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", gender='" + getGender() + "'" +
            ", age='" + getAge() + "'" +
            ", inputTraits='" + getInputTraits() + "'" +
            ", traitStructure='" + getTraitStructure() + "'" +
            ", activityStructure='" + getActivityStructure() + "'" +
            ", hobbyStructure='" + getHobbyStructure() + "'" +
            ", inputReletionship='" + getInputReletionship() + "'" +
            ", inputHobbies='" + getInputHobbies() + "'" +
            ", reccomendedProductTypes='" + getReccomendedProductTypes() + "'" +
            "}";
    }
}
