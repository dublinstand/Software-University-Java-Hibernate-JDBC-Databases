package com.airconditioners.domain.entities.reports;

import com.airconditioners.domain.entities.airConditioners.BasicAirConditioner;
import com.airconditioners.domain.enums.Mark;

import javax.persistence.*;

@Entity
@Table(name = "reports")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private Mark mark;

    @OneToOne
    @JoinColumn(name = "conditioner_id")
    private BasicAirConditioner basicAirConditioner;

    public Report() {
    }

    public Report(Mark mark, BasicAirConditioner basicAirConditioner) {
        this.setMark(mark);
        this.setBasicAirConditioner(basicAirConditioner);
    }

    public Mark getMark() {
        return mark;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }

    public BasicAirConditioner getBasicAirConditioner() {
        return basicAirConditioner;
    }

    public void setBasicAirConditioner(BasicAirConditioner basicAirConditioner) {
        this.basicAirConditioner = basicAirConditioner;
    }
}
