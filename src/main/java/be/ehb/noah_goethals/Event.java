package be.ehb.noah_goethals;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Titel is verplicht.")
    private String title;

    @NotBlank(message = "Beschrijving is verplicht.")
    private String description;

    @NotNull(message = "Datum en tijd zijn verplicht.")
    private LocalDateTime datetime;

    @NotBlank(message = "Organisatie is verplicht.")
    private String organization;

    @NotBlank(message = "E-mailadres is verplicht.")
    @Email(message = "Voer een geldig e-mailadres in.")
    private String contactEmail;

    @NotNull(message = "Locatie is verplicht.")
    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    // Constructors
    public Event() {
    }

    public Event(String title, String description, LocalDateTime datetime, String organization, String contactEmail, Location location) {
        this.title = title;
        this.description = description;
        this.datetime = datetime;
        this.organization = organization;
        this.contactEmail = contactEmail;
        this.location = location;
    }

    // Getters & setters
    public Long getId() {
        return id;
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

    public LocalDateTime getDatetime() {
        return datetime;
    }
    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public String getOrganization() {
        return organization;
    }
    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getContactEmail() {
        return contactEmail;
    }
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }
}
