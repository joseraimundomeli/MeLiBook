package br.com.socialmeli.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Client")
public class Client extends User{
}
