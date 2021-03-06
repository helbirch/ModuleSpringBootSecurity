package com.sec.demo.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "USER")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private  String userName;
    @JsonProperty(access =JsonProperty.Access.WRITE_ONLY )
    private String password;
    private Boolean actived;
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "USER_ROLE")
    private Collection<AppRole> appRoles=new ArrayList<>();

}
