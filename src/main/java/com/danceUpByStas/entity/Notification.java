package com.danceUpByStas.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "Notification")
@Table(name = "Notification")
@Data
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @Column(name = "message")
    private String message;

    @Column(name = "is_read")
    private int isRead;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_Notification_user_id"))
    private User user;


    public Notification() {

    }

    public Notification(String message, User user, int isRead, LocalDateTime dateTime) {

        this.message = message;
        this.user = user;
        this.isRead = isRead;
        this.dateTime = dateTime;
    }

}
