package sda.twitter2.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @Column(length = 255)
    private String accountName;

    @ManyToOne
    @JoinColumn(name = "usertId")
    private User userId;

    @Column(length = 255)
    private String description;

    @OneToMany
    private List<Follower> followers;

    @OneToMany
    private List<Follow> follows;

    private boolean active;

}
