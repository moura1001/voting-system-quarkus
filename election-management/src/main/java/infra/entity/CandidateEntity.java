package infra.entity;

import domain.Candidate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Optional;

@Entity(name = "candidate")
public class CandidateEntity {
    @Id private String id;
    private String photo;
    @Column(name = "given_name") private String givenName;
    @Column(name = "family_name") private String familyName;
    private String email;
    private String phone;
    @Column(name = "job_title") private String jobTitle;

    public CandidateEntity() {}

    public CandidateEntity(String photo, String givenName, String familyName, String email, String phone, String jobTitle) {
        this.photo = photo;
        this.givenName = givenName;
        this.familyName = familyName;
        this.email = email;
        this.phone = phone;
        this.jobTitle = jobTitle;
    }

    public CandidateEntity(String id, String photo, String givenName, String familyName, String email, String phone, String jobTitle) {
        this.id = id;
        this.photo = photo;
        this.givenName = givenName;
        this.familyName = familyName;
        this.email = email;
        this.phone = phone;
        this.jobTitle = jobTitle;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public static CandidateEntity toEntity(Candidate candidate) {
        CandidateEntity candidateEntity = new CandidateEntity(
                candidate.photo().isPresent() ? candidate.photo().get() : null,
                candidate.givenName(),
                candidate.familyName(),
                candidate.email(),
                candidate.phone().isPresent() ? candidate.phone().get() : null,
                candidate.jobTitle().isPresent() ? candidate.jobTitle().get() : null
        );
        if (candidate.id() != null)
            candidateEntity.setId(candidate.id());

        return candidateEntity;
    }

    public Candidate toDomain() {
        Candidate candidate = new Candidate(
                this.getId(),
                this.getPhoto() != null ? Optional.of(this.getPhoto()) : Optional.empty(),
                this.getGivenName(),
                this.getFamilyName(),
                this.getEmail(),
                this.getPhone() != null ? Optional.of(this.getPhone()) : Optional.empty(),
                this.getJobTitle() != null ? Optional.of(this.getJobTitle()) : Optional.empty()
        );

        return candidate;
    }
}
