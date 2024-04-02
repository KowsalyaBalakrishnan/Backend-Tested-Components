package com.async.practice.asyncimplservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_details_seq")
    @SequenceGenerator(name = "user_details_seq", sequenceName = "user_details_sequence", allocationSize = 1)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "user_name")
    private String name;
    @Column(name = "user_email_id")
    private String emailId;

    @Column(name = "user_city")
    private String city;
}
