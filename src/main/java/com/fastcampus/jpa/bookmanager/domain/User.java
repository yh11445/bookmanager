package com.fastcampus.jpa.bookmanager.domain;

import com.fastcampus.jpa.bookmanager.domain.listener.Auditable;
import com.fastcampus.jpa.bookmanager.domain.listener.UserEntityListener;
import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Builder
@EntityListeners(UserEntityListener.class)
public class User extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NonNull
	private String name;

	@Enumerated
	private Gender gender;

	@NonNull
	private String email;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	@ToString.Exclude
	private List<UserHistory> userHistories = new ArrayList<>(); // NullPointException 방지
//	private List<UserHistory> userHistories;

	@OneToMany
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	@ToString.Exclude
	private List<Review> reviews = new ArrayList<>();
//	private List<Review> reviews;

}
