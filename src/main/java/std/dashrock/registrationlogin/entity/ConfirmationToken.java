package std.dashrock.registrationlogin.entity;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class ConfirmationToken {
    

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="tocken_id")
    private Long tokenid;
    
    @Column(name="confirmation_token")
    private String confirmationToken;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @OneToOne(targetEntity = User.class,fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name="user_id")
    private User user;

    public ConfirmationToken(User user){
        this.user = user;
        createdDate = new Date();
        confirmationToken = UUID.randomUUID().toString();
    }

    /**
     * @return the tokenid
     */
    public Long getTokenid() {
        return tokenid;
    }

    /**
     * @param tokenid the tokenid to set
     */
    public void setTokenid(Long tokenid) {
        this.tokenid = tokenid;
    }

    /**
     * @return the confirmationToken
     */
    public String getConfirmationToken() {
        return confirmationToken;
    }

    /**
     * @param confirmationToken the confirmationToken to set
     */
    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    /**
     * @return the createdDate
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * @param createdDate the createdDate to set
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    
}
