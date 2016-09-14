package com.MobyRx.java.entity;

import com.MobyRx.java.entity.type.InvitationStatus;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 9/6/16
 * Time: 5:42 PM
 * To change this template use File | Settings | File Templates.
 */

@Entity
@javax.persistence.Table(name = "invitation")
@XmlRootElement(name = "invitation")
public class InvitationEntity extends BaseEntity{

    public UserEntity invitedBy;
    public String invitedTo;
    public String code;
    public InvitationStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invited_by", nullable = false)
    public UserEntity getInvitedBy() {
        return invitedBy;
    }

    public void setInvitedBy(UserEntity invitedBy) {
        this.invitedBy = invitedBy;
    }

    @Column(name = "invited_to")
    public String getInvitedTo() {
        return invitedTo;
    }

    public void setInvitedTo(String invitedTo) {
        this.invitedTo = invitedTo;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Enumerated
    public InvitationStatus getStatus() {
        return status;
    }

    public void setStatus(InvitationStatus status) {
        this.status = status;
    }
}
