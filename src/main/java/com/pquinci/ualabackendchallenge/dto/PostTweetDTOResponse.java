package com.pquinci.ualabackendchallenge.dto;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Builder
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostTweetDTOResponse implements Serializable {

    private Long id;
    private String username;
    private String text;
    //@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)

    private Date fecha;
}
