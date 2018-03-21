package com.suchaz.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.suchaz.app.domain.enumeration.Status;

import com.suchaz.app.domain.enumeration.UserRole;

import com.suchaz.app.domain.enumeration.FBAccessTokenType;

import com.suchaz.app.domain.enumeration.SignupMethod;

/**
 * A SuchAzUser.
 */
@Entity
@Table(name = "such_az_user")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SuchAzUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @NotNull
    @Column(name = "jhi_password", nullable = false)
    private String password;

    @Column(name = "contact")
    private Long contact;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "jhi_role", nullable = false)
    private UserRole role;

    @Column(name = "fb_access_token")
    private String fbAccessToken;

    @Enumerated(EnumType.STRING)
    @Column(name = "fb_access_token_type")
    private FBAccessTokenType fbAccessTokenType;

    @NotNull
    @Column(name = "is_varified", nullable = false)
    private String isVarified;

    @Column(name = "token_exp_date")
    private Long tokenExpDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "varified_by")
    private SignupMethod varifiedBy;

    @Enumerated(EnumType.STRING)
    @Column(name = "signup_method")
    private SignupMethod signupMethod;

    @Column(name = "verify_token")
    private String verifyToken;

    @NotNull
    @Column(name = "created_date", nullable = false)
    private Long createdDate;

    @Column(name = "last_updated_date")
    private Long lastUpdatedDate;

    @NotNull
    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @Column(name = "last_updated_by")
    private String lastUpdatedBy;

    @OneToMany(mappedBy = "suchAzUser")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<UserGiftWrapper> userGiftWrappers = new HashSet<>();

    @OneToMany(mappedBy = "suchAzUser")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ConsumerProfile> consumerProfiles = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "such_az_user_trait",
               joinColumns = @JoinColumn(name="such_az_users_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="traits_id", referencedColumnName="id"))
    private Set<Trait> traits = new HashSet<>();

    @OneToOne(mappedBy = "suchAzUser")
    @JsonIgnore
    private UserProfile userProfile;

    @OneToOne(mappedBy = "suchAzUser")
    @JsonIgnore
    private WishList wishList;

    @OneToOne(mappedBy = "suchAzUser")
    @JsonIgnore
    private BuyLaterList buyLaterList;

    @OneToOne(mappedBy = "suchAzUser")
    @JsonIgnore
    private ActivityList activityList;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public SuchAzUser email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public SuchAzUser password(String password) {
        this.password = password;
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getContact() {
        return contact;
    }

    public SuchAzUser contact(Long contact) {
        this.contact = contact;
        return this;
    }

    public void setContact(Long contact) {
        this.contact = contact;
    }

    public Status getStatus() {
        return status;
    }

    public SuchAzUser status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public UserRole getRole() {
        return role;
    }

    public SuchAzUser role(UserRole role) {
        this.role = role;
        return this;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getFbAccessToken() {
        return fbAccessToken;
    }

    public SuchAzUser fbAccessToken(String fbAccessToken) {
        this.fbAccessToken = fbAccessToken;
        return this;
    }

    public void setFbAccessToken(String fbAccessToken) {
        this.fbAccessToken = fbAccessToken;
    }

    public FBAccessTokenType getFbAccessTokenType() {
        return fbAccessTokenType;
    }

    public SuchAzUser fbAccessTokenType(FBAccessTokenType fbAccessTokenType) {
        this.fbAccessTokenType = fbAccessTokenType;
        return this;
    }

    public void setFbAccessTokenType(FBAccessTokenType fbAccessTokenType) {
        this.fbAccessTokenType = fbAccessTokenType;
    }

    public String getIsVarified() {
        return isVarified;
    }

    public SuchAzUser isVarified(String isVarified) {
        this.isVarified = isVarified;
        return this;
    }

    public void setIsVarified(String isVarified) {
        this.isVarified = isVarified;
    }

    public Long getTokenExpDate() {
        return tokenExpDate;
    }

    public SuchAzUser tokenExpDate(Long tokenExpDate) {
        this.tokenExpDate = tokenExpDate;
        return this;
    }

    public void setTokenExpDate(Long tokenExpDate) {
        this.tokenExpDate = tokenExpDate;
    }

    public SignupMethod getVarifiedBy() {
        return varifiedBy;
    }

    public SuchAzUser varifiedBy(SignupMethod varifiedBy) {
        this.varifiedBy = varifiedBy;
        return this;
    }

    public void setVarifiedBy(SignupMethod varifiedBy) {
        this.varifiedBy = varifiedBy;
    }

    public SignupMethod getSignupMethod() {
        return signupMethod;
    }

    public SuchAzUser signupMethod(SignupMethod signupMethod) {
        this.signupMethod = signupMethod;
        return this;
    }

    public void setSignupMethod(SignupMethod signupMethod) {
        this.signupMethod = signupMethod;
    }

    public String getVerifyToken() {
        return verifyToken;
    }

    public SuchAzUser verifyToken(String verifyToken) {
        this.verifyToken = verifyToken;
        return this;
    }

    public void setVerifyToken(String verifyToken) {
        this.verifyToken = verifyToken;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public SuchAzUser createdDate(Long createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public Long getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public SuchAzUser lastUpdatedDate(Long lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Long lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public SuchAzUser createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public SuchAzUser lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Set<UserGiftWrapper> getUserGiftWrappers() {
        return userGiftWrappers;
    }

    public SuchAzUser userGiftWrappers(Set<UserGiftWrapper> userGiftWrappers) {
        this.userGiftWrappers = userGiftWrappers;
        return this;
    }

    public SuchAzUser addUserGiftWrapper(UserGiftWrapper userGiftWrapper) {
        this.userGiftWrappers.add(userGiftWrapper);
        userGiftWrapper.setSuchAzUser(this);
        return this;
    }

    public SuchAzUser removeUserGiftWrapper(UserGiftWrapper userGiftWrapper) {
        this.userGiftWrappers.remove(userGiftWrapper);
        userGiftWrapper.setSuchAzUser(null);
        return this;
    }

    public void setUserGiftWrappers(Set<UserGiftWrapper> userGiftWrappers) {
        this.userGiftWrappers = userGiftWrappers;
    }

    public Set<ConsumerProfile> getConsumerProfiles() {
        return consumerProfiles;
    }

    public SuchAzUser consumerProfiles(Set<ConsumerProfile> consumerProfiles) {
        this.consumerProfiles = consumerProfiles;
        return this;
    }

    public SuchAzUser addConsumerProfile(ConsumerProfile consumerProfile) {
        this.consumerProfiles.add(consumerProfile);
        consumerProfile.setSuchAzUser(this);
        return this;
    }

    public SuchAzUser removeConsumerProfile(ConsumerProfile consumerProfile) {
        this.consumerProfiles.remove(consumerProfile);
        consumerProfile.setSuchAzUser(null);
        return this;
    }

    public void setConsumerProfiles(Set<ConsumerProfile> consumerProfiles) {
        this.consumerProfiles = consumerProfiles;
    }

    public Set<Trait> getTraits() {
        return traits;
    }

    public SuchAzUser traits(Set<Trait> traits) {
        this.traits = traits;
        return this;
    }

    public SuchAzUser addTrait(Trait trait) {
        this.traits.add(trait);
        trait.getSuchAzUsers().add(this);
        return this;
    }

    public SuchAzUser removeTrait(Trait trait) {
        this.traits.remove(trait);
        trait.getSuchAzUsers().remove(this);
        return this;
    }

    public void setTraits(Set<Trait> traits) {
        this.traits = traits;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public SuchAzUser userProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
        return this;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public WishList getWishList() {
        return wishList;
    }

    public SuchAzUser wishList(WishList wishList) {
        this.wishList = wishList;
        return this;
    }

    public void setWishList(WishList wishList) {
        this.wishList = wishList;
    }

    public BuyLaterList getBuyLaterList() {
        return buyLaterList;
    }

    public SuchAzUser buyLaterList(BuyLaterList buyLaterList) {
        this.buyLaterList = buyLaterList;
        return this;
    }

    public void setBuyLaterList(BuyLaterList buyLaterList) {
        this.buyLaterList = buyLaterList;
    }

    public ActivityList getActivityList() {
        return activityList;
    }

    public SuchAzUser activityList(ActivityList activityList) {
        this.activityList = activityList;
        return this;
    }

    public void setActivityList(ActivityList activityList) {
        this.activityList = activityList;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SuchAzUser suchAzUser = (SuchAzUser) o;
        if (suchAzUser.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), suchAzUser.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SuchAzUser{" +
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
