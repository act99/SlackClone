package com.example.slack.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Optional;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Dms extends Timestamped {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long dmsId;

    @ManyToOne
    @JoinColumn(name = "receiver_Id")
    private Members members;

    @ManyToOne
    @JoinColumn(name = "sender_Id")
    private User user;

    @ManyToOne
    @JoinColumn
    private Workspaces workspaces;

    @Column
    private String chat;


    public Dms(Members members, User user, Workspaces workspaces, String chat) {
        this.members = members;
        this.user = user;
        this.workspaces = workspaces;
        this.chat = chat;
    }
}
