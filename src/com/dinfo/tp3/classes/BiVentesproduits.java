package com.dinfo.tp3.classes;

// Generated Dec 6, 2014 1:39:51 PM by Hibernate Tools 4.3.1

import java.math.BigDecimal;

/**
 * BiVentesproduits generated by hbm2java
 */
public class BiVentesproduits implements java.io.Serializable {

	private BiVentesproduitsId id;
	private BiProduits biProduits;
	private BiVentes biVentes;
	private char indicateurTaxable;
	private int qteAchetee;
	private BigDecimal prixUnitaire;
	private BigDecimal totalAchatProduit;

	public BiVentesproduits() {
	}

	public BiVentesproduits(BiVentesproduitsId id, BiProduits biProduits,
			BiVentes biVentes, char indicateurTaxable, int qteAchetee,
			BigDecimal prixUnitaire, BigDecimal totalAchatProduit) {
		this.id = id;
		this.biProduits = biProduits;
		this.biVentes = biVentes;
		this.indicateurTaxable = indicateurTaxable;
		this.qteAchetee = qteAchetee;
		this.prixUnitaire = prixUnitaire;
		this.totalAchatProduit = totalAchatProduit;
	}

	public BiVentesproduitsId getId() {
		return this.id;
	}

	public void setId(BiVentesproduitsId id) {
		this.id = id;
	}

	public BiProduits getBiProduits() {
		return this.biProduits;
	}

	public void setBiProduits(BiProduits biProduits) {
		this.biProduits = biProduits;
	}

	public BiVentes getBiVentes() {
		return this.biVentes;
	}

	public void setBiVentes(BiVentes biVentes) {
		this.biVentes = biVentes;
	}

	public char getIndicateurTaxable() {
		return this.indicateurTaxable;
	}

	public void setIndicateurTaxable(char indicateurTaxable) {
		this.indicateurTaxable = indicateurTaxable;
	}

	public int getQteAchetee() {
		return this.qteAchetee;
	}

	public void setQteAchetee(int qteAchetee) {
		this.qteAchetee = qteAchetee;
	}

	public BigDecimal getPrixUnitaire() {
		return this.prixUnitaire;
	}

	public void setPrixUnitaire(BigDecimal prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	public BigDecimal getTotalAchatProduit() {
		return this.totalAchatProduit;
	}

	public void setTotalAchatProduit(BigDecimal totalAchatProduit) {
		this.totalAchatProduit = totalAchatProduit;
	}

}