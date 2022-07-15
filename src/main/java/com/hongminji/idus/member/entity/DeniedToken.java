package com.hongminji.idus.member.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity  // Redis로 바꾸기 가능
public class DeniedToken implements Serializable {

    @Id
    private String accessToken;

    private String email;
}
