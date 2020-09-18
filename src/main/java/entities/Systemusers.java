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
@Table(name = "systemusers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Systemusers.findAll", query = "SELECT s FROM Systemusers s")
    , @NamedQuery(name = "Systemusers.findById", query = "SELECT s FROM Systemusers s WHERE s.id = :id")
    , @NamedQuery(name = "Systemusers.findByName", query = "SELECT s FROM Systemusers s WHERE s.name = :name")
    , @NamedQuery(name = "Systemusers.findByDesig", query = "SELECT s FROM Systemusers s WHERE s.desig = :desig")
    , @NamedQuery(name = "Systemusers.findByUserType", query = "SELECT s FROM Systemusers s WHERE s.userType = :userType")
    , @NamedQuery(name = "Systemusers.findByMobile2", query = "SELECT s FROM Systemusers s WHERE s.mobile2 = :mobile2")
    , @NamedQuery(name = "Systemusers.findByMobile1", query = "SELECT s FROM Systemusers s WHERE s.mobile1 = :mobile1")
    , @NamedQuery(name = "Systemusers.findByEmailOff", query = "SELECT s FROM Systemusers s WHERE s.emailOff = :emailOff")
    , @NamedQuery(name = "Systemusers.findByEmailPersonal", query = "SELECT s FROM Systemusers s WHERE s.emailPersonal = :emailPersonal")
    , @NamedQuery(name = "Systemusers.findByUn", query = "SELECT s FROM Systemusers s WHERE s.un = :un")
    , @NamedQuery(name = "Systemusers.findByPw", query = "SELECT s FROM Systemusers s WHERE s.pw = :pw")
    , @NamedQuery(name = "Systemusers.findByAdmin", query = "SELECT s FROM Systemusers s WHERE s.admin = :admin")
    , @NamedQuery(name = "Systemusers.findByInst", query = "SELECT s FROM Systemusers s WHERE s.inst = :inst")})
public class Systemusers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Column(name = "Desig")
    private String desig;
    @Basic(optional = false)
    @Column(name = "UserType")
    private String userType;
    @Column(name = "mobile2")
    private String mobile2;
    @Column(name = "mobile1")
    private String mobile1;
    @Column(name = "email_off")
    private String emailOff;
    @Column(name = "email_personal")
    private String emailPersonal;
    @Basic(optional = false)
    @Column(name = "un")
    private String un;
    @Basic(optional = false)
    @Column(name = "pw")
    private String pw;
    @Basic(optional = false)
    @Column(name = "admin")
    private boolean admin;
    @Basic(optional = false)
    @Column(name = "inst")
    private int inst;

    public Systemusers() {
    }

    public Systemusers(Integer id) {
        this.id = id;
    }

    public Systemusers(Integer id, String name, String userType, String un, String pw, boolean admin, int inst) {
        this.id = id;
        this.name = name;
        this.userType = userType;
        this.un = un;
        this.pw = pw;
        this.admin = admin;
        this.inst = inst;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesig() {
        return desig;
    }

    public void setDesig(String desig) {
        this.desig = desig;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getMobile2() {
        return mobile2;
    }

    public void setMobile2(String mobile2) {
        this.mobile2 = mobile2;
    }

    public String getMobile1() {
        return mobile1;
    }

    public void setMobile1(String mobile1) {
        this.mobile1 = mobile1;
    }

    public String getEmailOff() {
        return emailOff;
    }

    public void setEmailOff(String emailOff) {
        this.emailOff = emailOff;
    }

    public String getEmailPersonal() {
        return emailPersonal;
    }

    public void setEmailPersonal(String emailPersonal) {
        this.emailPersonal = emailPersonal;
    }

    public String getUn() {
        return un;
    }

    public void setUn(String un) {
        this.un = un;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public boolean getAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public int getInst() {
        return inst;
    }

    public void setInst(int inst) {
        this.inst = inst;
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
        if (!(object instanceof Systemusers)) {
            return false;
        }
        Systemusers other = (Systemusers) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Systemusers[ id=" + id + " ]";
    }
    
}
