package com.tekarch.user_managementMs2.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "personal_info")
public class PersonalInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "info_id")
    private Long info_id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "user_id") // correctly map to user entity's column
    private User user;

    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "city", length = 100)
    private String city;

    @Column(name = "state", length = 100)
    private String state;

    @Column(name = "country", length = 100)
    private String country;

    @Column(name = "zip_code", length = 20)
    private String zipCode;

//    @PreUpdate
    //   protected void onUpdate() {
    //       this.onUpdate();
    //   }

    // New method to set userId directly
    public void setUserId(Long userId) {
        this.user = new User();
        this.user.setUserId(userId);
    }

}
