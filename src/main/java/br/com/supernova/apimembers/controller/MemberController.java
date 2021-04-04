package br.com.supernova.apimembers.controller;
import br.com.supernova.apimembers.model.Member;
import br.com.supernova.apimembers.repository.MemberRepository;
import br.com.supernova.apimembers.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController("/api/v1/members")
public class MemberController {

    private static final Logger log = LoggerFactory.getLogger(MemberController.class);
    MemberService memberService;
    MemberRepository memberRepository;

    public MemberController(MemberService memberService, MemberRepository memberRepository) {
        this.memberService = memberService;
        this.memberRepository = memberRepository;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<Member> getAllItems() {
        log.info("requesting the list off all heroes");
        return memberService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Member>> findByIdMember(@PathVariable Long id) {
            log.info("Requesting the hero with id {}", id);
            return memberService.findByIdMember(id)
                    .map((item) -> new ResponseEntity<>(item, HttpStatus.OK))
                    .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }

        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        public Mono<Member> createHero(@RequestBody Member heroes) {
            log.info("A new Hero was Created");
            return memberService.save(heroes);

        }

        @DeleteMapping("/{id}")
        @ResponseStatus(code = HttpStatus.NOT_FOUND)
        public Mono<HttpStatus> deletebyIDMember(@PathVariable Long id) {
            memberService.deleteByIDMember(id);
            log.info("Deleting the hero with id {}", id);
            return Mono.just(HttpStatus.NOT_FOUND);
        }
}
