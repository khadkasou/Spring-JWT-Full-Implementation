package com.souraj.demo3.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "role")
public class Role {

    @Id
    private String id;

    @JsonProperty("name")
    private String name;

    public Role(String name) {

        this.id= new ObjectId().toString();
        this.name = name;
    }

    public Role(){
        this.id=new ObjectId().toString();
    }

}
