package entity;

import jakarta.persistence.*;
import model.User;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED) // Strategia łącząca tabelę nadrzędną z podrzędnymi w bazie
public abstract class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(name = "is_archived", nullable = false)
    private boolean isArchived;

    public UserEntity() {}

    public abstract User toDomain();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isArchived() {
        return isArchived;
    }

    public void setArchived(boolean archived) {
        isArchived = archived;
    }
}