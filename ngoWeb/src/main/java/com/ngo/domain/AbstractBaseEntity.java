package com.ngo.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * Commom Base for all Entities. Contains standard Version and WHO columns.
 * 
 * @author anisht
 * 
 */
@MappedSuperclass
public abstract class AbstractBaseEntity implements Serializable {

    protected static final long serialVersionUID = 4892527487680070885L;
    /**
     * Creation Date of the entity instance.
     */
    @Column(name = "CREATION_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date creationDate;

    /**
     * Date when the entity instance was last updated.
     */
    @Column(name = "LAST_UPDATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date lastUpdatedDate;

    /**
     * User who created the entity instance.
     */
    @Column(name = "CREATED_BY", length = 100, nullable = false)
    protected String createdBy;

    /**
     * User who last updated the entity instance.
     */
    @Column(name = "UPDATED_BY", length = 100, nullable = false)
    protected String updatedBy;

    /**
     * JPA Version attribute.
     */
    @Version
    @Column(name = "VERSION")
    protected Long version;

    /**
     * Getter method for the user who created the entity instance.
     * 
     * @return The name of the user who created the entity instance.
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Getter method for the entity instance creation date.
     * 
     * @return The date when the entity instance was created.
     */
    public Date getCreationDate() {
        if (creationDate == null) {
            return null;
        } else {
            return (Date) creationDate.clone();
        }
    }

    /**
     * Getter method for the date when the entity instance was last updated.
     * 
     * @return Date when the entity instance was last updated.
     */
    public Date getLastUpdatedDate() {
        if (lastUpdatedDate == null) {
            return null;
        } else {
            return (Date) lastUpdatedDate.clone();
        }
    }

    /**
     * Getter method for the user who last updated the entity instance.
     * 
     * @return The name of the user who last updated the entity instance.
     */
    public String getUpdatedBy() {
        return updatedBy;
    }

    /**
     * Getter method for JPA version attribute.
     * 
     * @return The JPA version attribute.
     */
    public Long getVersion() {
        return version;
    }

    /**
     * Setter method for the user who created the entity instance.
     * 
     * @param createdBy
     *            The user who created the entity instance.
     */
    public void setCreatedBy(final String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Setter method for the entity instance creation date.
     * 
     * @param creationDate
     *            The entity instance creation date.
     */
    public void setCreationDate(final Date creationDate) {
        if (creationDate == null) {
            this.creationDate = new Date();
        } else {
            this.creationDate = (Date) creationDate.clone();
        }
    }

    /**
     * Setter method for the date when the entity instance was last updated.
     * 
     * @param lastUpdatedDate
     *            The date when the entity instance was last updated.
     */
    public void setLastUpdatedDate(final Date lastUpdatedDate) {
        if (lastUpdatedDate == null) {
            this.lastUpdatedDate = null;
        } else {
            this.lastUpdatedDate = (Date) lastUpdatedDate.clone();
        }
    }

    /**
     * Setter method for the user who last updated the entity instance.
     * 
     * @param updatedBy
     *            The user who last updated the entity instance.
     */
    public void setUpdatedBy(final String updatedBy) {
        this.updatedBy = updatedBy;
    }

    /**
     * Set method for JPA version attribute.
     * 
     * @param version
     *            The JPA version attribute.
     */
    public void setVersion(final Long version) {
        this.version = version;
    }

}
