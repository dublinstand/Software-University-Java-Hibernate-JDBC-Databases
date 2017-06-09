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

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Mark getMark() {
        return this.mark;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }

    public BasicAirConditioner getBasicAirConditioner() {
        return this.basicAirConditioner;
    }

    public void setBasicAirConditioner(BasicAirConditioner basicAirConditioner) {
        this.basicAirConditioner = basicAirConditioner;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Manufacturer: " + this.getBasicAirConditioner().getManufacturer())
                .append(System.lineSeparator());
        stringBuilder.append("Model: " + this.getBasicAirConditioner().getModel())
                .append(System.lineSeparator());;
        stringBuilder.append("Mark: " + this.getMark());
        return stringBuilder.toString();
    }
}
