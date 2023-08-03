package com.sugadev.scheduleservice.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name ="schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    private int id_schedule;
    @Column(name = "title")
    private String title;
    @Column(name = "date")
    private String date;
    private int id_video;
    private int id_user;
    private int id_schedule_parent;
//    @Version
//    private int version;
//    public void createVersion(){
//        ScheduleHistory scheduleHistory = new ScheduleHistory();
//        scheduleHistory.setSchedule(this);
//        scheduleHistory.setId_schedule(this.id_schedule);
//        scheduleHistory.setTitle(this.title);
//        scheduleHistory.setDate(this.date);
//        scheduleHistory.setId_video(this.id_video);
//        scheduleHistory.setId_user(this.id_user);
//        scheduleHistory.setVersion(this.version);
//    }
}
