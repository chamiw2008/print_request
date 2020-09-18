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
import javax.persistence.Lob;
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
@Table(name = "audits")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Audits.findAll", query = "SELECT a FROM Audits a")
    , @NamedQuery(name = "Audits.findById", query = "SELECT a FROM Audits a WHERE a.id = :id")
    , @NamedQuery(name = "Audits.findByUserid", query = "SELECT a FROM Audits a WHERE a.userid = :userid")
    , @NamedQuery(name = "Audits.findByIpaddress", query = "SELECT a FROM Audits a WHERE a.ipaddress = :ipaddress")
    , @NamedQuery(name = "Audits.findByUseragent", query = "SELECT a FROM Audits a WHERE a.useragent = :useragent")
    , @NamedQuery(name = "Audits.findByComputerid", query = "SELECT a FROM Audits a WHERE a.computerid = :computerid")
    , @NamedQuery(name = "Audits.findByTimestamp", query = "SELECT a FROM Audits a WHERE a.timestamp = :timestamp")})
public class Audits implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "userid")
    private String userid;
    @Basic(optional = false)
    @Column(name = "ipaddress")
    private String ipaddress;
    @Basic(optional = false)
    @Column(name = "useragent")
    private String useragent;
    @Basic(optional = false)
    @Column(name = "computerid")
    private String computerid;
    @Basic(optional = false)
    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @Basic(optional = false)
    @Lob
    @Column(name = "activity")
    private String activity;

    public Audits() {
    }

    public Audits(Integer id) {
        this.id = id;
    }

    public Audits(Integer id, String userid, String ipaddress, String useragent, String computerid, Date timestamp, String activity) {
        this.id = id;
        this.userid = userid;
        this.ipaddress = ipaddress;
        this.useragent = useragent;
        this.computerid = computerid;
        this.timestamp = timestamp;
        this.activity = activity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    public String getUseragent() {
        return useragent;
    }

    public void setUseragent(String useragent) {
        this.useragent = useragent;
    }

    public String getComputerid() {
        return computerid;
    }

    public void setComputerid(String computerid) {
        this.computerid = computerid;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
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
        if (!(object instanceof Audits)) {
            return false;
        }
        Audits other = (Audits) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Audits[ id=" + id + " ]";
    }
    
}
