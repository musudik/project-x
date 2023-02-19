package com.optum.portal.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    protected User() {
    }
    public User(String firstName, String lastName, String username, String password, String email, String role) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.active=true;
        this.username=username;
        this.role = role;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NotEmpty(message = "First name can't be empty!")
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty(message = "Last name can't be empty!")
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    @Email(message = "*Please provide a valid email")
    @NotEmpty(message = "*Please provide an email")
    private String email;

    @Column(name = "username")
    @NotEmpty(message = "*Please provide an username")
    private String username;

    @Column(name = "password")
    @Length(min = 5, message = "*Your password must have at least 5 characters")
    @NotEmpty(message = "*Please provide your password")
    private String password;

    @Column(name = "active")
    private Boolean active;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",referencedColumnName="user_id")
    private List<Program> programs = new ArrayList<>();

    @Transient
    private String accessToken;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",referencedColumnName="user_id")
    private List<Reward> rewards = new ArrayList<>();

    @Column(name = "role")
    private String role;

//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "users_roles",
//            joinColumns = @JoinColumn(
//                    name = "user_id", referencedColumnName = "user_id"),
//            inverseJoinColumns = @JoinColumn(
//                    name = "role_id", referencedColumnName = "role_id"))
//    private Collection <Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id){
        this.id=id;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public void setFirstName(String firstName){
        this.firstName=firstName;
    }

    public String getLastName(){
        return this.lastName;
    }

    public void setLastName(String lastName){
        this.lastName=lastName;
    }

//    public void setRoles(Collection<Role> roles){
//        this.roles = roles;
//    }
//
//    public Collection<Role> getRoles(){
//        return this.roles;
//    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password=password;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String username){
        this.email=username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(List<Program> programs) {
        this.programs = programs;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public List<Reward> getRewards() {
        return rewards;
    }

    public void setRewards(List<Reward> rewards) {
        this.rewards = rewards;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}