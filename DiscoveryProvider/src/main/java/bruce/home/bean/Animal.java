package bruce.home.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class Animal implements Serializable {
    private int id;
    private String name;
}
