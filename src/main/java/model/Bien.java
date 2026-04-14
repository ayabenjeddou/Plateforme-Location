package model;

import java.util.List;
import javax.persistence.*;

@Entity
public class Bien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String description;
    private double prixParJour;
    private boolean disponible;

    @ManyToOne
    @JoinColumn(name = "id_categorie")
    private CategorieBien categorie;

    @ManyToOne
    @JoinColumn(name = "id_agent")
    private Utilisateur agent; // لازم يكون role = AGENT

    @OneToMany(mappedBy = "bien", cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    public Bien() {}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrixParJour() {
		return prixParJour;
	}

	public void setPrixParJour(double prixParJour) {
		this.prixParJour = prixParJour;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

}