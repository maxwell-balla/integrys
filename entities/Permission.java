package com.integrys.backend.entities;

import java.io.Serial;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;


/**
 * The persistent class for the permission database table.
 *
 */
@Data
@JsonInclude(value = Include.NON_NULL)
@Entity
public class Permission extends BaseEntity {
  @Serial
  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "perm_id", updatable = false, nullable = false)
  private String permId;

  @Column(name = "perm_description")
  private String permDescription;



}
