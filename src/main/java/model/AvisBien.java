package model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class AvisBien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int note; // 1 à 5

    private String commentaire;

    private LocalDateTime dateCreation;

    @ManyToOne
    @JoinColumn(name = "id_bien")
    private Bien bien;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Utilisateur client;
    
    public AvisBien() {
        this.setDateCreation(LocalDateTime.now());
    }

	public int getNote() {
		return note;
	}

	public void setNote(int note) {
		this.note = note;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public LocalDateTime getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(LocalDateTime dateCreation) {
		this.dateCreation = dateCreation;
	}

    
}