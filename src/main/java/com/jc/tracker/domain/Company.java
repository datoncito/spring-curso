/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jc.tracker.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.json.JsonObjectBuilder;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author campitos
 */
@Entity
@Table(name = "ttt_company")
@NamedQueries({
    @NamedQuery(name = "Company.findAll", query = "SELECT c FROM Company c ORDER BY c.companyName ASC"),
    @NamedQuery(name = "Company.findByIdCompany", query = "SELECT c FROM Company c WHERE c.idCompany = :idCompany"),
    @NamedQuery(name = "Company.findByCompanyName", query = "SELECT c FROM Company c WHERE c.companyName = :companyName"),
    @NamedQuery(name = "Company.findByWhoCreated", query = "SELECT c FROM Company c WHERE c.whoCreated = :whoCreated"),
    @NamedQuery(name = "Company.findByWhoUpdated", query = "SELECT c FROM Company c WHERE c.whoUpdated = :whoUpdated"),
    @NamedQuery(name = "Company.findByWhenCreated", query = "SELECT c FROM Company c WHERE c.whenCreated = :whenCreated")})
public class Company  extends AbstractEntity implements EntityItem<Integer> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_company")
    private Integer idCompany;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "company_name")
    private String companyName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "who_created")
    private String whoCreated;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "who_updated")
    private String whoUpdated;
    @Basic(optional = false)
    @NotNull
    @Column(name = "when_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date whenCreated;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private List<Project> projects;

    public Company() {
    }

    public Company(Integer idCompany) {
        this.idCompany = idCompany;
    }
    
    @Override
    public void addJson(JsonObjectBuilder builder){
        builder.add("idCompany",idCompany).add("companyName", companyName);
    }

    public Company(Integer idCompany, String companyName, String whoCreated, String whoUpdated, Date whenCreated) {
        this.idCompany = idCompany;
        this.companyName = companyName;
        this.whoCreated = whoCreated;
        this.whoUpdated = whoUpdated;
        this.whenCreated = whenCreated;
    }

    public Integer getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(Integer idCompany) {
        this.idCompany = idCompany;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getWhoCreated() {
        return whoCreated;
    }

    public void setWhoCreated(String whoCreated) {
        this.whoCreated = whoCreated;
    }

    public String getWhoUpdated() {
        return whoUpdated;
    }

    public void setWhoUpdated(String whoUpdated) {
        this.whoUpdated = whoUpdated;
    }

    public Date getWhenCreated() {
        return whenCreated;
    }

    public void setWhenCreated(Date whenCreated) {
        this.whenCreated = whenCreated;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCompany != null ? idCompany.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Company other = (Company) obj;
        return Objects.equals(this.idCompany, other.idCompany);
    }

    

    @Override
    public String toString() {
        return "com.jc.tracker.domain.Company[ idCompany=" + idCompany + " ]";
    }

    @Override
    public Integer getId() {
     return idCompany;
    }
    
}
