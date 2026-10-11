package pl.lodz.p.auction.infrastructure.adapter.out.database.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class UserEntity {
    @Id
    private Long id;
    private String login;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
}