package com.basejava.webapp.model;

import com.basejava.webapp.util.DateUtil;
import com.basejava.webapp.util.JsonFieldAdapter;
import com.basejava.webapp.util.LocalDateAdapter;
import com.google.gson.annotations.JsonAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Getter;
import lombok.NonNull;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;

import static com.basejava.webapp.util.DateUtil.of;

@Getter
@XmlAccessorType(XmlAccessType.FIELD)
public final class Period implements Serializable{
    public static final Period EMPTY_PERIOD = new Period();
    @Serial
    private static final long serialVersionUID = 1L;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @JsonAdapter(JsonFieldAdapter.class)
    private LocalDate dateOfStart;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @JsonAdapter(JsonFieldAdapter.class)
    private LocalDate dateOfEnd;
    private String title;
    private String description;


    public Period() {}

    public Period(int yearOfStart, Month monthOfStart, String title, String description) {
        this(of(yearOfStart, monthOfStart), DateUtil.NOW, title, description);
    }

    public Period(int startYear, Month startMonth, int endYear, Month endMonth, String title, String description) {
        this(of(startYear, startMonth), of(endYear, endMonth), title, description);
    }

    public Period(@NonNull LocalDate dateOfStart, @NonNull LocalDate dateOfEnd, String title, String description) {
        this.dateOfStart = dateOfStart;
        this.dateOfEnd = dateOfEnd;
        this.title = title;
        this.description =  description == null ? "" : description;
    }
}
