package com.suchaz.app.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.suchaz.app.domain.enumeration.Gender;
import com.suchaz.app.domain.enumeration.AgeGroup;

/**
 * A DTO for the ConsumerProfileHistory entity.
 */
public class ConsumerProfileHistoryDTO implements Serializable {

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

    @NotNull
    private Long createdDate;

    private Long lastUpdatedDate;

    @NotNull
    private String createdBy;

    private String lastUpdatedBy;

<<<<<<< HEAD
    @NotNull
    private Boolean isLoggedInUser;

=======
>>>>>>> 6cab73db24b70508e06c72c996d5888289cc39ad
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

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public Long getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Long lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

<<<<<<< HEAD
    public Boolean isIsLoggedInUser() {
        return isLoggedInUser;
    }

    public void setIsLoggedInUser(Boolean isLoggedInUser) {
        this.isLoggedInUser = isLoggedInUser;
    }

=======
>>>>>>> 6cab73db24b70508e06c72c996d5888289cc39ad
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ConsumerProfileHistoryDTO consumerProfileHistoryDTO = (ConsumerProfileHistoryDTO) o;
        if(consumerProfileHistoryDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), consumerProfileHistoryDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ConsumerProfileHistoryDTO{" +
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
            ", createdDate=" + getCreatedDate() +
            ", lastUpdatedDate=" + getLastUpdatedDate() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
<<<<<<< HEAD
            ", isLoggedInUser='" + isIsLoggedInUser() + "'" +
=======
>>>>>>> 6cab73db24b70508e06c72c996d5888289cc39ad
            "}";
    }
}
