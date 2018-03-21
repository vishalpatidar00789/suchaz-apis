package com.suchaz.app.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import com.suchaz.app.domain.enumeration.Status;
import com.suchaz.app.domain.enumeration.UserRole;
import com.suchaz.app.domain.enumeration.FBAccessTokenType;
import com.suchaz.app.domain.enumeration.SignupMethod;
import com.suchaz.app.domain.enumeration.SignupMethod;

/**
 * A DTO for the SuchAzUser entity.
 */
public class SuchAzUserDTO implements Serializable {

    private Long id;

    @NotNull
    private String email;

    @NotNull
    private String password;

    private Long contact;

    @NotNull
    private Status status;

    @NotNull
    private UserRole role;

    private String fbAccessToken;

    private FBAccessTokenType fbAccessTokenType;

    @NotNull
    private String isVarified;

    private Long tokenExpDate;

    private SignupMethod varifiedBy;

    private SignupMethod signupMethod;

    private String verifyToken;

    @NotNull
    private Long createdDate;

    private Long lastUpdatedDate;

    @NotNull
    private String createdBy;

    private String lastUpdatedBy;

    private Set<TraitDTO> traits = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getContact() {
        return contact;
    }

    public void setContact(Long contact) {
        this.contact = contact;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getFbAccessToken() {
        return fbAccessToken;
    }

    public void setFbAccessToken(String fbAccessToken) {
        this.fbAccessToken = fbAccessToken;
    }

    public FBAccessTokenType getFbAccessTokenType() {
        return fbAccessTokenType;
    }

    public void setFbAccessTokenType(FBAccessTokenType fbAccessTokenType) {
        this.fbAccessTokenType = fbAccessTokenType;
    }

    public String getIsVarified() {
        return isVarified;
    }

    public void setIsVarified(String isVarified) {
        this.isVarified = isVarified;
    }

    public Long getTokenExpDate() {
        return tokenExpDate;
    }

    public void setTokenExpDate(Long tokenExpDate) {
        this.tokenExpDate = tokenExpDate;
    }

    public SignupMethod getVarifiedBy() {
        return varifiedBy;
    }

    public void setVarifiedBy(SignupMethod varifiedBy) {
        this.varifiedBy = varifiedBy;
    }

    public SignupMethod getSignupMethod() {
        return signupMethod;
    }

    public void setSignupMethod(SignupMethod signupMethod) {
        this.signupMethod = signupMethod;
    }

    public String getVerifyToken() {
        return verifyToken;
    }

    public void setVerifyToken(String verifyToken) {
        this.verifyToken = verifyToken;
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

    public Set<TraitDTO> getTraits() {
        return traits;
    }

    public void setTraits(Set<TraitDTO> traits) {
        this.traits = traits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SuchAzUserDTO suchAzUserDTO = (SuchAzUserDTO) o;
        if(suchAzUserDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), suchAzUserDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SuchAzUserDTO{" +
            "id=" + getId() +
            ", email='" + getEmail() + "'" +
            ", password='" + getPassword() + "'" +
            ", contact=" + getContact() +
            ", status='" + getStatus() + "'" +
            ", role='" + getRole() + "'" +
            ", fbAccessToken='" + getFbAccessToken() + "'" +
            ", fbAccessTokenType='" + getFbAccessTokenType() + "'" +
            ", isVarified='" + getIsVarified() + "'" +
            ", tokenExpDate=" + getTokenExpDate() +
            ", varifiedBy='" + getVarifiedBy() + "'" +
            ", signupMethod='" + getSignupMethod() + "'" +
            ", verifyToken='" + getVerifyToken() + "'" +
            ", createdDate=" + getCreatedDate() +
            ", lastUpdatedDate=" + getLastUpdatedDate() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            "}";
    }
}
