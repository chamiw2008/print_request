/*
 * Copyright 2017 Ministry of Health, Sri Lanka.
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
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@Entity
@Table(name = "insts")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Insts.findAll", query = "SELECT i FROM Insts i")
    , @NamedQuery(name = "Insts.findById", query = "SELECT i FROM Insts i WHERE i.id = :id")
    , @NamedQuery(name = "Insts.findByInstitution", query = "SELECT i FROM Insts i WHERE i.institution = :institution")
    , @NamedQuery(name = "Insts.findByParentint", query = "SELECT i FROM Insts i WHERE i.parentint = :parentint")})
public class Insts implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "institution")
    private String institution;
    @Basic(optional = false)
    @Column(name = "parentint")
    private int parentint;

    public Insts() {
    }

    public Insts(Integer id) {
        this.id = id;
    }

    public Insts(Integer id, String institution, int parentint) {
        this.id = id;
        this.institution = institution;
        this.parentint = parentint;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public int getParentint() {
        return parentint;
    }

    public void setParentint(int parentint) {
        this.parentint = parentint;
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
        if (!(object instanceof Insts)) {
            return false;
        }
        Insts other = (Insts) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Insts[ id=" + id + " ]";
    }
    
}
