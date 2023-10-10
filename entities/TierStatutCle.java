package com.integrys.backend.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class TierStatutCle implements Serializable{
public Long statutId;
public Long tierPayeurId;
}
