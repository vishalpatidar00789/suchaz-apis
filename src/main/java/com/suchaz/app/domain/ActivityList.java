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

/**
 * A ActivityList.
 */
@Entity
@Table(name = "activity_list")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ActivityList implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

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

    @OneToOne
    @JoinColumn(unique = true)
    private SuchAzUser suchAzUser;

    @OneToMany(mappedBy = "activityList")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ActivityListItem> activityListItems = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public ActivityList status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public ActivityList createdDate(Long createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public Long getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public ActivityList lastUpdatedDate(Long lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Long lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public ActivityList createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public ActivityList lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public SuchAzUser getSuchAzUser() {
        return suchAzUser;
    }

    public ActivityList suchAzUser(SuchAzUser suchAzUser) {
        this.suchAzUser = suchAzUser;
        return this;
    }

    public void setSuchAzUser(SuchAzUser suchAzUser) {
        this.suchAzUser = suchAzUser;
    }

    public Set<ActivityListItem> getActivityListItems() {
        return activityListItems;
    }

    public ActivityList activityListItems(Set<ActivityListItem> activityListItems) {
        this.activityListItems = activityListItems;
        return this;
    }

    public ActivityList addActivityListItem(ActivityListItem activityListItem) {
        this.activityListItems.add(activityListItem);
        activityListItem.setActivityList(this);
        return this;
    }

    public ActivityList removeActivityListItem(ActivityListItem activityListItem) {
        this.activityListItems.remove(activityListItem);
        activityListItem.setActivityList(null);
        return this;
    }

    public void setActivityListItems(Set<ActivityListItem> activityListItems) {
        this.activityListItems = activityListItems;
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
        ActivityList activityList = (ActivityList) o;
        if (activityList.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), activityList.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ActivityList{" +
            "id=" + getId() +
            ", status='" + getStatus() + "'" +
            ", createdDate=" + getCreatedDate() +
            ", lastUpdatedDate=" + getLastUpdatedDate() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            "}";
    }
}
