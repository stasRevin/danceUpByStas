package com.danceUpByStas.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * This is the Notification bean class representing the Notification entity.
 * @author srevin
 */
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

    /**
     * The empty constructor.
     */
    public Notification() {

    }

    /**
     * The parameterized constructor.
     * @param message The message to send.
     * @param user The reference to the user object.
     * @param isRead The true/false value on whether message was read.
     * @param dateTime The date and time of this notification.
     */
    public Notification(String message, User user, int isRead, LocalDateTime dateTime) {

        this.message = message;
        this.user = user;
        this.isRead = isRead;
        this.dateTime = dateTime;
    }

}
