package com.tpe.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,length = 25)
    private String firstName;//Bunlar student kismina denk olacak zaten
    @Column(nullable = false,length = 25)
    private String lastName;
    @Column(nullable = false,length = 25,unique = true)//unique olmasinin nedeni tek kullaniciya ait olmali
    private String userName;
    @Column(length = 255,nullable = false)// length 255 olma sebebi; password
    // haslemeye girerse karakter sayısı daha fazla olacaktır
    private String password;//security katmani oldugu icin password katmani

    @ManyToMany(fetch = FetchType.EAGER)//Userlari caigirinca roller de gelsin isterim
    @JoinTable(name="tbl_user_role",joinColumns = @JoinColumn(name="user_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))//3. tablo
    private Set<Role> roles = new HashSet<>();





}
