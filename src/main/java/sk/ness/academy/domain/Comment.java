package sk.ness.academy.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comments")
@SequenceGenerator(name = "comments_seq_store", sequenceName = "comments_seq", allocationSize = 1)
public class Comment {

  public Comment() {
    this.createDate = new Date();
  }

  @Id
  @Column(name = "id", unique = true, nullable = false, precision = 10, scale = 0)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comments_seq_store")
  private Integer id;

  @Column(name = "article_id", nullable = false)
  private Integer article_id;

  @Column(name = "author", length = 250)
  private String author;

  @Column(name = "text", length = 2000)
  private String text;


  @Column(name = "create_date")
  @Temporal(TemporalType.TIMESTAMP)
  private Date createDate;

  public Integer getId() {
    return this.id;
  }

  public void setId(final Integer id) {
    this.id = id;
  }

  public Integer getArticle_id() {
    return article_id;
  }

  public void setArticle_id(Integer article_id) {
    this.article_id = article_id;
  }

  public String getText() {
    return this.text;
  }

  public void setText(final String text) {
    this.text = text;
  }

  public String getAuthor() {
    return this.author;
  }

  public void setAuthor(final String author) {
    this.author = author;
  }

  public Date getCreateDate() {
    return this.createDate;
  }

  public void setCreateDate(final Date createTimestamp) {
    this.createDate = createTimestamp;
  }


}
