package com.springdatajpa.springdatajpademo.dto;

import java.time.LocalDateTime;

public class StatisticsReportDTO {

    private Long id;
    private String title;
    private String description;
    private String image;
    private String slug;
    private String type;
    private String reportDetailsDescription;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Optionally include only relevant fields from MLS if needed
    // private Long mlsId;

    // Constructors
    public StatisticsReportDTO() {}

    public StatisticsReportDTO(Long id, String title, String description, String image, String slug,
                               String type, String reportDetailsDescription, LocalDateTime createdAt,
                               LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.slug = slug;
        this.type = type;
        this.reportDetailsDescription = reportDetailsDescription;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReportDetailsDescription() {
        return reportDetailsDescription;
    }

    public void setReportDetailsDescription(String reportDetailsDescription) {
        this.reportDetailsDescription = reportDetailsDescription;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
