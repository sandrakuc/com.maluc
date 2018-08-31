package com.maluc.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name= UserTable.NAME)
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@NamedQueries(
        @NamedQuery(name = User.FIND_BY_LOGIN_QUERY_NAME,
        query = "SELECT u FROM User u WHERE u.login=:login")
)
public class User {

    public static final String FIND_BY_LOGIN_QUERY_NAME = "findByLogin";
    public static final String LOGIN_PARAM = "login";

    @Id
    @SequenceGenerator(name = "generator", sequenceName = "ID_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
    @Column(name= UserTable.USER_ID_COLUMN_NAME)
    private Integer userId;

    @Column(name= UserTable.LOGIN_COLUMN_NAME)
    @Size(max=20)
    private String login;

    @Column(name= UserTable.PASSWORD_COLUMN_NAME)
    @Size(min=8, max=20)
    private String password;

    @Column(name= UserTable.EMAIL_COLUMN_NAME)
    @Size(max=20)
    private String email;

    @Column(name= UserTable.PHONE_NUMBER_COLUMN_NAME)
    @Size(max=15)
    private String phoneNumber;

    @Column(name= UserTable.USER_NAME_COLUMN_NAME)
    @Size(max=40)
    private String name;

    @Column(name= UserTable.USER_SURNAME_COLUMN_NAME)
    @Size(max=40)
    private String surname;

    //@OneToMany(mappedBy = "user")
   // private List<Reservation> reservations;

}
