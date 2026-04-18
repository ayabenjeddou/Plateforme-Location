package model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class AvisBien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int note;
 // 1 à 5

    private String commentaire;

    private LocalDateTime dateCreation;

    @ManyToOne
    @JoinColumn(name = "bien_id")
    private Bien bien;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;
    
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

	public void setIdBien(Bien bien_id) {
		this.bien=bien_id;
		
	}

	public void setIdUser(Long id2) {
		// TODO Auto-generated method stub
		
	}

    
}