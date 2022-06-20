package ru.weber.auth.service.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

import static javax.persistence.TemporalType.TIMESTAMP;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "session")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Temporal(TIMESTAMP)
    @Column(name = "created_date", updatable = false)
    private Date createdDate;

    @UpdateTimestamp
    @Temporal(TIMESTAMP)
    @Column(name = "modified_date")
    private Date modifiedDate;

    @Column(name = "session_id")
    private String sessionId;

    @Column(name = "expire")
    private Instant expire;

    @Column(name = "is_invalidated")
    private boolean isInvalidated;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "meta_info",
            joinColumns = {@JoinColumn(name = "session_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "key")
    @Column(name = "value")
    private Map<String, String> metaInfo;
}

