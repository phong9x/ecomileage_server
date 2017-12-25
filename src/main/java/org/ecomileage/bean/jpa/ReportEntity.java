/*
 * Created on 8 thg 12 2017 ( Time 14:25:17 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a basic Primary Key (not composite) 

package org.ecomileage.bean.jpa;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnore;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

import java.util.Date;

import javax.persistence.*;

/**
 * Persistent class for entity stored in table "report"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="report")
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="ReportEntity.countAll", query="SELECT COUNT(x) FROM ReportEntity x" )
} )
@DynamicInsert
@DynamicUpdate
public class ReportEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id", nullable=false)
    private Integer    id           ;


    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="latitude", nullable=false)
    private Double     latitude     ;

    @Column(name="longtitude", nullable=false)
    private Double     longtitude   ;

    @Column(name="title", nullable=false, length=300)
    private String     title        ;

    @Column(name="content", nullable=false)
    private String     content      ;

    @Column(name="profile_image", nullable=false, length=500)
    private String     profileImage ;

    @Column(name="profile_url", nullable=false, length=500)
    private String     profileUrl   ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="update_date")
    private Date       updateDate   ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_date")
    private Date       createDate   ;

    @Column(name="status", nullable=false)
    private Short      status       ;

	// "userId" (column "user_id") is not defined by itself because used as FK in a link 
	// "categoryId" (column "category_id") is not defined by itself because used as FK in a link 


    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName="id")
    private UserEntity user        ;

    @ManyToOne
    @JoinColumn(name="category_id", referencedColumnName="id")
    private CategoryEntity category    ;

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public ReportEntity() {
		super();
    }
    
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
    //--- DATABASE MAPPING : latitude ( DOUBLE ) 
    public void setLatitude( Double latitude ) {
        this.latitude = latitude;
    }
    public Double getLatitude() {
        return this.latitude;
    }

    //--- DATABASE MAPPING : longtitude ( DOUBLE ) 
    public void setLongtitude( Double longtitude ) {
        this.longtitude = longtitude;
    }
    public Double getLongtitude() {
        return this.longtitude;
    }

    //--- DATABASE MAPPING : title ( VARCHAR ) 
    public void setTitle( String title ) {
        this.title = title;
    }
    public String getTitle() {
        return this.title;
    }

    //--- DATABASE MAPPING : content ( TEXT ) 
    public void setContent( String content ) {
        this.content = content;
    }
    public String getContent() {
        return this.content;
    }

    //--- DATABASE MAPPING : profile_image ( VARCHAR ) 
    public void setProfileImage( String profileImage ) {
        this.profileImage = profileImage;
    }
    public String getProfileImage() {
        return this.profileImage;
    }

    //--- DATABASE MAPPING : profile_url ( VARCHAR ) 
    public void setProfileUrl( String profileUrl ) {
        this.profileUrl = profileUrl;
    }
    public String getProfileUrl() {
        return this.profileUrl;
    }

    //--- DATABASE MAPPING : update_date ( DATETIME ) 
    public void setUpdateDate( Date updateDate ) {
        this.updateDate = updateDate;
    }
    public Date getUpdateDate() {
        return this.updateDate;
    }

    //--- DATABASE MAPPING : create_date ( DATETIME ) 
    public void setCreateDate( Date createDate ) {
        this.createDate = createDate;
    }
    public Date getCreateDate() {
        return this.createDate;
    }

    //--- DATABASE MAPPING : status ( SMALLINT ) 
    public void setStatus( Short status ) {
        this.status = status;
    }
    public Short getStatus() {
        return this.status;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------
    public void setUser( UserEntity user ) {
        this.user = user;
    }
    public UserEntity getUser() {
        return this.user;
    }

    public void setCategory( CategoryEntity category ) {
        this.category = category;
    }
    public CategoryEntity getCategory() {
        return this.category;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(id);
        sb.append("]:"); 
        sb.append(latitude);
        sb.append("|");
        sb.append(longtitude);
        sb.append("|");
        sb.append(title);
        // attribute 'content' not usable (type = String Long Text)
        sb.append("|");
        sb.append(profileImage);
        sb.append("|");
        sb.append(profileUrl);
        sb.append("|");
        sb.append(updateDate);
        sb.append("|");
        sb.append(createDate);
        sb.append("|");
        sb.append(status);
        return sb.toString(); 
    } 

}
