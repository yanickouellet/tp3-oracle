package com.dinfo.tp3.classes;

// Generated Dec 6, 2014 1:39:51 PM by Hibernate Tools 4.3.1

/**
 * BiCopiesarticlesId generated by hbm2java
 */
public class BiCopiesarticlesId implements java.io.Serializable {

	private int noArticle;
	private String isbn;

	public BiCopiesarticlesId() {
	}

	public BiCopiesarticlesId(int noArticle, String isbn) {
		this.noArticle = noArticle;
		this.isbn = isbn;
	}

	public int getNoArticle() {
		return this.noArticle;
	}

	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}

	public String getIsbn() {
		return this.isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof BiCopiesarticlesId))
			return false;
		BiCopiesarticlesId castOther = (BiCopiesarticlesId) other;

		return (this.getNoArticle() == castOther.getNoArticle())
				&& ((this.getIsbn() == castOther.getIsbn()) || (this.getIsbn() != null
						&& castOther.getIsbn() != null && this.getIsbn()
						.equals(castOther.getIsbn())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getNoArticle();
		result = 37 * result
				+ (getIsbn() == null ? 0 : this.getIsbn().hashCode());
		return result;
	}

}