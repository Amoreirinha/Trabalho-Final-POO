package org.example.academic.system.model;

import java.util.ArrayList;
import java.util.List;

public class Turma {
    private String code;
    private String title;
    private List<Assessment> assessments;

    public Turma() {
        this.assessments = new ArrayList<>();
    }

    public Turma(String code, String title) {
        this.code = code;
        this.title = title;
        this.assessments = new ArrayList<>();
    }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public List<Assessment> getAssessments() { return assessments; }
    public void setAssessments(List<Assessment> assessments) { this.assessments = assessments; }

    public void addAssessment(Assessment assessment) {
        if (assessment != null) this.assessments.add(assessment);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Turma turma = (Turma) o;
        return code != null && code.equals(turma.code);
    }

    @Override
    public int hashCode() {
        return code != null ? code.hashCode() : 0;
    }
}
