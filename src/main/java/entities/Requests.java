/*
 * Copyright 2018 Ministry of Health, Sri Lanka.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@Entity
@Table(name = "requests")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Requests.findAll", query = "SELECT r FROM Requests r")
    , @NamedQuery(name = "Requests.findById", query = "SELECT r FROM Requests r WHERE r.id = :id")
    , @NamedQuery(name = "Requests.findByUnit", query = "SELECT r FROM Requests r WHERE r.unit = :unit")
    , @NamedQuery(name = "Requests.findByPtname", query = "SELECT r FROM Requests r WHERE r.ptname = :ptname")
    , @NamedQuery(name = "Requests.findByPtstudydate", query = "SELECT r FROM Requests r WHERE r.ptstudydate = :ptstudydate")
    , @NamedQuery(name = "Requests.findByStudyid", query = "SELECT r FROM Requests r WHERE r.studyid = :studyid")
    , @NamedQuery(name = "Requests.findByModality", query = "SELECT r FROM Requests r WHERE r.modality = :modality")
    , @NamedQuery(name = "Requests.findByDescription", query = "SELECT r FROM Requests r WHERE r.description = :description")
    , @NamedQuery(name = "Requests.findByPtid", query = "SELECT r FROM Requests r WHERE r.ptid = :ptid")
    , @NamedQuery(name = "Requests.findBySender", query = "SELECT r FROM Requests r WHERE r.sender = :sender")
    , @NamedQuery(name = "Requests.findByPriority", query = "SELECT r FROM Requests r WHERE r.priority = :priority")
    , @NamedQuery(name = "Requests.findByRemarks", query = "SELECT r FROM Requests r WHERE r.remarks = :remarks")
    , @NamedQuery(name = "Requests.findByTimeStamps", query = "SELECT r FROM Requests r WHERE r.timeStamps = :timeStamps")
    , @NamedQuery(name = "Requests.findByCurrntStatus", query = "SELECT r FROM Requests r WHERE r.currntStatus = :currntStatus")
    , @NamedQuery(name = "Requests.findByStatusdt", query = "SELECT r FROM Requests r WHERE r.statusdt = :statusdt")
    , @NamedQuery(name = "Requests.findByStatusremarks", query = "SELECT r FROM Requests r WHERE r.statusremarks = :statusremarks")})
public class Requests implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Unit")
    private int unit;
    @Basic(optional = false)
    @Column(name = "ptname")
    private String ptname;
    @Basic(optional = false)
    @Column(name = "ptstudydate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ptstudydate;
    @Basic(optional = false)
    @Column(name = "studyid")
    private String studyid;
    @Basic(optional = false)
    @Column(name = "modality")
    private String modality;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "ptid")
    private String ptid;
    @Column(name = "Sender")
    private String sender;
    @Column(name = "Priority")
    private String priority;
    @Column(name = "Remarks")
    private String remarks;
    @Basic(optional = false)
    @Column(name = "TimeStamps")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeStamps;
    @Column(name = "currntStatus")
    private String currntStatus;
    @Column(name = "statusdt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date statusdt;
    @Column(name = "statusremarks")
    private String statusremarks;

    public Requests() {
    }

    public Requests(Integer id) {
        this.id = id;
    }

    public Requests(Integer id, int unit, String ptname, Date ptstudydate, String studyid, String modality, String description, String ptid, Date timeStamps) {
        this.id = id;
        this.unit = unit;
        this.ptname = ptname;
        this.ptstudydate = ptstudydate;
        this.studyid = studyid;
        this.modality = modality;
        this.description = description;
        this.ptid = ptid;
        this.timeStamps = timeStamps;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public String getPtname() {
        return ptname;
    }

    public void setPtname(String ptname) {
        this.ptname = ptname;
    }

    public Date getPtstudydate() {
        return ptstudydate;
    }

    public void setPtstudydate(Date ptstudydate) {
        this.ptstudydate = ptstudydate;
    }

    public String getStudyid() {
        return studyid;
    }

    public void setStudyid(String studyid) {
        this.studyid = studyid;
    }

    public String getModality() {
        return modality;
    }

    public void setModality(String modality) {
        this.modality = modality;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPtid() {
        return ptid;
    }

    public void setPtid(String ptid) {
        this.ptid = ptid;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getTimeStamps() {
        return timeStamps;
    }

    public void setTimeStamps(Date timeStamps) {
        this.timeStamps = timeStamps;
    }

    public String getCurrntStatus() {
        return currntStatus;
    }

    public void setCurrntStatus(String currntStatus) {
        this.currntStatus = currntStatus;
    }

    public Date getStatusdt() {
        return statusdt;
    }

    public void setStatusdt(Date statusdt) {
        this.statusdt = statusdt;
    }

    public String getStatusremarks() {
        return statusremarks;
    }

    public void setStatusremarks(String statusremarks) {
        this.statusremarks = statusremarks;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Requests)) {
            return false;
        }
        Requests other = (Requests) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Requests[ id=" + id + " ]";
    }
    
}
