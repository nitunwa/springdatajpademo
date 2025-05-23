package com.springdatajpa.springdatajpademo.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "statistics_reports")
public class StatisticsReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "image", nullable = false, length = 255)
    private String image;

    @Column(name = "slug", nullable = false, length = 255, unique = true)
    private String slug;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mls_id", foreignKey = @ForeignKey(name = "statistics_reports_mls_id_foreign"))
    // @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Mls mls;

    @Column(name = "type", nullable = false, length = 255)
    private String type = "market_stats";  // Default value 'market_stats'

    @Column(name = "report_details_description", columnDefinition = "LONGTEXT")
    private String reportDetailsDescription;

    @ManyToMany(mappedBy = "reports")
    private Set<User> users;

    // Getters and Setters

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

    public Mls getMls() {
        return mls;
    }

    public void setMls(Mls mls) {
        this.mls = mls;
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

    // Constructor, toString, equals, hashCode can be added as needed
}