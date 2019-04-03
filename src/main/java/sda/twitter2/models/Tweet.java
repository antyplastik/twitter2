package sda.twitter2.models;

import lombok.*;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.List;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tweet")
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    private String message;

    @Type(type="org.joda.time.contrib.hibernate.PersistentDateTime")
    private DateTime date;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany
    private List<Response> responses;

}
