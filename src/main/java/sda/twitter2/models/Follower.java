package sda.twitter2.models;

import lombok.*;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.jws.soap.SOAPBinding;
import javax.persistence.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "follower")
public class Follower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Type(type="org.joda.time.contrib.hibernate.PersistentDateTime")
    private DateTime date;

}
