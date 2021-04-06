package br.com.supernova.apimembers.service;

import br.com.supernova.apimembers.exception.MemberAlreadyRegisteredException;
import br.com.supernova.apimembers.exception.MemberNotFoundException;
import br.com.supernova.apimembers.model.Member;
import br.com.supernova.apimembers.repository.MemberRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Flux<Member> findAll(){
        return Flux.fromIterable(this.memberRepository.findAll());
    }

    public  Mono<Member> findByIdMember(Long id) throws MemberNotFoundException {
        checkedIfExistsMember(id);
        return  Mono.justOrEmpty(this.memberRepository.findById(id));
    }

    public  Mono<Member> findByNameMember(String name) throws MemberNotFoundException {
        checkedIfExistsMember(name);
        return  Mono.justOrEmpty(this.memberRepository.findByName(name));
    }

    /*
    public  Mono<Member> updateMember(Member member) throws MemberNotFoundException {
        checkedIfExistsMember(member.getName());
        return  Mono.justOrEmpty(this.memberRepository.saveAndFlush(member));
    }

     */

    public Mono<Member> save(Member member) throws MemberAlreadyRegisteredException {
        checkIfThereIsARecord(member.getName());
        return  Mono.justOrEmpty(this.memberRepository.save(member));
    }


    public Mono<Boolean> deleteByIDMember(Long id) throws MemberNotFoundException {
        checkedIfExistsMember(id);
        memberRepository.deleteById(id);
        return Mono.just(true);

    }

    private Member checkedIfExistsMember(Long id) throws MemberNotFoundException {
        return memberRepository.findById(id).orElseThrow(
                () -> new MemberNotFoundException(id));
    }

    private Member checkedIfExistsMember(String name) throws MemberNotFoundException {
        return memberRepository.findByName(name).orElseThrow(
                () -> new MemberNotFoundException(name));
    }

    private void checkIfThereIsARecord(String name) throws MemberAlreadyRegisteredException {
        Optional<Member> optionalMember = memberRepository.findByName(name);
        if(optionalMember.isPresent()){
            throw new MemberAlreadyRegisteredException(name);
        }
    }
}
