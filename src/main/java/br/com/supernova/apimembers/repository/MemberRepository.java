package br.com.supernova.apimembers.repository;

import br.com.supernova.apimembers.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Mono<Member> findByName(String name);
}
