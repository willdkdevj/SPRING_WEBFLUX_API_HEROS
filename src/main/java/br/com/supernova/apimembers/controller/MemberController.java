package br.com.supernova.apimembers.controller;
import br.com.supernova.apimembers.exception.MemberAlreadyRegisteredException;
import br.com.supernova.apimembers.exception.MemberNotFoundException;
import br.com.supernova.apimembers.model.Member;
import br.com.supernova.apimembers.repository.MemberRepository;
import br.com.supernova.apimembers.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/api/v1/members")
public class MemberController implements MemberControllerInterface{

    MemberService memberService;
    MemberRepository memberRepository;

    public MemberController(MemberService memberService, MemberRepository memberRepository) {
        this.memberService = memberService;
        this.memberRepository = memberRepository;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<Member> getAllItems() {
        return memberService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Member> findByIdMember(@PathVariable Long id) throws MemberNotFoundException {
        return memberService.findByIdMember(id);
    }

    @GetMapping("/{name]")
    public  Mono<Member> findByNameMember(@PathVariable String name) throws MemberNotFoundException {
        return  memberService.findByNameMember(name);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Member> createMember(@RequestBody Member member) throws MemberAlreadyRegisteredException {
        return memberService.save(member);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Mono<HttpStatus> deleteByIDMember(@PathVariable Long id) throws MemberNotFoundException {
        memberService.deleteByIDMember(id);
        return Mono.just(HttpStatus.NO_CONTENT);
    }
}
