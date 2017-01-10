package todo.example.app.persistence.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "todos")
public class TodoEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "title") @Length(max=200)
	private String title;
	@Column(name = "details") @Length(max=200)
	private String details;
	@Column(name = "dueDate")
	private Date dueDate;

	public TodoEntity() {
	}

	public TodoEntity(String title, String details, Date dueDate) {
		this.title = title;
		this.details = details;
		this.dueDate = dueDate;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
	@Override
	public String toString(){
		
		return "TODO: "+this.id+", "+this.title +", "+this.details+", "+this.dueDate;
	}

}
