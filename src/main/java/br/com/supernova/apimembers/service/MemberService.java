package br.com.supernova.apimembers.service;

import br.com.supernova.apimembers.model.Member;
import br.com.supernova.apimembers.repository.MemberRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Flux<Member> findAll(){

        return Flux.fromIterable(this.memberRepository.findAll());
    }

    public  Mono<Member> findByIdMember(Long id){

        return  Mono.justOrEmpty(this.memberRepository.findById(id));
    }


    public Mono<Member> save(Member heroes){
        return  Mono.justOrEmpty(this.memberRepository.save(heroes));
    }


    public Mono<Boolean> deleteByIDMember(Long id) {
        memberRepository.deleteById(id);
        return Mono.just(true);

    }
}
