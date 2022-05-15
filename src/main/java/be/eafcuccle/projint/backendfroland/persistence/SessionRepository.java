package be.eafcuccle.projint.backendfroland.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {

}
