/*
 * Created on 8 thg 12 2017 ( Time 14:25:17 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.ecomileage.bean;

import java.io.Serializable;

import javax.validation.constraints.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.util.Date;
@DynamicInsert
@DynamicUpdate
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @NotNull
    private Integer id;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @NotNull
    private Short groupId;

    @NotNull
    @Size( min = 1, max = 100 )
    private String name;

    @Size( max = 100 )
    private String decription;

    @Size( max = 100 )
    private String groupName;


    private Date createDate;


    private Date updateDate;



    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setId( Integer id ) {
        this.id = id ;
    }

    public Integer getId() {
        return this.id;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    public void setGroupId( Short groupId ) {
        this.groupId = groupId;
    }
    public Short getGroupId() {
        return this.groupId;
    }

    public void setName( String name ) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    public void setDecription( String decription ) {
        this.decription = decription;
    }
    public String getDecription() {
        return this.decription;
    }

    public void setGroupName( String groupName ) {
        this.groupName = groupName;
    }
    public String getGroupName() {
        return this.groupName;
    }

    public void setCreateDate( Date createDate ) {
        this.createDate = createDate;
    }
    public Date getCreateDate() {
        return this.createDate;
    }

    public void setUpdateDate( Date updateDate ) {
        this.updateDate = updateDate;
    }
    public Date getUpdateDate() {
        return this.updateDate;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
 
        public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(id);
        sb.append("|");
        sb.append(groupId);
        sb.append("|");
        sb.append(name);
        sb.append("|");
        sb.append(decription);
        sb.append("|");
        sb.append(groupName);
        sb.append("|");
        sb.append(createDate);
        sb.append("|");
        sb.append(updateDate);
        return sb.toString(); 
    } 


}
