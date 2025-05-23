package com.springdatajpa.springdatajpademo.domain;




import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users", schema="")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 255)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 255)
    private String lastName;

    @Column(name = "phone", length = 255)
    private String phone;

    @Column(name = "organization", length = 255)
    private String organization;

    @Column(name = "email", nullable = false, length = 255, unique = true)
    private String email;

    @Column(name = "email_verified_at")
    private LocalDateTime emailVerifiedAt;

    @Column(name = "is_admin", nullable = false)
    private boolean isAdmin;

    @Column(name = "is_super", nullable = false)
    private boolean isSuper;

    @Column(name = "tops_enabled", nullable = false)
    private boolean topsEnabled;

    @Column(name = "status", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.PENDING;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "remember_token", length = 100)
    private String rememberToken;

    @Column(name = "google_id", length = 255)
    private String googleId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "two_factor_required", nullable = false)
    private boolean twoFactorRequired;

    @Column(name = "last_authenticated")
    private LocalDateTime lastAuthenticated;

    @Column(name = "two_factor_expiration_period")
    private Long twoFactorExpirationPeriod;

    @Column(name = "statistics_reports_enabled", nullable = false)
    private boolean statisticsReportsEnabled;

    @Column(name = "parent_system_id", length = 255)
    private String parentSystemId;

    @Column(name = "parent_system", length = 255)
    private String parentSystem;

    @Column(name = "user_type", length = 255)
    private String userType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mls_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "users_mls_id_foreign"))
    private Mls mls;

    @ManyToMany
    @JoinTable(
            name = "user_statistics_reports",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "statistics_report_id")
    )
    private Set<StatisticsReport> reports = new HashSet<>();

    public Set<StatisticsReport> getReports() {
        return reports;
    }

    public void setReports(Set<StatisticsReport> reports) {
        this.reports = reports;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getEmailVerifiedAt() {
        return emailVerifiedAt;
    }

    public void setEmailVerifiedAt(LocalDateTime emailVerifiedAt) {
        this.emailVerifiedAt = emailVerifiedAt;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public boolean isSuper() {
        return isSuper;
    }

    public void setSuper(boolean isSuper) {
        this.isSuper = isSuper;
    }

    public boolean isTopsEnabled() {
        return topsEnabled;
    }

    public void setTopsEnabled(boolean topsEnabled) {
        this.topsEnabled = topsEnabled;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRememberToken() {
        return rememberToken;
    }

    public void setRememberToken(String rememberToken) {
        this.rememberToken = rememberToken;
    }

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
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

    public boolean isTwoFactorRequired() {
        return twoFactorRequired;
    }

    public void setTwoFactorRequired(boolean twoFactorRequired) {
        this.twoFactorRequired = twoFactorRequired;
    }

    public LocalDateTime getLastAuthenticated() {
        return lastAuthenticated;
    }

    public void setLastAuthenticated(LocalDateTime lastAuthenticated) {
        this.lastAuthenticated = lastAuthenticated;
    }

    public Long getTwoFactorExpirationPeriod() {
        return twoFactorExpirationPeriod;
    }

    public void setTwoFactorExpirationPeriod(Long twoFactorExpirationPeriod) {
        this.twoFactorExpirationPeriod = twoFactorExpirationPeriod;
    }

    public boolean isStatisticsReportsEnabled() {
        return statisticsReportsEnabled;
    }

    public void setStatisticsReportsEnabled(boolean statisticsReportsEnabled) {
        this.statisticsReportsEnabled = statisticsReportsEnabled;
    }

    public String getParentSystemId() {
        return parentSystemId;
    }

    public void setParentSystemId(String parentSystemId) {
        this.parentSystemId = parentSystemId;
    }

    public String getParentSystem() {
        return parentSystem;
    }

    public void setParentSystem(String parentSystem) {
        this.parentSystem = parentSystem;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Mls getMls() {
        return mls;
    }

    public void setMls(Mls mls) {
        this.mls = mls;
    }

    // Enum for status
    public enum UserStatus {
        ACTIVE,
        PENDING,
        DEACTIVATED,
        PENDING_EULA
    }
}
